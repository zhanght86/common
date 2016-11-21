package com.sjdf.platform.dictionary.bean.eiss.domain;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Hunk
 * @category 域名后缀
 */
@Entity
@DiscriminatorValue("RESOLVE_DOMAIN_SUFFIX")
@BeanName(name = "域名后缀")
public class ResolveDomainSuffix extends Dictionary {
    private static final long serialVersionUID = 3952924156326315180L;
    /** 支持的域名后缀 */
    @BeanAttrInfo(value = ".com,.net,.cn,.info,.org,.biz,.tv,.cc,.中国,.com.cn,.net.cn,.org.cn,.gov.cn,.mo.cn,.tw.cn,.hk.cn,.sc.cn,.tj.cn,.sn.cn,.sd.cn,.jx.cn,.yn.cn,.js.cn,.gd.cn,.sh.cn,.gx.cn,.fj.cn,.hb.cn,.hn.cn,.ha.cn,.bj.cn,.ac.cn,.cq.cn,.he.cn,.sx.cn,.nm.cn,.ln.cn,.jl.cn,.hl.cn,.zj.cn,.ah.cn,.hi.cn,.gz.cn,.gs.cn,.qh.cn,.nx.cn,.xj.cn,.edu.cn,.asia,.mobi,.name,.网络,.网址,.公司,.政务,.公益,.com.hk,.com.au,.com.sg,.hk,.ws,.bz,.sh,.la,.com.tw,.tw,.de,.io,.me,.co.uk,.co.th,.travel,.us,.com.cm,.net.cm,.co.cm,.co,.cm,.gd,.ca,.in,.im,.tk,.cd,.so,.ru,.cn.com,.pw,.kr,.today,.sg,.wang,.it,.tm,.fm,.xyz,.ooo,.tt,.top,.ren,.sc,.win,.uk,.sohu,.网店,.xin,.tel,.gs,.pro", cnName = "支持的域名后缀")
    public static final long SUFFIX = 1L;
}
