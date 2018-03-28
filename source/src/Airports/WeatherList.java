package Airports;

import Iterator.Container;
import Iterator.Iterator;
import Iterator.WeatherIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ethan Della Posta
 * List of weather objects for a given Airport
 */
public class WeatherList implements Container{

    private List<Weather> list;
    private Iterator weatherIter;

    /**
     * Constructor
     * list - list of weather objects to be traversed
     * weatherIter - iterator to be used
     */
    public WeatherList(){
        this.list = new ArrayList<>();
        this.weatherIter = new WeatherIterator(this.list);
    }

    /**
     * Add weather object to list
     * @param w - Weather object to be added
     */
    public void addWeather(Weather w){
        this.list.add(w);
    }


    /**
     * gets weather iterator for this WeatherList
     * @return weather iterator
     */
    @Override
    public Iterator getIterator() {
        return this.weatherIter;
    }

    /**
     * Gets the current weather
     * @return next weather object
     */
    public Weather getCurrWeather(){
        return (Weather) getIterator().next();
    }
}
