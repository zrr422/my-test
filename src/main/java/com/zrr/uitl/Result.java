package com.zrr.uitl;

import com.zrr.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author zhangrongrong
 * @Date 2022/7/25 14:54
 * @ClassName: Result
 * @Description: 返回结果类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    public static final Integer SUCCESS = 0;

    public static final Integer FAILURE = 1;

    private Integer code;

    private String message;

    private Object data;

    private Integer count;

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Result success(String message, Object data,Integer count) {
        return new Result(SUCCESS, message, data,count);
    }

    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result success(String message, Object data) {
        return new Result(SUCCESS, message, data);
    }

    public static Result success(String message, List<Object> data) {
        return new Result(SUCCESS, message, data);
    }

    public static Result failure(String message,Integer count) {
        return new Result(FAILURE, message, null,count);
    }


    public static Result failed(String message) {
        return new Result(FAILURE, message);
    }
}
