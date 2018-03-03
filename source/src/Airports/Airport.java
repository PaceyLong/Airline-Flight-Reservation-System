package Airports;

import java.util.ArrayList;

/**
 * Airport Object class.
 * Weather: Each time a client requests the current airport information, the system will report the next field pair.
 *      When airport information requests have reported all field pairs for an airport, the system will reset
 *      to the first field pair and report it in the next client weather request for that city.
 * Airport data object. Contains:
 *      -Name
 *      -Code
 *      -Weather
 *      -Temperature
 *      -DelayTime
 */
public class Airport {
    /* attributes */
    private String name;
    private String code;
    private ArrayList<String> weather;
    private int weatherIndex = 0;
    private String temperature;
    private int delayTime;

    public Airport(String name, String code, ArrayList<String> weather, String temperature, int delayTime){
        this.name = name;
        this.code = code;
        this.weather = weather;
        this.temperature = temperature;
        this.delayTime = delayTime;
    }

    /**
     * Get weather using weatherindex. Increment on each request. Reset after reaching size of array
     * @return weather string
     */
    private String getWeather(){
        String currWeather = "";
        if (weather.size() > 0){ // as long as weather isn't empty
            currWeather = weather.get(weatherIndex);
            weatherIndex = (weatherIndex < weather.size())? weatherIndex + 1 : 0;
        }
        return currWeather;
    }

    /**
     * toString(): airport name, weather, temperature, delay (minutes)
     * @return printout
     */
    @Override
    public String toString(){
        return name + "," + getWeather() + "," + temperature + "," + Integer.toString(delayTime);
    }
}
