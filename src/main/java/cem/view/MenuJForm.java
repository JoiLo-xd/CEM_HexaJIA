package cem.view;

import cem.view.dialogs.Menu2.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuJForm extends JFrame {

    private JPanel Contentpanel;
    private JButton ExitButton;
    private JButton marxesButton;
    private JButton crearMarxaButton;
    private JButton altaEsportistaButton;
    private JButton modificarEsportistaButton;
    private JButton mostrarEstadístiquesButton;

    private MarxesDialog marxes;
    private CreateMarxaDialog createMarxaDialog;
    private EditParticipantDialog editParticipantDialog;
    private RegisterParticipantDialog registerParticipantDialog;
    private WatchStatsDialog watchStatsDialog;

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
                createCreateMarxaDialog();
                setVisible(false);
                createMarxaDialog.setVisible(true);
                setVisible(true);
            }
        });

        altaEsportistaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createRegisterParticipantDialog();
                setVisible(false);
                registerParticipantDialog.setVisible(true);
                setVisible(true);
            }
        });
        modificarEsportistaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createEditParticipantDialog();
                setVisible(false);
                editParticipantDialog.setVisible(true);
                setVisible(true);
            }
        });
        mostrarEstadístiquesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createWatchStatsDialog();
                setVisible(false);
                watchStatsDialog.setVisible(true);
                setVisible(true);
            }
        });
    }

    private void createMarxesDialog() {
        marxes = new MarxesDialog(this, true);
    }

    private void createEditParticipantDialog() {
        editParticipantDialog = new EditParticipantDialog(this, true);
    }

    private void createCreateMarxaDialog() {
        createMarxaDialog = new CreateMarxaDialog(this, true);
    }

    private void createRegisterParticipantDialog() {
        registerParticipantDialog = new RegisterParticipantDialog(this, true);
    }

    private void createWatchStatsDialog() {
        watchStatsDialog = new WatchStatsDialog(this, true);
    }
}
