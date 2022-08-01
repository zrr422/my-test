package com.zrr.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @Author zhangrongrong
 * @Date 2022/7/25 14:54
 * @ClassName: Student
 * @Description: 学生表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "student",autoResultMap = true)
public class Student {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JSONField(serializeUsing = ToStringSerializer.class)//解决ID传入前台精度丢失问题
    @TableId(value = "stu_id", type = IdType.ASSIGN_ID)//ID使用雪花算法生成
    private Long stuId;

    @NotNull(message = "学生名不能为空")
    private String stuName;
    private String stuSex;
    private String stuAge;
    private String stuAddress;
    private String stuBirthday;
    private String createTime;
    private String updateTime;
    private String introduce;
    private Integer isDel;

}
