package com.slcp.devops.pojo;

/**
 * @author: Slcp
 * @date: 2020/10/29 17:13
 * @code: 一生的挚爱
 * @description: 注册用户
 */
public class RegisterUser {
    private Integer id;
    private String username;
    private String password;
    private String phone;
    private String code;
    private String email;

    private Integer flag;//0 表示没有激活 1 表示已经激活

    @Override
    public String toString() {
        return "RegisterUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", code='" + code + '\'' +
                ", email='" + email + '\'' +
                ", flag=" + flag +
                '}';
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RegisterUser(Integer id, String username, String password, String phone, String code, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.code = code;
        this.email = email;
    }

    public RegisterUser() {
    }
}
