import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class CalculatorApp extends JFrame implements ActionListener {
    Container c;
    JTextField display;
    JButton[] numberButtons = new JButton[10];
    JButton btnAdd, btnSub, btnMul, btnDiv, btnEqual, btnDel;

    String operator = "";
    double num1 = 0, num2 = 0;

    CalculatorApp() {
        c = getContentPane();
        c.setLayout(null);

        Font f = new Font("Arial", Font.BOLD, 24);

        // Display field
        display = new JTextField();
        display.setBounds(50, 30, 300, 50);
        display.setFont(f);
        display.setEditable(false);
        c.add(display);

        // Number buttons
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(f);
            numberButtons[i].addActionListener(this);
        }

        // Operator buttons
        btnAdd = new JButton("+");
        btnSub = new JButton("-");
        btnMul = new JButton("*");
        btnDiv = new JButton("/");
        btnEqual = new JButton("=");
        btnDel = new JButton("DEL");

        JButton[] ops = {btnAdd, btnSub, btnMul, btnDiv, btnEqual, btnDel};
        for (JButton b : ops) {
            b.setFont(f);
            b.addActionListener(this);
        }

        // Set bounds (grid style manually)
        numberButtons[1].setBounds(50, 100, 60, 50);
        numberButtons[2].setBounds(120, 100, 60, 50);
        numberButtons[3].setBounds(190, 100, 60, 50);
        btnAdd.setBounds(260, 100, 60, 50);

        numberButtons[4].setBounds(50, 160, 60, 50);
        numberButtons[5].setBounds(120, 160, 60, 50);
        numberButtons[6].setBounds(190, 160, 60, 50);
        btnSub.setBounds(260, 160, 60, 50);

        numberButtons[7].setBounds(50, 220, 60, 50);
        numberButtons[8].setBounds(120, 220, 60, 50);
        numberButtons[9].setBounds(190, 220, 60, 50);
        btnMul.setBounds(260, 220, 60, 50);

        numberButtons[0].setBounds(50, 280, 60, 50);
        btnDel.setBounds(120, 280, 60, 50);
        btnEqual.setBounds(190, 280, 60, 50);
        btnDiv.setBounds(260, 280, 60, 50);

        // Add all buttons
        for (JButton b : numberButtons) c.add(b);
        for (JButton b : ops) c.add(b);

        // Frame setup
        setTitle("Calculator");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // Number buttons
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                display.setText(display.getText() + i);
                return;
            }
        }

        // Operator buttons
        if (e.getSource() == btnAdd || e.getSource() == btnSub ||
            e.getSource() == btnMul || e.getSource() == btnDiv) {
            try {
                num1 = Double.parseDouble(display.getText());
                display.setText("");
                operator = ((JButton) e.getSource()).getText();
            } catch (Exception ex) {
                display.setText("Error");
            }
        }

        // Equal button
        if (e.getSource() == btnEqual) {
            try {
                num2 = Double.parseDouble(display.getText());
                double result = 0;

                switch (operator) {
                    case "+": result = num1 + num2; break;
                    case "-": result = num1 - num2; break;
                    case "*": result = num1 * num2; break;
                    case "/": 
                        if (num2 == 0) {
                            display.setText("Divide by 0");
                            return;
                        }
                        result = num1 / num2; 
                        break;
                    default:
                        display.setText("Error");
                        return;
                }
                display.setText(String.valueOf(result));
            } catch (Exception ex) {
                display.setText("Error");
            }
        }

        // Delete button
        if (e.getSource() == btnDel) {
            String text = display.getText();
            if (!text.isEmpty()) {
                display.setText(text.substring(0, text.length() - 1));
            }
        }
    }
}

public class P1 {
    public static void main(String[] args) {
        new CalculatorApp();
    }
}




