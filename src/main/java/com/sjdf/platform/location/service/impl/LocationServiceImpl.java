package com.sjdf.platform.location.service.impl;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.api.vo.JcdmQyxx;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.helper.ResourceBundleHelper;
import com.sjdf.platform.common.service.impl.BaseServiceImpl;
import com.sjdf.platform.common.utils.Page;
import com.sjdf.platform.common.utils.PinyinManager;
import com.sjdf.platform.dictionary.bean.OperatorAction;
import com.sjdf.platform.dictionary.bean.WhetherState;
import com.sjdf.platform.location.bean.Location;
import com.sjdf.platform.location.helper.LocationManager;
import com.sjdf.platform.location.service.LocationService;
import com.sjdf.platform.location.vo.LocationVo;
import com.sjdf.platform.notify.service.NotifyTrackService;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.hibernate.Hibernate;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Create at 2012-11-15 下午5:45:06
 * 省，市，县（区）的相关信息service
 *
 * @author KETQI
 */
@Service
public class LocationServiceImpl extends BaseServiceImpl implements LocationService {
    @Autowired
    private NotifyTrackService notifyTrackService;

    /**
     * @param location 查询条件地理位置信息
     * @param page     分页组件
     * @return 分页查询地理位置信息
     */
    @Override
    public List<Location> list(Location location, Page page) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Location.class, "l");

        if (location != null) {
            // 代码
            if (StringUtils.hasText(location.getCode())) {
                criteria.add(Restrictions.eq("l.code", location.getCode()));
            }

            // 中文名称
            if (StringUtils.hasText(location.getCnName())) {
                criteria.add(Restrictions.like("l.cnName", location.getCnName(), MatchMode.ANYWHERE));
            }

            // 英文名称
            if (StringUtils.hasText(location.getEnName())) {
                criteria.add(Restrictions.like("l.enName", location.getEnName(), MatchMode.ANYWHERE));
            }

            // 区号
            if (StringUtils.hasText(location.getAreaCode())) {
                criteria.add(Restrictions.eq("l.areaCode", location.getAreaCode()));
            }
            // 简称
            if (StringUtils.hasText(location.getAbbreviation())) {
                criteria.add(Restrictions.eq("l.abbreviation", location.getAbbreviation()));
            }
            // 邮编
            if (StringUtils.hasText(location.getPostal())) {
                criteria.add(Restrictions.eq("l.postal", location.getPostal()));
            }

            // 省份
            if (location.getProvince() != null && StringUtils.hasText(location.getProvince().getCode())) {
                Location province = location.getProvince();
                if ("0".equals(province.getCode())) {
                    criteria.add(Restrictions.isNull("l.province"));
                } else {
                    criteria.createAlias("l.province", "p");
                    criteria.add(Restrictions.eq("p.code", province.getCode()));
                }
            }

            // 城市
            if (location.getCity() != null && StringUtils.hasText(location.getCity().getCode())) {
                Location city = location.getCity();
                criteria.createAlias("l.city", "c");
                criteria.add(Restrictions.eq("c.code", city.getCode()));
            }
        }
        return baseDao.listByCriteria(criteria, page, Order.asc("l.code"));
    }

    /**
     * @param idx 主键
     * @return 加载指定的地理位置信息
     */
    @Override
    public Location getLocation(long idx) {
        Location location = baseDao.get(Location.class, idx);
        initialize(location);
        return location;
    }

    /**
     * @param location 地理位置信息
     * @return 添加地理位置信息
     */
    @Override
    public Message add(Location location) {
        Message message = validate(location, true);
        if (message.hasErrorMessage()) {
            return message;
        }
        baseDao.save(location);
        return Message.createMessage().setReturnData(location).setInfo(location.wrapUpdateContent(null, false));
    }

    /**
     * @param location 地理位置信息
     * @return 修改地理位置
     */
    @Override
    public Message update(Location location) {
        if (location == null) {
            return Message.createMessage("location.null");
        }

        Location oldLocation = baseDao.get(Location.class, location.getId());
        if (oldLocation == null) {
            return Message.createMessage("location.null");
        }

        Message message;
        if (oldLocation.getCode().equals(location.getCode())) {
            message = validate(location, false);
        } else {
            message = validate(location, true);
        }

        if (message.hasErrorMessage()) {
            return message;
        }

        String info = oldLocation.wrapUpdateContent(location, true);

        oldLocation.setAreaCode(location.getAreaCode());
        oldLocation.setCity(location.getCity());
        oldLocation.setCode(location.getCode());
        oldLocation.setCnName(location.getCnName());
        oldLocation.setEnName(location.getEnName());
        oldLocation.setPostal(location.getPostal());
        oldLocation.setProvince(location.getProvince());
        oldLocation.setAbbreviation(location.getAbbreviation());

        baseDao.update(oldLocation);
        return Message.createMessage().setReturnData(oldLocation).setInfo(info);
    }

    /**
     * @param list 地理位置信息列表
     * @return 添加或者修改地理位置信息
     */
    @Override
    public Message saveOrupdate(List<JcdmQyxx> list) {
        if (list != null && !list.isEmpty()) {
            for (JcdmQyxx jc : list) {
                Location loc = baseDao.findOne("from Location where code = ?", String.valueOf(jc.getQydmId()));
                if (loc != null) {
                    // 是否有效判断
                    if (jc.getSfyx() == WhetherState.YES) {
                        // 有效则更新
                        if (!loc.getCnName().equals(jc.getMc())) {
                            loc.setCnName(jc.getMc());
                            baseDao.update(loc);
                            notifyTrackService.saveLocaiotn(loc, OperatorAction.MODIFY);
                        }
                    } else {
                        // 无效则从公共平台删除
                        baseDao.delete(loc);
                        notifyTrackService.saveLocaiotn(loc, OperatorAction.DELETE);
                    }
                } else {
                    // 是否有效判断
                    if (jc.getSfyx() != WhetherState.YES) {
                        // 如果无效，且在公共平台不存在，不做任何处理
                        continue;
                    }
                    // 新增
                    Location locnew = new Location();
                    locnew.setCode(String.valueOf(jc.getQydmId()));
                    locnew.setCnName(jc.getMc());
                    locnew.setEnName(PinyinManager.getPinYin(locnew.getCnName()));
                    if (jc.getLx() == JcdmQyxx.IS_CITY) { // 城市
                        // 增加所属省份
                        String pcode = String.valueOf(jc.getQydmId()).substring(JcdmQyxx.INT0, JcdmQyxx.INT2) + JcdmQyxx.ZERO4;
                        Location province = baseDao.findOne("from Location where code = ?", String.valueOf(pcode));
                        if (province == null) {
                            continue;
                        }
                        locnew.setProvince(province);
                    } else if (jc.getLx() == JcdmQyxx.IS_COUNTRY) { // 区县
                        // 增加所属省份
                        String pcode = String.valueOf(jc.getQydmId()).substring(JcdmQyxx.INT0, JcdmQyxx.INT2) + JcdmQyxx.ZERO4;
                        Location province = baseDao.findOne("from Location where code = ?", String.valueOf(pcode));
                        if (province == null) {
                            continue;
                        }
                        locnew.setProvince(province);
                        // 增加所属城市
                        String ccode = String.valueOf(jc.getQydmId()).substring(JcdmQyxx.INT0, JcdmQyxx.INT4) + JcdmQyxx.ZERO2;
                        Location city = baseDao.findOne("from Location where code = ?", String.valueOf(ccode));
                        if (city == null) {
                            continue;
                        }
                        locnew.setCity(city);
                    }
                    baseDao.save(locnew);
                    notifyTrackService.saveLocaiotn(locnew, OperatorAction.ADD);
                }
            }
        }
        return Message.createEmptyMessage();
    }

    /**
     * @param idx 地理位置信息的id
     * @return 删除指定的地理位置信息
     */
    @Override
    public Message del(long idx) {
        Location location = getLocation(idx);
        if (location != null) {
            long count = baseDao.countHql("select count(id) from Location where province.id = ?", idx);
            if (count > 0) {
                return Message.createMessage("location.exist.province.ref", location.getCnName());
            }
            count = baseDao.countHql("select count(id) from Location where city.id = ?", idx);
            if (count > 0) {
                return Message.createMessage("location.exist.city.ref", location.getCnName());
            }
        } else {
            return Message.createMessage("location.null");
        }
        baseDao.delete(location);
        return Message.createMessage().setReturnData(location).setInfo(location.wrapUpdateContent(null, true));
    }

    /**
     * @param code 代码
     * @return 根据code获取地理位置信息
     */
    @Override
    public Location getLocation(String code) {
        Location location = null;
        List<Location> list = baseDao.find("from Location where code = ?", code);
        if (list != null && !list.isEmpty()) {
            location = list.get(0);
            initialize(location);
        }
        return location;
    }

    /**
     * @return 省份列表
     */
    @Override
    public List<Location> getProvinceList() {
        return baseDao.find("from Location where province is null order by code");
    }

    /**
     * @param provinceCode 省份代码
     * @return 城市列表
     */
    @Override
    public List<Location> getCityList(String provinceCode) {
        return baseDao.find("from Location where province.code = ? and city is null order by code", provinceCode);
    }

    /**
     * @param provinceCode 省份代码
     * @param cityCode     城市代码
     * @return 区县列表
     */
    @Override
    public List<Location> getCountyList(String provinceCode, String cityCode) {
        return baseDao.find("from Location where province.code = ? and city.code = ? order by code", provinceCode, cityCode);
    }

    /**
     * @param xls 文件
     * @return 解析并保存位置数据
     */
    @Override
    public Message upload(File xls) {
        Map<String, Location> map;
        try {
            map = parse4save(xls);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Message.createMessage("common.message", e.getMessage());
        }

        for (Map.Entry<String, Location> entry : map.entrySet()) {
            Message message = add(entry.getValue());
            if (message.hasErrorMessage()) {
                return message;
            }
        }
        return Message.createEmptyMessage();
    }

    /**
     * @param xls 文件
     * @return 更新邮编和区号
     */
    @Override
    public Message update(File xls) {
        Map<String, List<Location>> map;
        try {
            map = parse4Update(xls);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Message.createMessage("common.message", e.getMessage());
        }

        for (Map.Entry<String, List<Location>> entry : map.entrySet()) {
            for (Location temp : entry.getValue()) {
                Location location = getLocation(entry.getKey(), temp.getCnName());
                if (location != null) {
                    location.setAreaCode(temp.getAreaCode());
                    location.setPostal(temp.getPostal());
                    baseDao.update(location);
                }
            }
        }

        return Message.createEmptyMessage();
    }

    /**
     * @throws Exception 导出数据
     */
    @Override
    public void download(String path) throws Exception {
        File file = new File(path);
        List<LocationVo> provinceList = LocationManager.getInstance().getProvinceList();
        WritableWorkbook workbook = Workbook.createWorkbook(file);
        for (int i = 0; i < provinceList.size(); i++) {
            LocationVo province = provinceList.get(i);
            WritableSheet sheet = workbook.createSheet(province.getCnName(), i);
            int index = 0;
            // 标题
            sheet.addCell(new Label(0, index, "代码"));
            sheet.addCell(new Label(1, index, "省份代码"));
            sheet.addCell(new Label(CommonPlatformConstant.LENGTH_2, index, "城市代码"));
            sheet.addCell(new Label(CommonPlatformConstant.LENGTH_3, index, "中文名称"));
            sheet.addCell(new Label(CommonPlatformConstant.LENGTH_4, index, "英文名称"));
            sheet.addCell(new Label(CommonPlatformConstant.LENGTH_5, index, "邮编"));
            sheet.addCell(new Label(CommonPlatformConstant.LENGTH_6, index, "区号"));
            index++;

            // 省份
            sheet.addCell(new Label(0, index, province.getCode()));
            sheet.addCell(new Label(1, index, ""));
            sheet.addCell(new Label(CommonPlatformConstant.LENGTH_2, index, ""));
            sheet.addCell(new Label(CommonPlatformConstant.LENGTH_3, index, province.getCnName()));
            sheet.addCell(new Label(CommonPlatformConstant.LENGTH_4, index, province.getEnName()));
            sheet.addCell(new Label(CommonPlatformConstant.LENGTH_5, index, province.getPostal()));
            sheet.addCell(new Label(CommonPlatformConstant.LENGTH_6, index, province.getAreaCode()));
            index++;

            // 城市
            List<LocationVo> cityList = province.getAllChild();
            for (LocationVo city : cityList) {
                // 添加一个空行
                index++;

                sheet.addCell(new Label(0, index, city.getCode()));
                sheet.addCell(new Label(1, index, province.getCode()));
                sheet.addCell(new Label(CommonPlatformConstant.LENGTH_2, index, ""));
                sheet.addCell(new Label(CommonPlatformConstant.LENGTH_3, index, city.getCnName()));
                sheet.addCell(new Label(CommonPlatformConstant.LENGTH_4, index, city.getEnName()));
                sheet.addCell(new Label(CommonPlatformConstant.LENGTH_5, index, city.getPostal()));
                sheet.addCell(new Label(CommonPlatformConstant.LENGTH_6, index, city.getAreaCode()));
                index++;

                // 区县
                List<LocationVo> countyList = city.getAllChild();
                for (LocationVo county : countyList) {
                    sheet.addCell(new Label(0, index, county.getCode()));
                    sheet.addCell(new Label(1, index, province.getCode()));
                    sheet.addCell(new Label(CommonPlatformConstant.LENGTH_2, index, city.getCode()));
                    sheet.addCell(new Label(CommonPlatformConstant.LENGTH_3, index, county.getCnName()));
                    sheet.addCell(new Label(CommonPlatformConstant.LENGTH_4, index, county.getEnName()));
                    sheet.addCell(new Label(CommonPlatformConstant.LENGTH_5, index, county.getPostal()));
                    sheet.addCell(new Label(CommonPlatformConstant.LENGTH_6, index, county.getAreaCode()));
                    index++;
                }
            }
        }
        workbook.write();
        workbook.close();
    }

    private Location getLocation(String provinceCode, String name) {
        List<Location> list = baseDao.find("from Location where province.code = ? and name= ?", provinceCode, name);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    private Map<String, List<Location>> parse4Update(File xls) throws Exception {
        Map<String, List<Location>> map = new TreeMap<>();
        Workbook book = Workbook.getWorkbook(xls);
        for (Sheet sheet : book.getSheets()) {
            // 循环得到每个sheet(Excel通常会有3个Sheet)
            // 得到总行数
            int rownum = sheet.getRows();
            String provinceCode = sheet.getCell(0, 0).getContents().trim();
            List<Location> locationList = new ArrayList<>();
            map.put(provinceCode, locationList);

            for (int i = 1; i < rownum; i++) {
                // 第0行是标题
                if ("".equals(sheet.getCell(0, i).getContents().trim())) {
                    continue;
                }
                Location location = new Location();
                // 第i行的第0列;中文名称
                location.setCnName(sheet.getCell(0, i).getContents().trim());
                // 第i行的第1列;邮编
                location.setPostal(sheet.getCell(1, i).getContents().trim());
                // 第i行的第2列;区号
                location.setAreaCode(sheet.getCell(CommonPlatformConstant.LENGTH_2, i).getContents().trim());
                locationList.add(location);
            }
        }
        return map;
    }

    /**
     * @param xls 文件
     * @throws Exception 解析xls文件
     */
    private Map<String, Location> parse4save(File xls) throws Exception {
        Map<String, Location> map = new TreeMap<>();
        Workbook book = Workbook.getWorkbook(xls);
        for (Sheet sheet : book.getSheets()) {
            // 循环得到每个sheet(Excel通常会有3个Sheet)
            // 得到总行数
            int rownum = sheet.getRows();
            for (int i = 1; i < rownum; i++) {
                // 第0行是标题
                if ("".equals(sheet.getCell(0, i).getContents().trim())) {
                    continue;
                }
                Location location = new Location();
                // 第i行的第0列;代码(编号)
                location.setCode(sheet.getCell(0, i).getContents().trim());
                // 第i行的第1列;省份代码
                String prinviceCode = sheet.getCell(1, i).getContents().trim();
                if (StringUtils.hasText(prinviceCode)) {
                    location.setProvince(new Location(prinviceCode));
                }
                // 第i行的第2列;城市代码
                String cityCode = sheet.getCell(CommonPlatformConstant.LENGTH_2, i).getContents().trim();
                if (StringUtils.hasText(cityCode)) {
                    location.setCity(new Location(cityCode));
                }
                // 第i行的第3列;中文名称
                location.setCnName(sheet.getCell(CommonPlatformConstant.LENGTH_3, i).getContents().trim());
                // 第i行的第4列;英文名称
                location.setEnName(sheet.getCell(CommonPlatformConstant.LENGTH_4, i).getContents().trim());
                // 第i行的第5列;邮编
                location.setPostal(sheet.getCell(CommonPlatformConstant.LENGTH_5, i).getContents().trim());
                // 第i行的第6列;区号
                location.setAreaCode(sheet.getCell(CommonPlatformConstant.LENGTH_6, i).getContents().trim());
                map.put(location.getCode(), location);
            }
        }
        return map;
    }

    // 查询code的记录数目
    private long count(String code) {
        return baseDao.countHql("select count(id) from Location where code = ?", code);
    }

    /**
     * @param location 地理位置信息
     * @param isSave   是否保存
     * @return 验证
     */
    private synchronized Message validate(Location location, boolean isSave) {
        Message message = Location.validate(location);
        if (message.hasErrorMessage()) {
            return message;
        }

        if (isSave && count(location.getCode()) > 0) {
            return Message.createMessage("lcoation.code.repeat", location.getCode());
        }

        if (location.getProvince() != null && StringUtils.hasText(location.getProvince().getCode())) {
            Location province = getLocation(location.getProvince().getCode());
            if (province == null) {
                return Message.createMessage("lcoation.province.code.not.exist", location.getProvince().getCode());
            }
            location.setProvince(province);
        } else {
            location.setProvince(null);
        }

        if (location.getCity() != null && StringUtils.hasText(location.getCity().getCode())) {
            Location city = getLocation(location.getCity().getCode());
            if (city == null) {
                return Message.createMessage("lcoation.city.code.not.exist", location.getCity().getCode());
            }
            location.setCity(city);
        } else {
            location.setCity(null);
        }
        return Message.createEmptyMessage();
    }

    // 初始化lazy的数据
    private void initialize(Location location) {
        if (location != null) {
            Hibernate.initialize(location.getProvince());
            Hibernate.initialize(location.getCity());
            Hibernate.initialize(location.getCityList());
            Hibernate.initialize(location.getCountyList());
        }
    }

    // 获取国际资源化文件
    public String getMessage(Message message) {
        return ResourceBundleHelper.getInstance().getText(message);
    }
}