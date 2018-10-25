package com.ktw.duan.po;

public class GitPo {
    private String name;
    private String sex;
    private Integer age;
    private String hoby;
    private String addr;
    private String tel;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getHoby() {
        return hoby;
    }
    public void setHoby(String hoby) {
        this.hoby = hoby == null ? null : hoby.trim();
    }
    public String getAddr() {
        return addr;
    }
    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }
}