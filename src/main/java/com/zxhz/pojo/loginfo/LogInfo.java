package com.zxhz.pojo.loginfo;
import lombok.Builder;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
@Data
@Builder
public class LogInfo implements Serializable {
    private String id;
    private Date gmtCreate;
    private Date gmtModified;
    private String writerName;
    private Boolean isDelete;
    private Long modifiedRecordId;
    private String modifiedTableName;
    private String modifiedType;
    private String rawData;
}