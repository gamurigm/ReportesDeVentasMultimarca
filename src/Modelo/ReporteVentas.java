package Modelo;

import Controlador.ControladorVentas;
import Vista.FrmVentasTotales;
import Vista.LookAndFeelUtils;

public class ReporteVentas { 

    public static void main(String[] args) {
        LookAndFeelUtils.setLookAndFeel();
      
        java.awt.EventQueue.invokeLater(() -> {
            FrmVentasTotales frmVentasTotales = new FrmVentasTotales();  // Crear la instancia de la forma
            VentasDAO ventasDAO = new VentasDAO();  // Crear la instancia del DAO
            ControladorVentas controladorVentas = new ControladorVentas(frmVentasTotales, ventasDAO);  // Crear la instancia del controlador
            
            frmVentasTotales.setVisible(true);  // Hacer visible la forma
        });
    }
}
