package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * create at 2014-9-21
 *
 * @author 龚涛
 * @category 功能提示内容
 */

@Entity
@DiscriminatorValue("Common_Message_Note")
@BeanName(name = "公共提示")
public class CommonMessageNote extends Dictionary {

    private static final long serialVersionUID = -1625501391287357983L;

    /** 资源监控提示帮助信息 */
    @BeanAttrInfo(value = "1. 我们为用户提供了在线监控服务的功能，用户可以不用登陆系统也能了解云主机的资源使用情况。<br />"
            + "2. 监控频率: 系统盘情况: {monitorDisk}天一次&nbsp;&nbsp;数据盘情况: {monitorDisk}天一次&nbsp;&nbsp;CPU情况:"
            + " {monitorCpu}分钟一次&nbsp;&nbsp;带宽情况: {monitoBandwith}秒一次<br />"
            + "3. 带宽的检测{monitoBandwith}秒一次，如果您使用的带宽持续{monitoBandwith}秒超过建议限制速度以后，为了确保主服务器的稳定，<br />"
            + "&nbsp;&nbsp;&nbsp;带宽会被限制到【{punishBandwidthm}M(峰值速度: {punishBandwidthk}"
            + "KB/s)】。限制时间为{resetPeriod}分钟。<br />"
            + "4. 网速限制的方法请参考【<a target='_blank' href='http://help.cdnhost.cn/menu.php?id=art772#art772'>帮助中心</a>】。",
            cnName = "资源监控提示帮助信息")
    public static final long RESOURCE_MONITOR_NOTE = 1;

    /** 远程管理信息提示 */
    @BeanAttrInfo(value = " 1. 以上的远程管理密码只是系统的初始密码，远程连接进入系统后请立刻修改密码并牢记新密码。<br/>"
            + "2. 修改远程管理密码后，初始密码将不再可用。 <br/>"
            + "3. 如果您忘记了自己修改后的远程管理密码，可以点击上面【重置密码】按钮进行云主机远程管理密码重置，请注意：<br/>"
            + "&nbsp;&nbsp;&nbsp;①重置云主机远程管理密码需在云主机关机的情况下进行。 <br/>"
            + "&nbsp;&nbsp;&nbsp;②Linux系统重置远程管理密码为远程管理初始密码，Windows系统重置远程管理密码后云主机远程管理密码【被清空】。 <br/>"
            + "&nbsp;&nbsp;&nbsp;③重置远程管理密码后，通过WEB控制台进入系统后请立刻修改密码并牢记新密码。 <br/>"
            + "4. 因初始密码会在产品管理中心公开显示，任何因不修改初始密码造成的后果，我司不承担任何责任。",
            cnName = "远程管理信息提示")
    public static final long REMOTE_MANAGEMENT_NOTE = 2;

    /** 产品管理密码信息提示 */
    @BeanAttrInfo(value = "1. 本功能提供您修改产品管理密码，以确保信息安全！<br/>"
            + "2. 产品管理密码可用于登录产品管理平台！<br/>"
            + "3. 产品管理密码可用于域名备案！<br/>"
            + "4. 产品管理密码可用于备份FTP登录！<br/>",
            cnName = "产品管理密码信息提示")
    public static final long PASSWORD_MANAGEMENT_NOTE = 3;

    /** 开关机信息提示 */
    @BeanAttrInfo(value = "1. 在运行状态下可以做暂停、重启、正常关机和强制关机操作！<br />"
            + "2. 在暂停状态下可以做恢复和强制关机操作！<br />"
            + "3. 在关机状态下只可以做启动操作！<br />"
            + "4. 运行状态为管理员停止时，您可以联系管理员恢复云主机运行状态！<br />"
            + "5. 强制关机可能会造成数据的丢失，请谨慎操作！<br />",
            cnName = "开关机信息提示")
    public static final long STATUS_MANAGER_NOTE = 4;

