package cem.view;

import cem.view.dialogs.Menu2.MarxesDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuJForm extends JFrame {

    private JPanel Contentpanel;
    private JButton ExitButton;
    private JButton marxesButton;
    private JButton crearMarxaButton;
    private JButton altaEsportistaButton;
    private JButton modificarEsportistaButton;
    private JButton mostrarEstadístiquesButton;

    public MenuJForm(){
        setContentPane(Contentpanel);
        setResizable(true);
        setSize(500, 400);
        ExitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        marxesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MarxesDialog marxes = new MarxesDialog();
                marxes.setVisible(true);
            }
        });

        crearMarxaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        altaEsportistaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        modificarEsportistaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        mostrarEstadístiquesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
