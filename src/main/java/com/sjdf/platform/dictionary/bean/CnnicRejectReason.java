package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013年11月4日 上午11:41:10
 *
 * @author KETQI
 * @category cnnic审核标准拒绝原因
 */
@Entity
@DiscriminatorValue("CNNIC_REJECT_REASON")
@BeanName(name = "cnnic审核标准拒绝原因")
public class CnnicRejectReason extends Dictionary {
    private static final long serialVersionUID = -4555269803902187501L;

    @BeanAttrInfo(cnName = "1.1 注册者证明资料上的名称与系统中的注册者名称不一致")
    public static final long C_317 = 317;

    @BeanAttrInfo(cnName = "1.3 系统中注册者名称不能为空")
    public static final long C_320 = 320;

    @BeanAttrInfo(cnName = "2.1.1注册者身份证明资料不清晰")
    public static final long C_323 = 323;

    @BeanAttrInfo(cnName = "2.1.2 注册者身份证明资料不完整")
    public static final long C_334 = 334;

    @BeanAttrInfo(cnName = "2.1.3 注册者身份证明资料无法打开")
    public static final long C_345 = 345;

    @BeanAttrInfo(cnName = "3.1 未提交注册者身份证明资料")
    public static final long C_366 = 366;

    @BeanAttrInfo(cnName = "3.5 全国组织机构代码管理中心的组织机构代码证查询结果的资料不能作为有效的注册者身份证明资料，请提供组织机构代码证或企业营业执照复印件、扫描件")
    public static final long C_370 = 370;

    @BeanAttrInfo(cnName = "5.1 提交的组织机构代码证与全国组织机构代码管理中心查询结果不一致")
    public static final long C_382 = 382;

    @BeanAttrInfo(cnName = "5.3 提交的企业营业执照验证失败")
    public static final long C_389 = 389;

    @BeanAttrInfo(cnName = "7.1 提交资料显示为海外用户。")
    public static final long C_395 = 395;

    @BeanAttrInfo(cnName = "8.1 未提交注册者出具的域名注册公函，请提交域名注册公函，且公函中应写明注册的域名及注册者联系人为该组织工作人员")
    public static final long C_396 = 396;

    @BeanAttrInfo(cnName = "8.2 提交的注册者公函中未写明申请的域名或未写明注册者联系人为该组织工作人员")
    public static final long C_397 = 397;

    @BeanAttrInfo(cnName = "9.13 申请用户电话号码不真实、不准确、不完整，此次注册申请审核拒绝")
    public static final long C_399 = 399;

    @BeanAttrInfo(cnName = "9.10 回访未联系到《域名注册申请表》上的联系人，此次注册申请审核拒绝")
    public static final long C_400 = 400;

    @BeanAttrInfo(cnName = "9.11 回访结果非用户意愿，此次注册申请审核拒绝")
    public static final long C_401 = 401;

    @BeanAttrInfo(cnName = "10.10 违反《中国互联网络域名管理办法》第二十七条第（六）款，即域名含有散布谣言，扰乱社会秩序，破坏社会稳定的内容，此次注册申请审核拒绝")
    public static final long C_404 = 404;

    @BeanAttrInfo(cnName = "10.2 违反《中国互联网络域名管理办法》第二十七条 第（七）款，即域名含有散布淫秽、色情、赌博、暴力、凶杀、恐怖或者教唆犯罪的内容，此次注册申请审核拒绝")
    public static final long C_405 = 405;

    @BeanAttrInfo(cnName = "10.3 违反国务院令第345号《奥林匹克标志保护条例》规定，未经奥林匹克标志权利人许可,任何人不得为商业目的(含潜在商业目的)使用奥林匹克标志，此次注册申请审核拒绝")
    public static final long C_406 = 406;

    @BeanAttrInfo(cnName = "10.4 违反国务院令第422号《世界博览会标志保护条例》规定，未经世界博览会标志权利人许可，任何人不得为商业目的（含潜在商业目的）使用世界博览会标志，此次注册申请审核拒绝")
    public static final long C_407 = 407;

    @BeanAttrInfo(cnName = "10.5 违反《中国互联网络域名管理办法》第二十七条第（九）款，即域名含有法律、行政法规禁止的其他内容，此次注册申请审核拒绝")
    public static final long C_408 = 408;

