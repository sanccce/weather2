package com.sanccce.demoweather.data;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherController {

    private WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }
    @GetMapping("/login")
    public String showLogin(){
        return "login"; // show form
    }
    @GetMapping("/")
    public String home(Model model){
        return "home";
    }
    @PostMapping("/weather")
    public String getWeather(@RequestParam String city, Model model) throws InterruptedException {

        // Tiny delay so you can admire the spinner
        Thread.sleep(800);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (authentication != null)? authentication.getName(): "Guest";

        model.addAttribute("username", username);
        model.addAttribute("result", weatherService.getWeather(city));
        model.addAttribute("condition", weatherService.getCondition(city));
        model.addAttribute("city", city);

        return "home"; // show result in form
    }
}
