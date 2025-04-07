package cem.view.dialogs.Menu2;

import cem.controller.Controller;
import cem.enums.Sexe;
import cem.exceptions.AdditionException;
import cem.exceptions.CorredoresException;
import cem.model.Corredor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class RegisterParticipantDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField DNITextField;
    private JLabel NomJLabel;
    private JLabel CognomsJLabel;
    private JLabel DataNaixJLabel;
    private JLabel SexeJLabel;
    private JLabel PoblacioJLabel;
    private JLabel TelefonJLabel;
    private JLabel EmailJLabel;
    private JLabel EntitatJLabel;
    private JLabel FederatJLabel;
    private JTextField nomField;
    private JTextField cognomField;
    private JComboBox sexeBox1;
    private JTextField poblacioField;
    private JTextField telefonField;
    private JTextField emailField;
    private JTextField tentitatField;
    private JSpinner dateSpinner;
    private JLabel errorLabel;
    private JComboBox federatBox;
    private Controller controller;

    public RegisterParticipantDialog(Frame owner, boolean modal) {
        super(owner, modal);
        controller = Controller.getInstance();
        setContentPane(contentPane);
        setModal(true);
        setSize(400, 500);
        setLocationRelativeTo(null);

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

        spinnerDateTimeSetup();

        DNITextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void spinnerDateTimeSetup() {
        SpinnerDateModel dateModel = new SpinnerDateModel(
                new Date(),
                null,
                null,
                Calendar.DAY_OF_MONTH
        );
        dateSpinner.setModel(dateModel);
    }

    private void onOK() {
        boolean ok = true;
        String textoAMostrar = "Error";
        if (!controller.validateNif(DNITextField.getText())) {
            ok = false;
            textoAMostrar = "DNI Invalido";
        } else if (!controller.validateTlf(telefonField.getText())) {
            ok = false;
            textoAMostrar = "Telefon Malament";
        } else if (!controller.validateEmail(emailField.getText())) {
            ok = false;
            textoAMostrar = "Email malament";
        } else {

            boolean federat = "Si".equals(federatBox.getSelectedItem());
            String sexe1 = (String) sexeBox1.getSelectedItem();
            String dni = DNITextField.getText();
            String nom = nomField.getText();
            String cognom = cognomField.getText();
            String poblacio = poblacioField.getText();
            String entitat = tentitatField.getText();
            String email = emailField.getText();
            String telefon = telefonField.getText();
            Instant instant = ((Date) dateSpinner.getValue()).toInstant();
            LocalDate nacimiento = instant.atZone(ZoneId.systemDefault()).toLocalDate();
            if (ok) {
                try {
                    Sexe sexe = Sexe.valueOf(sexe1.toUpperCase());
                    Corredor corredor = new Corredor(dni, nom, cognom, nacimiento, sexe, poblacio, telefon, email, entitat, federat);
                    controller.addCorredor(corredor);
                } catch (CorredoresException e) {
                    ok = false;
                    textoAMostrar = "Faltan alguns camps per posar";
                } catch (AdditionException e) {
                    ok = false;
                    textoAMostrar = "Aquest corredor ja estaba registrat";
                } catch (IllegalArgumentException e){
                    ok = false;
                    textoAMostrar = "Faltan alguns camps per posar";
                }
            }
        }
        if (!ok) {
            JOptionPane.showMessageDialog(this, textoAMostrar, "Error", JOptionPane.ERROR_MESSAGE);


        }
        else{
            JOptionPane.showMessageDialog(this, "Participant Registrat!", "Registrat!", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }


            //dispose();

    }

    private void onCancel () {
        // add your code here if necessary
        dispose();
    }


}