    @BeanAttrInfo(cnName = "10.6 违反《中国互联网络域名管理办法》第二十七条 第（二）款，即域名含有危害国家安全，泄露国家秘密，颠覆国家政权，破坏国家统一的内容，此次注册申请审核拒绝")
    public static final long C_409 = 409;

    @BeanAttrInfo(cnName = "10.7 违反《中国互联网络域名管理办法》第二十七条第（三）款，即域名含有损害国家荣誉和利益的内容，此次注册申请审核拒绝")
    public static final long C_410 = 410;

    @BeanAttrInfo(cnName = "10.8 违反《中国互联网络域名管理办法》第二十七条第（四）款，即域名含有煽动民族仇恨、民族歧视，破坏民族团结的内容，此次注册申请审核拒绝")
    public static final long C_411 = 411;

    @BeanAttrInfo(cnName = "10.9 违反《中国互联网络域名管理办法》第二十七条第（五）款，即域名含有破坏国家宗教政策，宣扬邪教和封建迷信的内容，此次注册申请审核拒绝")
    public static final long C_412 = 412;

    @BeanAttrInfo(cnName = "11.10 非“机关”类型的申请单位，不能申请注册gov.cn域名")
    public static final long C_413 = 413;

    @BeanAttrInfo(cnName = "请创建新注册者ID，并提交注册者联系人身份证及组织机构代码证进行注册")
    public static final long C_414 = 414;

    @BeanAttrInfo(cnName = "11.21《域名注册申请表》中注册者联系人姓名与系统中的注册者联系人姓名不一致")
    public static final long C_416 = 416;

    @BeanAttrInfo(cnName = "11.22 《域名注册申请表》中注册者名称与系统中的注册者名称不一致")
    public static final long C_417 = 417;

    @BeanAttrInfo(cnName = "11.23《域名注册申请表》中注册者公章与系统中的注册者名称不一致")
    public static final long C_418 = 418;

    @BeanAttrInfo(cnName = "11.30 《域名注册申请表》必填项目缺失或涂改，请提交填写清晰完整的《域名注册申请表》")
    public static final long C_419 = 419;

    @BeanAttrInfo(cnName = "11.31《域名注册申请表》未加盖注册者公章，此次注册申请审核拒绝")
    public static final long C_420 = 420;

    @BeanAttrInfo(cnName = "11.50 请提交最新版《域名注册申请表》进行申请注册")
    public static final long C_421 = 421;

    @BeanAttrInfo(cnName = "11.20 《域名注册申请表》中域名与系统中的域名不一致")
    public static final long C_422 = 422;

    @BeanAttrInfo(cnName = "11.40 提交的《域名注册申请表》不清晰或无法打开或不完整，请重新提交")
    public static final long C_423 = 423;

    @BeanAttrInfo(cnName = "20.11 未提交承诺书。")
    public static final long C_425 = 425;

    @BeanAttrInfo(cnName = "20.8 承诺书填写不清晰、不完整。")
    public static final long C_426 = 426;

    @BeanAttrInfo(cnName = "20.5 承诺书上公章不清晰、不完整。")
    public static final long C_427 = 427;

    @BeanAttrInfo(cnName = "20.2 承诺书上填写域名不一致，请重新填写后再次进行提交")
    public static final long C_428 = 428;

    @BeanAttrInfo(cnName = "20.3 承诺书上填写的域名不规范，请去掉www.后再次进行提交。")
    public static final long C_429 = 429;

    @BeanAttrInfo(cnName = "20.10 承诺书上未加盖注册者公章或未签署注册者姓名。")
    public static final long C_430 = 430;

    @BeanAttrInfo(cnName = "20.4 承诺书上公章与系统中注册者名称不一致。")
    public static final long C_431 = 431;

    @BeanAttrInfo(cnName = "20.9 请提交规范的承诺书，承诺书模板可咨询注册服务机构支持人员。")
    public static final long C_432 = 432;

    @BeanAttrInfo(cnName = "域名尚未通过注册审核，请待域名审核通过后再进行备案验证")
    public static final long C_434 = 434;

    @BeanAttrInfo(cnName = "域名尚未进行备案，请到通信管理局进行新注册域名信息备案")
    public static final long C_435 = 435;

    @BeanAttrInfo(cnName = "20.13 域名不是因为未备案停止解析，具体停止解析原因请咨询注册服务机构支持人员。")
    public static final long C_436 = 436;

