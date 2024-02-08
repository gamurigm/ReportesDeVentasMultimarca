package Modelo;

import Controlador.ControladorVentas;
import Vista.FrmVentasTotales;
import Vista.LookAndFeelUtils;

public class ReporteVentas { 

    public static void main(String[] args) {
        LookAndFeelUtils.setLookAndFeel();
      
        java.awt.EventQueue.invokeLater(() -> {
            FrmVentasTotales frmVentasTotales = new FrmVentasTotales();  
            VentasDAO ventasDAO = new VentasDAO();  
            ControladorVentas controladorVentas = new ControladorVentas(frmVentasTotales, ventasDAO);  
            frmVentasTotales.setVisible(true); 
        });
    }
}
