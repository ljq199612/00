package com.ljq.springboot.healthStatus;

//import org.junit.Test;
import com.ljq.springboot.tools.CustomLogger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)

@SpringBootTest

public class RecordTest {

    int deviceID = 5;

    Record record = new Record(new Device(deviceID), new Person("ljq", 23, "男"));

    @Test
    public void main() {
        CustomLogger.startMethodLog();

        record.status();
        record.getMaxHeartRateList();
        record.getMinHeartRateList();

        CustomLogger.endMethodLog();
    }

}
