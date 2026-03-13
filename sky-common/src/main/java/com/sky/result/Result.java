package com.sky.result;

import lombok.Data;

import java.io.Serializable;

/**
 * 后端统一返回结果
 * 统一结果封装类
 * 核心作用：标准化后端接口返回给前端的数据格式
 * @param <T>
 */
@Data
// implements Serializable： 实现序列化接口
// => 意味着该类的对象可以被转换为字节流，以便在网络中传输或者存入缓存（如Redis）中
public class Result<T> implements Serializable {

    private Integer code; // 业务状态码：通常约定 1 表示成功，0和其它数字为失败  => 让前端能快速判断该请求是否处理成功，而不仅仅是依赖HTTP状态码
    private String msg; // 错误信息（提示信息） => 当后端报错，如“密码错误”的时候，会把具体的原因存放在这里，展示给前端看。
    private T data; // 泛型数据载荷 => 这是真正要传给前端的业务数据（如用户信息，菜品列表等），使用泛型 <T> 是为了让它可以装下任何类型的数据

    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.code = 1;
        return result;
    }

    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<>();
        result.data = object;
        result.code = 1;
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.msg = msg;
        result.code = 0;
        return result;
    }

}
