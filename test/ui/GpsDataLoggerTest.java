package ui;

import gps.calculations.NavigationCalculations;
import gps.data.Coordinate;
import gps.data.GpsDataModel;
import gps.data.Hemisphere;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 *
 * @author javarobots
 */
public class GpsDataLoggerTest {

    private GpsDataLogger mLogger;
    private GpsDataModel mDataModel;

    public GpsDataLoggerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
         mLogger = new GpsDataLogger();
         mDataModel = new GpsDataModel();
         mDataModel.addObserver(mLogger);
    }

    @After
    public void tearDown() {
    }

    /**
     * A test for the update method that allows
     * testing whether or not a stop coordinate
     * will be recording and the correct number
     * of coordinates
     */
    @Test
    public void stopCoordinateTest(){
        double lat = NavigationCalculations.getInstance().degreeMinutesSecondsToNmeaDegreesMinutes("35 04 01.03");
        double lon = NavigationCalculations.getInstance().degreeMinutesSecondsToNmeaDegreesMinutes("106 33 00.25");
        Coordinate coordinate1 = new Coordinate(lat, Hemisphere.NORTH, lon, Hemisphere.WEST);
        lat = NavigationCalculations.getInstance().degreeMinutesSecondsToNmeaDegreesMinutes("35 03 29.48");
        lon = NavigationCalculations.getInstance().degreeMinutesSecondsToNmeaDegreesMinutes("106 33 00.25");
        Coordinate coordinate2 = new Coordinate(lat, Hemisphere.NORTH, lon, Hemisphere.WEST);
        lat = NavigationCalculations.getInstance().degreeMinutesSecondsToNmeaDegreesMinutes("35 03 29.48");
        lon = NavigationCalculations.getInstance().degreeMinutesSecondsToNmeaDegreesMinutes("106 32 08.70");
        Coordinate coordinate3 = new Coordinate(lat, Hemisphere.NORTH, lon, Hemisphere.WEST);
        lat = NavigationCalculations.getInstance().degreeMinutesSecondsToNmeaDegreesMinutes("35 04 01.86");
        lon = NavigationCalculations.getInstance().degreeMinutesSecondsToNmeaDegreesMinutes("106 32 08.03");
        Coordinate coordinate4 = new Coordinate(lat, Hemisphere.NORTH, lon, Hemisphere.WEST);

        mDataModel.setGGACoordinate(coordinate1);
        mDataModel.setVTGSpeedInKilometers(10);
        mDataModel.setGGAHeightAboveSeaLevel(3000);
        mDataModel.setGsaFixType(3);
        mDataModel.setLogCoordinate(true);
        mDataModel.notifyObservers();

        mDataModel.setGGACoordinate(coordinate2);
        mDataModel.setVTGSpeedInKilometers(10);
        mDataModel.setGGAHeightAboveSeaLevel(3000);
        mDataModel.setGsaFixType(3);
        mDataModel.setLogCoordinate(true);
        mDataModel.notifyObservers();

        mDataModel.setGGACoordinate(coordinate3);
        mDataModel.setVTGSpeedInKilometers(10);
        mDataModel.setGGAHeightAboveSeaLevel(3000);
        mDataModel.setGsaFixType(3);
        mDataModel.setLogCoordinate(true);
        mDataModel.notifyObservers();

        mDataModel.setGGACoordinate(coordinate4);
        mDataModel.setVTGSpeedInKilometers(10);
        mDataModel.setGGAHeightAboveSeaLevel(3000);
        mDataModel.setGsaFixType(3);
        mDataModel.setLogCoordinate(true);
        mDataModel.notifyObservers();

        int numberOfLoggedCoordinates = mLogger.getLoggedCoordinates().size();
        int expectedNumberOfLoggedCoordinates = 4;
        assertEquals(expectedNumberOfLoggedCoordinates, numberOfLoggedCoordinates);

        mLogger.clearLoggerLists();

        mDataModel.setGGACoordinate(coordinate1);
        mDataModel.setVTGSpeedInKilometers(10);
        mDataModel.setGGAHeightAboveSeaLevel(3000);
        mDataModel.setGsaFixType(3);
        mDataModel.setLogCoordinate(true);
        mDataModel.notifyObservers();

        mDataModel.setGGACoordinate(coordinate2);
        mDataModel.setVTGSpeedInKilometers(0);
        mDataModel.setGGAHeightAboveSeaLevel(3000);
        mDataModel.setGsaFixType(3);
        mDataModel.setLogCoordinate(true);
        mDataModel.notifyObservers();

        mDataModel.setGGACoordinate(coordinate3);
        mDataModel.setVTGSpeedInKilometers(10);
        mDataModel.setGGAHeightAboveSeaLevel(3000);
        mDataModel.setGsaFixType(3);
        mDataModel.setLogCoordinate(true);
        mDataModel.notifyObservers();

        mDataModel.setGGACoordinate(coordinate4);
        mDataModel.setVTGSpeedInKilometers(10);
        mDataModel.setGGAHeightAboveSeaLevel(3000);
        mDataModel.setGsaFixType(3);
        mDataModel.setLogCoordinate(true);
        mDataModel.notifyObservers();

        numberOfLoggedCoordinates = mLogger.getLoggedCoordinates().size();
        expectedNumberOfLoggedCoordinates = 3;
        assertEquals(expectedNumberOfLoggedCoordinates, numberOfLoggedCoordinates);
        int numberOfLoggedStopCoordinates = mLogger.getLoggedStopCoordinates().size();
        expectedNumberOfLoggedCoordinates = 1;
        assertEquals(expectedNumberOfLoggedCoordinates, numberOfLoggedStopCoordinates);

        mLogger.clearLoggerLists();

        mDataModel.setGGACoordinate(coordinate1);
        mDataModel.setVTGSpeedInKilometers(10);
        mDataModel.setGGAHeightAboveSeaLevel(3000);
        mDataModel.setGsaFixType(3);
        mDataModel.setLogCoordinate(true);
        mDataModel.notifyObservers();

        mDataModel.setGGACoordinate(coordinate2);
        mDataModel.setVTGSpeedInKilometers(0);
        mDataModel.setGGAHeightAboveSeaLevel(3000);
        mDataModel.setGsaFixType(3);
        mDataModel.setLogCoordinate(true);
        mDataModel.notifyObservers();

        mDataModel.setGGACoordinate(coordinate3);
        mDataModel.setVTGSpeedInKilometers(0);
        mDataModel.setGGAHeightAboveSeaLevel(3000);
        mDataModel.setGsaFixType(3);
        mDataModel.setLogCoordinate(true);
        mDataModel.notifyObservers();

        mDataModel.setGGACoordinate(coordinate4);
        mDataModel.setVTGSpeedInKilometers(10);
        mDataModel.setGGAHeightAboveSeaLevel(3000);
        mDataModel.setGsaFixType(3);
        mDataModel.setLogCoordinate(true);
        mDataModel.notifyObservers();

        numberOfLoggedCoordinates = mLogger.getLoggedCoordinates().size();
        expectedNumberOfLoggedCoordinates = 2;
        assertEquals(expectedNumberOfLoggedCoordinates, numberOfLoggedCoordinates);
        numberOfLoggedStopCoordinates = mLogger.getLoggedStopCoordinates().size();
        expectedNumberOfLoggedCoordinates = 1;
        assertEquals(expectedNumberOfLoggedCoordinates, numberOfLoggedStopCoordinates);

    }
}
