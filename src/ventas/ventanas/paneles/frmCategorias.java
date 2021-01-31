/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventas.ventanas.paneles;

import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import ventas.configuraciones.globales;

/**
 *
 * @author samv
 */
public class frmCategorias extends javax.swing.JFrame {

    /**
     * Creates new form frmCategorias
     */
    private DefaultTableModel modelo;
    private String id;

    public frmCategorias() {
        initComponents();

        modelo = (DefaultTableModel) mitabla.getModel();

        String query = "select * from categoria order by id ";

        List<Map<String, Object>> resultado = (List<Map<String, Object>>) globales.db.consulta(query, false);

        for (Map<String, Object> item : resultado) {
            modelo.addRow(new Object[]{item.get("id"), item.get("nombre")});
        }
    
        
        
      
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        mitabla = new javax.swing.JTable();
        txtmodificar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nombre");

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        mitabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        mitabla.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                mitablaFocusGained(evt);
            }
        });
        mitabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mitablaMouseClicked(evt);
            }
        });
        mitabla.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                mitablaPropertyChange(evt);
            }
        });
        mitabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mitablaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(mitabla);

        txtmodificar.setText("Modificar");
        txtmodificar.setEnabled(false);
        txtmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmodificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtmodificar)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar)
                    .addComponent(txtmodificar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

        String nombre = txtNombre.getText();

        if (nombre.trim().equalsIgnoreCase("")) {

            txtNombre.setText("");

            JOptionPane.showMessageDialog(null, "El nombre no debe ir vacío ");

            txtNombre.requestFocus();

            return;
        }

        String query = String.format("insert into categoria (nombre)values('%1$s') returning *;", nombre);

        List<Map<String, Object>> resultado = (List<Map<String, Object>>) globales.db.consulta(query, false);

        for (Map<String, Object> item : resultado) {
            nombre = String.valueOf(item.get("nombre"));
            int id = Integer.parseInt(String.valueOf(item.get("id")));

            Object[] mirow = {id, nombre};

            modelo.addRow(mirow);

            txtNombre.setText("");

        }
        JOptionPane.showMessageDialog(null, "Registro insertado correctamente ");
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void mitablaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mitablaFocusGained

        // TODO add your handling code here:
    }//GEN-LAST:event_mitablaFocusGained

    private void mitablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mitablaMouseClicked

        int row = mitabla.getSelectedRow();
        String nombre = mitabla.getModel().getValueAt(row, 1).toString();
        id = mitabla.getModel().getValueAt(row, 0).toString();

        txtNombre.setText(nombre);

        txtmodificar.setEnabled(true);


    }//GEN-LAST:event_mitablaMouseClicked

    private void mitablaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_mitablaPropertyChange

    }//GEN-LAST:event_mitablaPropertyChange

    private void mitablaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mitablaKeyPressed
        System.out.println(evt.getKeyCode());

        if (evt.getKeyCode() == 127) {

            int rowSeleccionado = mitabla.getSelectedRow();

            int opcion = JOptionPane.showConfirmDialog(null, "¿Deseas eliminar el registro de la base de datos");
            if (opcion == JOptionPane.YES_OPTION) {

                String id = String.valueOf(mitabla.getModel().getValueAt(rowSeleccionado, 0));

                String query = "delete from categoria where id = " + id;
                globales.db.consulta(query, true);

                int row = mitabla.getSelectedRow();

                modelo.removeRow(mitabla.getSelectedRow());

                JOptionPane.showMessageDialog(null, "Registro eliminado correctamente");
                mitabla.removeRowSelectionInterval(opcion, opcion);
                
                txtmodificar.setEnabled(false);
            }
        }
    }//GEN-LAST:event_mitablaKeyPressed

    private void txtmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmodificarActionPerformed
        txtmodificar.setEnabled(false);
        String query = String.format("update categoria set nombre = '%1$s' where id = %2$s", txtNombre.getText(), id);

        globales.db.consulta(query, true);

        JOptionPane.showMessageDialog(null, "Registro actualziado correctamente");
        
        
        txtNombre.setText("");
        txtmodificar.setEnabled(false);
    }//GEN-LAST:event_txtmodificarActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_txtNombreKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmCategorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmCategorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmCategorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmCategorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmCategorias().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable mitabla;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JButton txtmodificar;
    // End of variables declaration//GEN-END:variables
}
