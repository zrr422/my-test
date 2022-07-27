package com.zrr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zrr.entity.Company;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author zhangrongrong
 * @Date 2022/7/25 15:41
 * @ClassName: CompanyMapper
 * @Description: 企业持久层
 */
@Mapper
public interface CompanyMapper extends BaseMapper<Company> {
}
