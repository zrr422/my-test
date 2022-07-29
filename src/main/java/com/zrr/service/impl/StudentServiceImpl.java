package com.zrr.service.impl;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zrr.entity.Student;
import com.zrr.mapper.StudentMapper;
import com.zrr.service.StudentService;
import com.zrr.uitl.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author zhangrongrong
 * @Date 2022/7/25 15:57
 * @ClassName: StudentServiceIMpl
 * @Description: 学生实现层实现类
 */
@Service
@Slf4j
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date currentTime = new DateTime(System.currentTimeMillis());

    @Resource
    StudentMapper mapper;

    /**
     * 学生分页查询
     * @param stuName
     * @param minBirthday
     * @param maxBirthday
     * @param page
     * @param limit
     * @return
     */
    @Override
    public IPage<Student> getStudent(String stuName, String minBirthday, String maxBirthday, Integer page, Integer limit) {
        Page<Student> p = new Page<>(page, limit); //page,limit
        QueryWrapper<Student> queryWrapper = new QueryWrapper<Student>();
        if (!StringUtils.isEmpty(stuName)) {
            queryWrapper.like("stu_name", stuName);
        }
        if (!StringUtils.isEmpty(minBirthday)) {
            queryWrapper.gt("stu_birthday", minBirthday);
        }
        if (!StringUtils.isEmpty(maxBirthday)) {
            queryWrapper.lt("stu_birthday", maxBirthday);
        }

        queryWrapper.orderByDesc("create_time");
        log.info("Student stu :{}", JSONObject.toJSONString(queryWrapper));
        return mapper.selectPage(p, queryWrapper);
    }

    @Override
    public Result<Student> getStuById(Long stuId) {
        Student byId = mapper.selectById(stuId);
        return Result.success("查询成功",byId);
    }

    /**
     * 添加学生
     * @param stu
     * @return
     */
    @Override
    public Result<Student> insertStu(Student stu) {
        stu.setCreateTime(formatter.format(currentTime));
        stu.setIsDel(0);
        mapper.insert(stu);
        return Result.success("添加成功",stu);
    }

    /**
     * 根据ID修改学生
     * @param stu
     * @return
     */
    @Override
    public Result<Student> updateStu(Student stu) {
        stu.setUpdateTime(formatter.format(currentTime));
        mapper.updateById(stu);
        return Result.success("修改成功",stu);
    }

    /**
     * 根据ID删除学生
     * @param stuId
     * @return
     */
    @Override
    public Result<Student> deleteStu(Integer stuId) {
        mapper.deleteById(stuId);
        return Result.success("删除成功",stuId);
    }
}
