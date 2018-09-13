package com.example.SmartHouseLite.repossitory;

import com.example.SmartHouseLite.domain.Alarm;
import org.springframework.data.repository.CrudRepository;

public interface AlarmRepository extends CrudRepository<Alarm, Integer> {
}
