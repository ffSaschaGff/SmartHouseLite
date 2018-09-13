package com.example.SmartHouseLite;

import com.example.SmartHouseLite.domain.TempSensorValue;
import com.example.SmartHouseLite.repossitory.AlarmRepository;
import com.example.SmartHouseLite.repossitory.TempSensorValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Autowired
    private TempSensorValueRepository tempSensorValueRepository;
    @Autowired
    private AlarmRepository alarmRepository;

    @Scheduled(fixedRate = 60000)//900000)
    public void getSensorData() {
        WebResouces resouces = new WebResouces();
        TempSensorValue sensorValue = resouces.getTempSensorVallue();
        if (sensorValue != null) {
            tempSensorValueRepository.save(sensorValue);
        }
    }

    @Scheduled(fixedRate = 5000)
    public void checkAlarm() {
        //
    }
}
