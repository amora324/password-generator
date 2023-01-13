import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

public class PasswordGenerator implements ActionListener {
    Random random = new Random();
    private int length;
    private int flag;

    private boolean upper;
    private boolean lower;
    private boolean number;
    private boolean symbol;

    private char[] password;

    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextField textField1;
    private JTextField textField2;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JCheckBox checkBox3;
    private JCheckBox checkBox4;
    private JFrame frame;
    private JPanel panel;
    public PasswordGenerator() {
        frame = new JFrame();

        checkBox1 = new JCheckBox("Include uppercase letters");
        checkBox2 = new JCheckBox("Include lowercase letters");
        checkBox3 = new JCheckBox("Include numbers");
        checkBox4 = new JCheckBox("Include symbols");

        label1 = new JLabel("Symbols:");
        textField1 = new JTextField("!\"#$%&'()*+,-./:;<=>?@[]^_`{|}~");
        textField1.setPreferredSize(new Dimension(250,40));

        label2 = new JLabel("Password length:");
        textField2 = new JTextField("8");
        textField2.setPreferredSize(new Dimension(250,40));

        JButton button = new JButton("Generate password");
        button.addActionListener(this);

        label3 = new JLabel("Password: ");

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        panel.setLayout(new GridLayout(0,1));

        panel.add(checkBox1);
        panel.add(checkBox2);
        panel.add(checkBox3);
        panel.add(checkBox4);
        panel.add(label1);
        panel.add(textField1);
        panel.add(label2);
        panel.add(textField2);
        panel.add(button);
        panel.add(label3);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Password Generator");
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new PasswordGenerator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        length = Integer.parseInt(textField2.getText());
        password = new char[length];

        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String symbols = (String)textField1.getText();

        if (!checkBox1.isSelected() && !checkBox2.isSelected() && !checkBox3.isSelected() && !checkBox4.isSelected()
        ){
            label3.setText("You must select at least one checkbox!");
        }
        else {
            for (int i = 0; i < length; i++) {
                flag = random.nextInt(4) + 1;
                if (flag == 1 && checkBox1.isSelected()) {
                    password[i] = upperCase.charAt(random.nextInt(upperCase.length()));
                } else if (flag == 2 && checkBox2.isSelected()) {
                    password[i] = lowerCase.charAt(random.nextInt(lowerCase.length()));
                } else if (flag == 3 && checkBox3.isSelected()) {
                    password[i] = numbers.charAt(random.nextInt(numbers.length()));
                } else if (flag == 4 && checkBox4.isSelected()) {
                    password[i] = symbols.charAt(random.nextInt(symbols.length()));
                } else {
                    i--;
                }
            }
            label3.setText("Password: " + Arrays.toString(password)
                    .replace("[", "")
                    .replace(",", "")
                    .replace("]", "")
                    .replace(" ", ""));
        }
    }
}
