package vista;

import modelo.Pedido;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Vista {
    private JPanel panel1;
    private JButton btnHacerPedido;
    private JButton btnVisualizarPedidos;
    private JButton btnBajaPedido;
    private JComboBox modeloComboBox;
    private JComboBox motorComboBox;
    private JComboBox colorComboBox;
    private JComboBox pilotoAutoComboBox;
    private JComboBox ruedasComboBox;
    private JList<Pedido> listaPedidos;
    private DefaultListModel<Pedido> modeloLista;

    public Vista() {
        JFrame frame = new JFrame("Examen Final. Ej2.");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        modeloLista = new DefaultListModel<>();
        listaPedidos.setModel(modeloLista);

        // Listener del JList. (como si fuera un botón).
        listaPedidos.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                // Si se selecciona algún elemento de la lista, el botón BAJA PEDIDO se activa.
                btnBajaPedido.setEnabled(listaPedidos.getSelectedIndex() != -1);
            }
        });

    }

    // Funciones que rellenan los comboBox con los datos de la base de datos.
    public void setListaModelo(ArrayList<String> modelos) {
        modeloComboBox.removeAllItems();
        for (String s : modelos) {
            modeloComboBox.addItem(s);
        }
    }
    public void setListaMotor(ArrayList<String> motor) {
        motorComboBox.removeAllItems();
        for (String s : motor) {
            motorComboBox.addItem(s);
        }
    }
    public void setListaColor(ArrayList<String> color) {
        colorComboBox.removeAllItems();
        for (String s : color) {
            colorComboBox.addItem(s);
        }
    }
    public void setListaRuedas(ArrayList<Integer> ruedas) {
        ruedasComboBox.removeAllItems();
        for (Integer s : ruedas) {
            ruedasComboBox.addItem(s);
        }
    }
    public void setListaPilotoAuto(ArrayList<Boolean> pilotoAut) {
        pilotoAutoComboBox.removeAllItems();
        for (Boolean s : pilotoAut) {
            pilotoAutoComboBox.addItem(s);
        }
    }

    // Funciones que devuelven los botones.
    public JButton getBtnHacerPedido() {
        return btnHacerPedido;
    }
    public JButton getBtnVisualizarPedidos() {
        return btnVisualizarPedidos;
    }
    public JButton getBtnBajaPedido() {
        return btnBajaPedido;
    }

    // Función que devuelve el panel para usarlo en las ventanas.
    public JPanel getPanel1() {
        return panel1;
    }

    // Funciones que devuelven los contenidos de los comboBox.
    public String getModeloSeleccionado() {
        return modeloComboBox.getSelectedItem().toString();
    }
    public String getMotorSeleccionado() {
        return motorComboBox.getSelectedItem().toString();
    }
    public String getColorSeleccionado() {
        return colorComboBox.getSelectedItem().toString();
    }
    public Integer getRuedasSeleccionado() {
        return (Integer) ruedasComboBox.getSelectedItem();
    }
    public Boolean getPilotoAutoSeleccionado() {
        return (Boolean) pilotoAutoComboBox.getSelectedItem();
    }

    // Mostrar la lista de pedidos en el JList.
    public void mostrarPedidosEnJList(List<Pedido> pedidos) {
        //Limpiamos el contenido antes de mostrar.
        modeloLista.clear();
        for (Pedido p : pedidos) {
            modeloLista.addElement(p);
        }

    }

    // Devuelve el objeto Pedido seleccionado.
    public Pedido getPedidoSeleccionado() {
        return listaPedidos.getSelectedValue();
    }

    // Eliminar el pedido del Jlist y controlar el acceso al botón de Borrar.
    public void eliminarPedidoDeLista(Pedido p) {
        modeloLista.removeElement(p); // borramos el pedido de la lista visible.
    }

}
