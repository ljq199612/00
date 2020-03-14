package com.ljq.springboot.healthStatus;


import com.ljq.springboot.healthStatus.helper.PersonStatusHelper;
import lombok.*;

public class Person {

    @Getter @Setter
    private String name;
    @Getter @Setter
    private int age;
    @Getter @Setter
    private String sex;

    @Getter
    private int heartRate;
    @Getter
    private int sleepStatus;

    public Person(String name, int age, String sex){
        this.name = name;
        this.age = age;
        this.sex = sex;
    }


    public void obtainHeartRate() {
        this.heartRate = PersonStatusHelper.measuringHeartRate();
    }

    public void obtianSleepStatus() {
        this.sleepStatus = PersonStatusHelper.sleepStatus();
    }

    @Override
    public String toString() {
        return "[ 姓名：" + name + ", 性别：" + sex + ", 年龄：" + age + ", 心率：" + heartRate + ", 睡眠状况：" + sleepStatus + " ]";
    }
}
