package Iterator;

import Airports.Weather;

import java.util.List;

/**
 * author Ethan Della Posta
 * Iterator for any given list of weather objects
 * which implements the Iterator interface defined in Iterator package.
 */
public class WeatherIterator implements Iterator {

    private int index;
    List<Weather> list;

    /**
     * Constructor
     * @param list - List of weather objects
     * index - defaults to zero
     */
    public WeatherIterator(List list){
        this.index = 0;
        this.list = list;
    }


    /**
     * check if we have reached end yet
     * @return reached end boolean
     */
    @Override
    public boolean hasNext() {
        return index < this.list.size()-1;
    }


    /**
     * go to next weather objectm or revert to beginning
     * @return weather object at index (as an Object so we must cast to Weather elsewhere)
     */
    @Override
    public Object next() {
        Weather currWeather = null;
        if(this.list.size() > 0) { //as long as weather list is not empty
            currWeather = this.list.get(this.index);
            if(hasNext()){
                this.index++;
            }
            else {
                this.index=0;
            }
        }
        return currWeather;
    }
}