package com.ljq.springboot.healthStatus;


import lombok.*;


public class Device {
    @Getter @Setter
    private int id;
    private Boolean is_idle;   //是否正在被用

    public Device(int id){
        this.id = id;
    }

/*

    public Person ServicePerson(String name, int age, String sex){
        this.is_idle = true;
        return new Person(name, age, sex);
    }

*/
    @Override
    public String toString() {
        return "["+"id: " + id + ", is_idle: " + is_idle + " ]";
    }
}
