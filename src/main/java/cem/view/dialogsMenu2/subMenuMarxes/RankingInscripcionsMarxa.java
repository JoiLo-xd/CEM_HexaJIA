/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package cem.view.dialogsMenu2.subMenuMarxes;

import cem.controller.Controller;
import cem.model.TO.InscripcionsRanking;
import cem.model.TO.ParticipantEditionTO;
import cem.view.dialogsMenu2.ThingsParticipant;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class RankingInscripcionsMarxa extends javax.swing.JDialog {
    
    //atributos
    private Controller controller;
    private String edicio;

    /**
     * Creates new form RankingInscripcionsMarxa
     */
    //contructor
    public RankingInscripcionsMarxa(java.awt.Frame parent, boolean modal, String edicio) throws SQLException {
        super(parent, modal);
        setTitle("Ranking de les inscripcions");
        setModal(true);
        pack();
        setResizable(false);
        initComponents();
        setLocationRelativeTo(null);
        controller = Controller.getInstance();
        this.edicio = edicio;
        editionjLabel.setText(edicio);
        editionjLabel.setForeground(Color.white);
        jCheckBox1.setForeground(Color.white);
        jCheckBox1.setSelected(true);
        jCheckBox1.setBackground(jPanel1.getBackground());
        listInscripcions(controller.getInscripcions(Integer.parseInt(edicio), (optionsjComboBox.getSelectedIndex()+3)));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        optionsjComboBox = new javax.swing.JComboBox<>();
        rankingjLabel = new javax.swing.JLabel();
        editionjLabel = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        exitjButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 204));

        optionsjComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ranking", "Absent", "Abandonament", "Homes", "Dones", "Curta", "Llarga" }));
        optionsjComboBox.setToolTipText("");
        optionsjComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                optionsjComboBoxItemStateChanged(evt);
            }
        });

        rankingjLabel.setFont(new java.awt.Font("Segoe UI Black", 2, 24)); // NOI18N
        rankingjLabel.setForeground(new java.awt.Color(255, 255, 255));
        rankingjLabel.setText("RANKING");

        editionjLabel.setFont(new java.awt.Font("Segoe UI Black", 2, 24)); // NOI18N

        jCheckBox1.setText("de menor a major");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        exitjButton.setText("sortir");
        exitjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitjButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 2, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Filtratges:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(224, 224, 224)
                .addComponent(rankingjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(editionjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(optionsjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addGap(126, 126, 126))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(exitjButton)
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(530, 530, 530)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(exitjButton)
                .addGap(2, 2, 2)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(optionsjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rankingjLabel)
                    .addComponent(editionjLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Dorsal", "Nom", "Sexe", "Temps", "Assistencia"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //valida wue los campos esten bien
    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        try {
        if (jCheckBox1.isSelected() && optionsjComboBox.getSelectedIndex() == 0) {
            jCheckBox1.setForeground(Color.black);
            listInscripcions(controller.getInscripcions(Integer.parseInt(edicio), (optionsjComboBox.getSelectedIndex()+7)));
        } else {
            jCheckBox1.setForeground(Color.white);
            listInscripcions(controller.getInscripcions(Integer.parseInt(edicio), optionsjComboBox.getSelectedIndex()));
        }
        } catch (SQLException ex) {
            Logger.getLogger(RankingInscripcionsMarxa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    //mira el indice del box1 y llama al getInscripcio del controller
    private void optionsjComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_optionsjComboBoxItemStateChanged
        try {
            if (optionsjComboBox.getSelectedIndex() == 0) {
                jCheckBox1.setEnabled(true);
            } else {
                jCheckBox1.setEnabled(false);
            }
            listInscripcions(controller.getInscripcions(Integer.parseInt(edicio), optionsjComboBox.getSelectedIndex()));
        } catch (SQLException ex) {
            Logger.getLogger(RankingInscripcionsMarxa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_optionsjComboBoxItemStateChanged

    
    //boton salir 
    private void exitjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitjButtonActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_exitjButtonActionPerformed

    //mostrar la tabla
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        JTable source = (JTable)evt.getSource();
        int row = source.rowAtPoint( evt.getPoint() );
        int column = source.columnAtPoint( evt.getPoint() );
        String s=source.getModel().getValueAt(row, column)+"";
        if (s.matches("[0-9]+")){
        if (controller.isInscripcio(s,edicio)){
            String dni = controller.getDNIInscripcioByDorsal(s, edicio);
            ThingsParticipant window = new ThingsParticipant(null, true, dni);
            window.setLocationRelativeTo(this);
            window.setVisible(true);
        }}
        
    }//GEN-LAST:event_jTable1MouseClicked

    //añade a la tabla las filas y columnas de las incripciones
    private void listInscripcions(ArrayList<InscripcionsRanking> inscripcions) {
        DefaultTableModel dtm = new DefaultTableModel(new String[]{"Dorsal","Nom", "Sexe", "Temps", "Assistencia"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // ninguna celda editable
            }
        };
        for (InscripcionsRanking p : inscripcions) {
            dtm.addRow(new String[]{p.getDorsal() + "", p.getNom(), p.getSexe(), p.getTemps(), p.getAssistencia()});
        }
        jTable1.setModel(dtm);
    }
    
    private int getAssistencia() {
        return optionsjComboBox.getSelectedIndex();
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel editionjLabel;
    private javax.swing.JButton exitjButton;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> optionsjComboBox;
    private javax.swing.JLabel rankingjLabel;
    // End of variables declaration//GEN-END:variables
}
