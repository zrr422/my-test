package com.zrr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zrr.entity.AuditLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author zhangrongrong
 * @Date 2022/7/26 15:32
 * @ClassName: AuditLogMapper
 * @Description: 审核日志持久层
 */
@Mapper
public interface AuditLogMapper extends BaseMapper<AuditLog> {
}
