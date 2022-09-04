package clases;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

public class Cls_Productos {

    private final String SQL_INSERT_HUELLA = "INSERT INTO huellas (h_id,h_nom,h_ocu,h_fecha) values (?,?,?,?)";
    private final String SQL_INSERT_INGRESO = "INSERT INTO ingreso (i_usuario,i_fecha,i_hora) values (?,?,?)";

    private final String SQL_INSERT_EMP = "INSERT INTO empleado (emp_ced,emp_nom,emp_ape,emp_dir,emp_tel,emp_mail) values (?,?,?,?,?,?)";
    private final String SQL_INSERT_PROV = "INSERT INTO proveedor (prov_ruc,prov_nombre,prov_direccion,prov_geren,prov_telef,prov_email) values (?,?,?,?,?,?)";
    private final String SQL_SELECT_CLI = "SELECT *FROM cliente ORDER BY cli_ape ASC";
    private final String SQL_SELECT_EMP = "SELECT *FROM empleado ORDER BY emp_ape ASC";

    private final String SQL_INSERT_PRO = "INSERT INTO producto (pro_des,pro_cos,pro_pre,pro_cat) values (?,?,?,?)";
    private final String SQL_SELECT_PRO = "SELECT *FROM producto";
    private final String SQL_INSERT_ING = "INSERT INTO ingreso (ing_cpro,ing_can,ing_fecha,ing_prov,ing_proveedor) values (?,?,?,?,?)";
    private final String SQL_INSERT_FAC = "INSERT INTO factura (fac_ced,fac_fec,fac_total) values (?,?,?)";
    private final String SQL_SELECT_ING = "SELECT ing_fecha, pro_cod, pro_des,  ing_can, ing_prov from producto INNER JOIN ingreso On pro_cod = ing_cpro";
    private final String SQL_INSERT_CAT = "INSERT INTO categoria (cat_id,cat_nombre) values (?,?)";

    private final String SQL_INSERT_FILA = "INSERT INTO fila (fil_nfac, fil_cpro, fil_can,fil_fecha, fil_tot) values (?,?,?,?,?)";
    private final String SQL_SELECT_FILA = "SELECT fil_nfac, fil_fecha, pro_cod, pro_des, fil_can, fil_tot from producto INNER JOIN fila On pro_cod = fil_cpro order by fil_nfac";
    private final String SQL_INSERT_USER = "INSERT INTO usuario (usu_nom,usu_contra,usu_tipo) values (?,?,?)";
    private final String SQL_SELECT_USER = "SELECT usu_nom,tipo_nombre FROM usuario INNER JOIN tipousuario ON usu_tipo=tipo_id";
    private final String SQL_SELECT_FACT = "SELECT fac_num, fac_fec, fac_ced, CONCAT(cli_nom,' ',cli_ape), fac_total from factura INNER JOIN cliente On fac_ced = cli_ced";
    private final String SQL_INSERT_KARDEX_ENTRADA = "INSERT INTO kardex (kar_fecha, kar_detalle, kar_codigo_producto, kar_cantidad_entradas, kar_total_entradas, kar_cantidad_saldo, kar_total_saldo) values (?,?,?,?,?,?,?)";
    private final String SQL_INSERT_KARDEX_SALIDA = "INSERT INTO kardex (kar_fecha, kar_detalle, kar_codigo_producto, kar_cantidad_salidas, kar_total_salidas, kar_cantidad_saldo, kar_total_saldo) values (?,?,?,?,?,?,?)";

    private PreparedStatement PS, PS2, PS3;
    private DefaultTableModel DT, DT2, DT3, DT4, DT6, DT7, DT8, DT9, DT10;
    private ResultSet RS, RS3;
    private final Conectar CN;

    public Cls_Productos() {
        PS = null;
        CN = new Conectar();
        PS2 = null;
    }

    private DefaultTableModel setTitulosRegistroCliente() {
        DT6 = new DefaultTableModel();
        DT6.addColumn("Cédula/RUC");
        DT6.addColumn("Apellidos");
        DT6.addColumn("Nombres");
        DT6.addColumn("Dirección");
        DT6.addColumn("Teléfono");
        return DT6;
    }

    private DefaultTableModel setTitulosProductos() {
        DT = new DefaultTableModel();
        DT.addColumn("Codigo");
        DT.addColumn("Nombre");
        DT.addColumn("Costo");
        DT.addColumn("Precio");
        DT.addColumn("Stock");
        return DT;
    }

    private DefaultTableModel setTitulosEntradas() {
        DT3 = new DefaultTableModel();
        DT3.addColumn("Fecha y hora");
        DT3.addColumn("Codigo");
        DT3.addColumn("Nombre");
        DT3.addColumn("Cantidad");
        DT3.addColumn("Proveedor");
        return DT3;
    }

    private DefaultTableModel setTitulosSalidas() {
        DT4 = new DefaultTableModel();
        DT4.addColumn("N° Factura");
        DT4.addColumn("Fecha y hora");
        DT4.addColumn("Codigo de Producto");
        DT4.addColumn("Nombre del Producto");
        DT4.addColumn("Cantidad");
        DT4.addColumn("Valor Total");
        return DT4;
    }

