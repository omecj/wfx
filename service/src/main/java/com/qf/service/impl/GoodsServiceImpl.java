package com.qf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.common.http.GoodsState;
import com.qf.entity.dto.Goods;
import com.qf.entity.dto.GoodsSku;
import com.qf.entity.po.LoginMerchant;
import com.qf.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author-izumi
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> {

    @Autowired
    private GoodsSkuServiceImpl goodsSkuService;

    @Transactional
    public void up(Long id) {
        UpdateWrapper<Goods> wrapper = new UpdateWrapper<>();
        wrapper.lambda().eq(Goods::getId,id).set(Goods::getState, GoodsState.UP.getCode());
        this.update(wrapper);
    }

    @Transactional
    public void down(Long id) {
        UpdateWrapper<Goods> wrapper = new UpdateWrapper<>();
        wrapper.lambda().eq(Goods::getId, id).set(Goods::getState, GoodsState.DOWN.getCode());
        this.update(wrapper);
    }

    @Transactional
    public void del(Long id) {
        this.removeById(id);
        goodsSkuService.removeByGoodsId(id);
    }

    @Transactional
    public List<Goods> getByMerchantId(Long id) {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Goods::getMerchantUserId, id);
        return this.list(wrapper);
    }

    @Transactional
    public void saveGoods(Goods goods, LoginMerchant loginMerchant) {
        //1.删除goods_sku
        if (goods.getId() != null){
            goodsSkuService.removeByGoodsId(goods.getId());
        }

        //2.保存goods
        Long orderNum = 1L;
        List<Goods> byMerchantId = this.getByMerchantId(loginMerchant.getId());
        if (byMerchantId.size()>0){
            orderNum = byMerchantId.get(byMerchantId.size()-1).getOrderNum()+1;
        }
        goods.setOrderNum(orderNum);
        goods.setMerchantUserId(loginMerchant.getId());
        this.saveOrUpdate(goods);   //保存goods对象

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        List<Object> titles = jsonParser.parseList(goods.getSkuTitle());
        List<Object> costs = jsonParser.parseList(goods.getSkuCost());
        List<Object> prices = jsonParser.parseList(goods.getSkuPrice());
        List<Object> pmoney = jsonParser.parseList(goods.getSkuPmoney());

        //String[] titles = goods.getSkuTitle().split("\\|");
        //String[] costs = goods.getSkuCost().split("\\|");
        //String[] prices = goods.getSkuPrice().split("\\|");
        //String[] pmoney = goods.getSkuPmoney().split("\\|");


        //3.插入goods_sku
        //String[] titles = goods.getSkuTitle().split("\\|");
        //String[] costs = goods.getSkuCost().split("\\|");
        //String[] prices = goods.getSkuPrice().split("\\|");
        //String[] pmoney = goods.getSkuPmoney().split("\\|");
        //int len = titles.length;
        int len = titles.size();
        int k = 1;
        List<GoodsSku> list = new ArrayList<>(len);
        for (int i = 0; i < len; i++) {
            if (titles.get(i).equals("")&& costs.get(i).equals("")&& prices.get(i).equals("")&& pmoney.get(i).equals("")){
                continue;
            }
            if (!titles.get(i).equals("|")){
                GoodsSku goodsSku = new GoodsSku();
                goodsSku.setGoodId(goods.getId());
                goodsSku.setTitle((String) titles.get(i));
                goodsSku.setCost((String) costs.get(i));
                goodsSku.setPrice((String) prices.get(i));
                goodsSku.setPmoney((String) pmoney.get(i));
                goodsSku.setOrderNo((long) k++);
                list.add(goodsSku);
            }
        }
        goodsSkuService.saveBatch(list);
    }

    public List<Goods> getByMerchantIdAndStatus(Long id) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Goods::getMerchantUserId, id).eq(Goods::getState, 1);
        return this.list(queryWrapper);
    }
}
