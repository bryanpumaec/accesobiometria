

import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import clases.Cls_Productos;
import clases.Conectar;

public final class LogIn extends javax.swing.JFrame {
    int timer;
    private final Cls_Productos CP;

    public static String cap;

    public LogIn() {

        initComponents();
   
     
        CP = new Cls_Productos();
        txtusu.requestFocus();
      
        this.setLocationRelativeTo(null);

    }

    

    
      
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")//
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtusu = new javax.swing.JTextField();
        txtpss = new javax.swing.JPasswordField();
        bts = new javax.swing.JButton();
        btr = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setText("Inicie Sesión");

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel2.setText("USUARIO");

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel3.setText("CONTRASEÑA");

        txtusu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtusuActionPerformed(evt);
            }
        });

        txtpss.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtpssFocusLost(evt);
            }
        });
        txtpss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpssActionPerformed(evt);
            }
        });
        txtpss.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtpssKeyPressed(evt);
            }
        });

        bts.setText("INICIAR SESION");
        bts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsActionPerformed(evt);
            }
        });

        btr.setText("REGRESAR");
        btr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btrActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(txtusu, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(txtpss, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(btr, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(bts, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtusu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtpss, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btr, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bts, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 private void limpiar(){
        txtusu.setText("");
        txtpss.setText("");
    }
    private void btsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsActionPerformed
          String UsuarioDefecto = "User";
        String PassDefecto = "12345";
        
        
        String U = txtusu.getText();
        String P = txtpss.getText();
        String Tipo = "DefaultProg";
        
        int t = CP.TipoUsuario(U, P);
        if(t==1)
            Tipo = "Administrador";
        else if(t==2)
            Tipo = "Empleado";
        
        if(CP.ValidarUsuario(U,P)>0)
        {
            Inicio M = new Inicio();
//            M.userconectado(U,Tipo);
            M.setVisible(true);
            dispose();
            
            if(CP.TipoUsuario(U,P)==2)
                {
                    M.getJMenuBar().getMenu(3).setVisible(false);
                }
        }
        
        else if(U.equals(UsuarioDefecto) && P.equals(PassDefecto)){
            Inicio M = new Inicio();
            M.setVisible(true);
            M.getJMenuBar().getMenu(0).setVisible(false);
            M.getJMenuBar().getMenu(1).setVisible(false);
            M.getJMenuBar().getMenu(2).setVisible(false);
            dispose();
//            M.userconectado(U,Tipo);
        }
        else if(timer==3){
        JOptionPane.showMessageDialog(null, "Ha excedido el número máximo de intentos \n Vuelva mas tarde", "ERROR", JOptionPane.ERROR_MESSAGE);
        menu e = new menu();
        e.setVisible(true);
        LogIn.this.dispose();
        
        }
      else
        {
//            JOptionPane .showMessageDialog(this, );
            JOptionPane.showMessageDialog(this, "Usuario y/o Contraseña incorrecta \n Le resta " +(3-timer)+ " intentos", "ERROR", JOptionPane.WARNING_MESSAGE);
            txtusu.setText("Escriba su nombre de usuario");
            txtpss.setText("");
            timer=  timer + 1;
            limpiar();
        }
    }//GEN-LAST:event_btsActionPerformed

    private void btrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btrActionPerformed
        menu e = new menu();
        e.setVisible(true);
        LogIn.this.dispose();
    }//GEN-LAST:event_btrActionPerformed

    private void txtpssActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpssActionPerformed
String UsuarioDefecto = "User";
        String PassDefecto = "12345";
        
        
        String U = txtusu.getText();
        String P = txtpss.getText();
        String Tipo = "DefaultProg";
        
        int t = CP.TipoUsuario(U, P);
        if(t==1)
            Tipo = "Administrador";
        else if(t==2)
            Tipo = "Empleado";
        
        if(CP.ValidarUsuario(U,P)>0)
        {
            Inicio M = new Inicio();
//            M.userconectado(U,Tipo);
            M.setVisible(true);
            dispose();
            
            if(CP.TipoUsuario(U,P)==2)
                {
                    M.getJMenuBar().getMenu(3).setVisible(false);
                }
        }
        
        else if(U.equals(UsuarioDefecto) && P.equals(PassDefecto)){
            Inicio M = new Inicio();
            M.setVisible(true);
            M.getJMenuBar().getMenu(0).setVisible(false);
            M.getJMenuBar().getMenu(1).setVisible(false);
            M.getJMenuBar().getMenu(2).setVisible(false);
            dispose();
//            M.userconectado(U,Tipo);
        }
        
      else
        {
            JOptionPane.showMessageDialog(this, "Usuario y/o Contraseña incorrecta");
            limpiar();
        }
    }//GEN-LAST:event_txtpssActionPerformed

    private void txtusuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtusuActionPerformed
        // TODO add your handling code here:
        txtusu.requestFocus();

    }//GEN-LAST:event_txtusuActionPerformed

    private void txtpssKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpssKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpssKeyPressed

    private void txtpssFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtpssFocusLost


    }//GEN-LAST:event_txtpssFocusLost

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
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LogIn().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btr;
    private javax.swing.JButton bts;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField txtpss;
    private javax.swing.JTextField txtusu;
    // End of variables declaration//GEN-END:variables


}