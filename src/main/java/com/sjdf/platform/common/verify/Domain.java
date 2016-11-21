package com.sjdf.platform.common.verify;

import com.sjdf.platform.CommonPlatformConstant;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Create at 2012-10-22
 * <p/>
 * 域名相关验证
 *
 * @author Chen Mohan
 */
public abstract class Domain {

    /**
     * 验证绑定域名格式（会自动将传入的域名转换为小写）
     * 中文（UTF-8范围）、英文字母（a-z，不区分大小写）、数字（0-9）、以及"-"（英文中的连词号
     * ，即中横线），不能使用空格及特殊字符(如!、$、&、? 等)； "-"不能用作开头和结尾；
     *
     * @param domainName 域名
     * @return 验证通过（true），验证不通过（false）
     */
    public static boolean checkBindingsDomain(String domainName) {
        String name = domainName;
        boolean ret;
        if (StringUtils.hasText(name)) {
            name = name.toLowerCase();

            String regEx = "^(((([a-z0-9\u4e00-\u9fa5]+-+)+[a-z0-9\u4e00-\u9fa5]+)|([a-z0-9\u4e00-\u9fa5]+))\\.)((((([a-z0-9\u4e00-\u9fa5]+-+)+[a-z0-9\u4e00-\u9fa5]+)|([a-z0-9\u4e00-\u9fa5]+))\\.))*((([a-z0-9\u4e00-\u9fa5]+-+)+[a-z0-9\u4e00-\u9fa5]+)|([a-z0-9\u4e00-\u9fa5]+))$";
            Pattern pattern = Pattern.compile(regEx);
            Matcher m = pattern.matcher(name);
            ret = m.matches();
        } else {
            ret = false;
        }

        return ret;
    }

    /**
     * 验证绑定域名格式（会自动将传入的域名转换为小写）
     *
     * @param domainName 域名
     * @return 验证通过（true），验证不通过（false）
     */
    public static boolean checkDomain(String domainName) {
        boolean ret;
        String name = domainName;
        if (StringUtils.hasText(name)) {
            name = name.toLowerCase();
            String regEx = "^([\\w-]+\\.)+((com)|(net)|(org)|(gov\\.cn)|(info)|(cc)|(com\\.cn)|(net\\.cn)|(org\\.cn)|(name)|"
                    + "(biz)|(tv)|(cn)|(mobi)|(name)|(sh)|(ac)|(io)|(tw)|(com\\.tw)|(hk)|(com\\.hk)|(ws)|(travel)|(us)|(tm)|"
                    + "(la)|(me\\.uk)|(org\\.uk)|(ltd\\.uk)|(plc\\.uk)|(in)|(eu)|(it)|(jp)|(co)|(me)|(mx)|(ca)|(ag)|(com\\.co)|"
                    + "(net\\.co)|(nom\\.co)|(com\\.ag)|(net\\.ag)|(fr)|(org\\.ag)|(am)|(asia)|(at)|(be)|(bz)|(com\\.bz)|"
                    + "(net\\.bz)|(net\\.br)|(com\\.br)|(de)|(es)|(com\\.es)|(nom\\.es)|(org\\.es)|(fm)|(gs)|(co\\.in)|"
                    + "(firm\\.in)|(gen\\.in)|(ind\\.in)|(net\\.in)|(org\\.in)|(jobs)|(ms)|(com\\.mx)|(nl)|(nu)|(co\\.nz)|"
                    + "(net\\.nz)|(org\\.nz)|(tc)|(tk)|(org\\.tw)|(idv\\.tw)|(co\\.uk)|(vg)|(ad)|(ae)|(af)|(ai)|(al)|(an)|(ao)|"
                    + "(aq)|(ar)|(as)|(au)|(aw)|(az)|(ba)|(bb)|(bd)|(bf)|(bg)|(bh)|(bi)|(bj)|(bm)|(bn)|(bo)|(br)|(bs)|(bt)|"
                    + "(bv)|(bw)|(by)|(cd)|(cf)|(cg)|(ch)|(ci)|(ck)|(cl)|(cm)|(cr)|(cu)|(cv)|(cx)|(cy)|(cz)|(dj)|(dk)|(dm)|"
                    + "(do)|(dz)|(ec)|(ee)|(eg)|(er)|(et)|(fi)|(fj)|(fk)|(fo)|(ga)|(gd)|(ge)|(gf)|(gg)|(gh)|(gi)|(gl)|(gm)|"
                    + "(gn)|(gp)|(gq)|(gr)|(gt)|(gu)|(gw)|(gy)|(hm)|(hn)|(hr)|(ht)|(hu)|(id)|(ie)|(il)|(im)|(iq)|(ir)|(is)|"
                    + "(je)|(jm)|(jo)|(ke)|(kg)|(kh)|(ki)|(km)|(kn)|(kr)|(kw)|(ky)|(kz)|(lb)|(lc)|(li)|(lk)|(lr)|(ls)|(lt)|"
                    + "(lu)|(lv)|(ly)|(ma)|(mc)|(md)|(mg)|(mh)|(mk)|(ml)|(mm)|(mn)|(mo)|(mp)|(mq)|(mr)|(mt)|(mu)|(mv)|(mw)|"
                    + "(my)|(mz)|(na)|(nc)|(ne)|(nf)|(ng)|(ni)|(no)|(np)|(nr)|(nz)|(om)|(pa)|(pe)|(pf)|(pg)|(ph)|(pk)|(pl)|"
                    + "(pm)|(pn)|(pr)|(ps)|(pt)|(pw)|(py)|(qa)|(re)|(ro)|(ru)|(rw)|(sa)|(sb)|(sc)|(sd)|(se)|(sg)|(si)|(sk)|"
                    + "(sl)|(sm)|(sn)|(sr)|(st)|(sv)|(sy)|(sz)|(td)|(tf)|(tg)|(th)|(tj)|(tl)|(tn)|(to)|(tr)|(tt)|(tz)|(ua)|"
                    + "(ug)|(uk)|(uy)|(uz)|(va)|(vc)|(ve)|(vi)|(vn)|(vu)|(wf)|(ye)|(yt)|(yu)|(za)|(zm)|(zw))$";
            Pattern pattern = Pattern.compile(regEx);
            Matcher m = pattern.matcher(name);
            ret = m.matches();
        } else {
            ret = false;
        }
        return ret;
    }

    /**
     * 判断输入的是否为中文域名的函数
     */
    public static boolean isChDomain(String argString) {
        if (!StringUtils.hasText(argString)) {
            return false;
        }
        boolean flag = false;
        String compStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz-1234567890";
        int length1 = argString.length();
        for (int chrIndex = 0; chrIndex < length1; chrIndex++) {
            char temp = argString.charAt(chrIndex);
            // 判断是否是中文
            if (temp >= CommonPlatformConstant.LENGTH_19968 && temp <= CommonPlatformConstant.LENGTH_171941) {
                flag = true;
            } else {
                long temp1 = compStr.indexOf(argString.charAt(chrIndex));
                if (temp1 == -1) {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }

    /**
     * 判断输入的是不是合法的英文域名的函数
     */
    public static boolean isEnDomain(String argValue) {
        if (!StringUtils.hasText(argValue)) {
            return false;
        }
        boolean flag1 = false;
        String compStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz-.1234567890";
        int length2 = argValue.length();
        for (int iIndex = 0; iIndex < length2; iIndex++) {
            int temp1 = compStr.indexOf(argValue.charAt(iIndex));
            if (temp1 == -1) {
                flag1 = false;
                break;
            } else {
                flag1 = true;
            }
        }
        return flag1;
    }
}
