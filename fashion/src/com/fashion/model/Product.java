/**
 * 
 */
package com.fashion.model;

/**
 * @author xkz
 * 
 */
public class Product {

	public String open_iid;// 开放商品id（淘宝客ID）
	public String pic_url;// 图片url
	public String seller_id;// 卖家id
	public String nick;// 卖家昵称
	public String item_location; // 店铺地址
	public String title;// 商品title 宝贝名称
	public String price;// 商品价格
	public String seller_credit_score; // 卖家信用等级
	public String commission;
	public String commission_num;// 累计成交量.注：返回的数据是30天内累计推广量
	public String commission_rate;// 淘宝客佣金比率
	public String commission_volume;//
	public String shop_type;// 店铺类型:B(商城),C
	public String volume;// 30天内交易量
}