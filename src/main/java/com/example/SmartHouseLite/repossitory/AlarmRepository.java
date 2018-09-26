package com.example.SmartHouseLite.repossitory;

import com.example.SmartHouseLite.domain.Alarm;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlarmRepository extends CrudRepository<Alarm, Integer> {
    @Query(value = "select * from alarm as T where T.is_active and T.enabled", nativeQuery = true)
    List<Alarm> getAllActive();
}
