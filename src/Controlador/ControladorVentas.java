package Controlador;

import Modelo.Venta;
import Modelo.VentasDAO;
import Vista.FrmVentasTotales;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ControladorVentas {
    private VentasDAO objetoDAO;
    private FrmVentasTotales frmVentasTotales;

    public ControladorVentas(FrmVentasTotales frmVentasTotales, VentasDAO objetoDAO) {
        this.frmVentasTotales = frmVentasTotales;
        this.objetoDAO = objetoDAO;

        frmVentasTotales.getCmbCliente().addActionListener(e -> {
            String clienteSeleccionado = (String) frmVentasTotales.getCmbCliente().getSelectedItem();
            llenarTabla(frmVentasTotales.tblVentas, "CLIENTE", clienteSeleccionado);
        });

        frmVentasTotales.getCmbProducto().addActionListener(e -> {
            String productoSeleccionado = (String) frmVentasTotales.getCmbProducto().getSelectedItem();
            llenarTabla(frmVentasTotales.tblVentas, "PRODUCTO", productoSeleccionado);
        });

        frmVentasTotales.getCmbAsesor().addActionListener(e -> {
            String asesorSeleccionado = (String) frmVentasTotales.getCmbAsesor().getSelectedItem();
            llenarTabla(frmVentasTotales.tblVentas, "asesor", asesorSeleccionado);
        });
    }

    public void mostrarFrmVentasTotales() {
        frmVentasTotales.setVisible(true);
    }

    private void llenarTabla(JTable tablaDatos, String seleccion, String valorSeleccionado) {
        DefaultTableModel modeloT = new DefaultTableModel();
        tablaDatos.setModel(modeloT);

        switch (seleccion) {
            case "CLIENTE" -> {
                modeloT.addColumn("Producto");
                modeloT.addColumn("Asesor");
                modeloT.addColumn("Total Venta");
                llenarTablaPorCliente(modeloT, valorSeleccionado);
            }

            case "PRODUCTO" -> {
                modeloT.addColumn("Cliente");
                modeloT.addColumn("Asesor");
                modeloT.addColumn("Total Venta");
                llenarTablaPorProducto(modeloT, valorSeleccionado);
            }

            case "ASESOR" -> {
                modeloT.addColumn("Cliente");
                modeloT.addColumn("Producto");
                modeloT.addColumn("Total Venta");
                modeloT.addColumn("Comisión");
                llenarTablaPorAsesor(modeloT, valorSeleccionado);
            }
        }
    }

    private void llenarTablaPorCliente(DefaultTableModel modelo, String cliente) {
        List<Venta> ventas = objetoDAO.obtenerVentasPorCliente(cliente);
        for (Venta venta : ventas) {
            Object[] fila = {venta.getProducto(), venta.getAsesor(), venta.getValor()};
            modelo.addRow(fila);
        }
    }

    private void llenarTablaPorProducto(DefaultTableModel modelo, String producto) {
        List<Venta> ventas = objetoDAO.obtenerVentasPorProducto(producto);
        for (Venta venta : ventas) {
            Object[] fila = {venta.getCliente(), venta.getAsesor(), venta.getValor(), venta.getComision()};
            modelo.addRow(fila);
        }
    }

    private void llenarTablaPorAsesor(DefaultTableModel modelo, String asesor) {
        List<Venta> ventas = objetoDAO.obtenerVentasPorAsesor(asesor);
        for (Venta venta : ventas) {
            Object[] fila = {venta.getCliente(), venta.getProducto(), venta.getValor(), venta.getComision()};
            modelo.addRow(fila);
        }
    }
}