    @BeanAttrInfo(cnName = "提交的域名非CNNIC管理的域名")
    public static final long C_437 = 437;

    @BeanAttrInfo(cnName = "1.1 注册者证明资料上的名称与系统中的注册者名称不一致")
    public static final long C_463 = 463;

    @BeanAttrInfo(cnName = "10.2 违反《中国互联网络域名管理办法》第二十七条 第（七）款，即域名含有散布淫秽、色情、赌博、暴力、凶杀、恐怖或者教唆犯罪的内容，此次注册申请审核拒绝")
    public static final long C_465 = 465;

    @BeanAttrInfo(cnName = "1.3 系统中注册者名称不能为空")
    public static final long C_493 = 493;

    @BeanAttrInfo(cnName = "2.1.1注册者身份证明资料不清晰")
    public static final long C_496 = 496;

    @BeanAttrInfo(cnName = "2.1.2 注册者身份证明资料不完整")
    public static final long C_497 = 497;

    @BeanAttrInfo(cnName = "2.1.3注册者身份证明资料无法打开")
    public static final long C_498 = 498;

    @BeanAttrInfo(cnName = "3.1 未提交注册者身份证明资料")
    public static final long C_506 = 506;

    @BeanAttrInfo(cnName = "3.5 全国组织机构代码管理中心的组织机构代码证查询结果的资料不能作为有效的注册者身份证明资料，请提供组织机构代码证或企业营业执照复印件、扫描件")
    public static final long C_510 = 510;

    @BeanAttrInfo(cnName = "5.1 提交的组织机构代码证与全国组织机构代码管理中心查询结果不一致")
    public static final long C_514 = 514;

    @BeanAttrInfo(cnName = "5.3 提交的企业营业执照验证失败")
    public static final long C_517 = 517;

    @BeanAttrInfo(cnName = "7.1 提交资料显示为海外用户。")
    public static final long C_529 = 529;

    @BeanAttrInfo(cnName = "8.1 未提交注册者出具的域名注册公函，请提交域名注册公函，且公函中应写明注册的域名及注册者联系人为该组织工作人员")
    public static final long C_530 = 530;

    @BeanAttrInfo(cnName = "8.2 提交的注册者公函中未写明申请的域名或未写明注册者联系人为该组织工作人员")
    public static final long C_531 = 531;

    @BeanAttrInfo(cnName = "10.3 违反国务院令第345号《奥林匹克标志保护条例》规定，未经奥林匹克标志权利人许可,任何人不得为商业目的(含潜在商业目的)使用奥林匹克标志，此次注册申请审核拒绝")
    public static final long C_537 = 537;

    @BeanAttrInfo(cnName = "10.4  违反国务院令第422号《世界博览会标志保护条例》规定，未经世界博览会标志权利人许可，任何人不得为商业目的（含潜在商业目的）使用世界博览会标志，此次注册申请审核拒绝")
    public static final long C_538 = 538;

    @BeanAttrInfo(cnName = "10.5 违反《中国互联网络域名管理办法》第二十七条第（九）款，即域名含有法律、行政法规禁止的其他内容，此次注册申请审核拒绝")
    public static final long C_539 = 539;

    @BeanAttrInfo(cnName = "10.6 违反《中国互联网络域名管理办法》第二十七条 第（二）款，即域名含有危害国家安全，泄露国家秘密，颠覆国家政权，破坏国家统一的内容，此次注册申请审核拒绝")
    public static final long C_540 = 540;

    @BeanAttrInfo(cnName = "10.7 违反《中国互联网络域名管理办法》第二十七条第（三）款，即域名含有损害国家荣誉和利益的内容，此次注册申请审核拒绝")
    public static final long C_541 = 541;

    @BeanAttrInfo(cnName = "10.8 违反《中国互联网络域名管理办法》第二十七条第（四）款，即域名含有煽动民族仇恨、民族歧视，破坏民族团结的内容，此次注册申请审核拒绝")
    public static final long C_542 = 542;

    @BeanAttrInfo(cnName = "10.9 违反《中国互联网络域名管理办法》第二十七条第（五）款，即域名含有破坏国家宗教政策，宣扬邪教和封建迷信的内容，此次注册申请审核拒绝")
    public static final long C_543 = 543;

    @BeanAttrInfo(cnName = "10.10 违反《中国互联网络域名管理办法》第二十七条第（六）款，即域名含有散布谣言，扰乱社会秩序，破坏社会稳定的内容，此次注册申请审核拒绝")
    public static final long C_544 = 544;

