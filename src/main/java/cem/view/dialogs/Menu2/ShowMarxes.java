package cem.view.dialogs.Menu2;

import cem.controller.Controller;
import cem.model.Marxa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class ShowMarxes extends JDialog {
    //atributos
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable marxesTable;
    private JScrollPane marxesScrollPane;
    private Controller controller;
    private HashMap<Integer, Marxa> marxes;


    //constructor
    public ShowMarxes(Frame owner, boolean modal) {
        super(owner, modal);
        setContentPane(contentPane);
        setTitle("Marxes");
        setModal(true);
        setSize(600, 500);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(buttonOK);
        controller = Controller.getInstance();
        listMarxes();

        //accion boton OK
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        //accion boton cancel
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

    //MUestra una tabla en la que se van añadiendo las marxas
    private void listMarxes() {
        marxes = (HashMap<Integer,Marxa>) controller.getMarxes();
        DefaultTableModel dtm = (DefaultTableModel) marxesTable.getModel();
        dtm.setColumnIdentifiers(new String[] {"Edició", "Participants"});
        for (Marxa m : marxes.values()){
            dtm.addRow(new Integer[] { m.getEdicio(),m.getInscripcionsMarxa().size()});
        }
        marxesTable.setModel(dtm);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    //elimina la ventana  vuelve al menu
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
