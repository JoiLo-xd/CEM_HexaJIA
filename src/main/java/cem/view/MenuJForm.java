package cem.view;

import cem.view.dialogs.Menu2.*;
import cem.view.subMenuMarxes.MarxesDialog;

import javax.swing.*;
import java.awt.event.*;

public class MenuJForm extends JFrame {

    //atributos
    private JPanel Contentpanel;
    private JButton ExitButton;
    private JButton marxesButton;
    private JButton crearMarxaButton;
    private JButton altaEsportistaButton;
    private JButton modificarEsportistaButton;
    private JButton mostrarEstadístiquesButton;

    private ShowMarxes showMarxes;
    private CreateMarxaDialog createMarxaDialog;
    private EditParticipantDialog editParticipantDialog;
    private RegisterParticipantDialog registerParticipantDialog;
    private WatchStatsDialog watchStatsDialog;

    //constructor
    public MenuJForm(){
        setContentPane(Contentpanel);
        setTitle("Menu principal");
        setResizable(false);
        setSize(500, 400);

        //accion boton salir, elimina la ventana
        ExitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //boton marxes, se muestran las marxas que hay creadas
        marxesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createMarxesDialog();
                setVisible(false);
                showMarxes.setVisible(true);
                setVisible(true);
            }
        });

        // boton que sirve para crear una marxa
        crearMarxaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createCreateMarxaDialog();
                setVisible(false);
                createMarxaDialog.setVisible(true);
                setVisible(true);
            }
        });

        // boton que sirve para crear un corredor
        altaEsportistaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createRegisterParticipantDialog();
                setVisible(false);
                registerParticipantDialog.setVisible(true);
                setVisible(true);
            }
        });
        // boton que sirve para modificar los datos de un corredor
        modificarEsportistaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createEditParticipantDialog();
                setVisible(false);
                editParticipantDialog.setVisible(true);
                setVisible(true);
            }
        });

        // boton que sirve para ver las estadisiticas
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
        showMarxes = new ShowMarxes(this, false);
    }

    private void createEditParticipantDialog() {
        editParticipantDialog = new EditParticipantDialog(this, false);
    }

    private void createCreateMarxaDialog() {
        createMarxaDialog = new CreateMarxaDialog(this, false);
    }

    private void createRegisterParticipantDialog() {
        registerParticipantDialog = new RegisterParticipantDialog(this, false);
    }

    private void createWatchStatsDialog() {
        watchStatsDialog = new WatchStatsDialog(this, false);
    }
}
