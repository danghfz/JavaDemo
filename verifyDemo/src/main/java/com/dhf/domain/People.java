package com.dhf.domain;

import javax.validation.constraints.*;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/07/13 8:50
 */
public class People {
    /**
     * 定义校验规则：在你的实体类中，使用注解来定义校验规则。常用的注解包括@NotNull、@NotBlank、@Size、@Min、@Max等。
     * @Size 是校验集合、数组或字符串长度
     */
    @NotNull(message = "用户名不能为空")
    /*
     *不能为 null
     */
    private String username;
    @NotBlank(message = "密码不能为空")
    /*
     * 不能为null或空字符串
     */
    private String password;
    @NotNull(message = "年龄不能为空")
    @Max(value = 100, message = "年龄不能大于100岁")
    @Min(value = 0, message = "年龄不能小于0岁")
    private int age;
    @NotNull(message = "性别不能为空")
    @Max(value = 1, message = "性别必须为0/1")
    @Min(value = 0, message = "性别必须为0/1")
    private int sex;

    public People() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
