package {{@controllerPackagePath@}};

import org.springframework.web.bind.annotation.*;
import com.zxhz.controller.abs.AbstractBaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zxhz.enums.ResultEnum ;
import com.zxhz.utils.Page;
import com.github.pagehelper.PageHelper;
import com.zxhz.utils.PaginationContext;
import com.zxhz.pojo.CommonResult;
import {{@pojoPath@}};
import {{@servicePackagePath@}}.{{@serviceName@}};
/**
 * {{@controllerTitle@}}
 *
 * @author {{@author@}}
 * @date：{{@date@}}
 */
@RestController
@RequestMapping("/{{@folderName@}}")
@Api(description = "{{@controllerTitle@}}", tags = {"{{@controllerName@}}"})
public class {{@controllerName@}} extends AbstractBaseController {

    private {{@serviceName@}} {{@serviceNameMin@}};
    @Autowired
    public {{@controllerName@}}({{@serviceName@}} record) {
        this.{{@serviceNameMin@}} = record;
    }

    @ApiOperation(value = "添加记录", notes = "（{{@author@}}）添加记录")
    @PostMapping("/insertSelective")
    public CommonResult insertSelective({{@pojoName@}} record) {
        this.{{@serviceNameMin@}}.insertSelective(record);
        return resultWrapper(ResultEnum.INSERT_SUCCESS);
    }

    @ApiOperation(value = "删除记录", notes = "（{{@author@}}）根据记录id删除记录")
    @PostMapping("/deleteById")
    public  CommonResult deleteById( Long id) {
        int i=this.{{@serviceNameMin@}}.deleteById(id);
        if(i==1){
        return resultWrapper(ResultEnum.DELETE_SUCCESS);
        }
        return  resultWrapper(ResultEnum.DELETE_ERROR);
    }

    @ApiOperation(value = "修改记录", notes = "（{{@author@}}）修改记录")
    @PostMapping("/updateById")
    public CommonResult updateById({{@pojoName@}} record) {
        int i= this.{{@serviceNameMin@}}.updateById(record);
        if (i==1) {
        return resultWrapper(ResultEnum.UPDATE_SUCCESS);
        }
        return  resultWrapper(ResultEnum.UPDATE_ERROR);
    }

    @ApiOperation(value = "获取记录信息", notes = "（{{@author@}}）根据id获取记录信息")
    @GetMapping("/selectById")
    public CommonResult selectById( Long id) {
        return resultDataWrapper(ResultEnum.SELECT_SUCCESS,this.{{@serviceNameMin@}}.selectById(id));
    }

    @ApiOperation(value = "获取全部记录", notes = "（{{@author@}}）获取全部记录")
    @GetMapping("/listAll")
    public CommonResult listAll() {
        return resultDataWrapper(ResultEnum.SELECT_SUCCESS,this.{{@serviceNameMin@}}.list(null));
    }

    @GetMapping("/findByQuery")
    public Page<{{@pojoName@}}> findByQuery({{@pojoName@}} record) {
        PageHelper.startPage(PaginationContext.getPageNum(), PaginationContext.getPageSize());
        return new Page<{{@pojoName@}}>(this.{{@serviceNameMin@}}.findByQuery(record));

    }
}