    /** 系统重装提示 */
    @BeanAttrInfo(value = "1. 本功能将删除您现有系统，除非选择“保留数据”，否则您的所有数据都会丢失，请谨慎操作！即使选择了“保留数据”也有丢失数据的风险(即使风险极小)，"
            + "请在重装系统前做好数据备份，因重装导致的数据丢失由客户自己承担责任。<br/>"
            + "2. 跨平台(如Windows平台和Linux平台互换)重装系统时，数据不能被保留，请先做好备份！<br/>"
            + "3. 系统重装前，请不要把数据放在系统盘（如：Windows:C盘）！<br/>"
            + "4. Windows操作系统保留数据重装系统后，D盘需要进行重新挂载，"
            + "请参照<a href='http://help.51web.com/page.php?pid=966' target='_blank'>【如何找回消失的数据盘？】</a><br/>"
            + "5. 不保留数据重装系统后会重置所有数据库管理密码为新的随机字符串密码，重装后请到管理助手文件夹中配置文件里查看新的数据库管理密码。<br/>"
            + "6. 保留数据重装系统为保证建站助手正常使用，建议先备份云主机上c:/vhostmanager/config.ini，重装系统后用备份的文件覆盖c:/vhostmanager/config.ini。<br/>"
            + "7. Windows+助手系统保留数据重装系统后站点要恢复运行和FTP等功能需要使用建站助手操作修复站点。<br/>",
            cnName = "系统重装提示")
    public static final long RELOAD_SYSTEM_NOTE = 5;

    /** 快照备份恢复提示 */
    @BeanAttrInfo(value = "1. 我们为用户的磁盘定时做快照，快照技术可使我们在还原的时候更安全、更快速、更方便。<br/>"
            + "2. 备份类型：系统盘是指系统所在的磁盘(如C盘)、数据盘是指网站或数据库所在的磁盘(如D盘)。<br/>"
            + "3. 快照创建时间：是指此快照磁盘上保留了这个时间以前的数据。当点击恢复时，磁盘就会回滚到这个快照时间之前的状态。<br/>"
            + "4. 注意：如果点击了恢复，那么该快照时间之后的磁盘快照将会被删除。因此为了防止用户选择错误，我们给用户提供了系统预览模式，"
            + "即：当用户点击恢复后系统将进入预览模式，此时撤销将变为\"确定\"与\"取消\"，当用户确认无误后可点击\"确定\"按钮完成恢复操作。<br/>"
            + "5. 如果云主机进入预览模式，则系统不会再创建新的快照，因此用户在使用完预览模式后一定不要忘记做\"确定\"或\"取消\"操作。",
            cnName = "快照备份恢复提示")
    public static final long SNAPSHOT_RECOVER_NOTE = 6;

    /** 白名单/免备案提示 */
    @BeanAttrInfo(value = "1. 若您购买的国内云主机没有通过备案，请参考<a href='http://www.cdnhost.cn/product/vps/help/chost.htm' target='_blank'>【云主机别名解析操作说明】</a>进行别名解析。<br/>"
            + "2. 需要使用免备案转发的域名，请先做转发，再设置别名（CNAME）解析到${chostInfo.orderNum}.51cloudzj.com<br />"
            + "3. 如果您的域名是在本站注册或者您的域名使用的是本站的DNS ，可以点击自动解析完成正确的解析。<br />"
            + "4. 管理员对域名状态的修改只能通过申请恢复联系管理员进行处理。<br />"
            + "5. 添加到白名单的域名${chostInfo.whiteListInfo }后生效。",
            cnName = "白名单/免备案提示")
    public static final long BINDING_LIST_NOTE = 7;

    /** 网站安全提示 */
    @BeanAttrInfo(value = "Q.<a href='http://help.51web.com/page.php?pid=1081' target='blank'>漏洞类型说明</a><br/>"
            + "Q.<a href='http://help.51web.com/page.php?pid=1082' target='blank'>漏洞的危害</a><br/>"
            + "Q.<a href='http://help.51web.com/page.php?pid=1083' target='blank'>漏洞解决方案</a><br/>",
            cnName = "网站安全提示")
    public static final long SIT_SCANV_NOTE = 8;

    /** WEB控制台提示 */
    @BeanAttrInfo(value = "用户在系统未联网（无法远程管理系统）的情况下可使用web控制台查看系统状态（包括启动过程）并进行系统管理。",
            cnName = "WEB控制台提示")
    public static final long WEB_CONSOLE_NOTE = 9;

}
