package com.zxhz.service.loginfo;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONUtil;
import com.zxhz.enums.LogInfoTypeEnum;
import com.zxhz.enums.TableNameEnum;
import com.zxhz.pojo.loginfo.LogInfo;
import com.zxhz.utils.Page;
import com.zxhz.utils.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * 使用 MongoDB 记录日志
 *
 * @author lixingwu
 */
@Service
public class LogInfoService {
    /**
     * <p>方法名称：插入日志.</p>
     * <p>详细描述：.</p>
     * <p>创建时间：2019-10-23 09:54:30</p>
     * <p>创建作者：李兴武</p>
     * <p>修改记录：</p>
     *
     * @param recordId    被修改的记录的id
     * @param tableName   被修改的记录的表名的枚举
     * @param logInfoType 修改类型(审核或普通修改)
     * @param object      该记录的【被修改前】的数据
     * @author "lixingwu"
     */
    @Autowired
    private MongoTemplate mongoTemplate;

    public int insertOneLog(Long recordId, TableNameEnum tableName, LogInfoTypeEnum logInfoType, Object object) {
        LogInfo logInfo = LogInfo.builder()
                .gmtCreate(new Date())
                .gmtModified(new Date())
                .modifiedRecordId(recordId)
                .modifiedTableName(tableName.getMessage())
                .modifiedType(logInfoType.getMessage())
                .rawData(JSONUtil.toJsonStr(object))
                .build();
        LogInfo log = this.mongoTemplate.insert(logInfo);
        if (log.getId() != null) {
            return 1;
        }
        return 0;
    }
}
