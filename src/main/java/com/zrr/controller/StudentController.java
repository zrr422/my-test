package com.zrr.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zrr.entity.Student;
import com.zrr.service.StudentService;
import com.zrr.uitl.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author zhangrongrong
 * @Date 2022/7/25 16:09
 * @ClassName: StudentController
 * @Description: TODO
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Resource
    StudentService service;

    /**
     * 学生分页查询
     * @param stuName
     * @param minBirthday
     * @param maxBirthday
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/getStudent")
    public Object getStudent(String stuName, String minBirthday, String maxBirthday,@RequestParam Integer page,@RequestParam Integer limit){
        IPage<Student> ipage = service.getStudent(stuName,minBirthday,maxBirthday,page,limit);
        List<Student> data = ipage.getRecords(); // 当前页的数据
        int total = (int) ipage.getTotal();
        return Result.success("查询成功",data,total);

    }

    /**
     * 添加学生
     * @param stu
     * @return
     */
    @PostMapping("/insertStu")
    public Result<Student> insertStu(@Valid @RequestBody Student stu) {
        return service.insertStu(stu);
    }

    /**
     * 修改学生
     * @param stu
     * @return
     */
    @PostMapping("/updateStu")
    public Result<Student> updateStu(@Valid @RequestBody Student stu) {
        return service.updateStu(stu);
    }

    /**
     * 删除学生
     * @param stuId
     * @return
     */
    @PostMapping("/deleteStu")
    public Result<Student> deleteStu(Integer stuId) {
        return service.deleteStu(stuId);
    }
}
