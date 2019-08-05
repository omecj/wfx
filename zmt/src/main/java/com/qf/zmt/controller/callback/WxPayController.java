package com.qf.zmt.controller.callback;

import com.qf.common.WxPay.MyWXConfig;
import com.qf.common.WxPay.WXPayConstants;
import com.qf.common.WxPay.WXPayUtil;
import com.qf.common.http.OrderStatus;
import com.qf.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;

/**
 * @Author oumae
 * @date 2019/8/5
 */
@Controller
@RequestMapping("callback/wxpay")
public class WxPayController {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";


    @Autowired
    private OrderServiceImpl orderService;


    @RequestMapping("notifyCall")
    @ResponseBody
    public String notifyCall(HttpServletRequest request) throws Exception {
        if (request.getContentLength() < 0) {
            return SUCCESS;
        }
        DataInputStream in;
        String wxNotifyXml = "";
        try {
            in = new DataInputStream(request.getInputStream());
            byte[] bytes = new byte[request.getContentLength()];
            in.readFully(bytes);
            if (in != null) {
                in.close();
            }
            wxNotifyXml = new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, String> params = WXPayUtil.xmlToMap(wxNotifyXml);
        if (params == null || params.size() < 1) {
            return FAIL;
        }
        if (WXPayUtil.isSignatureValid(params, new MyWXConfig().getKey(), WXPayConstants.SignType.HMACSHA256)) {
            String resultCode = params.get("result_code");
            String orderId = params.get("attach");
            if ("SUCCESS".equals(resultCode) && !StringUtils.isEmpty(orderId)) {
                if (orderService.updatePayState(orderId, OrderStatus.PAID.getCode())) {
                    return SUCCESS;
                }
            }
        }
        return FAIL;
    }

}
