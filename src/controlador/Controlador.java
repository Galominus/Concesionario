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

    private void configurarEventos(){

        vista.getBtnVisualizarPedidos().addActionListener(e -> {

            try {
                List<Pedido> l = PedidoDAO.getPedido();
                // Si no hay pedidos informamos al usuario con una ventana.
                if (l.isEmpty()){
                    JOptionPane.showMessageDialog(null, "No hay ningún pedido realizado.", "Información", JOptionPane.INFORMATION_MESSAGE);
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

           if (pedidoSeleccionado != null) {
               int confirmacion = JOptionPane.showConfirmDialog(null,
                           "¿Desea borrar el pedido con número " + pedidoSeleccionado.getId() + "?",
                           "Select an Option",
                           JOptionPane.YES_NO_CANCEL_OPTION);

               if (confirmacion == JOptionPane.YES_OPTION) {
                   boolean borrado;
                   try {
                       borrado = PedidoDAO.borrarPedido(pedidoSeleccionado.getId());
                   } catch (SQLException ex) {
                       throw new RuntimeException(ex);
                   }

                   if (borrado) {
                           vista.eliminarPedidoDeLista(pedidoSeleccionado);
                           JOptionPane.showMessageDialog(null, "Pedido eliminado correctamente.");
                       } else {
                           JOptionPane.showMessageDialog(null, "No se pudo eliminar el pedido.");
                       }
                   }
           } else {
                   JOptionPane.showMessageDialog(null, "No hay ningún pedido seleccionado.");
           }



        });




    }
}
