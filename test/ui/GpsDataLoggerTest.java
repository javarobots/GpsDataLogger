package ui;

import gps.data.GpsDataModel;
import org.junit.*;

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
        
    }
}
