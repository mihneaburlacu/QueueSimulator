package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Font;

public class ViewText extends JFrame {

    private JPanel contentPane;
    private JTextArea textArea;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewText frame = new ViewText();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public ViewText() {
        setTitle("Queue management system Text");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 950, 620);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textArea = new JTextArea();
        textArea.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        textArea.setBounds(0, 0, 925, 585);
        contentPane.add(textArea);
    }

    public void setText(String msg) {
        this.textArea.setText(msg);
    }
}
