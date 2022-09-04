
import clases.Cls_Productos;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import static java.awt.image.ImageObserver.ERROR;
import javax.swing.JOptionPane;
import java.io.OutputStream;
import java.io.InputStream;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JTextField;

public class Sensor extends javax.swing.JFrame implements Runnable {

    String hora, minuto, segundo;
    Thread hilo;
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
    public Sensor() {
        initComponents();
        hilo = new Thread(this);
        hilo.start();
        this.setLocationRelativeTo(null);
        jBtnConectar.setEnabled(false);
        jBtnDesconectar.setEnabled(false);
        CP = new Cls_Productos();
        jBtnCleanText.setEnabled(false);
        jd_fecha.setCalendar(fechactual);
        jBtnDetectar.setEnabled(false);
        jBtnSalirDetec.setEnabled(false);
        jBtnEncender.setEnabled(false);
        jBtnApagar.setEnabled(false);

    }

    public void hora() {
        Calendar calendario = new GregorianCalendar();
        Date Hora = new Date();
        calendario.setTime(Hora);
        hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY) : "0" + calendario.get(Calendar.HOUR_OF_DAY);
        minuto = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
        segundo = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);
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
        jBtnSalirDetec = new javax.swing.JButton();
        jBtnDetectar = new javax.swing.JButton();
        jBtnCleanText = new javax.swing.JButton();
        jBtnEncender = new javax.swing.JButton();
        jBtnApagar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jd_fecha = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        lblhora = new javax.swing.JLabel();

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

        jBtnSalirDetec.setText("FINALIZAR DETECCION");
        jBtnSalirDetec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSalirDetecActionPerformed(evt);
            }
        });

        jBtnDetectar.setText("DETECTAR");
        jBtnDetectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDetectarActionPerformed(evt);
            }
        });

        jBtnCleanText.setText("LIMPIAR");
        jBtnCleanText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCleanTextActionPerformed(evt);
            }
        });

        jBtnEncender.setBackground(new java.awt.Color(51, 255, 0));
        jBtnEncender.setText("ENCENDER");
        jBtnEncender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEncenderActionPerformed(evt);
            }
        });

        jBtnApagar.setBackground(new java.awt.Color(255, 0, 0));
        jBtnApagar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnApagar.setText("APAGAR");
        jBtnApagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnApagarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Leelawadee UI", 0, 24)); // NOI18N
        jLabel1.setText("FUNCIONAMIENTO");

        jd_fecha.setDateFormatString("yyyy/MM/dd");
        jd_fecha.setEnabled(false);

        jButton1.setText("REGRESAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblhora.setText("HORA");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(277, 277, 277)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblhora)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jBtnEncender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jBtnDetectar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(44, 44, 44)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jBtnSalirDetec, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jBtnApagar, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(33, 33, 33)
                                    .addComponent(jBtnCleanText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jTxtRecibir)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jBtnBuscar)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jCbxPuertos, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(30, 30, 30)
                                    .addComponent(jBtnConectar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jBtnDesconectar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(60, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jd_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(217, 217, 217))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jd_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblhora))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnBuscar)
                    .addComponent(jCbxPuertos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnConectar)
                    .addComponent(jBtnDesconectar))
                .addGap(18, 18, 18)
                .addComponent(jTxtRecibir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBtnDetectar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBtnSalirDetec, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBtnApagar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBtnEncender, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jBtnCleanText, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(44, 44, 44))
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

            jBtnCleanText.setEnabled(true);

            jBtnDetectar.setEnabled(true);
            jBtnSalirDetec.setEnabled(true);
            jBtnEncender.setEnabled(true);
            jBtnApagar.setEnabled(true);
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

            jBtnCleanText.setEnabled(false);

            jBtnDetectar.setEnabled(false);
            jBtnSalirDetec.setEnabled(false);
            jBtnEncender.setEnabled(false);
            jBtnApagar.setEnabled(false);
            jBtnDesconectar.setEnabled(false);

        } catch (Exception e) {
            mostrarError(e.getMessage());
            System.exit(ERROR);
        }
    }//GEN-LAST:event_jBtnDesconectarActionPerformed

    private void jBtnSalirDetecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSalirDetecActionPerformed
        // SALIR DETECCION
        enviarDatos("X");
    }//GEN-LAST:event_jBtnSalirDetecActionPerformed

    private void jBtnDetectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDetectarActionPerformed
        enviarDatos("H");
    }//GEN-LAST:event_jBtnDetectarActionPerformed

    private void jBtnCleanTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCleanTextActionPerformed
        // TODO add your handling code here:

        jTxtArea.selectAll();
        jTxtArea.replaceSelection("");


    }//GEN-LAST:event_jBtnCleanTextActionPerformed

    private void jBtnEncenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEncenderActionPerformed
        enviarDatos("C");//ENCENDER PROYECTO

    }//GEN-LAST:event_jBtnEncenderActionPerformed

    private void jBtnApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnApagarActionPerformed
        // APAGAR PROYECTO
        enviarDatos("X");
    }//GEN-LAST:event_jBtnApagarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Inicio e = new Inicio();
        e.setVisible(true);
        Sensor.this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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

    private void ingreso() {

        String usuario = jTxtRecibir.getText();
        String fecha = ((JTextField) jd_fecha.getDateEditor().getUiComponent()).getText();
        String hora = lblhora.getText();
        if (num == 0) {
            int respuesta = CP.insertDatosIngreso(usuario, fecha,hora);
            if (respuesta > 0) {

            }
        } else {

        }
    }

    @Override
    public void run() {
        ///////////////////////////xxxxxxxxxx///////////////////////////
        Thread current = Thread.currentThread();
        while(current==hilo){
            hora();
            lblhora.setText(hora+":"+minuto+":"+segundo);
        }
            
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
                    jTxtRecibir.setText("USUARIO 1");
                    ingreso();
                }

                if (cadena_aux.indexOf("ID#2") == 0) {
                    jTxtRecibir.setText("");
                    jTxtRecibir.setText("USUARIO 2");
                    ingreso();
                }

                if (cadena_aux.indexOf("ID#3") == 0) {
                    jTxtRecibir.setText("");
                    jTxtRecibir.setText("USUARIO 3");
                    ingreso();
                }
                if (cadena_aux.indexOf("ID#4") == 0) {
                    jTxtRecibir.setText("");
                    jTxtRecibir.setText("USUARIO 4");
                    ingreso();
                }
                if (cadena_aux.indexOf("ID#5") == 0) {
                    jTxtRecibir.setText("");
                    jTxtRecibir.setText("USUARIO 5");
                    ingreso();
                }
                if (cadena_aux.indexOf("ID#6") == 0) {
                    jTxtRecibir.setText("");
                    jTxtRecibir.setText("USUARIO 6");
                    ingreso();
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
                new Sensor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnApagar;
    private javax.swing.JButton jBtnBuscar;
    private javax.swing.JButton jBtnCleanText;
    private javax.swing.JButton jBtnConectar;
    private javax.swing.JButton jBtnDesconectar;
    private javax.swing.JButton jBtnDetectar;
    private javax.swing.JButton jBtnEncender;
    private javax.swing.JButton jBtnSalirDetec;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jCbxPuertos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTxtArea;
    private javax.swing.JTextField jTxtRecibir;
    private com.toedter.calendar.JDateChooser jd_fecha;
    private javax.swing.JLabel lblhora;
    // End of variables declaration//GEN-END:variables
}
