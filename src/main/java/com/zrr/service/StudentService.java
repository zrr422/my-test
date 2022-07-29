package com.zrr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zrr.entity.Student;
import com.zrr.uitl.Result;

/**
 * @Author zhangrongrong
 * @Date 2022/7/25 15:42
 * @ClassName: StudentService
 * @Description: 学生实现层接口
 */
public interface StudentService extends IService<Student> {
    /**
     *学生分页查询
     * @param stuName
     * @param minBirthday
     * @param maxBirthday
     * @param page
     * @param limit
     * @return
     */
    IPage<Student> getStudent(String stuName,String minBirthday,String maxBirthday, Integer page, Integer limit);


    Result<Student> getStuById(Long stuId);

    /**
     *学生添加
     * @param stu
     * @return
     */
    Result<Student> insertStu(Student stu);

    /**
     *学生修改
     * @param stu
     * @return
     */
    Result<Student> updateStu(Student stu);

    /**
     *学生删除
     * @param stuId
     * @return
     */
    Result<Student> deleteStu(Integer stuId);
}
