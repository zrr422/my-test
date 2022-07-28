package com.zrr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zrr.entity.Users;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author zhangrongrong
 * @Date 2022/7/25 15:16
 * @ClassName: UserMapper
 * @Description: 用户持久层
 */
@Mapper
public interface UserMapper extends BaseMapper<Users> {
}
