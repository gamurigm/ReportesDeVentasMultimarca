package Modelo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class VentasDAO {
    private MongoCollection<Document> coleccion;

    public VentasDAO() {
        Conexion objCon = new Conexion();
        this.coleccion = objCon.getColeccion();
    }

    public void insertarVenta(Venta venta) {
        Document documento = new Document();
        documento.append("cliente", venta.getCliente())
                .append("producto", venta.getProducto())
                .append("asesor", venta.getAsesor())
                .append("totalVenta", venta.getTotalVenta())
                .append("comision", venta.getComision());
        coleccion.insertOne(documento);
    }

    public List<Venta> obtenerVentasPorCliente(String cliente) {
        List<Venta> ventas = new ArrayList<>();
        Document consulta = new Document("cliente", cliente);
        
        try (MongoCursor<Document> cursor = coleccion.find(consulta).iterator()) {
            while (cursor.hasNext()) {
                Document documento = cursor.next();
                Venta venta = new Venta(
                        documento.getString("cliente"),
                        documento.getString("producto"),
                        documento.getString("asesor"),
                        documento.getDouble("totalVenta"),
                        documento.getDouble("comision")
                );
                ventas.add(venta);
            }
        }

        return ventas;
    }
}