    private DefaultTableModel setTitulosIngreso() {
        DT2 = new DefaultTableModel();
        DT2.addColumn("Id");
        DT2.addColumn("Nombre");
        DT2.addColumn("Stock Actual");
        return DT2;
    }

    private DefaultTableModel setTituloUser() {
        DT7 = new DefaultTableModel();
        DT7.addColumn("Usuario");
        DT7.addColumn("Tipo de Cuenta");
        return DT7;
    }

    private DefaultTableModel setTitulosFactura() {
        DT8 = new DefaultTableModel();
        DT8.addColumn("N° Factura");
        DT8.addColumn("Fecha");
        DT8.addColumn("Cédula");
        DT8.addColumn("Cliente");
        DT8.addColumn("Valor Total");
        return DT8;
    }

    private DefaultTableModel setTitulosInventario() {
        DT9 = new DefaultTableModel();
        DT9.addColumn("Código");
        DT9.addColumn("Producto");
        DT9.addColumn("Entradas");
        DT9.addColumn("Salidas");
        DT9.addColumn("Stock");
        return DT9;
    }

    private DefaultTableModel setTitulosKardex() {
        DT10 = new DefaultTableModel();
        DT10.addColumn("Fecha");
        DT10.addColumn("Detalle");
        DT10.addColumn("Valor Unitario");
        DT10.addColumn("Cantidad");
        DT10.addColumn("Total");
        DT10.addColumn("Cantidad");
        DT10.addColumn("Total");
        DT10.addColumn("Cantidad");
        DT10.addColumn("Total");
        return DT10;
    }

    public int insertDatosCli(String id, String nom, String ocu, String fecha) {
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement(SQL_INSERT_HUELLA);
            PS.setString(1, id);
            PS.setString(2, nom);
            PS.setString(3, ocu);
            PS.setString(4, fecha);

            res = PS.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Usuario Guardado \n Continue registrando la huella dactilar");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al ingresar datos");
            System.err.println("Error al guardar los datos" + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return res;
    }

