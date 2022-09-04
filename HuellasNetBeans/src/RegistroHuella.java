
import clases.Cls_Productos;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import static java.awt.image.ImageObserver.ERROR;
import javax.swing.JOptionPane;
import java.io.OutputStream;
import java.io.InputStream;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JTextField;

public class RegistroHuella extends javax.swing.JFrame {

    Calendar fechactual = new GregorianCalendar();
    private final Cls_Productos CP;
    //VARIABLES QUE PERMITEN ESTABLER LOS PARAMETROS DEL PUERTO 
    //Y CONEXION DEL MISMO
    SerialPort My_PuertoSerie;
    String PUERTO_IdName;
    private static final int TIMEOUT = 2000; //Milisegundos
    private static final int BAUD_RATE = 57600;
    //VARIABLES QUE CAPTURAN Y ENVIA LOS DATOS SERIALES
    private OutputStream output = null;
    private InputStream input_str = null;
    //PERMITE BUSCAR Y ENUMERAR TODOS LOS PUERTOS DISPONIBLES
    java.util.Enumeration<CommPortIdentifier> portEnum = CommPortIdentifier.getPortIdentifiers();
    int num = 0;

    //DENTRO DE LA PARTE DE INICIALIZACION TAMBIEN SE PUEDEN ASIGNAR
    //VALORES INICIALES PARA LOS CONTROLES
    public RegistroHuella() {
        initComponents();
        CP = new Cls_Productos();
        this.setLocationRelativeTo(null);
        jBtnConectar.setEnabled(false);
        jBtnDesconectar.setEnabled(false);
        jBtnEnrolar.setEnabled(false);
        jBtnEnrolOK.setEnabled(false);
        jBtnEliminar.setEnabled(false);
        jBtnDeleteOK.setEnabled(false);
        jBtnCleanText.setEnabled(false);
        jBtnBorrarTodo.setEnabled(false);
        jd_fecha.setCalendar(fechactual);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBtnBuscar = new javax.swing.JButton();
        jBtnConectar = new javax.swing.JButton();
        jBtnDesconectar = new javax.swing.JButton();
        jCbxPuertos = new javax.swing.JComboBox<>();
        jTxtRecibir = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTxtArea = new javax.swing.JTextArea();
        jBtnEnrolar = new javax.swing.JButton();
        jBtnEliminar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        txtideliminar = new javax.swing.JTextField();
        jBtnEnrolOK = new javax.swing.JButton();
        jBtnDeleteOK = new javax.swing.JButton();
        jBtnBorrarTodo = new javax.swing.JButton();
        jBtnCleanText = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtocu = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jd_fecha = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jBtnBuscar.setText("BUSCAR PUERTOS");
        jBtnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnBuscarActionPerformed(evt);
            }
        });

        jBtnConectar.setText("CONECTAR");
        jBtnConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnConectarActionPerformed(evt);
            }
        });

        jBtnDesconectar.setText("DESCONECTAR");
        jBtnDesconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDesconectarActionPerformed(evt);
            }
        });

        jTxtArea.setColumns(20);
        jTxtArea.setRows(5);
        jScrollPane1.setViewportView(jTxtArea);

        jBtnEnrolar.setText("ENROLAR");
        jBtnEnrolar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEnrolarActionPerformed(evt);
            }
        });

        jBtnEliminar.setText("ELIMINAR");
        jBtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEliminarActionPerformed(evt);
            }
        });

        jLabel3.setText("ID");

        jLabel4.setText("ID");

        txtid.setName("TxtEnrolar"); // NOI18N

        txtideliminar.setName("TxtEliminar"); // NOI18N

        jBtnEnrolOK.setText("ACEPTAR Y GUARDAR");
        jBtnEnrolOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEnrolOKActionPerformed(evt);
            }
        });

        jBtnDeleteOK.setText("ACEPTAR");
        jBtnDeleteOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDeleteOKActionPerformed(evt);
            }
        });

        jBtnBorrarTodo.setText("BORRAR REGISTROS");
        jBtnBorrarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnBorrarTodoActionPerformed(evt);
            }
        });

        jBtnCleanText.setText("LIMPIAR");
        jBtnCleanText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCleanTextActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Leelawadee UI", 0, 24)); // NOI18N
        jLabel1.setText("REGISTRO DE HUELLAS DACTILARES");

        jLabel2.setText("NOMBRE");

        jLabel5.setText("OCUPACIÓN");

        txtocu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtocuActionPerformed(evt);
            }
        });

        jButton1.setText("REGRESAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jd_fecha.setDateFormatString("yyyy/MM/dd");

        jLabel6.setText("FECHA");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jBtnBuscar)
                        .addGap(10, 10, 10)
                        .addComponent(jCbxPuertos, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jBtnConectar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnDesconectar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jTxtRecibir, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(265, 265, 265)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jBtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(jBtnDeleteOK, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(jLabel4)
                                .addGap(35, 35, 35)
                                .addComponent(txtideliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jBtnBorrarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(54, 54, 54)
                        .addComponent(jBtnCleanText, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(57, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jd_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtocu, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(39, 39, 39)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jBtnEnrolOK, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jBtnEnrolar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnBuscar)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jCbxPuertos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jBtnConectar)
                    .addComponent(jBtnDesconectar))
                .addGap(18, 18, 18)
                .addComponent(jTxtRecibir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnEnrolar)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnEnrolOK)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtocu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jd_fecha, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBtnEliminar)
                            .addComponent(jBtnDeleteOK))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel4))
                            .addComponent(txtideliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jBtnBorrarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jBtnCleanText, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81)
                .addComponent(jButton1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBuscarActionPerformed
        while (portEnum.hasMoreElements()) {
            jBtnConectar.setEnabled(true);
            CommPortIdentifier portIdentifier = portEnum.nextElement();
            String Mensajes = portIdentifier.getName() + " - " + getPortTypeName(portIdentifier.getPortType());
            String NombrePuertos = portIdentifier.getName();
            jTxtArea.append(Mensajes + "\n");
            jCbxPuertos.addItem(NombrePuertos);
        }
    }//GEN-LAST:event_jBtnBuscarActionPerformed
    public void keyReleased(KeyEvent e) {
        jBtnEnrolOK.setEnabled(
                txtid.getText().length() != 0
        );
        jBtnEnrolOK.setEnabled(
                txtnombre.getText().length() != 0
        );
        jBtnEnrolOK.setEnabled(
                txtocu.getText().length() != 0
        );
    }

    private void jBtnConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnConectarActionPerformed
        CommPortIdentifier puertoID = null;
        java.util.Enumeration<CommPortIdentifier> listaPuertos = CommPortIdentifier.getPortIdentifiers();

        while (listaPuertos.hasMoreElements()) {
            CommPortIdentifier actualPuertoID = (CommPortIdentifier) listaPuertos.nextElement();
            PUERTO_IdName = jCbxPuertos.getSelectedItem().toString();
            if (PUERTO_IdName.equals(actualPuertoID.getName())) {
                puertoID = actualPuertoID;
                break;
            }
        }

        if (puertoID == null) {
            mostrarError("No se puede conectar al puerto");
            System.exit(ERROR);
        }

        try {
            My_PuertoSerie = (SerialPort) puertoID.open(this.getClass().getName(), TIMEOUT);
            My_PuertoSerie.setSerialPortParams(BAUD_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            input_str = My_PuertoSerie.getInputStream();
            output = My_PuertoSerie.getOutputStream();
            My_PuertoSerie.addEventListener(new SerialReader(input_str));
            My_PuertoSerie.notifyOnDataAvailable(true);

            jBtnConectar.setEnabled(false);
            jBtnConectar.setEnabled(true);
            jBtnEnrolar.setEnabled(true);
            jBtnEnrolOK.setEnabled(true);
            jBtnEliminar.setEnabled(true);
            jBtnDeleteOK.setEnabled(true);
            jBtnCleanText.setEnabled(true);
            jBtnBorrarTodo.setEnabled(true);
            jBtnDesconectar.setEnabled(true);
        } catch (Exception e) {
            mostrarError(e.getMessage());
            System.exit(ERROR);
        }
    }//GEN-LAST:event_jBtnConectarActionPerformed

    private void jBtnDesconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDesconectarActionPerformed
        try {
            My_PuertoSerie.close();
            jBtnConectar.setEnabled(true);
            jBtnEnrolar.setEnabled(false);
            jBtnEnrolOK.setEnabled(false);
            jBtnEliminar.setEnabled(false);
            jBtnDeleteOK.setEnabled(false);
            jBtnCleanText.setEnabled(false);
            jBtnBorrarTodo.setEnabled(false);
            jBtnDesconectar.setEnabled(false);

        } catch (Exception e) {
            mostrarError(e.getMessage());
            System.exit(ERROR);
        }
    }//GEN-LAST:event_jBtnDesconectarActionPerformed

    private void jBtnEnrolarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEnrolarActionPerformed
        enviarDatos("E");
        jBtnEnrolOK.setEnabled(true);

    }//GEN-LAST:event_jBtnEnrolarActionPerformed

    private void jBtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEliminarActionPerformed
        enviarDatos("D");
        String id = txtideliminar.getText();


    }//GEN-LAST:event_jBtnEliminarActionPerformed

    private void jBtnEnrolOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEnrolOKActionPerformed
        // ACEPTAR ENROLAMIEMTO
        if (txtid.getText().isEmpty() || txtnombre.getText().isEmpty() || txtocu.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Faltan campos por llenar");
        } else {

            String IdEnrolar = txtid.getText();
            enviarDatos(IdEnrolar);
            enviarDatos("\n");
        }
        guardar();

    }//GEN-LAST:event_jBtnEnrolOKActionPerformed
    private void guardar() {
        String id = txtid.getText();
        String nom = txtnombre.getText();
        String ocu = txtocu.getText();
        String fecha = ((JTextField) jd_fecha.getDateEditor().getUiComponent()).getText();

        if (num == 0) {
            int respuesta = CP.insertDatosCli(id, nom, ocu, fecha);
            if (respuesta > 0) {
                limpiar();
            }
        } else {

        }
    }
    

    private void limpiar() {
        txtid.setText("");
        txtnombre.setText("");
        txtocu.setText("");

    }
    private void jBtnDeleteOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDeleteOKActionPerformed
        // ACEPTAR ELIMINAR     
        String IdEliminar = txtideliminar.getText();
        enviarDatos(IdEliminar);
        enviarDatos("\n");
    }//GEN-LAST:event_jBtnDeleteOKActionPerformed

    private void jBtnBorrarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBorrarTodoActionPerformed
        JOptionPane.showMessageDialog(this, "Esta a punto de eliminar todos los registros");

        enviarDatos("B"); // BORRAR TODO   
    }//GEN-LAST:event_jBtnBorrarTodoActionPerformed

    private void jBtnCleanTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCleanTextActionPerformed
        // TODO add your handling code here:

        jTxtArea.selectAll();
        jTxtArea.replaceSelection("");


    }//GEN-LAST:event_jBtnCleanTextActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Inicio e = new Inicio();
        e.setVisible(true);
        RegistroHuella.this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtocuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtocuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtocuActionPerformed

