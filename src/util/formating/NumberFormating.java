package util.formating;

import java.text.NumberFormat;

/**
 *
 * @author javarobots
 */
public class NumberFormating {

    private NumberFormat mNumberFormatter = NumberFormat.getInstance();

    public String formatLatitudeValue(double number, int decimalPlaces) {
        mNumberFormatter.setMaximumFractionDigits(decimalPlaces);
        StringBuilder latitudeBuilder = new StringBuilder(mNumberFormatter.format(number));
        latitudeBuilder.deleteCharAt(1);
        latitudeBuilder.insert(2, " ");
        return latitudeBuilder.toString();

    }

    public String formatLongitudeValue(double number, int decimalPlaces) {
        mNumberFormatter.setMaximumFractionDigits(decimalPlaces);
        StringBuilder longitudeBuilder = new StringBuilder(mNumberFormatter.format(number));
        longitudeBuilder.insert(longitudeBuilder.indexOf(".") - 2, " ");
        int commaIndex = longitudeBuilder.indexOf(",");
        if (commaIndex != -1){
            longitudeBuilder.deleteCharAt(commaIndex);
        }
        //Check for leading zero
        String[] splitLongitude = longitudeBuilder.toString().split(" ");
        if (splitLongitude[0].length() == 2){
            longitudeBuilder.insert(0, "0");
        }
        return longitudeBuilder.toString();
    }

    public String formatValue(double number, int decimalPlaces){
        mNumberFormatter.setMaximumFractionDigits(decimalPlaces);
        return mNumberFormatter.format(number);
    }
}
