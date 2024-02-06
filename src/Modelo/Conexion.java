package Modelo;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Conexion {
    
    MongoDatabase baseDatos;
    MongoCollection<Document> coleccion;
    BasicDBObject documento = new BasicDBObject();
    
    public Conexion(){
        MongoClient mongo = new MongoClient("localhost", 27017);
        baseDatos = mongo.getDatabase("Multimarca");
        coleccion = baseDatos.getCollection("Ventas");
        System.out.println("Conexion Exitosa");
    }
    
    public MongoDatabase getBaseDatos() {
        return this.baseDatos;
    }

    public MongoCollection<Document> getColeccion() {
        return this.coleccion;
    }
}
