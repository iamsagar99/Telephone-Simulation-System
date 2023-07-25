import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeScreen extends JFrame {
    private JPanel panel1;
    private JButton OKButton;
    private static WelcomeScreen ws;

    public WelcomeScreen(String title) {
        super(title);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();

        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConnectingTelephone.main(null);
                ws.setVisible(false);
            }
        });
    }

    public static void main(String[] args) {
        ws = new WelcomeScreen("Telephone System Simulation");
        ws.setSize(750,540);
        ws.setLocationRelativeTo(null);
        ws.setVisible(true);
    }
}
