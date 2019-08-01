package com.qf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.entity.dto.GoodsSku;
import com.qf.mapper.GoodsSkuMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author-izumi
 */
@Service
public class GoodsSkuServiceImpl extends ServiceImpl<GoodsSkuMapper, GoodsSku> {
    public List<GoodsSku> getByGoodsId(Long id) {
        QueryWrapper<GoodsSku> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(GoodsSku::getGoodId,id);
        return this.list(wrapper);
    }

    public void removeByGoodsId(Long id) {
        QueryWrapper<GoodsSku> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(GoodsSku::getGoodId,id);
        this.remove(queryWrapper);   //移除goods对应的sku记录
    }
}