    @BeanAttrInfo(cnName = "域名尚未通过注册审核，请待域名审核通过后再进行备案验证")
    public static final long C_557 = 557;

    @BeanAttrInfo(cnName = "域名尚未进行备案，请到通信管理局进行新注册域名信息备案")
    public static final long C_558 = 558;

    @BeanAttrInfo(cnName = "20.11 未提交承诺书。")
    public static final long C_560 = 560;

    @BeanAttrInfo(cnName = "20.8 承诺书填写不清晰、不完整。")
    public static final long C_561 = 561;

    @BeanAttrInfo(cnName = "20.5 承诺书上公章不清晰、不完整。")
    public static final long C_562 = 562;

    @BeanAttrInfo(cnName = "20.2 承诺书上填写域名不一致，请重新填写后再次进行提交")
    public static final long C_563 = 563;

    @BeanAttrInfo(cnName = "20.3 承诺书上填写的域名不规范，请去掉www.后再次进行提交。")
    public static final long C_564 = 564;

    @BeanAttrInfo(cnName = "20.4 承诺书上公章与系统中注册者名称不一致。")
    public static final long C_566 = 566;

    @BeanAttrInfo(cnName = "20.13 域名不是因为未备案停止解析，具体停止解析原因请咨询注册服务机构支持人员。")
    public static final long C_567 = 567;

    @BeanAttrInfo(cnName = "提交的域名非CNNIC管理的域名")
    public static final long C_568 = 568;

    @BeanAttrInfo(cnName = "20.9 请提交规范的承诺书，承诺书模板可咨询注册服务机构支持人员。")
    public static final long C_569 = 569;

    @BeanAttrInfo(cnName = "20.1 承诺书资料上的域名与系统中的域名不一致。")
    public static final long C_642 = 642;

    @BeanAttrInfo(cnName = "10.1 违反《中国互联网络域名管理办法》第二十七条 第（八）款，即域名含有侵害他人合法权益的内容，此次注册申请审核拒绝。")
    public static final long C_682 = 682;

    @BeanAttrInfo(cnName = "10.1 违反《中国互联网络域名管理办法》第二十七条 第（八）款，即域名含有侵害他人合法权益的内容，此次注册申请审核拒绝。")
    public static final long C_683 = 683;

    @BeanAttrInfo(cnName = "10.1 违反《中国互联网络域名管理办法》第二十七条 第（八）款，即域名含有侵害他人合法权益的内容，此次注册申请审核拒绝。")
    public static final long C_684 = 684;

    @BeanAttrInfo(cnName = "该域名对应的注册者ID未收到注册信息核验资料，此次域名注册申请拒绝")
    public static final long C_723 = 723;

    @BeanAttrInfo(cnName = "该域名对应的注册者ID未通过注册信息验证，请查询注册者id的拒绝原因，此次注册申请审核拒绝")
    public static final long C_725 = 725;

    @BeanAttrInfo(cnName = "注册者ID已经通过信息核验，因域名注册时间已超过审核期，被系统自动删除，请用该ID重新注册域名")
    public static final long C_726 = 726;

    @BeanAttrInfo(cnName = "该域名已由注册服务机构自行删除")
    public static final long C_742 = 742;

    @BeanAttrInfo(cnName = "该gov域名未提交《域名注册申请表》，此次域名注册申请拒绝 请通过web页面点击“gov域名申请表上传”按钮，根据页面提示，提交《域名注册申请表》")
    public static final long C_743 = 743;

    @BeanAttrInfo(cnName = "该域名对应的注册者ID未收到注册信息核验资料，此次域名注册申请拒绝")
    public static final long C_764 = 764;

    @BeanAttrInfo(cnName = "该域名对应的注册者ID未通过注册信息验证，请查询注册者id的拒绝原因，此次注册申请审核拒绝")
    public static final long C_765 = 765;

    @BeanAttrInfo(cnName = "注册者ID已经通过信息核验，因域名注册时间已超过审核期，被系统自动删除，请用该ID重新注册域名")
    public static final long C_766 = 766;

    @BeanAttrInfo(cnName = "该域名已由注册服务机构自行删除")
    public static final long C_767 = 767;

    @BeanAttrInfo(cnName = "该注册者ID已由注册服务机构自行删除")
    public static final long C_769 = 769;

