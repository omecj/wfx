package com.qf.zmt.controller;

import com.qf.common.WxPay.MyWXConfig;
import com.qf.common.WxPay.WXPay;
import com.qf.common.http.OrderStatus;
import com.qf.common.http.Result;
import com.qf.entity.dto.Order;
import com.qf.entity.po.LoginMerchant;
import com.qf.service.impl.GoodsServiceImpl;
import com.qf.service.impl.GoodsSkuServiceImpl;
import com.qf.service.impl.OrderServiceImpl;
import com.qf.service.impl.OrderVoServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author-izumi
 */
@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    private GoodsServiceImpl goodsService;
    @Autowired
    private GoodsSkuServiceImpl goodsSkuService;
    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private OrderVoServiceImpl orderVoService;

    @RequestMapping("list")
    public String toList(Model model, LoginMerchant loginMerchant) {
        model.addAttribute("orderVoList", orderVoService.getOrderByMerchantUserId(loginMerchant.getId()));
        return "order/list";
    }


    @RequestMapping("new")
    public String newList(Model model, @RequestParam(value = "id", required = true) Long id) {
        model.addAttribute("goods", goodsService.getById(id));
        model.addAttribute("skuList", goodsSkuService.getByGoodsId(id));
        model.addAttribute("totalSale", 1020);
        return "order/new";
    }

    @RequestMapping("createOrder")
    @ResponseBody
    public Result<?> createOrder(Order order, LoginMerchant loginMerchant) {
        orderService.createOrder(order, loginMerchant);
        return Result.success();
    }

    @RequestMapping("wxpay")
    public String toWxPay(Model model, @RequestParam(value = "id", required = true) Long id) {
        model.addAttribute("orderDetail", orderVoService.getByOrderId(id));
        return "order/pay";
    }

    @RequestMapping("getQR")
    @ResponseBody
    public Result<?> getQRCode(LoginMerchant loginMerchant, @RequestParam(value = "orderId", required = true) Long orderId) {
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Order order = orderService.getById(orderId);
        try {
            WXPay wxPay = new WXPay(new MyWXConfig());
            Long goodsId = order.getGoodsId();
            Map<String, String> data = new HashMap<String, String>();
            data.put("body", order.getName()); //商品描述
            data.put("out_trade_no", dataFormat.format(new Date())
                    .concat(loginMerchant.getId().toString())
                    .concat(String.valueOf(RandomUtils.nextInt(10000, 100000)))); //外部订单号
            data.put("fee_type", "CNY"); //币种
            data.put("total_fee", "1"); //金额，单位:分
            data.put("spbill_create_ip", "127.0.0.1"); //终端ip
            data.put("notify_url", "http://sr9i6m.natappfree.cc/callback/wxpay/notifyCall"); //回调地址
            data.put("trade_type", "NATIVE"); // 此处指定为扫码支付
            data.put("product_id", goodsId.toString()); //商品id
            data.put("attach", order.getOrderId()); //内部订单号
            try {
                Map<String, String> resp = wxPay.unifiedOrder(data);
                String resultCode = resp.get("result_code");
                if ("FAIL".equals(resultCode)) {
                    return Result.error(resp.get("err_code_desc"));
                } else {
                    return Result.success(resp.get("code_url"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success("支付失败");
    }

    @RequestMapping("getPayStatus")
    @ResponseBody
    public Result<?> getPayStatus(@RequestParam(value = "orderId", required = true) Long orderId) {
        Long payState = orderService.getById(orderId).getPayState();
        if (payState == OrderStatus.PAID.getCode()) {
            return Result.success("paid");
        }
        return Result.success();
    }
}
