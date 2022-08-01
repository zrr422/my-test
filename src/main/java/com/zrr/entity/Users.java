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
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Author zhangrongrong
 * @Date 2022/7/25 14:54
 * @ClassName: User
 * @Description: 用户表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "users",autoResultMap = true)
public class Users {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JSONField(serializeUsing = ToStringSerializer.class)//解决ID传入前台精度丢失问题
    @TableId(value = "user_id", type = IdType.ASSIGN_ID)//ID使用雪花算法生成
    private Long userId;

    @NotNull(message = "学生名不能为空")
    private String userName;

    @NotEmpty(message = "手机号不能为空")
    @Pattern(regexp = "^1\\d{10}$", message = "手机号格式错误")
    private String userPhone;

    @NotEmpty(message = "邮箱不能为空")
    @Email(regexp = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$",message = "邮箱格式错误")
    private String userEmail;
    private String userAddress;
    private String createTime;
    private String updateTime;
    private String userAdvice;
    private Integer isDel;
    private String remark;
}
