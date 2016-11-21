package com.sjdf.platform.dictionary.bean.wj;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("WJ_GLOBLE")
@BeanName(name = "网居公共配置")
public class WangJuGloble extends Dictionary {

    private static final long serialVersionUID = -7488185431836340624L;

    @BeanAttrInfo(value = "此支付宝帐号已经申请过退款出账，如果您又要用产品又不停的找我们退款，您的帐号就无法在我们店铺购买商品，请慎重选择退款出账！", cnName = "同一个账号多次退款提示", orderBy = 1)
    public static final long SAME_ACCOUNT_REFUND_TIPS = 1;

    @BeanAttrInfo(value = "20", cnName = "需调整的会员级别列表")
    public static final long MEMBER_LEVEL_AUTO_ADJUSTMENT = 2;

    @BeanAttrInfo(value = "ch2014062500001,wjch2015082100001", cnName = "基础性挂机宝列表")
    public static final long BASE_CHOST_PRODUCTID = 3;

    @BeanAttrInfo(value = "2016-09-29 11:11:11", cnName = "会员级别自动调整上线时间")
    public static final long MEMBER_LEVEL_AUTO_ADJUSTMENT_ONLINE_TIME = 4;

    @BeanAttrInfo(value = "1,7", cnName = "代理考核产品分类列表")
    public static final long MEMBER_LEVEL_AUTO_ADJUSTMENT_PRODUCT_CLASS = 5;

    @BeanAttrInfo(value = "1,2", cnName = "代理考核产品动作列表")
    public static final long MEMBER_LEVEL_AUTO_ADJUSTMENT_PRODUCT_ACTION = 6;
}
