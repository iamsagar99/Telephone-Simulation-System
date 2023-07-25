import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CallState extends JFrame {
    private JTextField fromCall;
    private JTextField toCall;
    private JTextField lengthCall;
    private JTextField arrivalTimeCall;
    private JPanel jpanel1;
    private JPanel jpanel2;
    private JTextField a3TextField;
    private JTextField linkInUse;
    private JList<Integer> list1;
    private JTextField from1;
    private JTextField from2;
    private JTextField from3;
    private JTextField to1;
    private JTextField end1;
    private JTextField to2;
    private JTextField end2;
    private JTextField to3;
    private JTextField end3;
    private JTextField processedText;
    private JTextField completedText;
    private JTextField blockedText;
    private JTextField busyText;
    private JTextField clockText;
    private JButton changeClockPulseButton;
    private JButton menuBtn;

    private final CallCounters callCounters;
    private final CallProgress[] callProgress;
    private NextCall nextCall;
    private int[] links;
    private boolean[] telephoneStatus;

    public CallState(String title, boolean[] telephoneStatus) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(jpanel1);
        this.pack();
        this.callCounters = ConnectingTelephone.getCallCounters();
        this.callProgress = ConnectingTelephone.getCallProgress();
        this.nextCall = ConnectingTelephone.getNextCall();
        this.links = new int[9];
        this.telephoneStatus = telephoneStatus; // Initialize telephoneStatus

        changeClockPulseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                callCounters.setLines(links);
                callCounters.setClock(5);

                // ... (existing code)

                displayCalls(); // Update the call state display after each clock pulse
            }
        });

        menuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToMenu();
            }
        });

        displayCalls(); // Display the initial call state
    }

    // Method to go back to the ConnectingTelephone screen
    private void goToMenu() {
        ConnectingTelephone telephone = new ConnectingTelephone("Telephone Simulation System");
        telephone.setSize(500, 500);
        telephone.setLocationRelativeTo(null);
        telephone.setVisible(true);
        telephone.setTelephoneStatus(telephoneStatus);
        this.dispose(); // Close the CallState screen
    }

    private void displayCalls() {
        linkInUse.setText(String.valueOf(callCounters.getLinks_IN_USE()));
        processedText.setText(String.valueOf(callCounters.getProcessed_call()));
        completedText.setText(String.valueOf(callCounters.getCompleted_call()));
        blockedText.setText(String.valueOf(callCounters.getBlocked_call()));
        busyText.setText(String.valueOf(callCounters.getBusy_call()));

        // Set Timer
        clockText.setText(String.valueOf(callCounters.getClock()));
        // Set Timer finish

        // Next Call
        fromCall.setText(String.valueOf(nextCall.getFrom()));
        toCall.setText(String.valueOf(nextCall.getTo()));
        lengthCall.setText(String.valueOf(nextCall.getLength()));
        arrivalTimeCall.setText(String.valueOf(nextCall.getArrivalTime()));
        // Next Call end

        // Call Progress
        from1.setText(String.valueOf(callProgress[0].getFrom()));
        to1.setText(String.valueOf(callProgress[0].getTo()));
        end1.setText(String.valueOf(callProgress[0].getEnd()));

        from2.setText(String.valueOf(callProgress[1].getFrom()));
        to2.setText(String.valueOf(callProgress[1].getTo()));
        end2.setText(String.valueOf(callProgress[1].getEnd()));

        from3.setText(String.valueOf(callProgress[2].getFrom()));
        to3.setText(String.valueOf(callProgress[2].getTo()));
        end3.setText(String.valueOf(callProgress[2].getEnd()));
        // Call Progress End

        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
            arrayList.add(telephoneStatus[i] ? 1 : 0);
        }
        list1.setListData(arrayList.toArray(new Integer[0]));
    }

    public static void callStatePanel(int[] links, boolean[] telephoneStatus) {
        CallState callState = new CallState("Call State", telephoneStatus);
        callState.links = links; // Update the links array
        callState.setSize(1000, 500);
        callState.setVisible(true);
        callState.setLocationRelativeTo(null);
    }
}
