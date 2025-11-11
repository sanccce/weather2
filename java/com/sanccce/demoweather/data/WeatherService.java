package com.sanccce.demoweather.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.url}")
    private String apiUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getWeather(String city) {
        try {
            String fullUrl = apiUrl + "?q="+city + "&appid="+apiKey + "&units=metric";

            WeatherResponse response = restTemplate.getForObject(fullUrl, WeatherResponse.class);

            return "Weather in " + response.getName() + ": " + response.getMain().getTemp()+ "°C";
        }catch (Exception e){
            return "Error:" + e.getMessage();
        }
    }

    public String getCondition(String city) {
        try {
            String fullUrl = apiUrl + "?q="+city + "&appid="+apiKey + "&units=metric";

            WeatherResponse response = restTemplate.getForObject(fullUrl, WeatherResponse.class);

            double myTem = response.getMain().getTemp();
            if(myTem >= 30){
                return "Today might be Sunny!";
            } else if (myTem >= 15) {
                return "Today might be Cloudy!";
            } else if(myTem < 15 && myTem > 0){
                return "Today could be very cold!";
            }
            else {
                return "Today is Freezing!";
            }

        }catch (Exception e){
            return "Error:" + e.getMessage();
        }
    }
}
