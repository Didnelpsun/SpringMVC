// User.java
package org.didnelpsun.entity;

import java.io.Serializable;

public class User implements Serializable{
    private Integer id;
    private String name;
    private String sex;
    private String birthday;
    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString(){
        return "User{" + "id=" + this.id + ",name=" + this.name + ",birthday=" + this.birthday + ",sex=" + this.sex + ",address=" + this.address + "}";
    }

    public User() {
        System.out.println("UserClass");
    }

    public User(Integer id, String name, String sex, String birthday, String address){
        System.out.println("UserClass");
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.address = address;
    }

    public User(String name, String sex, String birthday, String address){
        System.out.println("UserClass");
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.address = address;
    }
}