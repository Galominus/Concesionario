package modelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class Conexion {

    private static Connection conexion;

    public static Connection getConexion() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalCoches", "root", "root");
        }
        return conexion;
    }

    /*
    private void cargarDatosIniciales(){
        try {
            ArrayList<String> apodos = CorredorDAO.getApodos();
            vista.setListaApodos(apodos);
            ArrayList<String> emails = CorredorDAO.getCorreos();
            vista.setListaEmails(emails);
        } catch (SQLException e) {
            vista.muestraAlexta("Error al cargar la lista de apodos.");
            e.printStackTrace();
        }
    }

    private void configurarEventos() {

        vista.getBtnListarDatosApodo().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String apodo = vista.getApodoSeleccionado();
                try {
                    Corredor c = CorredorDAO.getCorredor(apodo);
                    vista.mostrarTextoPanel("Resumen de corredor; \nid="+c.getId()+"\nnombre="
                            +c.getNombre()+"\napodo="+c.getApodo()+"\ncorreo="+c.getCorreo());
                } catch (SQLException ex) {
                    vista.muestraAlexta("Error al cargar los datos del apodo seleccionado");
                }
            }
        });


     */
}
