package com.zrr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zrr.entity.Users;
import com.zrr.uitl.Result;

/**
 * @Author zhangrongrong
 * @Date 2022/7/26 13:53
 * @ClassName: UserService
 * @Description: 用户实现层接口
 */
public interface UserService extends IService<Users> {
    /**
     * 用户分页查询
     * @param userName
     * @param page
     * @param limit
     * @return
     */
    IPage<Users> getUser(String userName, Integer page, Integer limit);

    /**
     *用户添加
     * @param user
     * @return
     */
    Result<Users> insertUser(Users user);

    /**
     *用户修改
     * @param user
     * @return
     */
    Result<Users> updateUser(Users user);

    /**
     *用户删除
     * @param userId
     * @return
     */
    Result<Users> deleteUser(Integer userId);
}
