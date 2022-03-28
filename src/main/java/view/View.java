package view;

import enums.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import javax.swing.JComboBox;
import javax.swing.JButton;

public class View extends JFrame{

    private JPanel contentPane;
    private JLabel timeLimitLabel;
    private JLabel maxProcTimeLabel;
    private JLabel minProcTimeLabel;
    private JLabel nrServerLabel;
    private JLabel selecPolicyLabel;
    private JTextField timeLimitTextField;
    private JTextField maxProcTimeTextField;
    private JTextField minProcTimeTextField;
    private JTextField nrServersTextField;
    private JTextField nrClientsTextField;
    private JButton sendButton;
    private JComboBox selecPolicyComboBox;
    private JLabel finalLabel;
    private JLabel finalSecondLabel;
    //private JLabel finalThirdLabel;
    private JLabel minArrTimeLabel;
    private JTextField minArrTextField;
    private JTextField maxArrTextField;
    private JLabel maxArrTimeLabel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    View frame = new View();
                    frame.setTitle("Queue management system");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public View() {

        setTitle("Queue management system");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        timeLimitLabel = new JLabel("Time Limit:");
        timeLimitLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        timeLimitLabel.setBounds(10, 10, 130, 13);
        contentPane.add(timeLimitLabel);

        maxProcTimeLabel = new JLabel("Max. Processing time:");
        maxProcTimeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        maxProcTimeLabel.setBounds(10, 35, 130, 13);
        contentPane.add(maxProcTimeLabel);

        minProcTimeLabel = new JLabel("Min. Processing time:");
        minProcTimeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        minProcTimeLabel.setBounds(10, 60, 130, 13);
        contentPane.add(minProcTimeLabel);

        nrServerLabel = new JLabel("Number of Servers:");
        nrServerLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        nrServerLabel.setBounds(10, 85, 130, 13);
        contentPane.add(nrServerLabel);

        JLabel nrClientsLabel = new JLabel("Number of Clients:");
        nrClientsLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        nrClientsLabel.setBounds(10, 110, 130, 13);
        contentPane.add(nrClientsLabel);

        selecPolicyLabel = new JLabel("Selection Policy:");
        selecPolicyLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        selecPolicyLabel.setBounds(10, 135, 130, 13);
        contentPane.add(selecPolicyLabel);

        timeLimitTextField = new JTextField();
        timeLimitTextField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        timeLimitTextField.setBounds(150, 8, 100, 19);
        contentPane.add(timeLimitTextField);
        timeLimitTextField.setColumns(10);

        maxProcTimeTextField = new JTextField();
        maxProcTimeTextField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        maxProcTimeTextField.setColumns(10);
        maxProcTimeTextField.setBounds(150, 33, 100, 19);
        contentPane.add(maxProcTimeTextField);

        minProcTimeTextField = new JTextField();
        minProcTimeTextField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        minProcTimeTextField.setColumns(10);
        minProcTimeTextField.setBounds(150, 58, 100, 19);
        contentPane.add(minProcTimeTextField);

        nrServersTextField = new JTextField();
        nrServersTextField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        nrServersTextField.setColumns(10);
        nrServersTextField.setBounds(150, 83, 100, 19);
        contentPane.add(nrServersTextField);

        nrClientsTextField = new JTextField();
        nrClientsTextField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        nrClientsTextField.setColumns(10);
        nrClientsTextField.setBounds(150, 108, 100, 19);
        contentPane.add(nrClientsTextField);

        minArrTimeLabel = new JLabel("Min arrival time:");
        minArrTimeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        minArrTimeLabel.setBounds(251, 10, 100, 13);
        contentPane.add(minArrTimeLabel);

        minArrTextField = new JTextField();
        minArrTextField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        minArrTextField.setBounds(351, 8, 96, 19);
        contentPane.add(minArrTextField);
        minArrTextField.setColumns(10);

        maxArrTimeLabel = new JLabel("Max arrival time:");
        maxArrTimeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        maxArrTimeLabel.setBounds(251, 36, 100, 13);
        contentPane.add(maxArrTimeLabel);

        maxArrTextField = new JTextField();
        maxArrTextField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        maxArrTextField.setColumns(10);
        maxArrTextField.setBounds(351, 33, 96, 19);
        contentPane.add(maxArrTextField);

        SelectionPolicy[] selectionPolicyChoices = new SelectionPolicy[] {SelectionPolicy.SHORTEST_QUEUE, SelectionPolicy.SHORTEST_TIME};
        selecPolicyComboBox = new JComboBox<SelectionPolicy>(selectionPolicyChoices);
        //selecPolicyComboBox = new JComboBox();
        selecPolicyComboBox.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        selecPolicyComboBox.setBounds(150, 132, 100, 21);
        contentPane.add(selecPolicyComboBox);

        sendButton = new JButton("Send");
        sendButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
        sendButton.setBounds(300, 60, 121, 25);
        contentPane.add(sendButton);

        finalLabel = new JLabel("");
        finalLabel.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        finalLabel.setBounds(10, 169, 426, 94);
        contentPane.add(finalLabel);

        finalSecondLabel = new JLabel("");
        finalSecondLabel.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        finalSecondLabel.setBounds(260, 86, 176, 69);
        contentPane.add(finalSecondLabel);

        /*
        finalThirdLabel = new JLabel("");
        finalThirdLabel.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        finalThirdLabel.setBounds(260, 0, 176, 58);
        contentPane.add(finalThirdLabel);
        */
    }

    public String getTimeLimitTextField() {
        return timeLimitTextField.getText();
    }

    public String getMaxProcTimeTextField() {
        return maxProcTimeTextField.getText();
    }

    public String getMinProcTimeTextField() {
        return minProcTimeTextField.getText();
    }

    public String getNrServersTextField() {
        return nrServersTextField.getText();
    }

    public String getNrClientsTextField() {
        return nrClientsTextField.getText();
    }

    public SelectionPolicy getSelecPolicyComboBox() {
        return (SelectionPolicy) selecPolicyComboBox.getSelectedItem();
    }

    public String getMaxArrivalTimeTextField() {
        return this.maxArrTextField.getText();
    }

    public String getMinArrivalTimeTextField() {
        return this.minArrTextField.getText();
    }

    public void setFinalLabelText(String msg) {
        finalLabel.setText(msg);
    }

    public void setFinalSecondLabelText(String msg) {
        finalSecondLabel.setText(msg);
    }
    /*
    public void setFinalThirdLabelText(String msg) {
        finalThirdLabel.setText(msg);
    }
    */
    public void addSendListener(ActionListener actionListener) {
        this.sendButton.addActionListener(actionListener);
    }

}

