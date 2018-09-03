package com.example.SmartHouseLite;

import com.example.SmartHouseLite.domain.RemoteArduino;
import com.example.SmartHouseLite.repossitory.RemoteArduinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingController {
    @Autowired
    private RemoteArduinoRepository remoteArduino;

    @GetMapping("index")
    public String index(@RequestParam(defaultValue="World") String name, Map<String, Object> model) {
        model.put("name", name);
        Iterable<RemoteArduino> arduinos = remoteArduino.findAll();
        model.put("arduinos", arduinos);
        return "greeting";
    }

    @GetMapping("addNewRemote")
    public String addNewRemote(@RequestParam(defaultValue="ddd") String name, Map<String, Object> model) {
        model.put("name", name);
        Iterable<RemoteArduino> arduinos = remoteArduino.findAll();
        model.put("arduinos", arduinos);
        return "greeting";
    }

}