package Modelo;

public class Venta {
    private String cliente;
    private String producto;
    private String asesor;
    private double valor;
    private double comision;

    public Venta(String cliente, String producto, String asesor, double valor, double comision) {
        this.cliente = cliente;
        this.producto = producto;
        this.asesor = asesor;
        this.valor = valor;
        this.comision = comision;
    }

    public Venta() {
        
    }

    public String getCliente() {
        return cliente;
    }

    public String getProducto() {
        return producto;
    }

    public String getAsesor() {
        return asesor;
    }

    public double getValor() {
        return valor;
    }

    public double getComision() {
        return comision;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public void setAsesor(String asesor) {
        this.asesor = asesor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    @Override
    public String toString() {
        return "Venta{" + "cliente=" + cliente + ", producto=" + producto + ", asesor=" + asesor + ", valor=" + valor + ", comision=" + comision + '}';
    }

    public Object getTotalVenta() {
        return 0;
    }
}
