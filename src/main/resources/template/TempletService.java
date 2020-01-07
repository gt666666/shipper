package {{@servicePackagePath@}};

import cn.hutool.core.date.DateTime;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zxhz.config.global.FailException;
import com.zxhz.enums.LogInfoTypeEnum;
import com.zxhz.enums.ResultEnum;
import com.zxhz.enums.TableNameEnum;
import com.zxhz.service.loginfo.LogInfoService;
import com.zxhz.utils.Tools;
import {{@mapperPath@}};
import {{@pojoPath@}};
import java.util.List;

/**
 * {{@serviceTitle@}}
 *
 * @author {{@author@}}
 */
@Service
public class {{@serviceName@}} {
    private LogInfoService logInfoService;
    private {{@mapperName@}} {{@mapperNameMin@}};

    @Autowired
    public {{@serviceName@}}(LogInfoService logInfoService,{{@mapperName@}} {{@mapperNameMin@}}) {
        this.logInfoService = logInfoService;
        this.{{@mapperNameMin@}} = {{@mapperNameMin@}};
    }

    /**
     * <p> 方法描述：添加记录（要修改xml对应的方法，让其返回添加记录的id）. </p>
     * <p> 创建时间：{{@date@}} </p>
     * <p> 创建作者：{{@author@}} </p>
     * <p> 修改作者： </p>
     *
     * @param record 记录对象
     */
    @Transactional(rollbackFor = Exception.class)
    public void insertSelective({{@pojoName@}} record) {
        record.setGmtCreate(new DateTime());
        record.setIsDelete(false);
        this.{{@mapperNameMin@}}.insertSelective(record);
        logInfoService.insertOneLog(record.getId(), TableNameEnum.{{@tableNameEnum@}}, LogInfoTypeEnum.INSERT, record);
    }

    /**
     * <p> 方法描述：通过记录id删除记录. </p>
     * <p> 创建时间：{{@date@}} </p>
     * <p> 创建作者：{{@author@}} </p>
     * <p> 修改作者： </p>
     *
     * @param id 记录id
     */
    @Transactional(rollbackFor = Exception.class)
    public int deleteById(Long id) {
        {{@pojoName@}} record =this.selectById(id);
        if (null == record) {
            throw new FailException(ResultEnum.RECORD_IS_NULL);
        }
        logInfoService.insertOneLog(id, TableNameEnum.{{@tableNameEnum@}}, LogInfoTypeEnum.DELETE, record);
        return  this.{{@mapperNameMin@}}.deleteByPrimaryKey(id);
    }

    /**
     * <p> 方法描述：更新记录. </p>
     * <p> 创建时间：{{@date@}} </p>
     * <p> 创建作者：{{@author@}} </p>
     * <p> 修改作者： </p>
     *
     * @param record 记录对象
     */
    @Transactional(rollbackFor = Exception.class)
    public int updateById({{@pojoName@}} record) {
        // 先查询，再次修改。记录必须有id才能修改
        if (Tools.isBlank(record.getId())) {
            throw new FailException(ResultEnum.RECORD_IS_NULL);
        }
        // 未查询到记录的不能修改
        {{@pojoName@}} select = this.selectById(record.getId());
        if (null == select) {
            throw new FailException(ResultEnum.RECORD_IS_NULL);
        }
        // 修改成功后再保存日志
        record.setGmtModified(new DateTime());
        if (this.{{@mapperNameMin@}}.updateByPrimaryKeySelective(record) > 0) {
            this.logInfoService.insertOneLog(record.getId(), TableNameEnum.{{@tableNameEnum@}}, LogInfoTypeEnum.UPDATE, select);
            return 1;
        }
        throw new FailException(ResultEnum.SAVE_FAIL);
    }

    /**
     * <p> 方法描述：根据记录id查询记录. </p>
     * <p> 创建时间：{{@date@}} </p>
     * <p> 创建作者：{{@author@}} </p>
     * <p> 修改作者： </p>
     *
     * @param id 记录id
     */
    public {{@pojoName@}} selectById(Long id) {
        return this.{{@mapperNameMin@}}.selectByPrimaryKey(id);
    }

    /**
     * <p> 方法描述：根据 查询对象 查询出单条记录. </p>
     * <p> 创建时间：{{@date@}} </p>
     * <p> 创建作者：{{@author@}} </p>
     * <p> 修改作者： </p>
     *
     * @param record 查询对象
     */
    public {{@pojoName@}} find({{@pojoName@}} record) {
        return this.{{@mapperNameMin@}}.find(record);
    }

    /**
     * <p> 方法描述：根据 查询对象 查询出列表记录. </p>
     * <p> 创建时间：{{@date@}} </p>
     * <p> 创建作者：{{@author@}} </p>
     * <p> 修改作者： </p>
     *
     * @param record 查询对象
     */
    public List<{{@pojoName@}}> list({{@pojoName@}} record) {
        return this.{{@mapperNameMin@}}.list(record);
    }

    /**
     * <p> 方法描述：根据 查询对象 分页查询数据. </p>
     * <p> 创建时间：{{@date@}} </p>
     * <p> 创建作者：{{@author@}} </p>
     * <p> 修改作者： </p>
     *
     * @param record 查询对象
     */
    public List<{{@pojoName@}}> findByQuery({{@pojoName@}} record) {

        return  this.{{@mapperNameMin@}}.list(record);
    }

}
