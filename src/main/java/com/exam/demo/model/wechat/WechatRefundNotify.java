package com.exam.demo.model.wechat;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * 微信退款回调报文信息
 **/
@Getter
@Setter
@XmlRootElement(name = "xml")
public class WechatRefundNotify implements Serializable {

    private String return_code;//SUCCESS/FAIL 此字段是通信标识，非结果标识，退款是否成功需要解密后查看refund_status 来判断
    private  String appid;
    private String mch_id;
    private String nonce_str;
    private  String req_info;//加密信息

    @Getter
    @Setter
    @XmlRootElement(name = "root")
    public static class ReqInfo implements Serializable {
        private String out_refund_no;//商户退款单号
        private String out_trade_no;//订单号
        private String refund_account;
        private String refund_fee;//申请退款金额
        private String refund_id;//微信退款单号
        private String refund_recv_accout;
        private String refund_request_source;
        private String refund_status;//退款状态 SUCCESS-退款成功 CHANGE-退款异常 REFUNDCLOSE—退款关闭
        private String settlement_refund_fee;//退款金额
        private String settlement_total_fee;//应结订单金额 当该订单有使用非充值券时，返回此字段。应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
        private String success_time;//退款成功时间
        private String total_fee;//订单金额
        private String transaction_id;//微信订单号
    }
}
