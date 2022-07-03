package com.slcp.devops.pojo;

/**
 * @author: Slcp
 * @date: 2020/11/17 21:27
 * @code: 一生的挚爱
 * @description:
 */
public class QQInfo {

    private Long id;

    private String name;

    private String avatar;

    //浏览器
    private String browser;
    //系统
    private String system;

    public QQInfo() {
    }

    public QQInfo(Long id, String name, String avatar, String browser, String system) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.browser = browser;
        this.system = system;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    @Override
    public String toString() {
        return "QQInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", browser='" + browser + '\'' +
                ", system='" + system + '\'' +
                '}';
    }
}
