package com.zrr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zrr.entity.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author zhangrongrong
 * @Date 2022/7/25 15:16
 * @ClassName: StudentMapper
 * @Description: 学生持久层
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
}
