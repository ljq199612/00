package com.ljq.springboot.healthStatus;

import com.ljq.springboot.tools.CustomLogger;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.*;

public class Record {
    int flag = 0;
    int hourToMin = 60;
    int dayToHour = 24;

    @Getter @Setter
    private Device device;
    @Getter @Setter
    private Person person;

    public Record(Device device, Person person){
        this.device = device;
        this.person = person;
    }


    @Getter    // [1] 一个小时内每分钟最大的心率列表，本例共24小时
    private List<Integer> maxHeartRateList = new ArrayList<>();
    @Getter
    private List<Integer> minHeartRateList = new ArrayList<>();

    private int maxHeartRate = Integer.MIN_VALUE;
    private int minHeartRate = Integer.MAX_VALUE;

    // [2] 设置当前用户的心率/分钟，
    public void status(){
        // 因缺数据省去每隔一秒执行
        //Thread.sleep(1000);

        flag++;
        person.obtainHeartRate();
        person.obtianSleepStatus();
        // [4] 模拟的每分钟 设备状况和人的状况记录
        String msg = "\n [ " + recordTime(flag*60) + " ] "+ " --> " + person.toString() + " --> " + device.toString();
        // [5] 将每分钟的记录打印到日志 log.txt
        CustomLogger.info(msg);
        //System.out.println(msg);

        {   // [3]以及记录中每小时最大最小的心率/分钟
            maxHeartRate = maxHeartRate > person.getHeartRate() ? maxHeartRate : person.getHeartRate();
            minHeartRate = minHeartRate < person.getHeartRate() ? minHeartRate : person.getHeartRate();

            if (0 == flag % hourToMin) {
                maxHeartRateList.add(maxHeartRate);
                minHeartRateList.add(minHeartRate);
                maxHeartRate = Integer.MIN_VALUE;
                minHeartRate = Integer.MAX_VALUE;

            }
        }

        if(flag == dayToHour* hourToMin){
            return;
        }else{
            status();
        }

    }


    static String recordTime(long second /*时间间隔, 秒为单位 */ ){
        long timeStamp = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(calendar.getTimeInMillis() + second*1000);
    }


}