    public int insertDatosIngreso(String usuario, String fecha, String hora) {
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement(SQL_INSERT_INGRESO);
            PS.setString(1, usuario);
            PS.setString(2, fecha);
            PS.setString(3, hora);

            res = PS.executeUpdate();
            if (res > 0) {

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al ingresar datos");
            System.err.println("Error al guardar los datos" + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return res;
    }

    public int insertDatosEmp(String ced, String nom, String ape, String dir, String tel, String mail) {
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement(SQL_INSERT_EMP);
            PS.setString(1, ced);
            PS.setString(2, nom);
            PS.setString(3, ape);
            PS.setString(4, dir);
            PS.setString(5, tel);
            PS.setString(6, mail);
            res = PS.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Empleado registrado correctamente.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al ingresar datos");
            System.err.println("Error al guardar los datos" + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return res;
    }

    public int BusquedaEmpleado(String cedu) {
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement("Select emp_ced from empleado where emp_ced=" + cedu);
            RS = PS.executeQuery();

            if (RS.next()) {
                res = 1;
                JOptionPane.showMessageDialog(null, "Cédula ya ingresada");
            }

        } catch (SQLException e) {
            System.err.println("Error al listar los datos." + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return res;
    }

    public DefaultTableModel getDatosEmpleado() {
        try {
            setTitulosRegistroCliente();
            PS = CN.getConnection().prepareStatement(SQL_SELECT_EMP);
            RS = PS.executeQuery();
            Object[] fila = new Object[5];
            while (RS.next()) {
                fila[0] = RS.getString(1);
                fila[2] = RS.getString(2);
                fila[1] = RS.getString(3);
                fila[3] = RS.getString(4);
                fila[4] = RS.getString(5);
                DT6.addRow(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los datos." + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return DT6;
    }

    public int BusquedaCliente(String cedu) {
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement("Select cli_ced from cliente where cli_ced=" + cedu);
            RS = PS.executeQuery();

            if (RS.next()) {
                res = 1;
                JOptionPane.showMessageDialog(null, "Cédula ya ingresada");
            }

        } catch (SQLException e) {
            System.err.println("Error al listar los datos." + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return res;
    }

    public DefaultTableModel getDatosCliente() {
        try {
            setTitulosRegistroCliente();
            PS = CN.getConnection().prepareStatement(SQL_SELECT_CLI);
            RS = PS.executeQuery();
            Object[] fila = new Object[5];
            while (RS.next()) {
                fila[0] = RS.getString(1);
                fila[2] = RS.getString(2);
                fila[1] = RS.getString(3);
                fila[3] = RS.getString(4);
                fila[4] = RS.getString(5);
                DT6.addRow(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los datos." + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return DT6;
    }

    public int insertDatosPro(String des, float costo, float precio, int cat) {
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement(SQL_INSERT_PRO);
            PS.setString(1, des);

            PS.setFloat(2, costo);
            PS.setFloat(3, precio);
            PS.setInt(4, cat);

            res = PS.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Registro Guardado");
            }
        } catch (SQLException e) {
            System.err.println("Error al guardar los datos" + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return res;
    }

    public DefaultTableModel getDatosPro() {
        try {
            setTitulosProductos();
            PS = CN.getConnection().prepareStatement(SQL_SELECT_PRO);
            RS = PS.executeQuery();
            Object[] fila = new Object[5];
            while (RS.next()) {
                fila[0] = RS.getInt(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getFloat(3);
                fila[3] = RS.getFloat(4);
                fila[4] = RS.getFloat(5);

                DT.addRow(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los datos." + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return DT;
    }

    public int insertDatosProveedor(String ci, String nom, String dir, String ger, String tel, String mail) {
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement(SQL_INSERT_PROV);
//            PS.setString(1, id);
            PS.setString(1, ci);
            PS.setString(2, nom);
            PS.setString(3, dir);
            PS.setString(4, ger);
            PS.setString(5, tel);
            PS.setString(6, mail);

            res = PS.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Registro Guardado");
            }
        } catch (SQLException e) {
            System.err.println("Error al guardar los datos" + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return res;
    }

    public int actualizarProveedor(String ced, String nom, String ape, String dir, String tel, String mail) {
        String SQL = "UPDATE proveedor SET prov_ruc='" + ced + "',prov_nombre='" + nom + "',prov_direccion='" + dir + "',prov_geren='" + ape + "',prov_telef='" + tel + "', prov_email='" + mail + "'  WHERE prov_ruc=" + ced;
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement(SQL);
            res = PS.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Registro Modificado con Éxito");
            }
        } catch (SQLException e) {
            System.err.println("Error al modificar los datos" + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return res;
    }

    public DefaultTableModel getDatosProveedor() {
        try {
            setTitulosProductos();
            PS = CN.getConnection().prepareStatement(SQL_SELECT_PRO);
            RS = PS.executeQuery();
            Object[] fila = new Object[6];
            while (RS.next()) {
                fila[0] = RS.getInt(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getString(3);
                fila[3] = RS.getString(4);
                fila[4] = RS.getString(5);
                fila[5] = RS.getString(6);

                DT.addRow(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los datos." + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return DT;
    }

    //Método para mostrar datos en tabla del formulario Ingreso
    public DefaultTableModel getDatosIngreso() {
        try {
            setTitulosIngreso();
            PS = CN.getConnection().prepareStatement(SQL_SELECT_PRO);
            RS = PS.executeQuery();
            Object[] fila = new Object[3];
            while (RS.next()) {
                fila[0] = RS.getInt(1);
                fila[1] = RS.getString(2);
//                //fila[2] = RS.getFloat(3);
//                //fila[3] = RS.getFloat(4);
                fila[2] = RS.getInt(5);
                DT2.addRow(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los datos." + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return DT2;
    }

    public int insertCat(String id, String nom) {
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement(SQL_INSERT_CAT);
            PS.setString(1, id);
            PS.setString(2, nom);

            res = PS.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Registro Guardado");
            }

        } catch (SQLException e) {
            System.err.println("Error al guardar los datos" + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return res;
    }

    public int actualizarCat(int id, String nom) {
        String SQL = "UPDATE categoria SET cat_nombre='" + nom + "',WHERE cat_id=" + id;
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement(SQL);
            res = PS.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Registro Modificado con Éxito.");
            }
        } catch (SQLException e) {
            System.err.println("Error al modificar los datos" + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return res;
    }

    //INGRESO DE CANTIDADES DE PRODCUTOS
    public int insertDatosI(int cod, int cant, String fecha, String prov, int proveedor) {
        int res = 0, can = 0;
        try {
            PS = CN.getConnection().prepareStatement(SQL_INSERT_ING);
            PS.setInt(1, cod);
            PS.setInt(2, cant);
            PS.setString(3, fecha);
            PS.setString(4, prov);
            PS.setInt(5, proveedor);
            res = PS.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al guardar los datos3" + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }

        try {
            PS3 = CN.getConnection().prepareStatement("SELECT pro_can from producto WHERE pro_cod=" + cod);
            RS3 = PS3.executeQuery();

            while (RS3.next()) {
                can = RS3.getInt(1);
            }

            can += cant;

        } catch (SQLException e) {
            System.err.println("Error al guardar los datos2" + e.getMessage());
        } finally {
            PS3 = null;
            CN.desconectar();
        }

        try {
            PS2 = CN.getConnection().prepareStatement("UPDATE producto SET pro_can='" + can + "'where pro_cod=" + cod);
            PS2.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al guardar los datos1" + e.getMessage());
        } finally {
            PS2 = null;
            CN.desconectar();
        }

        return res;
    }

    public void BuscarCliente(JTable grilla) {

        try {
            PS = CN.getConnection().prepareStatement("SELECT cli_ced,CONCAT(a.cli_nom,' ',a.cli_ape),cli_dir,cli_tel, cli_mail  FROM cliente a");
            RS = PS.executeQuery();

            DefaultTableModel dtm = new DefaultTableModel();
            dtm.addColumn("Cedula");
            dtm.addColumn("Nombres y Apellidos");
            dtm.addColumn("Dirección");
            dtm.addColumn("Telefono");
            dtm.addColumn("Email");

            while (RS.next()) {

                Object vector[] = new Object[5];

                for (int i = 0; i < 5; i++) {
                    vector[i] = RS.getObject(i + 1);

                }
                dtm.addRow(vector);
                grilla.setModel(dtm);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public void BuscarClienteR(JTable grilla) {

        try {
            PS = CN.getConnection().prepareStatement("SELECT cli_ced,cli_nom,cli_ape,cli_dir,cli_tel, cli_mail  FROM cliente ");
            RS = PS.executeQuery();

            DefaultTableModel dtm = new DefaultTableModel();
            dtm.addColumn("Cedula");
            dtm.addColumn("Nombres");
            dtm.addColumn("Apellido");
            dtm.addColumn("Dirección");
            dtm.addColumn("Telefono");
            dtm.addColumn("Email");

            while (RS.next()) {

                Object vector[] = new Object[6];

                for (int i = 0; i < 6; i++) {
                    vector[i] = RS.getObject(i + 1);

                }
                dtm.addRow(vector);
                grilla.setModel(dtm);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public void BuscarEmpleado(JTable grilla) {

        try {
            PS = CN.getConnection().prepareStatement("SELECT emp_ced,emp_nom,emp_ape,emp_dir,emp_tel,emp_mail FROM empleado");
            RS = PS.executeQuery();

            DefaultTableModel dtm = new DefaultTableModel();
            dtm.addColumn("Cedula");
            dtm.addColumn("Nombres");
            dtm.addColumn("Apellidos");
            dtm.addColumn("Dirección");
            dtm.addColumn("Telefono");
            dtm.addColumn("E-mail");

            while (RS.next()) {

                Object vector[] = new Object[6];

                for (int i = 0; i < 6; i++) {
                    vector[i] = RS.getObject(i + 1);

                }
                dtm.addRow(vector);
                grilla.setModel(dtm);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public void BuscarProveedor(JTable grilla) {

        try {
            PS = CN.getConnection().prepareStatement("SELECT v.prov_ruc, v.prov_nombre, v.prov_direccion, v.prov_geren, v.prov_telef, v.prov_email FROM proveedor v");
            RS = PS.executeQuery();

            DefaultTableModel dtm = new DefaultTableModel();
            dtm.addColumn("RUC");
            dtm.addColumn("Nombre de la empresa");
            dtm.addColumn("Dirección");
            dtm.addColumn("Gerente");
            dtm.addColumn("Teléfono");
            dtm.addColumn("E-mail");

            while (RS.next()) {

                Object vector[] = new Object[6];

                for (int i = 0; i < 6; i++) {
                    vector[i] = RS.getObject(i + 1);

                }
                dtm.addRow(vector);
                grilla.setModel(dtm);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public void buscarproducto(JTable grilla) {
        try {
            PS = CN.getConnection().prepareStatement("SELECT p.pro_cod, p.pro_des,p.pro_pre,p.pro_can FROM producto p");
            RS = PS.executeQuery();

            DefaultTableModel dtm = new DefaultTableModel();
            dtm.addColumn("Codigo");
            dtm.addColumn("Producto");
            dtm.addColumn("Precio U.");
            dtm.addColumn("Stock");

            while (RS.next()) {

                Object vector[] = new Object[4];

                for (int i = 0; i < 4; i++) {
                    vector[i] = RS.getObject(i + 1);

                }
                dtm.addRow(vector);
                grilla.setModel(dtm);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

    }

    public int insertDatosFac(String ced, String fecha, float total) {
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement(SQL_INSERT_FAC);
            PS.setString(1, ced);
            PS.setString(2, fecha);
            PS.setFloat(3, total);

            res = PS.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al guardar los datos" + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return res;
    }

    public int factu() {
        int can = 0;
        try {
            PS = CN.getConnection().prepareStatement("select fac_num from factura order by fac_num desc limit 1");
            RS = PS.executeQuery();

            while (RS.next()) {
                can = RS.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los datos." + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return can;
    }

    public int busquedap(int c) {
        int can = 0;
        try {
            PS = CN.getConnection().prepareStatement("select pro_can from producto where pro_cod=" + c);
            RS = PS.executeQuery();

            while (RS.next()) {
                can = RS.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los datos." + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return can;
    }

    public int actualizarP(int id, String des, float cos, float pre) {
        String SQL = "UPDATE producto SET pro_des='" + des + "',pro_cos='" + cos + "',pro_pre='" + pre + "' WHERE pro_cod=" + id;
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement(SQL);
            res = PS.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Registro Modificado con Éxito.");
            }
        } catch (SQLException e) {
            System.err.println("Error al modificar los datos" + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return res;
    }

    public int eliminarP(int id) {
        String SQL = "DELETE from huellas WHERE h_id=" + id;
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement(SQL);
            res = PS.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar el dato" + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return res;
    }

    public int actualizarC(String ced, String nom, String ape, String dir, String tel, String mail) {
        String SQL = "UPDATE cliente SET cli_ced='" + ced + "',cli_nom='" + nom + "',cli_ape='" + ape + "',cli_dir='" + dir + "',cli_tel='" + tel + "', cli_mail='" + mail + "'  WHERE cli_ced=" + ced;
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement(SQL);
            res = PS.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Registro Modificado con Éxito");
            }
        } catch (SQLException e) {
            System.err.println("Error al modificar los datos" + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return res;
    }

    public int elimiarC(String ced) {
        String SQL = "DELETE from cliente WHERE cli_ced=" + ced;
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement(SQL);
            res = PS.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar el dato" + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return res;
    }

    public int actualizarEmp(String ced, String nom, String ape, String dir, String tel, String mail) {
        String SQL = "UPDATE empleado SET emp_ced='" + ced + "',emp_nom='" + nom + "',emp_ape='" + ape + "',emp_dir='" + dir + "',emp_tel='" + tel + "', emp_mail='" + mail + "'  WHERE emp_ced=" + ced;
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement(SQL);
            res = PS.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Registro Modificado con Éxito");
            }
        } catch (SQLException e) {
            System.err.println("Error al modificar los datos" + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return res;
    }

    public int eliminarEmp(String ced) {
        String SQL = "DELETE from empleado WHERE emp_ced=" + ced;
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement(SQL);
            res = PS.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar el dato" + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return res;
    }

    public DefaultTableModel getDatoC(int crt, String inf) {
        String SQL;
        if (crt == 0) {
            SQL = "SELECT *FROM cliente where cli_ced=" + inf;
        } else {
            SQL = "SELECT *FROM cliente where cli_ape like '" + inf + "%'";
        }
        try {
            setTitulosRegistroCliente();
            PS = CN.getConnection().prepareStatement(SQL);
            RS = PS.executeQuery();
            Object[] fila = new Object[5];
            while (RS.next()) {
                fila[0] = RS.getString(1);
                fila[1] = RS.getString(3);
                fila[2] = RS.getString(2);
                fila[3] = RS.getString(4);
                fila[4] = RS.getString(5);
                DT6.addRow(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los datos." + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return DT6;
    }

    public DefaultTableModel getDatoP(int crt, String inf) {
        String SQL;
        if (crt == 0) {
            SQL = "SELECT *FROM producto where pro_cod=" + inf;
        } else {
            SQL = "SELECT *FROM producto where pro_des like '" + inf + "%'";
        }
        try {
            setTitulosProductos();
            PS = CN.getConnection().prepareStatement(SQL);
            RS = PS.executeQuery();
            Object[] fila = new Object[5];
            while (RS.next()) {
                fila[0] = RS.getInt(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getFloat(3);
                fila[3] = RS.getFloat(4);
                fila[4] = RS.getInt(5);
                DT.addRow(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los datos." + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return DT;
    }
//    getFloat

    public DefaultTableModel getDatosEntradas() {
        try {
            setTitulosEntradas();
            PS = CN.getConnection().prepareStatement(SQL_SELECT_ING);
            RS = PS.executeQuery();
            Object[] fila = new Object[5];
            while (RS.next()) {
                fila[0] = RS.getString(1);
                //fila[1] = RS.getString(2);
                fila[1] = RS.getInt(2);
                fila[2] = RS.getString(3);
                fila[3] = RS.getInt(4);
                fila[4] = RS.getString(5);
                DT3.addRow(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los datos." + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return DT3;
    }

    public DefaultTableModel getDatosSalidas() {
        try {
            setTitulosSalidas();
            PS = CN.getConnection().prepareStatement(SQL_SELECT_FILA);
            RS = PS.executeQuery();
            Object[] fila = new Object[6];
            while (RS.next()) {
                fila[0] = RS.getInt(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getInt(3);
                fila[3] = RS.getString(4);
                fila[4] = RS.getInt(5);
                fila[5] = RS.getFloat(6);
                DT4.addRow(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los datos." + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return DT4;
    }

    public void updateSalidas(int cod, int cant, int n2, String fecha, float vu) {
        int can = 0;
        try {
            PS = CN.getConnection().prepareStatement(SQL_INSERT_FILA);
            PS.setInt(1, n2);
            PS.setInt(2, cod);
            PS.setInt(3, cant);
            PS.setString(4, fecha);
            PS.setFloat(5, vu);
            PS.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al guardar los datos3" + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }

        try {
            PS3 = CN.getConnection().prepareStatement("SELECT pro_can from producto WHERE pro_cod=" + cod);
            RS3 = PS3.executeQuery();

            while (RS3.next()) {
                can = RS3.getInt(1);
            }

            can -= cant;

        } catch (SQLException e) {
            System.err.println("Error al guardar los datos2" + e.getMessage());
        } finally {
            PS3 = null;
            CN.desconectar();
        }

        try {
            PS2 = CN.getConnection().prepareStatement("UPDATE producto SET pro_can='" + can + "'where pro_cod=" + cod);
            PS2.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al guardar los datos1" + e.getMessage());
        } finally {
            PS2 = null;
            CN.desconectar();
        }

    }

    public int idUser() {
        int can = 0;
        try {
            PS = CN.getConnection().prepareStatement("select usu_num from usuario order by usu_num desc limit 1");
            RS = PS.executeQuery();

            while (RS.next()) {
                can = RS.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los datos." + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return can;
    }

    public int insertDatosUser(String user, String contra, int tipo) {
        CantidadTipoU();
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement(SQL_INSERT_USER);
            PS.setString(1, user);
            PS.setString(2, contra);
            PS.setInt(3, tipo);
            res = PS.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Usuario Registrado Correctamente");
            }
        } catch (SQLException e) {
            System.err.println("Error al guardar los datos" + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return res;
    }

    public DefaultTableModel getDatosUser() {
        try {
            setTituloUser();
            PS = CN.getConnection().prepareStatement(SQL_SELECT_USER);
            RS = PS.executeQuery();
            Object[] fila = new Object[2];
            while (RS.next()) {
                fila[0] = RS.getString(1);
                fila[1] = RS.getString(2);
                DT7.addRow(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los datos Inner Join." + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return DT7;
    }

    public void CantidadTipoU() {
        int can = 0;
        try {
            PS = CN.getConnection().prepareStatement("select count(*) from tipousuario");
            RS = PS.executeQuery();

            while (RS.next()) {
                can = RS.getInt(1);
            }

            if (can == 0) {
                PS = CN.getConnection().prepareStatement("INSERT INTO tipousuario (tipo_id,tipo_nombre) values (1,'Administrador'),(2,'Empleado')");
                PS.executeUpdate();
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar tipo de usuarios. " + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.desconectar();
        }
    }

    public int ValidarUsuario(String Usuario, String Contra) {
        int valor = 0;
        try {
            PS = CN.getConnection().prepareStatement("SELECT COUNT(*) FROM usuario WHERE usu_nom='" + Usuario + "' AND usu_contra='" + Contra + "'");
            RS = PS.executeQuery();

            while (RS.next()) {
                valor = RS.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error al validar Usuario. " + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return valor;
    }

    public int TipoUsuario(String U, String P) {
        int tipo = 0;
        try {
            PS = CN.getConnection().prepareStatement("SELECT usu_tipo FROM usuario WHERE usu_nom='" + U + "' AND usu_contra='" + P + "'");
            RS = PS.executeQuery();
            while (RS.next()) {
                tipo = RS.getInt(1);

            }
        } catch (SQLException ex) {
            ex.getMessage();
            System.out.println("Existen Errores!!");
        }

        return tipo;

    }

    public DefaultTableModel getDatosFacturas() {
        try {
            setTitulosFactura();
            PS = CN.getConnection().prepareStatement(SQL_SELECT_FACT);
            RS = PS.executeQuery();
            Object[] fila = new Object[5];
            while (RS.next()) {
                fila[0] = RS.getInt(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getString(3);
                fila[3] = RS.getString(4);
                fila[4] = RS.getFloat(5);
                DT8.addRow(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los datos." + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return DT8;
    }

    public DefaultTableModel getDatosInventario() {
        int can = 0;
        try {
            setTitulosInventario();
            PS = CN.getConnection().prepareStatement("SELECT fil_can from fila where fil_cpro=18");
            RS = PS.executeQuery();

            while (RS.next()) {
                can = RS3.getInt(1);
            }

            Object[] fila = new Object[4];
            while (RS.next()) {
                fila[0] = RS.getInt(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getString(3);
                fila[3] = RS.getFloat(4);
                DT9.addRow(fila);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar los datos." + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return DT9;
    }

    public void eliminarUser(String ced) {
        String SQL = "DELETE from usuario WHERE usu_nom='" + ced + "'";
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement(SQL);
            res = PS.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Usuario Eliminado");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar el dato. " + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
    }

    public int ConfirmarContraseña(String Contra) {
        int valor = 0;
        try {
            PS = CN.getConnection().prepareStatement("SELECT COUNT(*) FROM usuario WHERE usu_contra='" + Contra + "' AND usu_tipo='1'");
            RS = PS.executeQuery();

            while (RS.next()) {
                valor = RS.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error al validar Usuario. " + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return valor;
    }

    public DefaultTableModel getDatosEntradasB(int crt, String inf) {
        String SQL;
        if (crt == 0) {
            SQL = "SELECT ing_fecha, pro_cod, pro_des,  ing_can, ing_prov from producto INNER JOIN ingreso On pro_cod = ing_cpro where pro_cod=" + inf;
        } else {
            SQL = "SELECT ing_fecha, pro_cod, pro_des,  ing_can, ing_prov from producto INNER JOIN ingreso On pro_cod = ing_cpro where pro_des like '" + inf + "%'";
        }
        try {
            setTitulosEntradas();
            PS = CN.getConnection().prepareStatement(SQL);
            RS = PS.executeQuery();
            Object[] fila = new Object[5];
            while (RS.next()) {
                fila[0] = RS.getString(1);
                fila[1] = RS.getInt(2);
                fila[2] = RS.getString(3);
                fila[3] = RS.getInt(4);
                fila[4] = RS.getString(5);
                DT3.addRow(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los datos." + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return DT3;
    }

    public DefaultTableModel getDatosSalidasB(int crt, String inf) {
        String SQL;
        if (crt == 0) {
            SQL = "SELECT fil_nfac, fil_fecha, pro_cod, pro_des, fil_can, fil_tot from producto INNER JOIN fila On pro_cod = fil_cpro where pro_cod=" + inf;
        } else if (crt == 1) {
            SQL = "SELECT fil_nfac, fil_fecha, pro_cod, pro_des, fil_can, fil_tot from producto INNER JOIN fila On pro_cod = fil_cpro where pro_des like '" + inf + "%'";
        } else {
            SQL = "SELECT fil_nfac, fil_fecha, pro_cod, pro_des, fil_can, fil_tot from producto INNER JOIN fila On pro_cod = fil_cpro where fil_nfac=" + inf;
        }
        try {
            setTitulosSalidas();
            PS = CN.getConnection().prepareStatement(SQL);
            RS = PS.executeQuery();
            Object[] fila = new Object[6];
            while (RS.next()) {
                fila[0] = RS.getInt(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getInt(3);
                fila[3] = RS.getString(4);
                fila[4] = RS.getInt(5);
                fila[5] = RS.getFloat(6);
                DT4.addRow(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los datos." + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return DT4;
    }

    public DefaultTableModel getDatosFactB(int crt, String inf) {
        String SQL;
        if (crt == 0) {
            SQL = "SELECT fac_num, fac_fec, fac_ced, CONCAT(cli_nom,' ',cli_ape), fac_total from factura INNER JOIN cliente On fac_ced = cli_ced where fac_num=" + inf;
        } else {
            SQL = "SELECT fac_num, fac_fec, fac_ced, CONCAT(cli_nom,' ',cli_ape), fac_total from factura INNER JOIN cliente On fac_ced = cli_ced where fac_ced=" + inf;
        }
        try {
            setTitulosFactura();
            PS = CN.getConnection().prepareStatement(SQL);
            RS = PS.executeQuery();
            Object[] fila = new Object[5];
            while (RS.next()) {
                fila[0] = RS.getInt(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getString(3);
                fila[3] = RS.getString(4);
                fila[4] = RS.getFloat(5);
                DT8.addRow(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los datos." + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return DT8;
    }

    public int CodPro(String Nom) {
        int can = 0;
        try {
            PS = CN.getConnection().prepareStatement("select pro_cod from producto where pro_des='" + Nom + "'");
            RS = PS.executeQuery();

            while (RS.next()) {
                can = RS.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los datos." + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return can;
    }
//CONSULTA ENTRADAS

    public void insertDatosInvEntradas(int cod, int cant) {
        int can = 0;
        try {
            PS = CN.getConnection().prepareStatement("SELECT pro_ent from producto where pro_cod=" + cod);
            RS = PS.executeQuery();

            while (RS.next()) {
                can = RS.getInt(1);
            }

            can += cant;

        } catch (SQLException e) {
            System.err.println("Error al guardar los datos3" + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }

        try {
            PS2 = CN.getConnection().prepareStatement("UPDATE producto SET pro_ent='" + can + "'where pro_cod=" + cod);
            int r = PS2.executeUpdate();

            if (r > 0) {
                JOptionPane.showMessageDialog(null, "Ingreso Correcto");
            }
        } catch (SQLException e) {
            System.err.println("Error al guardar los datos1" + e.getMessage());
        } finally {
            PS2 = null;
            CN.desconectar();
        }
    }

    public void insertDatosInvSalidas(int cod, int cant) {
        int can = 0;
        try {
            PS = CN.getConnection().prepareStatement("SELECT pro_salidas from producto where pro_cod=" + cod);
            RS = PS.executeQuery();

            while (RS.next()) {
                can = RS.getInt(1);
            }

            can += cant;

        } catch (SQLException e) {
            System.err.println("Error al guardar los datos3" + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }

        try {
            PS2 = CN.getConnection().prepareStatement("UPDATE producto SET pro_salidas='" + can + "'where pro_cod=" + cod);
            PS2.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al guardar los datos1" + e.getMessage());
        } finally {
            PS2 = null;
            CN.desconectar();
        }
    }

    public DefaultTableModel getDatosInv() {
        try {
            setTitulosInventario();
            PS = CN.getConnection().prepareStatement("SELECT pro_cod, pro_des, pro_ent, pro_salidas, pro_can from producto");
            RS = PS.executeQuery();
            Object[] fila = new Object[5];
            while (RS.next()) {
                fila[0] = RS.getInt(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getInt(3);
                fila[3] = RS.getInt(4);
                fila[4] = RS.getInt(5);

                DT9.addRow(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los datos." + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return DT9;
    }

    public DefaultTableModel getDatosInvB(int crt, String inf) {
        String SQL;
        if (crt == 0) {
            SQL = "SELECT pro_cod, pro_des,  pro_ent, pro_salidas, pro_can from producto where pro_cod=" + inf;
        } else {
            SQL = "SELECT pro_cod, pro_des,pro_ent, pro_salidas, pro_can from producto where pro_des like '" + inf + "%'";
        }
        try {
            setTitulosInventario();
            PS = CN.getConnection().prepareStatement(SQL);
            RS = PS.executeQuery();
            Object[] fila = new Object[5];
            while (RS.next()) {
                fila[0] = RS.getInt(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getInt(3);
                fila[3] = RS.getInt(4);
                fila[4] = RS.getInt(5);
                DT9.addRow(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los datos." + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return DT9;
    }

    public float ValorProd(int cod) {
        float v = 0;

        try {
            PS = CN.getConnection().prepareStatement("SELECT pro_pre from producto where pro_cod=" + cod);
            RS = PS.executeQuery();

            while (RS.next()) {
                v = RS.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error al devolver valor del producto. " + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return v;
    }
//CONSULTA

    public float costoProducto(int cod) {
        float v = 0;

        try {
            PS = CN.getConnection().prepareStatement("SELECT pro_cos from producto where pro_cod=" + cod);
            RS = PS.executeQuery();

            while (RS.next()) {
                v = RS.getFloat(1);
            }
        } catch (SQLException e) {
            System.err.println("Error al devolver valor del producto. " + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return v;
    }
//CONSULTA REGISTROS

    public int totalRegistros() {
        int v = 0;

        try {
            PS = CN.getConnection().prepareStatement("SELECT count(*) FROM kardex");
            RS = PS.executeQuery();

            while (RS.next()) {
                v = RS.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error al contar registros. " + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return v;
    }

    public int cantidadSaldo(int producto) {
        int v = 0;

        try {
            PS = CN.getConnection().prepareStatement("SELECT kar_id, kar_cantidad_saldo FROM kardex where kar_codigo_producto =" + producto + " order by kar_id desc limit 1");
            RS = PS.executeQuery();

            while (RS.next()) {
                v = RS.getInt(2);
            }
        } catch (SQLException e) {
            System.err.println("Error al contar cantidad de saldo. " + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        System.out.println("Cantidad Saldo= " + v);
        return v;
    }

    public float totalSaldo(int producto) {
        float v = 0;

        try {
            PS = CN.getConnection().prepareStatement("SELECT kar_id, kar_total_saldo FROM kardex where kar_codigo_producto = " + producto + " order by kar_id desc limit 1");
            RS = PS.executeQuery();

            while (RS.next()) {
                v = RS.getFloat(2);
            }
        } catch (SQLException e) {
            System.err.println("Error al contar total del saldo.. " + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        System.out.println("Cantidad Saldo Total = " + v);
        return v;
    }

    public String nombreProducto(int producto) {
        String v = "";

        try {
            PS = CN.getConnection().prepareStatement("SELECT pro_des from producto where pro_cod = " + producto);
            RS = PS.executeQuery();

            while (RS.next()) {
                v = RS.getString(1);
            }
        } catch (SQLException e) {
            System.err.println("Error al contar total del saldo.. " + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        System.out.println("Cantidad Saldo Total = " + v);
        return v;
    }

    public int insertDatosKardex(String fecha, String detalle, int producto, int cantidad_e, float total_e, int cantidad_saldo, float total_saldo) {
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement(SQL_INSERT_KARDEX_ENTRADA);
            PS.setString(1, fecha);
            PS.setString(2, detalle);
            PS.setInt(3, producto);
            PS.setInt(4, cantidad_e);
            PS.setFloat(5, total_e);
            PS.setInt(6, cantidad_saldo);
            PS.setFloat(7, total_saldo);

            res = PS.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Registro Guardado");
            }
        } catch (SQLException e) {
            System.err.println("Error al guardar los datos" + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return res;
    }

    public int insertDatosKardexSalidas(String fecha, String detalle, int producto, int cantidad_e, float total_e, int cantidad_saldo, float total_saldo) {
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement(SQL_INSERT_KARDEX_SALIDA);
            PS.setString(1, fecha);
            PS.setString(2, detalle);
            PS.setInt(3, producto);
            PS.setInt(4, cantidad_e);
            PS.setFloat(5, total_e);
            PS.setInt(6, cantidad_saldo);
            PS.setFloat(7, total_saldo);

            res = PS.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Registro Guardado");
            }
        } catch (SQLException e) {
            System.err.println("Error al guardar los datos" + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return res;
    }
//CONSULTA

    public DefaultTableModel getDatosKardex(int cod) {
        try {
            setTitulosKardex();
            PS = CN.getConnection().prepareStatement("SELECT kar_fecha, kar_detalle, pro_cos, kar_cantidad_entradas, kar_total_entradas, kar_cantidad_salidas, kar_total_salidas, kar_cantidad_saldo, kar_total_saldo from kardex INNER JOIN producto ON kar_codigo_producto = pro_cod where kar_codigo_producto =" + cod);
            RS = PS.executeQuery();
            Object[] fila = new Object[9];
            while (RS.next()) {
                fila[0] = RS.getString(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getFloat(3);
                fila[3] = RS.getInt(4);
                fila[4] = RS.getFloat(5);
                fila[5] = RS.getInt(6);
                fila[6] = RS.getFloat(7);
                fila[7] = RS.getInt(8);
                fila[8] = RS.getFloat(9);
                DT10.addRow(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los datos." + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return DT10;
    }

    public int BuscarTel(String ced) {
        int v = 0;
        try {
            PS = CN.getConnection().prepareStatement("SELECT cli_tel from cliente where cli_ced=" + ced);
            RS = PS.executeQuery();

            while (RS.next()) {
                v = RS.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar telefono. " + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return v;
    }

    public int BuscarUser(String User) {
        int v = 0;
        try {
            PS = CN.getConnection().prepareStatement("SELECT COUNT(*) from usuario where usu_nom='" + User + "'");
            RS = PS.executeQuery();

            while (RS.next()) {
                v = RS.getInt(1);
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar usuario. " + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return v;
    }

}
