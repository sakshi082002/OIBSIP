import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

class Login extends JFrame implements ActionListener {
    JButton submitButton;
    JPanel panel;
    JLabel userLabel, passLabel;
    final JTextField usernameField, passwordField;

    Login() {
        userLabel = new JLabel();
        userLabel.setText("Username:");
        usernameField = new JTextField(15);
        passLabel = new JLabel();
        passLabel.setText("Password:");
        passwordField = new JPasswordField(8);
        submitButton = new JButton("Submit");
        panel = new JPanel(new GridLayout(3, 1));
        panel.add(userLabel);
        panel.add(usernameField);
        panel.add(passLabel);
        panel.add(passwordField);
        panel.add(submitButton);
        add(panel, BorderLayout.CENTER);
        submitButton.addActionListener(this);
        setTitle("Login Form");
    }

    public void actionPerformed(ActionEvent ae) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (!password.equals("")) {
            new OnlineTest(username);
        } else {
            passwordField.setText("Enter Password");
            actionPerformed(ae);
        }
    }
}

class OnlineTest extends JFrame implements ActionListener {
    JLabel questionLabel, timerLabel;
    JRadioButton[] options = new JRadioButton[4];
    JButton nextButton, laterButton, logoutButton, updateProfileButton;
    ButtonGroup buttonGroup;
    int currentQuestionIndex = 0;
    int[] correctAnswers = {0, 2, 2, 0, 1, 0, 1, 0, 0, 1}; // Index of correct answers for each question
    Timer timer;

    OnlineTest(String username) {
        super(username);
        questionLabel = new JLabel();
        add(questionLabel);
        buttonGroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            add(options[i]);
            buttonGroup.add(options[i]);
        }
        nextButton = new JButton("Save and Next");
        laterButton = new JButton("Save for later");
        logoutButton = new JButton("Logout");
        updateProfileButton = new JButton("Update Profile");
        nextButton.addActionListener(this);
        laterButton.addActionListener(this);
        logoutButton.addActionListener(this);
        updateProfileButton.addActionListener(this);
        add(nextButton);
        add(laterButton);
        add(logoutButton);
        add(updateProfileButton);
        set();
        questionLabel.setBounds(30, 40, 450, 20);
        for (int i = 0; i < 4; i++) {
            options[i].setBounds(50, 80 + i * 30, 200, 20);
        }
        nextButton.setBounds(95, 240, 140, 30);
        laterButton.setBounds(270, 240, 150, 30);
        logoutButton.setBounds(95, 280, 140, 30);
        updateProfileButton.setBounds(270, 280, 150, 30);
        timerLabel = new JLabel("Time Left: 10 minutes 0 seconds");
        timerLabel.setBounds(400, 10, 200, 25);
        add(timerLabel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocation(250, 100);
        setVisible(true);
        setSize(600, 350);
        startTimer();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            if (check()) {
                // Increase score if answer is correct
                // For demonstration purpose, displaying the score in console
                System.out.println("Score: " + (currentQuestionIndex + 1));
            }
            currentQuestionIndex++;
            set();
            if (currentQuestionIndex == 9) {
                nextButton.setEnabled(false);
                laterButton.setText("Result");
            }
        } else if (e.getActionCommand().equals("Save for later")) {
            // Logic to save the current question for later
            // For demonstration purpose, displaying message box
            JOptionPane.showMessageDialog(this, "Question saved for later.");
        } else if (e.getActionCommand().equals("Logout")) {
            dispose(); // Close the current window
            new Login(); // Open the login window
        } else if (e.getActionCommand().equals("Update Profile")) {
            // Open a dialog box for updating profile information
            String updatedProfile = JOptionPane.showInputDialog(this, "Enter your updated profile information:");
            // Update the user's profile with the new information
            // For demonstration purpose, just display the updated profile in console
            System.out.println("Updated Profile: " + updatedProfile);
        }
    }

    void set() {
        buttonGroup.clearSelection();
        if (currentQuestionIndex == 0) {
            questionLabel.setText("Que1: Who is known as the father of the C programming language?");
            options[0].setText("Dennis Ritchie");
            options[1].setText("Ken Thompson");
            options[2].setText("Alan Turing");
            options[3].setText("Linus Torvalds");
        } else if (currentQuestionIndex == 1) {
            questionLabel.setText("Que2: What is the output of 'printf(\"%d\", sizeof('a'))' in C?");
            options[0].setText("1");
            options[1].setText("4");
            options[2].setText("97");
            options[3].setText("Compiler error");
        } else if (currentQuestionIndex == 2) {
            questionLabel.setText("Que3: Which of the following is not a valid variable name in C?");
            options[0].setText("my_variable");
            options[1].setText("_variable");
            options[2].setText("123variable");
            options[3].setText("variable123");
        } else if (currentQuestionIndex == 3) {
            questionLabel.setText("Que4: What is the keyword used for dynamic memory allocation in C?");
            options[0].setText("malloc");
            options[1].setText("calloc");
            options[2].setText("new");
            options[3].setText("alloc");
        } else if (currentQuestionIndex == 4) {
            questionLabel.setText("Que5: Which operator is used to access the value stored at a specified address in C?");
            options[0].setText("*");
            options[1].setText("&");
            options[2].setText("->");
            options[3].setText(".");
        } else if (currentQuestionIndex == 5) {
            questionLabel.setText("Que6: What is the size of 'int' data type in C?");
            options[0].setText("4 bytes");
            options[1].setText("2 bytes");
            options[2].setText("Depends on the compiler");
            options[3].setText("8 bytes");
        } else if (currentQuestionIndex == 6) {
            questionLabel.setText("Que7: Which function is used to read a character from the standard input in C?");
            options[0].setText("scanf");
            options[1].setText("getchar");
            options[2].setText("gets");
            options[3].setText("getch");
        } else if (currentQuestionIndex == 7
) {
            questionLabel.setText("Que8: What is the output of 'printf(\"%d\", sizeof(float))' in C?");
            options[0].setText("4");
            options[1].setText("8");
            options[2].setText("Depends on the compiler");
            options[3].setText("2");
        } else if (currentQuestionIndex == 8) {
            questionLabel.setText("Que9: Which header file is used for input and output operations in C?");
            options[0].setText("<stdio.h>");
            options[1].setText("<stdlib.h>");
            options[2].setText("<math.h>");
            options[3].setText("<string.h>");
        } else if (currentQuestionIndex == 9) {
            questionLabel.setText("Que10: What is the output of 'printf(\"%c\", 65)' in C?");
            options[0].setText("65");
            options[1].setText("A");
            options[2].setText("Compiler error");
            options[3].setText("97");
        }
    }

    boolean check() {
        int selectedOption = -1;
        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected()) {
                selectedOption = i;
                break;
            }
        }
        return selectedOption == correctAnswers[currentQuestionIndex];
    }

    void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int timeLeft = 600; // 10 minutes

            public void run() {
                timeLeft--;
                if (timeLeft < 0) {
                    timer.cancel();
                    JOptionPane.showMessageDialog(null, "Time's up!");
                    System.exit(0);
                } else {
                    timerLabel.setText("Time Left: " + (timeLeft / 60) + " minutes " + (timeLeft % 60) + " seconds");
                }
            }
        }, 0, 1000); // Update every second
    }
}

public class e {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login login = new Login();
            login.setSize(400, 250);
            login.setVisible(true);
        });
    }
}

