package com.zxhz.zgenerator;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import com.zxhz.utils.Tools;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.springframework.util.ResourceUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CodeGeneratorUtils {


    public static void generateService(Map<String, Object> param) throws FileNotFoundException {
        // 获取模板内容
        File templetServiceFile = ResourceUtils.getFile("classpath:template\\TempletService.java");
        String serviceContent = FileUtil.readUtf8String(templetServiceFile);
        // 循环参数map，替换对应值
        for (Map.Entry<String, Object> m : param.entrySet()) {
            serviceContent = StrUtil.replace(serviceContent, "{{@" + m.getKey() + "@}}", String.valueOf(m.getValue()));
        }
        // 创建 service
        String rootPath = String.valueOf(param.get("rootPath"));
        String folderName = String.valueOf(param.get("folderName"));
        String serviceName = String.valueOf(param.get("serviceName"));
        String servicePath = rootPath + "service\\" + folderName + "\\" + serviceName + ".java";
        File service = FileUtil.touch(new File(servicePath));
        FileUtil.writeString(serviceContent, service, "UTF-8");
    }


    public static void generateController(Map<String, Object> param) throws FileNotFoundException {
        // 获取模板内容
        File templetControllerFile = ResourceUtils.getFile("classpath:template\\TempletController.java");
        String controllerContent = FileUtil.readUtf8String(templetControllerFile);
        // 循环参数map，替换对应值
        for (Map.Entry<String, Object> m : param.entrySet()) {
            controllerContent = StrUtil.replace(controllerContent, "{{@" + m.getKey() + "@}}", String.valueOf(m.getValue()));
        }
        // 创建 service
        String rootPath = String.valueOf(param.get("rootPath"));
        String folderName = String.valueOf(param.get("folderName"));
        String controllerName = String.valueOf(param.get("controllerName"));
        String controllerPath = rootPath + "controller\\" + folderName + "\\" + controllerName + ".java";
        File controller = FileUtil.touch(new File(controllerPath));
        FileUtil.writeString(controllerContent, controller, "UTF-8");
    }


    public static Map<String, Object> getParamMap(Configuration config) throws IOException {
        // 获取项目主目录
        File directory = new File("");
        String courseFile = directory.getCanonicalPath();

        // 加载配置文件
        ClassPathResource resource = new ClassPathResource("mybatis/generatorConfig.properties");
        Properties properties = new Properties();
        properties.load(resource.getReader(Charset.defaultCharset()));

        // 加载MybatisGenerator生成的文件
        Context context = config.getContext("DB2Tables");
        String rootPack = "com.zxhz";
        String pojoDir = context.getJavaModelGeneratorConfiguration().getTargetPackage();
        String pojoName = context.getTableConfigurations().get(0).getDomainObjectName();
        String rootPath = courseFile + "\\src\\main\\java\\com\\zxhz\\";
        String folderName = properties.getProperty("folder.name");
        String serviceTitle = properties.getProperty("service.title");
        String serviceName = pojoName + "Service";
        String serviceNameMin = StrUtil.lowerFirst(serviceName);
        String servicePackagePath = rootPack + ".service." + folderName;
        String controllerTitle = properties.getProperty("controller.title");
        String controllerName = pojoName + "Ctl";
        String controllerPackagePath = rootPack + ".controller." + folderName;
        String mapperName = pojoName + "Mapper";
        String mapperNameMin = StrUtil.lowerFirst(mapperName);
        String mapperPath = rootPack + ".dao." + folderName + "." + mapperName;
        String pojoPath = pojoDir + "." + pojoName;
        String tableName = properties.getProperty("table.name");
        String author = Tools.isBlank(properties.getProperty("author")) ? System.getProperty("user.name") : properties.getProperty("author");
        String date = Tools.isBlank(properties.getProperty("date")) ? DateUtil.now() : properties.getProperty("date");

        // 创建一个存储值的map
        Map<String, Object> param = new HashMap<>(20);
        param.put("rootPath", rootPath);
        param.put("folderName", folderName);
        param.put("serviceTitle", serviceTitle);
        param.put("serviceName", serviceName);
        param.put("serviceNameMin", serviceNameMin);
        param.put("servicePackagePath", servicePackagePath);
        param.put("controllerTitle", controllerTitle);
        param.put("controllerName", controllerName);
        param.put("controllerPackagePath", controllerPackagePath);
        param.put("mapperName", mapperName);
        param.put("mapperNameMin", mapperNameMin);
        param.put("mapperPath", mapperPath);
        param.put("pojoName", pojoName);
        param.put("pojoPath", pojoPath);
        param.put("author", author);
        param.put("date", date);
        param.put("tableNameEnum", tableName.toUpperCase());
        param.put("path2", StrUtil.toCamelCase(tableName));
        return param;
    }
}