    @BeanAttrInfo(cnName = "该注册者ID已由注册服务机构自行删除")
    public static final long C_770 = 770;

    @BeanAttrInfo(cnName = "20.1 承诺书资料上的域名与系统中的域名不一致。")
    public static final long C_782 = 782;

    @BeanAttrInfo(cnName = "域名注册信息不真实、不准确，此次注册申请审核拒绝")
    public static final long C_862 = 862;

    @BeanAttrInfo(cnName = "11.60 同一机关法人同时注册五个以上GOV.CN域名时，请补充加盖注册者公章的域名及其使用机构的用途说明函")
    public static final long C_922 = 922;

    @BeanAttrInfo(cnName = "4.1 全国组织机构代码管理中心显示代码证已过期。")
    public static final long C_942 = 942;

    @BeanAttrInfo(cnName = "域名无停止解析状态")
    public static final long C_960 = 960;

    @BeanAttrInfo(cnName = "4.1 全国组织机构代码管理中心显示代码证已过期。")
    public static final long C_965 = 965;

    @BeanAttrInfo(cnName = "域名无停止解析状态")
    public static final long C_976 = 976;

    @BeanAttrInfo(cnName = "3.2 提交注册者身份证明资料验证不合格。组织用户请提交组织机构代码证或企业营业执照复印件、扫描件，自然人用户请提交大陆居民身份证或户籍证明复印件、扫描件")
    public static final long C_1007 = 1007;

    @BeanAttrInfo(cnName = "5.2 注册者提交的身份证与公安部公民身份证信息查询接口中的查询结果不一致")
    public static final long C_1008 = 1008;

    @BeanAttrInfo(cnName = "3.2 提交注册者身份证明资料验证不合格。组织用户请提交组织机构代码证或企业营业执照复印件、扫描件，自然人用户请提交大陆居民身份证或户籍证明复印件、扫描件")
    public static final long C_1048 = 1048;

    @BeanAttrInfo(cnName = "5.2 注册者提交的身份证与公安部公民身份证信息查询接口中的查询结果不一致")
    public static final long C_1050 = 1050;

    @BeanAttrInfo(cnName = "20.7 承诺书上签名不清晰、不完整。")
    public static final long C_1062 = 1062;

    @BeanAttrInfo(cnName = "承诺书上注册者签名与注册者名称不一致")
    public static final long C_1063 = 1063;

    @BeanAttrInfo(cnName = "20.7 承诺书上签名不清晰、不完整。")
    public static final long C_1065 = 1065;

    @BeanAttrInfo(cnName = "20.6 承诺书上签名与系统中注册者名称不一致。")
    public static final long C_1066 = 1066;

    @BeanAttrInfo(cnName = "20.10 承诺书上未加盖注册者公章或未签署注册者姓名。")
    public static final long C_1067 = 1067;

    @BeanAttrInfo(cnName = "7.3未提交承诺书")
    public static final long C_1122 = 1122;

    @BeanAttrInfo(cnName = "7.3未提交承诺书")
    public static final long C_1123 = 1123;

    @BeanAttrInfo(cnName = "10.1 违反《中国互联网络域名管理办法》第二十七条 第（八）款，即域名含有侵害他人合法权益的内容，此次注册申请审核拒绝。")
    public static final long C_1162 = 1162;

    @BeanAttrInfo(cnName = "20.6 承诺书上签名与系统中注册者名称不一致。")
    public static final long C_1182 = 1182;

    @BeanAttrInfo(cnName = "承诺书上签名与系统中注册者名称不一致")
    public static final long C_1202 = 1202;

    @BeanAttrInfo(cnName = "20.12 承诺书不清晰。")
    public static final long C_1282 = 1282;

    @BeanAttrInfo(cnName = "20.12 承诺书不清晰。")
    public static final long C_1283 = 1283;

    @BeanAttrInfo(cnName = "注册者名称已变更，请填写变更后的组织名称并提供变更后的组织证明资料。")
    public static final long C_1442 = 1442;

    @BeanAttrInfo(cnName = "注册者名称已变更，请填写变更后的组织名称并提供变更后的组织证明资料。")
    public static final long C_1443 = 1443;

    @BeanAttrInfo(cnName = "9.12 《域名注册申请表》中填写的域名注册事宜联系人需为申请单位工作人员，此次注册申请审核拒绝。")
    public static final long C_1462 = 1462;
}
