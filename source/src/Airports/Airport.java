package Airports;
import java.util.ArrayList;

/**
 * @author Joshua Ehling, Ethan Della Posta
 *
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
    private ArrayList<Weather> weather;
    private int weatherIndex = 0;
    private int minConnectionTime;
    private int delayTime;

    /**
     * Constructor
     * @param name - name of airport
     * @param code - unique airport code
     * @param weather - weather array
     * @param delayTime - delay time (integer value)
     */
    public Airport(String name, String code, ArrayList<Weather> weather, int delayTime, int minConnectionTime){
        this.name = name;
        this.code = code;
        this.weather = weather;
        this.delayTime = delayTime;
        this.minConnectionTime = minConnectionTime;

    }

    /**
     * Get weather using weatherindex. Increment on each request. Reset after reaching size of array
     * @return weather string
     */
    private Weather getWeather(){
        Weather currWeather = null;
        if (weather.size() > 0){ // as long as weather isn't empty
            currWeather = weather.get(weatherIndex);
            weatherIndex = (weatherIndex < weather.size() -1)? weatherIndex + 1 : 0;
        }
        return currWeather;
    }

    /**
     * add new weather object to arraylist
     */
    public void addWeather(Weather weather){
        this.weather.add(weather);
    }

    /**
     * Set minimum connection time
     */
    public void setMinConnectionTime(int min){
        this.minConnectionTime = min;
    }

    /**
     * accessor for connection time
     * @return minConnectionTime
     */
    public int getMinConnectionTime(){
        return this.minConnectionTime;
    }

    /**
     * Set airport delay
     */
    public void setDelayTime(int delayTime){
        this.delayTime = delayTime;
    }

    /**
     * accessor for delay time
     * @return delayTime
     */
    public int getDelayTime(){
        return this.delayTime;
    }

    /**
     * toString(): airport name, weather, temperature, delay (minutes)
     * @return printout
     */
    @Override
    public String toString(){
        Weather weather = getWeather();
        String delayTime = Integer.toString(this.delayTime);
        return name + "," + weather.getCondition() + "," + weather.getTemp() + "," + delayTime;

    }
}
