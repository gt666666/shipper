package com.zxhz.controller;
import com.zxhz.controller.abs.AbstractBaseController;
import com.zxhz.utils.JxlsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 类描述：
 * 创建作者：gt
 * 创建日期 ： 2019/11/19
 */
@RestController
@Slf4j   // 如果不想每次都写private  final Logger log = LoggerFactory.getLogger(当前类名.class); 可以用注解@Slf4j  this.log.info("nihao");
public class HelloController  extends AbstractBaseController {


    @GetMapping("/hello")
    public  void hello(HttpServletResponse response){
        Map<String ,Object> map=new HashMap<>();
        map.put("nihao","你好");
        map.put("num",2);
        try {
            JxlsUtil.downWord(response,"car","云A1111",map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
