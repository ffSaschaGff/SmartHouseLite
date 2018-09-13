package com.example.SmartHouseLite.repossitory;

import com.example.SmartHouseLite.domain.TempSensorValue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TempSensorValueRepository  extends CrudRepository<TempSensorValue, Integer> {
    @Query(value = "select * from temp_sensor_value as T order by T.date desc limit 1", nativeQuery = true)
    List<TempSensorValue> getAllByDate();
}
