import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ConnectingTelephone extends JFrame {
    private JPanel panel1;
    private JCheckBox telephone1CheckBox;
    private JCheckBox telephone6CheckBox;
    private JCheckBox telephone8CheckBox;
    private JCheckBox telephone7CheckBox;
    private JCheckBox telephone5CheckBox;
    private JCheckBox telephone2CheckBox;
    private JCheckBox telephone3CheckBox;
    private JCheckBox telephone4CheckBox;
    private JButton connectTelephoneButton;
    private JButton menuButton;

    private static CallCounters callCounters;
    private static NextCall nextCall;
    private static CallProgress[] callProgress;
    private int cp;
    private int[] links;
    private boolean[] telephoneStatus; // To store the status of telephones

    public ConnectingTelephone(String title) {
        super(title);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();
        callCounters = new CallCounters();
        nextCall = new NextCall();
        callProgress = new CallProgress[3];
        for (int i = 0; i < 3; i++) {
            callProgress[i] = new CallProgress();
        }
        cp = 0;
        links = new int[9]; // Initialize the links array
        telephoneStatus = new boolean[9]; // Initialize the telephoneStatus array

        connectTelephoneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkConnection();
                CallState.callStatePanel(links, telephoneStatus); // Pass the links and telephoneStatus arrays to the CallState panel
                dispose(); // Close the current ConnectingTelephone panel after successful connection
            }
        });
    }

    private void checkConnection() {
        int count = 0, set = 0;

        if (telephone1CheckBox.isSelected()) {
            links[1] = 1;
            telephoneStatus[1] = true;
            telephone1CheckBox.setSelected(false);
        }
        if (telephone2CheckBox.isSelected()) {
            links[2] = 1;
            telephoneStatus[2] = true;
            telephone2CheckBox.setSelected(false);
        }
        if (telephone3CheckBox.isSelected()) {
            links[3] = 1;
            telephoneStatus[3] = true;
            telephone3CheckBox.setSelected(false);
        }
        if (telephone4CheckBox.isSelected()) {
            links[4] = 1;
            telephoneStatus[4] = true;
            telephone4CheckBox.setSelected(false);
        }
        if (telephone5CheckBox.isSelected()) {
            links[5] = 1;
            telephoneStatus[5] = true;
            telephone5CheckBox.setSelected(false);
        }
        if (telephone6CheckBox.isSelected()) {
            links[6] = 1;
            telephoneStatus[6] = true;
            telephone6CheckBox.setSelected(false);
        }
        if (telephone7CheckBox.isSelected()) {
            links[7] = 1;
            telephoneStatus[7] = true;
            telephone7CheckBox.setSelected(false);
        }
        if (telephone8CheckBox.isSelected()) {
            links[8] = 1;
            telephoneStatus[8] = true;
            telephone8CheckBox.setSelected(false);
        }

        // ... (rest of the existing code)


        if (count > 2 || count == 1 || (callCounters.getLinks_IN_USE() >= callCounters.getLinks_MAX_NO())) {
            JOptionPane.showMessageDialog(this, "Call connection not valid");
        } else {
            JOptionPane.showMessageDialog(this, "Connection Successful");
            count = 0;
            /* Save calls in call Progress */
            if (cp <= 2) {
                /* Calls saved to next call if callProgress and the links are equal */
                for (int i = 0; i <= 2; i++) {
                    for (int k = 0; k < 9; k++) {
                        if (links[k] == 1) {
                            if (callProgress[i].getFrom() == k || callProgress[i].getTo() == k) {
                                count += 1;

                            }
                        }
                    }
                }
                //If the count= 0 then it is progress call
                if (count == 0) {
                    for (int k = 0; k < 9; k++) {
                        if (links[k] == 1) {
                            if (set == 0) {
                                callProgress[cp].setFrom(k);
                                set = 1;
                            } else {
                                callProgress[cp].setTo(k);
                                set = 0;
                            }
                        }
                    }
                    callProgress[cp].setEnd(callCounters.getClock() + new Random().nextInt(50));
                }else { //If the count!=0 then it is a next call as the links meet
                    for(int k = 0; k<9 ; k++){
                        if(links[k]==1){
                            if (set == 0) {
                                nextCall.setFrom(k);
                                set = 1;
                            } else {
                                nextCall.setTo(k);
                                set = 0;
                            }
                        }
                    }
                    nextCall.setArrivalTime(callCounters.getClock()+ new Random().nextInt(50));
                }
                /* Calls saved to next call completed*/
                cp += 1;
            }
            /* Call progress saved completed */


            callCounters.setLines(links);
            callCounters.setLinks_IN_USE(1);
            callCounters.setProcessed_call(1);

        }
//        CallState.main(null);
    }

    // ... (rest of the existing code)

    // Method to set the telephone status
    public void paint(Graphics g) {
        super.paint(g);

        //Horizontal lines for telephone
        g.drawLine(100, 100, 500, 100);
        g.drawLine(100, 140, 500, 140);
        g.drawLine(100, 190, 500, 190);
        g.drawLine(100, 240, 500, 240);
        g.drawLine(100, 280, 500, 280);
        g.drawLine(100, 330, 500, 330);
        g.drawLine(100, 380, 500, 380);
        g.drawLine(100, 430, 500, 430);

        //Vertical lines for link
        g.drawLine(300, 0, 300, 500);
        g.drawLine(350, 0, 350, 500);
        g.drawLine(400, 0, 400, 500);
    }
    public void setTelephoneStatus(boolean[] telephoneStatus) {
        this.telephoneStatus = telephoneStatus;
    }

    public static void main(String[] args) {
        ConnectingTelephone connectingTelephone = new ConnectingTelephone("Telephone Simulation System");
        connectingTelephone.setSize(500, 500);
        connectingTelephone.setLocationRelativeTo(null);
        connectingTelephone.setVisible(true);
    }

    public static CallCounters getCallCounters() {
        return callCounters;
    }

    public static NextCall getNextCall() {
        return nextCall;
    }

    public static CallProgress[] getCallProgress() {
        return callProgress;
    }
}