//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    private void enviarDatos(String datos) {
        try {
            output.write(datos.getBytes());
        } catch (Exception e) {
            mostrarError(e.getMessage());
            System.exit(ERROR);
        }
    }

    static String getPortTypeName(int portType) {
        switch (portType) {
            case CommPortIdentifier.PORT_SERIAL:
                return "Serial";
            default:
                return "unknown type";
        }
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    public class SerialReader implements SerialPortEventListener {

        private InputStream in;
        private byte[] buffer = new byte[1024];
        private StringBuffer cadena = new StringBuffer();
        private String cadena_aux;

        public SerialReader(InputStream in) {
            this.in = in;
        }

        public void serialEvent(SerialPortEvent arg0) {
            int data;

            try {
                cadena_aux = "";

                int len = 0;
                while ((data = in.read()) > -1) {
                    // El carácter de nueva línea (\n) es equivalente al 
                    // carácter de salto de línea ASCII (hex 0A)

                    if (data == '\n') {
                        //if ( data == '&' ) {//Determina que carater se considera como uno final del mensaje.
                        break;
                    }
                    buffer[len++] = (byte) data;
                    cadena = cadena.append((char) data);
                }

                cadena_aux = cadena.toString();

                jTxtRecibir.setText("");
                jTxtRecibir.setText(cadena.toString());

                jTxtArea.append(cadena_aux + "\n");
                cadena.delete(0, cadena.length());

                //A PARTIR DE AQUI ES NECESARIO UTILIZAR LAS VARIABLES
                //cadena_aux  => como string - pues es la unica variable que contiene los valores que ingresaron
                //buffer[x] = Contiene los datos en una matriz
                //int posicion = cadena_aux.indexOf("ID#1");
                //jTextField1.setText(Integer.toString(posicion));
                if (cadena_aux.indexOf("ID#1") == 0) {
                    jTxtRecibir.setText("");
                    jTxtRecibir.setText("INGRESO USUARIO 1");
                
                    
                }

                if (cadena_aux.indexOf("ID#2") == 0) {
                    jTxtRecibir.setText("");
                    jTxtRecibir.setText("INGRESO USUARIO 2");
                }

                if (cadena_aux.indexOf("ID#3") == 0) {
                    jTxtRecibir.setText("");
                    jTxtRecibir.setText("INGRESO USUARIO 3");
                }
               
            } catch (Exception e) {
                mostrarError(e.getMessage());
            }
        }
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
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
            java.util.logging.Logger.getLogger(Sensor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sensor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sensor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sensor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistroHuella().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnBorrarTodo;
    private javax.swing.JButton jBtnBuscar;
    private javax.swing.JButton jBtnCleanText;
    private javax.swing.JButton jBtnConectar;
    private javax.swing.JButton jBtnDeleteOK;
    private javax.swing.JButton jBtnDesconectar;
    private javax.swing.JButton jBtnEliminar;
    private javax.swing.JButton jBtnEnrolOK;
    private javax.swing.JButton jBtnEnrolar;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jCbxPuertos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTxtArea;
    private javax.swing.JTextField jTxtRecibir;
    private com.toedter.calendar.JDateChooser jd_fecha;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtideliminar;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtocu;
    // End of variables declaration//GEN-END:variables
}
