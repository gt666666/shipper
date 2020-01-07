package com.zxhz.zgenerator;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MybatisGenerator {

    private static File configFile;

    static {
        String path = System.getProperty("user.dir").concat("\\src\\main\\resources\\mybatis\\generatorConfig.xml");
        configFile = new File(path);
    }

    public static void main(String[] args) throws IOException, XMLParserException,
            InvalidConfigurationException, SQLException, InterruptedException {

        if (!configFile.exists()) {
            throw new RuntimeException("配置文件不存在");
        }

        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);

        // 加载参数列表
        Map<String, Object> param = CodeGeneratorUtils.getParamMap(config);
        // 根据Service模板生成对应的Service
        CodeGeneratorUtils.generateService(param);
        // 根据controller模板生成对应的controller
        CodeGeneratorUtils.generateController(param);
    }
}