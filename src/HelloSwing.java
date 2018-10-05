package ru.jf17.demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelloSwing extends JFrame {

    JLabel jLabel;
    JTextField jTextField;
    JButton jButton;
    DataClass data;
    ActionListener actionListener;


    public HelloSwing( DataClass data_IN) throws HeadlessException {

        super();
        this.setSize(300, 200);
        this.getContentPane().setLayout(null);
        this.add(getJLabel(), null);
        this.add(getJTextField(), null);
        this.add(getJButton(), null);
        this.setTitle("Hello LWJGL 3 + Swing.");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        data = data_IN;

        actionListener = new TestActionListener();

        jButton.addActionListener(actionListener);
    }

    private javax.swing.JLabel getJLabel() {
        if(jLabel == null) {
            jLabel = new javax.swing.JLabel();
            jLabel.setBounds(34, 49, 53, 18);
            jLabel.setText("Name:");
        }
        return jLabel;
    }

    private javax.swing.JTextField getJTextField() {
        if(jTextField == null) {
            jTextField = new javax.swing.JTextField();
            jTextField.setBounds(96, 49, 160, 20);
        }
        return jTextField;
    }

    private javax.swing.JButton getJButton() {
        if(jButton == null) {
            jButton = new javax.swing.JButton();
            jButton.setBounds(103, 110, 71, 27);
            jButton.setText("UP");
        }
        return jButton;
    }


    public class TestActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //Код, который нужно выполнить при нажатии

            data.setUp_view_roty(data.getUp_view_roty() + data.getPLUS());
            data.setDown_view_roty(data.getDown_view_roty() + data.getPLUS());
        }
    }
}
