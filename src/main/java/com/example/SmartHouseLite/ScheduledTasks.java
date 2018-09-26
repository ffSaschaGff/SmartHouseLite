package com.example.SmartHouseLite;

import com.example.SmartHouseLite.domain.Alarm;
import com.example.SmartHouseLite.domain.TempSensorValue;
import com.example.SmartHouseLite.repossitory.AlarmRepository;
import com.example.SmartHouseLite.repossitory.TempSensorValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
        synchronized (Application.class) {
            Iterable<Alarm> alarms = alarmRepository.findAll();
            Date date = new Date();
            SimpleDateFormat formatText = new SimpleDateFormat("dd.MM.yyyy");
            SimpleDateFormat formatMin = new SimpleDateFormat("m");
            SimpleDateFormat formatHour = new SimpleDateFormat("H");
            for (Alarm alarm: alarms) {
                if (alarm.getEnabled() && !alarm.getLastDay().equals(formatText.format(date))) {
                    if (alarm.getHour() < Integer.parseInt(formatHour.format(date)) ||
                            (alarm.getHour() == Integer.parseInt(formatHour.format(date)) && alarm.getMinute() <= Integer.parseInt(formatMin.format(date)))) {
                        alarm.setActive(true);
                        alarm.setLastDay(formatText.format(date));
                        alarmRepository.save(alarm);
                        makeAlarm();
                    }
                }
            }
        }
    }

    private void makeAlarm() {
        Iterable<Alarm> alarms = null;
        int i = 0;
        do {
            i++;
            System.out.println("alarm!"+i);
            alarms = alarmRepository.getAllActive();
        } while (!((List<Alarm>) alarms).isEmpty());
    }
}
