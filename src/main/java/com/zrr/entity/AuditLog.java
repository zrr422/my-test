package com.zrr.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Author zhangrongrong
 * @Date 2022/7/25 15:51
 * @ClassName: AuditLog
 * @Description: 审核日志
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "audit_log",autoResultMap = true)
public class AuditLog {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JSONField(serializeUsing = ToStringSerializer.class)//解决ID传入前台精度丢失问题
    @TableId(value = "audit_id", type = IdType.ASSIGN_ID)//ID使用雪花算法生成
    private Long auditId;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JSONField(serializeUsing = ToStringSerializer.class)//解决ID传入前台精度丢失问题
    private Long companyId;

    private String auditCompany;
    private String auditMan;
    private String auditTime;
    private Integer auditStatus;
    private String remark;

}
