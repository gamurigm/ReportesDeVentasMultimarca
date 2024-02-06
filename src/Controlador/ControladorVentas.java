package Controlador;

import Modelo.Venta;
import Modelo.VentasDAO;
import Vista.FrmVentasTotales;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorVentas implements ActionListener {

    private VentasDAO ventasDAO;
    private FrmVentasTotales frmVentasTotales;
    private Venta venta; // Añadido el atributo Venta

    public ControladorVentas(FrmVentasTotales vista, VentasDAO dao) {
        this.ventasDAO = dao;
        this.frmVentasTotales = vista;
        this.venta = new Venta();
        configurarEventos();
        cargarDatosIniciales();
    }

    private void configurarEventos() {
        frmVentasTotales.cmbCliente.addActionListener(this);
        frmVentasTotales.cmbProducto.addActionListener(this);
        frmVentasTotales.cmbAsesor.addActionListener(this);
    }

    private void cargarDatosIniciales() {
        // Lógica para cargar datos iniciales en tus ComboBox
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedItem = ((javax.swing.JComboBox<?>) e.getSource()).getSelectedItem().toString();

        if (e.getSource() == frmVentasTotales.cmbCliente) {
            venta.setCliente(selectedItem);
            cargarVentasPorCliente(venta);
        } else if (e.getSource() == frmVentasTotales.getCmbProducto()) {
            venta.setProducto(selectedItem);
            cargarVentasPorProducto(venta);
        } else if (e.getSource() == frmVentasTotales.getCmbAsesor()) {
            venta.setAsesor(selectedItem);
            cargarVentasPorAsesor(venta);
        }
    }

    public void cargarVentasPorCliente(Venta venta) {
        List<Venta> ventas = ventasDAO.obtenerVentasPorCliente(venta.getCliente());
        actualizarTabla(ventas);
    }

    public void cargarVentasPorProducto(Venta venta) {
        List<Venta> ventas = ventasDAO.obtenerVentasPorProducto(venta.getProducto());
        actualizarTabla(ventas);
    }

    public void cargarVentasPorAsesor(Venta venta) {
        List<Venta> ventas = ventasDAO.obtenerVentasPorAsesor(venta.getAsesor());
        actualizarTabla(ventas);
    }

    public void actualizarTabla(List<Venta> ventas) {
        DefaultTableModel model = (DefaultTableModel) frmVentasTotales.tblVentas.getModel();
        model.setRowCount(0);

        for (Venta venta : ventas) {
            Object[] rowData = {
                venta.getProducto(),
                venta.getAsesor(),
                venta.getTotalVenta(),
                venta.getComision()
            };
            model.addRow(rowData);
        }
    }

    // Método adicional para mostrar mensajes
    private void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(frmVentasTotales, mensaje, titulo, tipo);
    }
}
