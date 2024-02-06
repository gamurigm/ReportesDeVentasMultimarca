package Controlador;

import Modelo.Venta;
import Modelo.VentasDAO;
import Vista.FrmVentasTotales;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ControladorVentas {
    private VentasDAO objetoDAO;
    private Venta objetoVentas;
    private FrmVentasTotales frmVentasTotales; // Agregado para mostrar el formulario

     public ControladorVentas(FrmVentasTotales frmVentasTotales, VentasDAO objetoDAO) {
        this.frmVentasTotales = frmVentasTotales;
        this.objetoDAO = objetoDAO;
    }

    // Método para mostrar el formulario
    public void mostrarFrmVentasTotales() {
        frmVentasTotales.setVisible(true);
    }

    public void llenarTabla(JTable tablaDatos, String seleccion, String valorSeleccionado) {
        DefaultTableModel modeloT = new DefaultTableModel();
        tablaDatos.setModel(modeloT);

        switch (seleccion) {
            case "CLIENTE":
                modeloT.addColumn("Producto");
                modeloT.addColumn("Asesor");
                modeloT.addColumn("Total Venta");
                llenarTablaPorCliente(modeloT, valorSeleccionado);
                break;
            case "PRODUCTO":
                modeloT.addColumn("Cliente");
                modeloT.addColumn("Asesor");
                modeloT.addColumn("Total Venta");
                llenarTablaPorProducto(modeloT, valorSeleccionado);
                break;
//            case "asesor":
//                modeloT.addColumn("Cliente");
//                modeloT.addColumn("Producto");
//                modeloT.addColumn("Total Venta");
//                modeloT.addColumn("Comisión");
//                llenarTablaPorAsesor(modeloT, valorSeleccionado);
//                break;
            default:
                break;
        }
    }

    private void llenarTablaPorCliente(DefaultTableModel modelo, String cliente) {
        List<Venta> ventas = objetoDAO.obtenerVentasPorCliente(cliente);
        for (Venta venta : ventas) {
            Object[] fila = {venta.getProducto(), venta.getAsesor(), venta.getTotalVenta()};
            modelo.addRow(fila);
        }
    }

    private void llenarTablaPorProducto(DefaultTableModel modelo, String producto) {
        // Implementa lógica similar para llenar la tabla por producto
    }

//    private void llenarTablaPorAsesor(DefaultTableModel modelo, String asesor) {
//        List<Venta> ventas = objetoDAO.obtenerVentasPorAsesor(asesor);
//        for (Venta venta : ventas) {
//            Object[] fila = {venta.getCliente(), venta.getProducto(), venta.getTotalVenta(), venta.getComision()};
//            modelo.addRow(fila);
//        }
    }

