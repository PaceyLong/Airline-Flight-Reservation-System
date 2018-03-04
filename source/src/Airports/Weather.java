package Airports;

public class Weather {
    private int temp;
    private String condition;

    public Weather(int temp, String condition){
        this.temp = temp;
        this.condition = condition;
    }

    public String getCondition(){
        return this.condition;
    }

    public int getTemp(){
        return this.temp;
    }
}
