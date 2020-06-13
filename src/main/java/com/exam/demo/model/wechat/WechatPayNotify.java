package com.exam.demo.model.wechat;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * 微信支付回调通知
 */
@Getter
@Setter
@XmlRootElement(name = "xml")
public class WechatPayNotify implements Serializable {
    private String appid;
    private String mch_id;
    private String device_info;
    private String nonce_str;
    private String sign;// 签名 sign 是
    private String result_code;// 业务结果 result_code 是 SUCCESS/FAIL
    private String return_code;//返回结果
    private String return_msg;//
    private String err_code;// 错误代码 err_code 否
    private String err_code_des;// 错误代码描述 err_code_des 否
    private String openid;// 用户标识 openid 是
    private String is_subscribe;// 是否关注公众账号 is_subscribe 否
    private String trade_type; // 交易类型 trade_type
    private String bank_type;// 付款银行
    private Integer total_fee;// 总金额 total_fee
    private String fee_type;// 货币种类 fee_type
    private Integer cash_fee;// 现金支付金额 cash_fee
    private String cash_fee_type;// 现金支付货币类型 cash_fee_type
    private String coupon_fee;// 代金券或立减优惠金额 coupon_fee
    private String coupon_count;// 代金券或立减优惠使用数量 coupon_count
    private String coupon_id_0;// 代金券或立减优惠ID coupon_id_$n
    private String coupon_fee_0;// 单个代金券或立减优惠支付金额 coupon_fee_$n
    private String coupon_id_1;// 代金券或立减优惠ID coupon_id_$n
    private String coupon_fee_1;// 单个代金券或立减优惠支付金额 coupon_fee_$n
    private String coupon_id_2;// 代金券或立减优惠ID coupon_id_$n
    private String coupon_fee_2;// 单个代金券或立减优惠支付金额 coupon_fee_$n
    private String transaction_id;// 微信支付订单号 transaction_id
    private String out_trade_no;// 商户订单号 out_trade_no
    private String attach;// 商家数据包 attach
    private String time_end;// 支付完成时间 time_end
}
