package data;

import gps.data.Coordinate;
import java.text.NumberFormat;
import org.joda.time.DateTime;

/**
 *
 *  KML TimeStamp example
 *  <TimeStamp>
 *       <when>1997-07-16T07:30:15Z</when>
 *  </TimeStamp>
 * 
 * @author javarobots
 */
public class StopCoordinate extends Coordinate {
    
    private String mStopTime;
    
    public StopCoordinate(){
        super();
    }
    
    public void markStopTime(){        
        StringBuilder whenBuilder = new StringBuilder();
        NumberFormat formatter = NumberFormat.getInstance();
        formatter.setMinimumIntegerDigits(2);
        
        //Get current date and time and adjust to UTC
        DateTime timeInstance = new DateTime();
        int rawOffset = timeInstance.getZone().toTimeZone().getRawOffset();
        timeInstance = timeInstance.plusMillis(rawOffset);     
        
        //Get and build the date
        String year = timeInstance.year().getAsString();
        String month = timeInstance.monthOfYear().getAsString();
        String day = timeInstance.dayOfMonth().getAsString();
        whenBuilder.append(year).append("-").append(month).append("-").append(day);
        
        //Add time separator
        whenBuilder.append("T");
        
        //Get and build the UTC time
        int hour = timeInstance.getHourOfDay();
        int minute = timeInstance.getMinuteOfHour();
        int second = timeInstance.getSecondOfMinute();        
        whenBuilder.append(formatter.format(hour)).append(":").append(formatter.format(minute)).append(":").append(formatter.format(second)).append("Z");
        
        //Set the time
        mStopTime = whenBuilder.toString();       
    }
    
    public String getStopTime(){
        return mStopTime;
    }
    
}
