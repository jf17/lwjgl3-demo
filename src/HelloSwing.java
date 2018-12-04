package ru.jf17.demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelloSwing extends JFrame {

    JLabel jLabel;
    JTextField jTextField;
    JButton jButton;
    JButton jButton2;
    DataClass data;
    ActionListener actionListener;
    ActionListener actionListener2;
    float transparency; // прозрачность
    float transparencyON; // прозрачность
    float transparencyOFF;


    public HelloSwing( DataClass data_IN) throws HeadlessException {

        super();
         transparencyON = 0.5f;
         transparencyOFF = 1f;

        this.setSize(300, 400);
        this.setLocation(960,100);
        this.getContentPane().setLayout(null);
        this.add(getJLabel(), null);
        this.add(getJTextField(), null);
        this.add(getJButton(), null);
        this.add(getJButton2(), null);
        this.setTitle("Hello LWJGL 3 + Swing.");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        data = data_IN;

        actionListener = new TestActionListener();
        actionListener2 = new TestActionListener2();

        jButton.addActionListener(actionListener);
        jButton2.addActionListener(actionListener2);
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

    private javax.swing.JButton getJButton2() {
        if(jButton2 == null) {
            jButton2 = new javax.swing.JButton();
            jButton2.setBounds(58, 150, 200, 27);
            jButton2.setText("Прозрачность ВКЛ/ВЫКЛ");
        }
        return jButton2;
    }


    public class TestActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //Код, который нужно выполнить при нажатии

            data.setUp_view_roty(data.getUp_view_roty() + data.getPLUS());
            data.setDown_view_roty(data.getDown_view_roty() + data.getPLUS());
        }
    }

    public class TestActionListener2 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //Код, который нужно выполнить при нажатии

            data.setTransparency();
        }
    }

}
