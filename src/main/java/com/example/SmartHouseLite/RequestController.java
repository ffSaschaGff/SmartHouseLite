package com.example.SmartHouseLite;

import com.example.SmartHouseLite.domain.Alarm;
import com.example.SmartHouseLite.domain.RemoteArduino;
import com.example.SmartHouseLite.domain.RemoteSonoff;
import com.example.SmartHouseLite.repossitory.AlarmRepository;
import com.example.SmartHouseLite.repossitory.RemoteArduinoRepository;
import com.example.SmartHouseLite.repossitory.RemoteSonoffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Controller
public class RequestController {
    @Autowired
    private RemoteArduinoRepository remoteArduinoRepository;
    @Autowired
    private RemoteSonoffRepository remoteSonoffRepository;
    @Autowired
    private AlarmRepository alarmRepository;

    @GetMapping("index")
    public String index(Map<String, Object> model) {
        Iterable<Alarm> alarms = alarmRepository.findAll();
        model.put("hour", "");
        model.put("minute", "");
        model.put("alarmIsCheced","");
        for (Alarm alarm: alarms) {
            model.put("hour", alarm.getHour());
            model.put("minute", alarm.getMinute());
            model.put("alarmIsCheced", alarm.getEnabled() ? "checked": "");
        }
        model.put("tempeture", "");
        model.put("pressure", "");
        model.put("humidity", "");
        model.put("tempetureOut", "");
        model.put("pressureOut", "");
        model.put("humidityOut", "");

        Iterable<RemoteArduino> arduinos = remoteArduinoRepository.findAll();
        model.put("arduinos", arduinos);
        Iterable<RemoteSonoff> sonoffs = remoteSonoffRepository.findAll();
        model.put("sonoffs", sonoffs);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        model.put("currentTime",format.format(date));
        return "index";
    }

    @GetMapping("editSwitch")
    public String editRemoteGet(Map<String, Object> model) {
        model.put("name", "");
        model.put("address", "");
        model.put("id", "null");
        Iterable<RemoteArduino> arduinos = remoteArduinoRepository.findAll();
        model.put("arduinos", arduinos);
        return "editSwitch";
    }

    @PostMapping("editSwitch")
    public String editRemotePost(@RequestParam(value = "id", defaultValue = "null") String id, Map<String, Object> model) {
        if (!id.equals("null")) {
            Optional<RemoteArduino> arduinos = remoteArduinoRepository.findById(Integer.parseInt(id));
            if (arduinos.isPresent()) {
                model.put("name", arduinos.get().getName());
                model.put("address", arduinos.get().getAdress());
            }
        }
        if (!model.containsKey("name") || !model.containsKey("address")) {
            model.put("name", "");
            model.put("address", "");
        }
        model.put("id", id);
        Iterable<RemoteArduino> arduinos = remoteArduinoRepository.findAll();
        model.put("arduinos", arduinos);
        return "editSwitch";
    }

    @PostMapping("editSwitchSave")
    public ModelAndView editRemoteSave(@RequestParam(value = "id") String id,
                                       @RequestParam(value = "name") String name,
                                       @RequestParam(value = "address") String address, ModelMap model) {
        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        if (!id.equals("null")) {
            RemoteArduino arduino = new RemoteArduino(Integer.parseInt(id), name, address);
            remoteArduinoRepository.save(arduino);
        }
        return new ModelAndView("redirect:/index", model);
    }

    @GetMapping("addNew")
    public  String addNewGet(Map<String, Object> model) {
        return "addNew";
    }

    @PostMapping("addNew")
    public ModelAndView addNewPost(@RequestParam(value = "name") String name,
                                       @RequestParam(value = "address") String address, ModelMap model) {
        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        RemoteArduino arduino = new RemoteArduino(name, address);
        remoteArduinoRepository.save(arduino);
        return new ModelAndView("redirect:/index", model);
    }

    @PostMapping("deleteSwitch")
    public ModelAndView deleteSwitch(@RequestParam(value = "id") String id, ModelMap model) {
        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        if (!id.equals("null")) {
            remoteArduinoRepository.deleteById(Integer.parseInt(id));
        }
        return new ModelAndView("redirect:/index", model);
    }

    @PostMapping("turnSwitch")
    public ModelAndView turnSwitch(@RequestParam(value = "id") String id, ModelMap model) {
        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        if (!id.equals("null")) {
            Optional<RemoteArduino> arduino = remoteArduinoRepository.findById(Integer.parseInt(id));
            arduino.ifPresent(RemoteArduino::turnSwitch);
        }
        return new ModelAndView("redirect:/index", model);
    }

    @PostMapping("setAlarm")
    public ModelAndView setAlarm(@RequestParam(value = "hour") String sHour,
                                 @RequestParam(value = "minute") String sMinute,
                                 @RequestParam(value = "isOn", defaultValue = "off") String sIsOn,
                                 ModelMap model) {
        Alarm alarm = new Alarm(Integer.parseInt(sMinute),Integer.parseInt(sHour),sIsOn.equals("on"));
        alarm.setLastDay("");
        alarmRepository.save(alarm);
        return new ModelAndView("redirect:/index", model);
    }

}