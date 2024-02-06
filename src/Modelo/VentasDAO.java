package Modelo;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class VentasDAO {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> ventasCollection;

    public VentasDAO() {
        // Configura la conexión a la base de datos MongoDB
        this.mongoClient = MongoClients.create("mongodb://localhost:27017");
        this.database = mongoClient.getDatabase("NombreDeTuBaseDeDatos");
        this.ventasCollection = database.getCollection("Ventas");
    }

    public List<Venta> obtenerVentas() {
        List<Venta> ventas = new ArrayList<>();

        // Realiza la consulta a la colección de ventas
        FindIterable<Document> iterable = ventasCollection.find();

        // Itera sobre los resultados y mapea los documentos a objetos Venta
        for (Document document : iterable) {
            Cliente cliente = new Cliente(document.getString("cliente"));
            Producto producto = new Producto(document.getString("producto"));
            Asesor asesor = new Asesor(document.getString("asesor"));

            Venta venta = new Venta(cliente, producto, document.getDouble("valor"), asesor, document.getDouble("comision"));
            ventas.add(venta);
        }

        return ventas;
    }
}
