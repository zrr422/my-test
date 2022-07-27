package com.zrr.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zrr.entity.User;
import com.zrr.service.UserService;
import com.zrr.uitl.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author zhangrongrong
 * @Date 2022/7/26 14:19
 * @ClassName: UserController
 * @Description: 用户控制层
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService service;

    /**
     * 用户分页查询
     * @param userName
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/getUser")
    public Object getUser(String userName, Integer page, Integer limit){
        IPage<User> ipage = service.getUser(userName,page,limit);
        List<User> data = ipage.getRecords(); // 当前页的数据
        int total = (int) ipage.getTotal();
        return Result.success("查询成功",data,total);

    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @PostMapping("/insertUser")
    public Result<User> insertUser(@Valid @RequestBody User user) {
        return service.insertUser(user);
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @PostMapping("/updateUser")
    public Result<User> updateUser(@Valid @RequestBody User user) {
        return service.updateUser(user);
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @PostMapping("/deleteUser")
    public Result<User> deleteUser(Integer userId) {
        return service.deleteUser(userId);
    }

}
