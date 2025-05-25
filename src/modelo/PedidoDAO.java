package modelo;

import java.sql.*;
import java.util.ArrayList;

public class PedidoDAO {

    public static ArrayList<String> getModelos() throws SQLException {
        Connection con = Conexion.getConexion();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select modelo from opcionesModelo");
        ArrayList<String> modelos = new ArrayList<>();
        while (rs.next()) {
            modelos.add(rs.getString(1));
        }
        return modelos;
    }

    public static ArrayList<String> getMotor() throws SQLException {
        Connection con = Conexion.getConexion();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select motor from opcionesMotor");
        ArrayList<String> motores = new ArrayList<>();
        while (rs.next()) {
            motores.add(rs.getString(1));
        }
        return motores;
    }

    public static ArrayList<String> getColores() throws SQLException {
        Connection con = Conexion.getConexion();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select color from opcionesColor");
        ArrayList<String> colores = new ArrayList<>();
        while (rs.next()) {
            colores.add(rs.getString(1));
        }
        return colores;
    }

    public static ArrayList<Integer> getRuedas() throws SQLException {
        Connection con = Conexion.getConexion();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select ruedas from opcionesRuedas");
        ArrayList<Integer> ruedas = new ArrayList<>();
        while (rs.next()) {
            ruedas.add(rs.getInt(1));
        }
        return ruedas;
    }

    public static ArrayList<Boolean> getPilotoAuto() throws SQLException {
        Connection con = Conexion.getConexion();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select pilotoAutomatico from opcionesPilotoAutomatico");
        ArrayList<Boolean> pilotosAutos = new ArrayList<>();
        while (rs.next()) {
            pilotosAutos.add(rs.getBoolean(1));
        }
        return pilotosAutos;
    }

    public static ArrayList<Pedido> getPedido() throws SQLException {
        Connection con = Conexion.getConexion();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from pedidos");
        ArrayList<Pedido> lista = new ArrayList<>();
        Pedido p;
        while (rs.next()) {
            int id = rs.getInt(1);
            String modelo = rs.getString(2);
            String motor = rs.getString(3);
            String color = rs.getString(4);
            int ruedas = rs.getInt(5);
            boolean pilotoAuto = rs.getBoolean(6);
            p = new Pedido(id, modelo, motor, color, ruedas, pilotoAuto);
            lista.add(p);
        }
        return lista;
    }

    public static boolean borrarPedido(int id) {
        String sql = "DELETE FROM pedidos WHERE id = ?";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
