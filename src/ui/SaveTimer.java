
package ui;

import javax.swing.JLabel;
import javax.swing.SwingWorker;

/**
 *
 * @author javarobots
 */
public class SaveTimer extends SwingWorker<Boolean,Boolean> {

    private JLabel mLabel;

    public SaveTimer(JLabel label){
        mLabel = label;
    }

    @Override
    protected Boolean doInBackground() throws Exception {
        Thread.sleep(250);
        return true;
    }

    @Override
    public void done(){
        mLabel.setText(" ");
    }

}
