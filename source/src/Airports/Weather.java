package Airports;

/**
 * @author Ethan Della Posta
 * Simple weather data structure, used in airports
 */
public class Weather {
    private int temp;
    private String condition;

    /**
     * Constructor
     * @param temp - temperature of object
     * @param condition - condition of object
     */
    public Weather(int temp, String condition){
        this.temp = temp;
        this.condition = condition;
    }

    /**
     * Get condition (phrase like "Sunny, Cloudy, etc.)
     * @return weather condition
     */
    public String getCondition(){
        return this.condition;
    }

    /**
     * Get numeric temperature (F)
     * @return weather temperature
     */
    public int getTemp(){
        return this.temp;
    }
}
