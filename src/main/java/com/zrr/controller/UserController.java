package com.zrr.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zrr.entity.Users;
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
    public Object getUser(String userName,@RequestParam Integer page,@RequestParam Integer limit){
        IPage<Users> ipage = service.getUser(userName,page,limit);
        List<Users> data = ipage.getRecords(); // 当前页的数据
        int total = (int) ipage.getTotal();
        return Result.success("查询成功",data,total);

    }

    /**
     * 根据用户ID查看用户详细信息
     * @param userId
     * @return
     */
    @GetMapping("/getUserById")
    public Result<Users> getUserById(Long userId) {
        return service.getUserById(userId);
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @PostMapping("/insertUser")
    public Result<Users> insertUser(@Valid @RequestBody Users user) {
        return service.insertUser(user);
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @PostMapping("/updateUser")
    public Result<Users> updateUser(@Valid @RequestBody Users user) {
        return service.updateUser(user);
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @PostMapping("/deleteUser")
    public Result<Users> deleteUser(Long userId) {
        return service.deleteUser(userId);
    }

}
