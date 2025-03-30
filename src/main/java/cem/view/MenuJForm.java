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

    private MarxesDialog marxes;

    public MenuJForm(){
        setContentPane(Contentpanel);
        setResizable(false);
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
                createMarxesDialog();
                setVisible(false);
                marxes.setVisible(true);
                setVisible(true);
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

    private void createMarxesDialog() {
        marxes = new MarxesDialog(this, true);
    }
}
