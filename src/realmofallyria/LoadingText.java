
package realmofallyria;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LoadingText {

    String fullText;
    JLabel givenLabel;
    JButton givenButton;
    JTextField givenTextField;
    Timer localTimer;
    TimerTask timerTask;

    public LoadingText(String givenText, JComponent givenComponent, boolean formatText, int speed) {

        if (givenComponent instanceof JLabel) {

            this.givenLabel = (JLabel) givenComponent;

        } else if (givenComponent instanceof JButton) {

            this.givenButton = (JButton) givenComponent;

        } else if (givenComponent instanceof JTextField) {

            this.givenTextField = (JTextField) givenComponent;

        }

        this.localTimer = new Timer();

        this.fullText = formatText ? String.format("<html><p align=\"center\">%s</p></html>", givenText) : givenText;
        this.timerTask = new TimerTask() {

            char[] textChars = fullText.toCharArray();
            int loadTextIndex = formatText ? 24 : 0;
            String loadedText = "";

            @Override
            public void run() {
                loadedText = loadedText + textChars[loadTextIndex];

                if (givenButton != null) {

                    givenButton.setText(loadedText);

                } else if (givenTextField != null) {

                    givenTextField.setText(loadedText);

                } else {

                    givenLabel.setText(loadedText);

                }

                loadTextIndex++;

                if (formatText ? loadTextIndex >= textChars.length - 11 : loadTextIndex >= textChars.length) {
                    timerTask = null;
                    forceCancel();
                }

            }

        };

//        System.out.printf("Length / Speed: %s / %s (%.2f)\n",
//                givenText.length(), speed, (float) (((float) speed)) / givenText.length());
        localTimer.schedule(timerTask, 0, (int) (((float) speed) * 15) / givenText.length());

    }

    public void forceCancel() {

        this.localTimer.cancel();

        if (givenButton != null) {

            givenButton.setText(fullText);

        } else if (givenTextField != null) {

            givenTextField.setText(fullText);

        } else {

            givenLabel.setText(fullText);

        }

    }

}
