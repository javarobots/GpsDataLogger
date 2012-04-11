
package ui;

import commonutilities.swing.ComponentPosition;
import de.micromata.opengis.kml.v_2_2_0.*;
import gnu.io.SerialPort;
import gps.calculations.DistanceConversion;
import gps.calculations.NavigationCalculations;
import gps.data.GpsDataModel;
import gps.nmea.NmeaSentences;
import gps.nmea.SelectedSentences;
import gps.nmea.SentenceParser;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import util.rxtx.GpsSerialDataListener;
import util.rxtx.RxTxUtilities;

/**
 *
 * @author javarobots
 */
public class GpsDataLogger extends javax.swing.JFrame implements Observer {

    private String mSelectedCom = "";
    private SerialPort mSerialPort;
    private SentenceParser mParser;
    private double mTotalForSpeedAverage;
    private int mNumberMeasurements;
    private List<Coordinate> mLoggedCoordinates;
    private double mLogAboveSpeed = 5;

    /** Creates new form GpsDataLogger */
    @SuppressWarnings("LeakingThisInConstructor")
    public GpsDataLogger() {
        initComponents();

        //Init the available ports
        initAvailablePorts();

        GpsDataModel dataModel = new GpsDataModel();
        dataModel.setSelectedSentences(new SelectedSentences());
        mParser = new SentenceParser(dataModel);
        dataModel.addObserver(this);

        mLoggedCoordinates = new ArrayList<>();

        mSaveLabel.setText(" ");
        BufferedImage image = null;
        try {
            image = ImageIO.read(this.getClass().getResource("/images/compass.png"));
        } catch (IOException e){

        }
        this.setIconImage(image);

        //Init the NMEA Sentence menu and sentenced to parse
        SelectedSentences selectedSentences = dataModel.getSelectedSentences();
        selectedSentences.setParseGGA(true);
        selectedSentences.setParseGSA(true);
        selectedSentences.setParseVTG(true);
        selectedSentences.setParseRMC(false);
        for (NmeaSentences sentence : EnumSet.allOf(NmeaSentences.class)){
            JCheckBoxMenuItem menuItem = new JCheckBoxMenuItem(sentence.toString());
            menuItem.setSelected(selectedSentences.isParse(sentence));
            mNMEASentenceMenu.add(menuItem);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mLatitudeLabel = new javax.swing.JLabel();
        mLongitudeLabel = new javax.swing.JLabel();
        mSpeedLabel = new javax.swing.JLabel();
        mAltitudeLabel = new javax.swing.JLabel();
        mAverageSpeedLabel = new javax.swing.JLabel();
        mLogAllCheckBox = new javax.swing.JCheckBox();
        mFixModeLabel = new javax.swing.JLabel();
        mSaveLabel = new javax.swing.JLabel();
        mLogAboveLabel = new javax.swing.JLabel();
        mLogAboveSpinner = new javax.swing.JSpinner();
        mMenuBar = new javax.swing.JMenuBar();
        mFileMenu = new javax.swing.JMenu();
        mClearCoordinatesMenuItem = new javax.swing.JMenuItem();
        mSaveKmlMenuItem = new javax.swing.JMenuItem();
        mExitMenuItem = new javax.swing.JMenuItem();
        mToolsMenu = new javax.swing.JMenu();
        mNMEASentenceMenu = new javax.swing.JMenu();
        mSerialPortMenu = new javax.swing.JMenu();
        mStartMenuItem = new javax.swing.JMenuItem();
        mStopMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GPS Data Logger");
        setResizable(false);

        mLatitudeLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mLatitudeLabel.setText("Latitude: 00.0000 N");

        mLongitudeLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mLongitudeLabel.setText("Longitude: 000.0000 W");

        mSpeedLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mSpeedLabel.setText("Speed: 00.0");

        mAltitudeLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mAltitudeLabel.setText("Altitude: 0");

        mAverageSpeedLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mAverageSpeedLabel.setText("Average Speed: 0");

        mLogAllCheckBox.setText("Log All");
        mLogAllCheckBox.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        mFixModeLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mFixModeLabel.setText("Fix Mode: 1");

        mSaveLabel.setText("Save");

        mLogAboveLabel.setText("Log Above (mph):");

        mLogAboveSpinner.setModel(new javax.swing.SpinnerNumberModel(5.0d, 0.0d, 25.0d, 1.0d));
        mLogAboveSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                mLogAboveSpinnerStateChanged(evt);
            }
        });

        mFileMenu.setText("File");

        mClearCoordinatesMenuItem.setText("Clear Logged Coordinates");
        mClearCoordinatesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mClearCoordinatesMenuItemActionPerformed(evt);
            }
        });
        mFileMenu.add(mClearCoordinatesMenuItem);

        mSaveKmlMenuItem.setText("Save KML");
        mSaveKmlMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mSaveKmlMenuItemActionPerformed(evt);
            }
        });
        mFileMenu.add(mSaveKmlMenuItem);

        mExitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        mExitMenuItem.setText("Exit");
        mExitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mExitMenuItemActionPerformed(evt);
            }
        });
        mFileMenu.add(mExitMenuItem);

        mMenuBar.add(mFileMenu);

        mToolsMenu.setText("Tools");

        mNMEASentenceMenu.setText("NMEA Sentences");
        mToolsMenu.add(mNMEASentenceMenu);

        mSerialPortMenu.setText("Serial Port");
        mToolsMenu.add(mSerialPortMenu);

        mStartMenuItem.setText("Start");
        mStartMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mStartMenuItemActionPerformed(evt);
            }
        });
        mToolsMenu.add(mStartMenuItem);

        mStopMenuItem.setText("Stop");
        mStopMenuItem.setEnabled(false);
        mStopMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mStopMenuItemActionPerformed(evt);
            }
        });
        mToolsMenu.add(mStopMenuItem);

        mMenuBar.add(mToolsMenu);

        setJMenuBar(mMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mSaveLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                        .addComponent(mLogAllCheckBox))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mLongitudeLabel)
                            .addComponent(mAltitudeLabel)
                            .addComponent(mSpeedLabel)
                            .addComponent(mFixModeLabel)
                            .addComponent(mAverageSpeedLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(mLogAboveLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mLogAboveSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(mLatitudeLabel))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mSaveLabel)
                    .addComponent(mLogAllCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mLatitudeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mLongitudeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mAltitudeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mSpeedLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mFixModeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mAverageSpeedLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mLogAboveLabel)
                    .addComponent(mLogAboveSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mExitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mExitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mExitMenuItemActionPerformed

    private void mStartMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mStartMenuItemActionPerformed
        if (!mSelectedCom.isEmpty()){
            try {
                mStartMenuItem.setEnabled(false);
                mStopMenuItem.setEnabled(true);
                mSerialPort = RxTxUtilities.openPortByName(mSelectedCom, 9600);
                mSerialPort.notifyOnDataAvailable(true);
                mSerialPort.addEventListener(new GpsSerialDataListener(mSerialPort.getInputStream(), mParser));
            } catch (TooManyListenersException | IOException ex) {
                Logger.getLogger(GpsDataLogger.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_mStartMenuItemActionPerformed

    private void mStopMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mStopMenuItemActionPerformed
        mStartMenuItem.setEnabled(true);
        mStopMenuItem.setEnabled(false);
        mSerialPort.close();
    }//GEN-LAST:event_mStopMenuItemActionPerformed

    private void mClearCoordinatesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mClearCoordinatesMenuItemActionPerformed
        int clearResponse = JOptionPane.showConfirmDialog(this, "Do you wish to clear all logged coordinates?", "Clear Coordinates", JOptionPane.YES_NO_OPTION);
        if (clearResponse == JOptionPane.YES_OPTION){
            mLoggedCoordinates.clear();
        }
    }//GEN-LAST:event_mClearCoordinatesMenuItemActionPerformed

    private void mSaveKmlMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mSaveKmlMenuItemActionPerformed
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(("Keyhole Markup Language"), "kml");
        chooser.setFileFilter(filter);
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
            try {
                //Create the KML object
                Kml kml = new Kml();
                Placemark placemark = kml.createAndSetPlacemark();
                Style style = placemark.createAndAddStyle();
                LineStyle lineStyle = style.createAndSetLineStyle();
                lineStyle.setWidth(4.0);
                lineStyle.setColor("7fee0000");
                LineString linestring = placemark.createAndSetLineString();
                //Add coordinates
                linestring.setCoordinates(mLoggedCoordinates);

                kml.marshal(chooser.getSelectedFile());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GpsDataLogger.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_mSaveKmlMenuItemActionPerformed

    private void mLogAboveSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_mLogAboveSpinnerStateChanged
        mLogAboveSpeed = (double) mLogAboveSpinner.getValue();
    }//GEN-LAST:event_mLogAboveSpinnerStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel mAltitudeLabel;
    private javax.swing.JLabel mAverageSpeedLabel;
    private javax.swing.JMenuItem mClearCoordinatesMenuItem;
    private javax.swing.JMenuItem mExitMenuItem;
    private javax.swing.JMenu mFileMenu;
    private javax.swing.JLabel mFixModeLabel;
    private javax.swing.JLabel mLatitudeLabel;
    private javax.swing.JLabel mLogAboveLabel;
    private javax.swing.JSpinner mLogAboveSpinner;
    private javax.swing.JCheckBox mLogAllCheckBox;
    private javax.swing.JLabel mLongitudeLabel;
    private javax.swing.JMenuBar mMenuBar;
    private javax.swing.JMenu mNMEASentenceMenu;
    private javax.swing.JMenuItem mSaveKmlMenuItem;
    private javax.swing.JLabel mSaveLabel;
    private javax.swing.JMenu mSerialPortMenu;
    private javax.swing.JLabel mSpeedLabel;
    private javax.swing.JMenuItem mStartMenuItem;
    private javax.swing.JMenuItem mStopMenuItem;
    private javax.swing.JMenu mToolsMenu;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof GpsDataModel){

            System.out.println("Update Data Logger");

            GpsDataModel model = (GpsDataModel)o;

            //Create number formatter preventing decimals
            NumberFormat numberFormatter = NumberFormat.getNumberInstance();
            numberFormatter.setMaximumFractionDigits(4);

            //Get coordinate
            gps.data.Coordinate coordinate = model.getGGACoordinate();

            //Build latitude
            StringBuilder latitudeBuilder = new StringBuilder(numberFormatter.format(coordinate.getLatitude()));
            latitudeBuilder.deleteCharAt(1);
            latitudeBuilder.insert(2, " ");

            //Build longitude
            StringBuilder longitudeBuilder = new StringBuilder(numberFormatter.format(coordinate.getLongitude()));
            longitudeBuilder.deleteCharAt(2);
            longitudeBuilder.insert(3, " ");

            //Set Latitude and longitude labels
            mLatitudeLabel.setText("Latitude: " + latitudeBuilder.toString() + " " + coordinate.getLatitudeHemisphere().getHemisphere());
            mLongitudeLabel.setText("Longitude: " + longitudeBuilder.toString() + " " + coordinate.getLongitudeHemisphere().getHemisphere());

            numberFormatter.setMaximumFractionDigits(0);

            //Set altitude and speed
            mAltitudeLabel.setText("Altitude: " + numberFormatter.format(DistanceConversion.metersToFeet(model.getGGAHeightAboveSeaLevel())));
            numberFormatter.setMaximumFractionDigits(2);
            double speed = model.getVTGSpeedInKilometers() * .621371192;
            mSpeedLabel.setText("Speed: " + numberFormatter.format(speed));

            //Set fix mode
            mFixModeLabel.setText("Fix Mode: " + model.getGsaFixMode());

            //Set up lat and lon for logging
            NavigationCalculations navCalc = NavigationCalculations.getInstance();
            double longitude = navCalc.degreesMinutesToDegrees(longitudeBuilder.toString());
            double latitude = navCalc.degreesMinutesToDegrees(latitudeBuilder.toString());
            if (coordinate.getLongitudeHemisphere().getHemisphere().equals("W")){
                longitude = longitude * -1;
            }
            if (coordinate.getLatitudeHemisphere().getHemisphere().equals("S")){
                latitude = latitude * -1;
            }

            //Log coordinate if log all or speed cut off is met
            if ((mLogAllCheckBox.isSelected() || speed >= mLogAboveSpeed) && model.isLogCoordinate()){
                logCoordinate(longitude, latitude, model.getGGAHeightAboveSeaLevel());
                model.setLogCoordinate(false);
            }

            //Perform averaging
            mNumberMeasurements++;
            mTotalForSpeedAverage += speed;
            double speedAverage = mTotalForSpeedAverage/mNumberMeasurements;
            mAverageSpeedLabel.setText("Average Speed: " + numberFormatter.format(speedAverage));

        }
    }

    private void logCoordinate(double longitude, double latitude, double altitude){
        mSaveLabel.setText("Logging");
        SaveTimer timer = new SaveTimer((mSaveLabel));
        Coordinate loggedCoordinate = new Coordinate(longitude, latitude, altitude);
        mLoggedCoordinates.add(loggedCoordinate);
        timer.execute();
    }

    private void initAvailablePorts(){
        List<String> availablePorts = RxTxUtilities.getAvailablePorts();
        ButtonGroup comButtonGroup = new ButtonGroup();
        for (String portName : availablePorts){
            JCheckBoxMenuItem item = new JCheckBoxMenuItem(portName);
            item.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    mSelectedCom = ((JCheckBoxMenuItem)evt.getSource()).getText();
                }
            });
            comButtonGroup.add(item);
            mSerialPortMenu.add(item);
        }
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(new NimbusLookAndFeel());
                    GpsDataLogger logger = new GpsDataLogger();
                    ComponentPosition.centerFrame(logger);
                    logger.setVisible(true);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(GpsDataLogger.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

}
