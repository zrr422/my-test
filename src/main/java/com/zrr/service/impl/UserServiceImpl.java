package com.zrr.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zrr.entity.Users;
import com.zrr.mapper.UserMapper;
import com.zrr.service.UserService;
import com.zrr.uitl.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author zhangrongrong
 * @Date 2022/7/26 13:54
 * @ClassName: UserServiceImpl
 * @Description: 用户实现层实现类
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, Users> implements UserService {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date currentTime = new DateTime(System.currentTimeMillis());

    @Resource
    UserMapper mapper;

    /**
     * 用户分页查询
     * @param userName
     * @param page
     * @param limit
     * @return
     */
    @Override
    public IPage<Users> getUser(String userName, Integer page, Integer limit) {
        Page<Users> p = new Page<>(page, limit); //page,limit
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        if (!StringUtils.isEmpty(userName)) {
            queryWrapper.like("user_name", userName);
        }

        queryWrapper.orderByDesc("create_time");
        return mapper.selectPage(p, queryWrapper);
    }

    @Override
    public Result<Users> getUserById(Long userId) {
        Users data = mapper.selectById(userId);
        return Result.success("查询成功",data);
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @Override
    public Result<Users> insertUser(Users user) {
        user.setCreateTime(formatter.format(currentTime));
        user.setIsDel(0);
        mapper.insert(user);
        return Result.success("添加成功",user);
    }

    /**
     * 根据ID修改用户
     * @param user
     * @return
     */
    @Override
    public Result<Users> updateUser(Users user) {
        user.setUpdateTime(formatter.format(currentTime));
        mapper.updateById(user);
        return Result.success("修改成功",user);
    }

    /**
     * 根据ID删除用户
     * @param userId
     * @return
     */
    @Override
    public Result<Users> deleteUser(Integer userId) {
        mapper.deleteById(userId);
        return Result.success("删除成功",userId);
    }
}
