package com.example.SmartHouseLite;

import com.example.SmartHouseLite.domain.RemoteArduino;
import com.example.SmartHouseLite.repossitory.RemoteArduinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Optional;

@Controller
public class RequestController {
    @Autowired
    private RemoteArduinoRepository remoteArduino;

    @GetMapping("index")
    public String index(Map<String, Object> model) {
        model.put("tempeture", "");
        model.put("pressure", "");
        model.put("humidity", "");
        model.put("hour", "");
        model.put("minute", "");
        Iterable<RemoteArduino> arduinos = remoteArduino.findAll();
        model.put("arduinos", arduinos);
        return "index";
    }

    @GetMapping("editSwitch")
    public String editRemoteGet(Map<String, Object> model) {
        model.put("name", "");
        model.put("address", "");
        model.put("id", "null");
        Iterable<RemoteArduino> arduinos = remoteArduino.findAll();
        model.put("arduinos", arduinos);
        return "editSwitch";
    }

    @PostMapping("editSwitch")
    public String editRemotePost(@RequestParam(value = "id", defaultValue = "null") String id, Map<String, Object> model) {
        if (!id.equals("null")) {
            Optional<RemoteArduino> arduinos = remoteArduino.findById(Integer.parseInt(id));
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
        Iterable<RemoteArduino> arduinos = remoteArduino.findAll();
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
            remoteArduino.save(arduino);
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
        remoteArduino.save(arduino);
        return new ModelAndView("redirect:/index", model);
    }

    @PostMapping("deleteSwitch")
    public ModelAndView deleteSwitch(@RequestParam(value = "id") String id, ModelMap model) {
        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        if (!id.equals("null")) {
            remoteArduino.deleteById(Integer.parseInt(id));
        }
        return new ModelAndView("redirect:/index", model);
    }

    @PostMapping("turnSwitch")
    public ModelAndView turnSwitch(@RequestParam(value = "id") String id, ModelMap model) {
        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        if (!id.equals("null")) {
            Optional<RemoteArduino> arduino = remoteArduino.findById(Integer.parseInt(id));
            if (arduino.isPresent()) {
                arduino.get().turnSwitch();
            }
        }
        return new ModelAndView("redirect:/index", model);
    }

}