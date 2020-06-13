package com.exam.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单信息表
 */
@Getter
@Setter
public class OrderInfo implements Serializable {

	private static final long serialVersionUID = -1700549278683125820L;
	// 订单号
	private String orderNo;
	// 买家用户id
	private String buyerUserId;
	// 买家用户姓名
	private String buyerUserName;
	// 卖家用户id
	private String sellerUserId;
	// 卖家用户姓名
	private String sellerUserName;
	// 卖家用户等级id
	private Long sellerUserLevelId;
	// 卖家用户类型id
	private Long sellerUserTypeId;
	// 卖家与平台的分成比例
	private Integer sellerMoneyShare = 0;
	// 虚拟物品类型 1-购买资料 2-购买VIP 3-购买下载券
	private Integer goodsType;
	// 商品名称
	private String goodsName;
	// 商品id
	private String goodsId;
	//分类ID
	private String classId;
	// 下载券个数
	private Long voluNum;
	//下载券赠送个数
	private Long giveVoluNum;
	// 订单金额
	private Long payPrice = 0l;
	// 平台分成金额
	private Long platformPrice = 0l;
	// 卖家分成金额
	private Long sellerPrice;
	// 订单状态 0-待支付 1-支付进行中 2-支付成功 3-支付失败 4-订单取消 5-订单异常（回调金额与订单金额不一致）
	private Integer orderStatus = 0;
	// 下单时间
	private Date orderTime;
	// 支付时间
	private Date payTime;
	// 订单取消时间
	private Date cancelTime;
	// 订单删除时间
	private Date deleteTime;
	//用户操作状态  0-正常  9-删除
	private Integer userOpt=0;
	//退款状态  0-未申请退款  1-退款申请中  2-退款审核中  3-退款审核不通过 4-退款成功  5-退款失败 7-退款异常 申请退款金额不等于回调退款金额
	private Integer refundStatus=0;
	//退款金额
	private Long refundPrice;
	//退款申请时间
	private Date refundApplyTime;
	// 退款完成时间
	private Date refundFinishTime;
	// 提现申请时间
	private Date withdrawApplyTime;
	// 提现批次id
	private String withdrawId;
	// 提现状态 0-未提现 1-提现进行中 2-提现成功 3-提现失败
	private Integer withdrawStatus = 0;
	// 支付类型 wechat-微信支付 alipay-支付宝
	private String payType;
	private Integer sourceMode;//来源渠道 0-pc 1-m版
	private String clientIp;//客户端id
	private String referrer; //上级页面

	//对账状态   	   0-未对账    1-对账成功   2-对账异常（
	//a-业务系统订单状态【待支付或者已取消】-->第三方记录中状态为支付成功；
	//b-业务系统订单状态【已支付】-->第三方支付找不到订单或者支付失败或者已退款
	//c-业务系统的支付金额!=第三方支付订单的金额）
	private Integer billStatus;
	//对账备注信息     对账成功 ;支付异常具体信息
	private String billRemark;

}
