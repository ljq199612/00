package com.ljq.springboot.healthStatus.helper;

import java.util.List;

public class PersonStatusHelper {

    public static int measuringHeartRate(){
        // FIXME 心率的求解
        // 用 40-140 之间的随机数来模拟
        return (int)(Math.random()*100)+40;
    }

    public static int sleepStatus(){
        // FIXME 睡眠状态的求解
        // 随机模拟
        //SleepCondition.WAKE.ordinal();
        return (int)(Math.random()*2);
    }


    public static int maxHeartRatePerHour(List heartRateList){
        return  1;
    }

}

//enum SleepCondition{WAKE, SLEEP}