package controlador;

import modelo.Pedido;
import modelo.PedidoDAO;
import vista.Vista;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Controlador {
    private Vista vista;

    public Controlador(Vista vista) {
        this.vista = vista;
        cargarDatosIniciales();
        configurarEventos();
    }

    // Cargamos los datos al inicio del programa.
    private void cargarDatosIniciales(){
        try {
            ArrayList<String> modelos = PedidoDAO.getModelos();
            vista.setListaModelo(modelos);

            ArrayList<String> motores = PedidoDAO.getMotor();
            vista.setListaMotor(motores);

            ArrayList<String> colores = PedidoDAO.getColores();
            vista.setListaColor(colores);

            ArrayList<Integer> ruedas = PedidoDAO.getRuedas();
            vista.setListaRuedas(ruedas);

            ArrayList<Boolean> pilotoAuto = PedidoDAO.getPilotoAuto();
            vista.setListaPilotoAuto(pilotoAuto);

        } catch (SQLException e) {
            System.out.println("Error al cargar la lista de elementos.");
            e.printStackTrace();
        }
    }

    // Métodos para los botones
    private void configurarEventos(){

        vista.getBtnVisualizarPedidos().addActionListener(e -> {

            try {
                List<Pedido> l = PedidoDAO.getPedido();
                // Si no hay pedidos informamos al usuario con una ventana.
                if (l.isEmpty()){
                    JOptionPane.showMessageDialog(vista.getPanel1(), "No hay ningún pedido realizado.", "Información", JOptionPane.INFORMATION_MESSAGE);
                }else {
                    // Si hay pedidos, lo mostramos en el JList.
                    vista.mostrarPedidosEnJList(l);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }


        });
        vista.getBtnBajaPedido().addActionListener(e -> {

            Pedido pedidoSeleccionado = vista.getPedidoSeleccionado();

            // Si seleccionamos un pedido.
           if (pedidoSeleccionado != null) {
               int confirmacion = JOptionPane.showConfirmDialog(vista.getPanel1(),
                           "¿Desea borrar el pedido con número " + pedidoSeleccionado.getId() + "?",
                           "Select an Option",
                           JOptionPane.YES_NO_CANCEL_OPTION);

               // Borrar el pedido.
               if (confirmacion == JOptionPane.YES_OPTION) {
                   boolean borrado;
                   try {
                       borrado = PedidoDAO.borrarPedido(pedidoSeleccionado.getId());
                   } catch (SQLException ex) {
                       throw new RuntimeException(ex);
                   }

                   // Informamos al usuario si se ha borrado con éxito el pedido.
                   if (borrado) {
                           vista.eliminarPedidoDeLista(pedidoSeleccionado);
                           JOptionPane.showMessageDialog(vista.getPanel1(), "Pedido eliminado correctamente.");
                       } else {
                           JOptionPane.showMessageDialog(vista.getPanel1(), "No se pudo eliminar el pedido.");
                       }
                   }
           }

        });
        vista.getBtnHacerPedido().addActionListener(e -> {

           // Creamos un pedido con los datos seleccionados.
           String modelo = vista.getModeloSeleccionado();
           String motor = vista.getMotorSeleccionado();
           String color = vista.getColorSeleccionado();
           int ruedas = vista.getRuedasSeleccionado();
           boolean piloto = vista.getPilotoAutoSeleccionado();
           Pedido p = new Pedido(modelo, motor, color, ruedas, piloto);

           // Confirmación del pedido.
            int confirmacion = JOptionPane.showConfirmDialog(vista.getPanel1(),
                    "¿Desea encargar el coche?",
                    "Select an Option",
                    JOptionPane.YES_NO_OPTION);

            // Creamos pedido en caso afirmativo.
            if (confirmacion == JOptionPane.YES_OPTION) {

                try {
                  PedidoDAO.agregarPedido(p);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });

    }
}
