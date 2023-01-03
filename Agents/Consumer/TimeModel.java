package Agents.Consumer;

public class TimeModel {

    private long startTime;

    long unit = 2000;

    private long time;

    private long hour;

    public TimeModel(){
        this.startTime = System.currentTimeMillis();
    }


    public long getHour() {
        hour = ((System.currentTimeMillis() - startTime)/unit + 1)%24;
        return hour;
    }

    public long getTime(long startTime) {

        return System.currentTimeMillis() - startTime/unit;

    }
}
