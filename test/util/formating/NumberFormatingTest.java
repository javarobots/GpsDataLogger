package util.formating;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author javarobots <javarobots74@gmail.com>
 */
public class NumberFormatingTest {

    public NumberFormatingTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of formatLatitudeValue method, of class NumberFormating.
     */
    @Test
    public void testFormatLatitudeValue() {
        System.out.println("formatLatitudeValue");
        double number = 3035.964;
        int decimalPlaces = 4;
        NumberFormating instance = new NumberFormating();
        String expResult = "30 35.964";
        assertEquals(expResult, instance.formatLatitudeValue(number, decimalPlaces));
    }

    /**
     * Test of formatLongitudeValue method, of class NumberFormating.
     */
    @Test
    public void testFormatLongitudeValue() {
        System.out.println("formatLongitudeValue");
        double number = 08709.666;
        int decimalPlaces = 4;
        NumberFormating instance = new NumberFormating();
        String expResult = "087 09.666";
        assertEquals(expResult, instance.formatLongitudeValue(number, decimalPlaces));
    }

    /**
     * Test of formatValue method, of class NumberFormating.
     */
    @Test
    public void testFormatValue(){
        System.out.println("formatValue");
        double number = 5356;
        int decimalPlaces = 0;
        NumberFormating instance = new NumberFormating();
        String expResult = "5,356";
        assertEquals(expResult, instance.formatValue(number, decimalPlaces));
    }
}
