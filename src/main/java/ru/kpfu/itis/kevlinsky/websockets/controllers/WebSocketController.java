package ru.kpfu.itis.kevlinsky.websockets.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebSocketController {
    @GetMapping("/index")
    public String getIndexPage(){
        return "index";
    }
}
