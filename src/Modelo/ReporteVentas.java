package Modelo;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.Arrays;

public class ReporteVentas {

    public static void main(String[] args) {
        // Crear una instancia de Conexion para obtener acceso a la base de datos y colección
        Conexion conexion = new Conexion();
        MongoCollection<Document> ventasCollection = conexion.getColeccion();

        // Cliente seleccionado para el resumen de ventas
        String clienteSeleccionado = "Wallmart";

        // Consulta para obtener el resumen de ventas para el cliente seleccionado
        AggregateIterable<Document> ventasPorClienteResult = ventasCollection.aggregate(Arrays.asList(
                new Document("$match", new Document("cliente", clienteSeleccionado)),
                new Document("$group", new Document("_id", new Document("producto", "$producto").append("asesor", "$asesor"))
                        .append("totalVentas", new Document("$sum", "$valor")))
        ));

        // Mostrar el resumen de ventas para el cliente seleccionado
        System.out.println("Resumen de Ventas para el Cliente: " + clienteSeleccionado);
       for (Document result : ventasPorClienteResult) {
    Document id = (Document) result.get("_id");
    String producto = id.getString("producto");
    String asesor = id.getString("asesor");
    Number totalVentas = (Number) result.get("totalVentas");
    System.out.println("Producto: " + producto + ", Asesor: " + asesor + ", Total Venta: " + totalVentas);
}

      
    }
}
