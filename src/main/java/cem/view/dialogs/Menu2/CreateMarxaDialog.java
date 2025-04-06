package cem.view.dialogs.Menu2;

import cem.controller.Controller;
import cem.model.Marxa;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

public class CreateMarxaDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JSpinner editionSpinner;
    private JLabel errorMessage;
    private HashMap<Integer, Marxa> marxes;
    private Controller controller;


    public CreateMarxaDialog(Frame owner, boolean modal) {
        super(owner, modal);
        errorMessage.setVisible(false);
        setContentPane(contentPane);
        setModal(true);
        setSize(600, 500);
        setLocationRelativeTo(null);
        controller = Controller.getInstance();
        marxes= (HashMap<Integer, Marxa>) Controller.getInstance().getMarxes();


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }

        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        editionSpinner.addComponentListener(new ComponentAdapter() {
        });
        editionSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (marxes.containsKey((int) editionSpinner.getValue())) {
                    errorMessage.setVisible(true);
                    buttonOK.setEnabled(false);
                } else {
                    errorMessage.setVisible(false);
                    buttonOK.setEnabled(true);
                }
            }
        });
    }

    private void onOK() {
        // add your code here
        if (marxes.containsKey((int)editionSpinner.getValue())) {
            errorMessage.setVisible(true);
        } else {
            errorMessage.setVisible(false);
            controller.addMarxa((int)editionSpinner.getValue());
            dispose();
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
