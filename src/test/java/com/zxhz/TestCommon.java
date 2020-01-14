package com.zxhz;
import javax.annotation.Resource;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.hutool.poi.excel.cell.CellUtil;
import com.zxhz.dao.IUserInfoDAO;
import com.zxhz.enums.ResultEnum;
import com.zxhz.pojo.Course;
import com.zxhz.pojo.HGpsCarInfo;
import com.zxhz.pojo.Student;
import com.zxhz.utils.JxlsUtil;
import org.jxls.common.Context;
import com.zxhz.pojo.Fu;
import com.zxhz.service.MemberServiceImpl;
import com.zxhz.utils.PropertiesUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.jxls.util.JxlsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest(classes = ShipperApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestCommon {
    @Resource
    private MemberServiceImpl memberService;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testGet() {
    }

    @Test
    public void testAuth() throws Exception {
        List<Fu> fus = new ArrayList<>();
        File file = new File("F:\\统计表格");
        File[] files = file.listFiles();
        Fu fu= null;
        for (File file1 : files) {
            ExcelReader reader = ExcelUtil.getReader(file1.getPath());
            fu = new Fu();
            fu.setName((String)CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(0), true));
            fu.setB((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(1), true));
            fu.setC((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(2), true));
            fu.setD((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(3), true));
            fu.setE((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(4), true));
            fu.setF((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(5), true));
            fu.setG((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(6), true));
            fu.setH((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(7), true));
            fu.setI((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(8), true));
            fu.setJ((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(9), true));
            fu.setK((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(10), true));
            fu.setL((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(11), true));
            fu.setM((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(12), true));
            fu.setN((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(13), true));
            fu.setO((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(14), true));
            fu.setP((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(15), true));
            fu.setQ((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(16), true));
            fu.setR((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(17), true));
            fu.setS((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(18), true));
            fu.setT((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(19), true));
            fu.setU((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(20), true));
            fu.setV((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(21), true));
            fu.setW((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(22), true));
            fu.setX((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(23), true));
            fu.setY((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(24), true));
            fu.setZ((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(25), true));
            fu.setAa((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(26), true));
            fu.setAb((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(27), true));
            fu.setAc((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(28), true));
            fu.setAd((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(29), true));
            fu.setAe((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(30), true));
            fu.setAf((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(31), true));
            fu.setAg((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(32), true));
            fu.setAh((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(33), true));
            fu.setAi((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(34), true));
            fu.setAj((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(35), true));
            fu.setAk((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(36), true));
            fu.setAl((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(37), true));
            fu.setAm((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(38), true));
            fu.setAn((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(39), true));
            fu.setAo((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(40), true));
            fu.setAp((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(41), true));
            fu.setAq((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(42), true));
            fu.setAr((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(43), true));
            fu.setAs((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(44), true));
            fu.setAt((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(45), true));
            fu.setAu((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(46), true));
            fu.setAv((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(47), true));
            fu.setAw((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(48), true));
            fu.setAx((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(49), true));
            fu.setAy((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(50), true));
            fu.setAz((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(51), true));
            fu.setBa((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(52), true));
            fu.setBb((Long) CellUtil.getCellValue(reader.getSheets().get(0).getRow(10).getCell(53), true));
            fus.add(fu);
        }
        //模板里展示的数据
        Map<String, Object> data = new HashMap<>();
        data.put("fus", fus);
        //data.put("zname","赵丽");
        // 模板路径和输出流
        String templatePath = "F:\\template.xlsx";
        OutputStream os = new FileOutputStream("F:\\test.xlsx");
        //调用封装的工具类，传入模板路径，输出流，和装有数据的Map,按照模板导出
        JxlsUtil.exportExcel(templatePath, os, data);
    }

    @Test
    public void excel() throws Exception {
        List<Fu> fus = new ArrayList<>();
        File file = new File("F:\\统计表格");
        File[] files = file.listFiles();
        Fu fu = null;
        for (File file1 : files) {
            ExcelReader reader = ExcelUtil.getReader(file1.getPath());
            fu = new Fu();
            fu.setB((Long) CellUtil.getCellValue(reader.getSheets().get(1).getRow(10).getCell(1), true));
            fu.setC((Long) CellUtil.getCellValue(reader.getSheets().get(1).getRow(10).getCell(2), true));
            fu.setD((Long) CellUtil.getCellValue(reader.getSheets().get(1).getRow(10).getCell(3), true));
            fu.setE((Long) CellUtil.getCellValue(reader.getSheets().get(1).getRow(10).getCell(4), true));
            fu.setF((Long) CellUtil.getCellValue(reader.getSheets().get(1).getRow(10).getCell(5), true));
            fu.setG((Long) CellUtil.getCellValue(reader.getSheets().get(1).getRow(10).getCell(6), true));
            fu.setH((Long) CellUtil.getCellValue(reader.getSheets().get(1).getRow(10).getCell(7), true));
            fu.setI((Long) CellUtil.getCellValue(reader.getSheets().get(1).getRow(10).getCell(8), true));
            fu.setJ((Long) CellUtil.getCellValue(reader.getSheets().get(1).getRow(10).getCell(9), true));
            fu.setK((Long) CellUtil.getCellValue(reader.getSheets().get(1).getRow(10).getCell(10), true));
            fu.setL((Long) CellUtil.getCellValue(reader.getSheets().get(1).getRow(10).getCell(11), true));
            fu.setM((Long) CellUtil.getCellValue(reader.getSheets().get(1).getRow(10).getCell(12), true));
            fu.setN((Long) CellUtil.getCellValue(reader.getSheets().get(1).getRow(10).getCell(13), true));
            fu.setO((Long) CellUtil.getCellValue(reader.getSheets().get(1).getRow(10).getCell(14), true));
            fu.setP((Long) CellUtil.getCellValue(reader.getSheets().get(1).getRow(10).getCell(15), true));
            fu.setQ((Long) CellUtil.getCellValue(reader.getSheets().get(1).getRow(10).getCell(16), true));
            fu.setR((Long) CellUtil.getCellValue(reader.getSheets().get(1).getRow(10).getCell(17), true));
            fu.setS((Long) CellUtil.getCellValue(reader.getSheets().get(1).getRow(10).getCell(18), true));
            fu.setT((Long) CellUtil.getCellValue(reader.getSheets().get(1).getRow(10).getCell(19), true));
            fu.setU((Long) CellUtil.getCellValue(reader.getSheets().get(1).getRow(10).getCell(20), true));
            fu.setV((Long) CellUtil.getCellValue(reader.getSheets().get(1).getRow(10).getCell(21), true));
            fu.setW((Long) CellUtil.getCellValue(reader.getSheets().get(1).getRow(10).getCell(22), true));
            fu.setX((Long) CellUtil.getCellValue(reader.getSheets().get(1).getRow(10).getCell(23), true));
            fu.setY((Long) CellUtil.getCellValue(reader.getSheets().get(1).getRow(10).getCell(24), true));
            fu.setZ((Long) CellUtil.getCellValue(reader.getSheets().get(1).getRow(10).getCell(25), true));
            fu.setAa((Long) CellUtil.getCellValue(reader.getSheets().get(1).getRow(10).getCell(26), true));
            fu.setAb((Long) CellUtil.getCellValue(reader.getSheets().get(1).getRow(10).getCell(27), true));
            fu.setAc((Long) CellUtil.getCellValue(reader.getSheets().get(1).getRow(10).getCell(28), true));
            fu.setAd((Long) CellUtil.getCellValue(reader.getSheets().get(1).getRow(10).getCell(29), true));
            fu.setAe((Long) CellUtil.getCellValue(reader.getSheets().get(1).getRow(10).getCell(30), true));
            fu.setAf((Long) CellUtil.getCellValue(reader.getSheets().get(1).getRow(10).getCell(31), true));
            fu.setAg((Long) CellUtil.getCellValue(reader.getSheets().get(1).getRow(10).getCell(32), true));
            fu.setAh((Long) CellUtil.getCellValue(reader.getSheets().get(1).getRow(10).getCell(33), true));
            fu.setAi((Long) CellUtil.getCellValue(reader.getSheets().get(1).getRow(10).getCell(34), true));
            fu.setAj((Long) CellUtil.getCellValue(reader.getSheets().get(1).getRow(10).getCell(35), true));
            fu.setAk((Long) CellUtil.getCellValue(reader.getSheets().get(1).getRow(10).getCell(36), true));
            fus.add(fu);
        }
        System.out.println(fus);
        //模板里展示的数据
        Map<String, Object> data = new HashMap<>();
        data.put("fus", fus);
        // 模板路径和输出流
        String templatePath = "F:\\template1.xlsx";
        OutputStream os = new FileOutputStream("F:\\test.xlsx");
        //调用封装的工具类，传入模板路径，输出流，和装有数据的Map,按照模板导出
        JxlsUtil.exportExcel(templatePath, os, data);
    }

    @Test
    public void testWord() {
        System.out.println(PropertiesUtils.getInstance().properties.getProperty("profiles.active"));
        System.out.println(PropertiesUtils.getInstance().propertiesCustom.getProperty("path"));
    }

    @Test
    public void testEnum() {
        for (ResultEnum resultEnum : ResultEnum.values()) {
            System.out.println(resultEnum);
            System.out.println(resultEnum.getCode());
            System.out.println(resultEnum.getMessage());
        }

    }



    @Test
    public void testMongo() throws Exception {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(1575291448000L);
        System.out.println(format);
    }
    @Test
    public void testMongoExcel() throws Exception {
       Set<String>  set=new HashSet<>();
       set.add("5");
       set.add("1");
       set.add("3");
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
}