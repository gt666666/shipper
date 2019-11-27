package com.zxhz.utils;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import cn.hutool.core.io.resource.ClassPathResource;
import com.deepoove.poi.XWPFTemplate;
import org.apache.commons.io.FileUtils;
import org.jxls.common.Context;
import org.jxls.expression.JexlExpressionEvaluator;
import org.jxls.transform.Transformer;
import org.jxls.transform.poi.PoiTransformer;
import org.jxls.util.JxlsHelper;
import javax.servlet.http.HttpServletResponse;

public class JxlsUtil {
  public static void exportExcel(String templatePath, OutputStream os, Map<String, Object> model) throws Exception {
        File template = getTemplate(templatePath);

        if (template != null) {
            exportExcel(new FileInputStream(template), os, model);
        } else {
            throw new Exception("Excel 模板未找到。");
        }
    }
    // 获取jxls模版文件

    public static File getTemplate(String path) {
        File template = new File(path);

        if (template.exists()) {
            return template;
        }

        return null;
    }
    public static void exportExcel(InputStream is, OutputStream os, Map<String, Object> model) throws IOException {
        Context context = PoiTransformer.createInitialContext();

        if (model != null) {
            for (String key : model.keySet()) {
                context.putVar(key, model.get(key));
            }
        }

        JxlsHelper jxlsHelper = JxlsHelper.getInstance();
        Transformer transformer = jxlsHelper.createTransformer(is, os);

        // 获得配置
        JexlExpressionEvaluator evaluator = (JexlExpressionEvaluator) transformer.getTransformationConfig()
                .getExpressionEvaluator();

        // 设置静默模式，不报警告
        // evaluator.getJexlEngine().setSilent(true);
        // 函数强制，自定义功能
        Map<String, Object> funcs = new HashMap<String, Object>();

        // 添加自定义功能
        funcs.put("utils", new JxlsUtil());
        evaluator.getJexlEngine().setFunctions(funcs);

        // 必须要这个，否者表格函数统计会错乱
        jxlsHelper.setUseFastFormulaProcessor(false).processTemplate(context, transformer);
    }
    public static void downWord(HttpServletResponse response, String fileName, String outFileName, Object data) throws IOException {
        // 将不安全的文件名改为UTF-8格式,设置文件名的编码
        File file = FileUtils.getFile("D:\\intellijCode\\shipper/src/main/resources/doc/car.docx");
        InputStream in = new ClassPathResource("doc/"+fileName+".docx").getStream();
        // 渲染模板数据
        XWPFTemplate template = XWPFTemplate.compile(file).render(data);
        //向客户端推送文件流
        outFileName = outFileName + "_" + "司机信息";
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(outFileName, "UTF-8") + ".docx");
        OutputStream out = response.getOutputStream();
        template.write(out);
        template.close();
        out.flush();
        out.close();
    }
}