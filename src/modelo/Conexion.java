package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static Connection conexion;

    // Conexión a la base de datos.
    public static Connection getConexion() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalCoches", "root", "root");
        }
        return conexion;
    }
}
