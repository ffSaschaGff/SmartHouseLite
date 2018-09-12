package com.example.SmartHouseLite.repossitory;

import com.example.SmartHouseLite.domain.TempSensorValue;
import org.springframework.data.repository.CrudRepository;

public interface TempSensorValueRepository  extends CrudRepository<TempSensorValue, Integer> {
}
