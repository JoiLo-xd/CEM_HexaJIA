package cem.view.dialogs.Menu2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MarxesDialog extends JDialog {
    private JPanel contentPane;
    private JButton exitButton;
    private JButton afegirParticipantButton;
    private JButton editarParticipantButton;
    private JButton sortidaParticipantButton;
    private JButton arribadaParticipantButton;
    private JButton corredorsDeLaMarxaButton;
    private JPanel marxesBg;
    private JPanel buttonsBg;
    private JPanel marxesMenuBg;

    public MarxesDialog(Frame owner, boolean modal) {
        super(owner, modal);
        setContentPane(contentPane);
        setModal(true);
        setSize(600, 500);
        setLocationRelativeTo(null);

        exitButton.addActionListener(new ActionListener() {
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
    }


    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
