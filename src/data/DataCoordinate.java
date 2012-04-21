
package data;

import gps.data.Coordinate;
import gps.data.Hemisphere;

/**
 *
 * @author javarobots
 */
public class DataCoordinate extends Coordinate {

    private double mSpeed;
    private double mElevation;

    public DataCoordinate(){
        super();
    }

    public DataCoordinate(double latitude, Hemisphere latHemisphere, double longitude, Hemisphere lonHemisphere){
        super(latitude, latHemisphere, longitude, lonHemisphere);
    }

    public DataCoordinate(Coordinate baseCoordinate){
        super(baseCoordinate.getLatitude(),
                baseCoordinate.getLatitudeHemisphere(),
                baseCoordinate.getLongitude(),
                baseCoordinate.getLongitudeHemisphere());
    }

    public double getSpeed() {
        return mSpeed;
    }

    public void setSpeed(double speed) {
        this.mSpeed = speed;
    }

    public double getElevation() {
        return mElevation;
    }

    public void setElevation(double elevation) {
        this.mElevation = elevation;
    }





}
