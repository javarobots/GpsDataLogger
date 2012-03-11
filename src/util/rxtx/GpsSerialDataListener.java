
package util.rxtx;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gps.nmea.SentenceParser;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author javarobots
 */
public class GpsSerialDataListener implements SerialPortEventListener {

    private InputStream mInputStream;
    private byte[] mBuffer = new byte[1024];
    private SentenceParser mParser;

    public GpsSerialDataListener(InputStream inStream, SentenceParser parser){
        mInputStream = inStream;
        mParser = parser;
    }

    @Override
    public void serialEvent(SerialPortEvent spe) {
        try {
            Thread.sleep(500);
            int bytesRead = mInputStream.read(mBuffer);
            while(bytesRead > 0){
                //Process the read bytes
                mParser.processData(new String(mBuffer,0,bytesRead));
                bytesRead = mInputStream.read(mBuffer);
            }
        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(GpsSerialDataListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
