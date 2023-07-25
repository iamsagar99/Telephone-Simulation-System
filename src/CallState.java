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
    private JList list1;
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
    int[] links = new int[9];

    public CallState(String title) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(jpanel1);
        this.pack();
        this.callCounters = ConnectingTelephone.getCallCounters();
        this.callProgress = ConnectingTelephone.getCallProgress();
        this.nextCall = ConnectingTelephone.getNextCall();
        System.arraycopy(callCounters.getLines(),0,links,0,9);



        changeClockPulseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                callCounters.setLines(links);
                callCounters.setClock(5);
                for (int i = 0; i <= 2; i++) {

                    if (callProgress[i].getFrom() != 0) {
                        if (callProgress[i].getFrom() == nextCall.getFrom()
                                || callProgress[i].getTo() == nextCall.getFrom()
                                || callProgress[i].getFrom() == nextCall.getTo()
                                || callProgress[i].getTo() == nextCall.getTo()) {
                            if (callProgress[i].getEnd() >= nextCall.getArrivalTime()) {
                                JOptionPane.showMessageDialog(jpanel1, "Call busy");
                                links[nextCall.getFrom()]=0;
                                links[nextCall.getTo()]=0;
                                nextCall.setFrom(0);
                                nextCall.setTo(0);
                                nextCall.setArrivalTime(0);
                                callCounters.setBusy_call(1);
                                callCounters.setLinks_IN_USE(-1);
                            }
                        }

                        if (callProgress[i].getEnd() <= callCounters.getClock()) {
                            if (nextCall.getFrom() != 0) {
                                links[callProgress[i].getFrom()] =0;
                                links[callProgress[i].getTo()] =0;
//                                links[nextCall.getFrom()] =1;
//                                links[nextCall.getTo()]=1;
                                callCounters.setCompleted_call(1);
                                callProgress[i].setFrom(nextCall.getFrom());
                                callProgress[i].setTo(nextCall.getTo());
                                callProgress[i].setEnd(nextCall.getArrivalTime() + nextCall.getLength());
                                nextCall.setFrom(0);
                                nextCall.setTo(0);
                                nextCall.setArrivalTime(0);
                                callCounters.setLinks_IN_USE(-1);
                            } else {
                                links[callProgress[i].getFrom()]=0;
                                links[callProgress[i].getTo()]=0;
                                callCounters.setCompleted_call(1);
                                callProgress[i].setEnd(0);
                                callProgress[i].setFrom(0);
                                callProgress[i].setTo(0);
                                callCounters.setLinks_IN_USE(-1);
                            }

                        }
                    }
                }
                displayCalls();
            }
        });

        menuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToMenu();
            }
        });

        displayCalls();

    }
public  void goToMenu(){
    ConnectingTelephone telephone = new ConnectingTelephone("Telephone Simulation System");

}

    private void displayCalls() {
        linkInUse.setText(String.valueOf(callCounters.getLinks_IN_USE()));
        processedText.setText(String.valueOf(callCounters.getProcessed_call()));
        completedText.setText(String.valueOf(callCounters.getCompleted_call()));
        blockedText.setText(String.valueOf(callCounters.getBlocked_call()));
        busyText.setText(String.valueOf(callCounters.getBusy_call()));

        //Set Timer
        clockText.setText(String.valueOf(callCounters.getClock()));
        //Set Timer finish

        //Next Call
        fromCall.setText(String.valueOf(nextCall.getFrom()));
        toCall.setText(String.valueOf(nextCall.getTo()));
        lengthCall.setText(String.valueOf(nextCall.getLength()));
        arrivalTimeCall.setText(String.valueOf(nextCall.getArrivalTime()));
        //Next Call end

        //Call Progress
        from1.setText(String.valueOf(callProgress[0].getFrom()));
        to1.setText(String.valueOf(callProgress[0].getTo()));
        end1.setText(String.valueOf(callProgress[0].getEnd()));

        from2.setText(String.valueOf(callProgress[1].getFrom()));
        to2.setText(String.valueOf(callProgress[1].getTo()));
        end2.setText(String.valueOf(callProgress[1].getEnd()));

        from3.setText(String.valueOf(callProgress[2].getFrom()));
        to3.setText(String.valueOf(callProgress[2].getTo()));
        end3.setText(String.valueOf(callProgress[2].getEnd()));
        //Call Progress End

        ArrayList<Integer> arrayList = new ArrayList();
        for (int i = 1; i < 9; i++) {
            arrayList.add(links[i]);
        }
        list1.setListData(arrayList.toArray());
    }

    public static void main(String[] args) {
        CallState callState = new CallState("Call State");

        callState.setSize(1000, 500);
        callState.setVisible(true);
        callState.setLocationRelativeTo(null);


    }
}
