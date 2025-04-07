package cem.view.dialogs.Menu2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditParticipantDialog extends JDialog {
    //atributos
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton nomButton;
    private JButton cognomsButton;
    private JButton sexeButton;
    private JButton poblacioButton;
    private JButton numeroTelefonButton;
    private JButton mailButton;
    private JButton entitatButton;
    private JButton federatButton;
    private JPanel buttonsValuesPanel;
    private JPanel menuPanel;
    private JPanel acceptCancelButtonsPanel;
    private JPanel acceptCancelButtonsBg;

    //constructor
    public EditParticipantDialog(Frame owner, boolean modal) {
        super(owner, modal);
        setContentPane(contentPane);
        setTitle("Modificar participant");
        setModal(true);
        setResizable(false);
        setSize(700, 600);
        setLocationRelativeTo(null);

        //accion del boton ok
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        //accion del boton cancel
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
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    //vuelve al menu inicial ya que elimina esa ventana
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
