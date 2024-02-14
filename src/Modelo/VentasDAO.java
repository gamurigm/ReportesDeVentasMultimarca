package Modelo;

import com.mongodb.client.DistinctIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
                .append("valor", venta.getValor())
                .append("comision", venta.getComision());
        coleccion.insertOne(documento);
    }

    public List<String> obtenerAsesores() {
        DistinctIterable<String> distinctAsesores = coleccion.distinct("asesor", String.class);
        List<String> asesores = new ArrayList<>();
        for (String asesor : distinctAsesores) {
            asesores.add(asesor);
        }
        return asesores;
    }

    public List<String> obtenerProductos() {
        DistinctIterable<String> distinctProductos = coleccion.distinct("producto", String.class);
        List<String> productos = new ArrayList<>();
        for (String producto : distinctProductos) {
            productos.add(producto);
        }
        return productos;
    }






   

    public List<String> obtenerClientes() {
        DistinctIterable<String> distinctClientes = coleccion.distinct("cliente", String.class);
        List<String> clientes = new ArrayList<>();
        for (String cliente : distinctClientes) {
            clientes.add(cliente);
        }
        return clientes;
    }

    public List<Venta> obtenerVentasPorCliente(String cliente) {
        List<Venta> ventas = new ArrayList<>();
        Document consulta = new Document("cliente", cliente);

        try (MongoCursor<Document> cursor = coleccion.find(consulta).iterator()) {
            while (cursor.hasNext()) {
                Document documento = cursor.next();

                // Modifica la obtención de datos para manejar el tipo de datos
                String clienteResultado = documento.getString("cliente");
                String productoResultado = documento.getString("producto");
                String asesorResultado = documento.getString("asesor");

                // Verifica si el valor es una cadena o un número
                double totalVentaResultado;

                Object totalVentaObject = documento.get("valor");

                if (totalVentaObject instanceof Number) {
                totalVentaResultado = ((Number) totalVentaObject).doubleValue();
                } else if (totalVentaObject instanceof String) {
                try {
                    totalVentaResultado = Double.parseDouble((String) totalVentaObject);
                } catch (NumberFormatException e) {
                    // Maneja la excepción si la conversión no es posible
                    totalVentaResultado = 0.0;
                }
                } else {
                // Puedes manejar otros tipos de datos aquí según sea necesario
                totalVentaResultado = 0.0;
                }

                // Verifica si el valor es una cadena o un número
                double comisionResultado;
                Object comisionObject = documento.get("comision");
                switch (comisionObject) {
                    case Number number -> comisionResultado = number.doubleValue();
                    case String string -> {
                        try {
                            comisionResultado = Double.parseDouble(string);
                        } catch (NumberFormatException e) {
                            // Maneja la excepción si la conversión no es posible
                            comisionResultado = 0.0;
                        }
                    }
                    default -> // Puedes manejar otros tipos de datos aquí según sea necesario
                        comisionResultado = 0.0;
                }

                Venta venta = new Venta(
                        clienteResultado,
                        productoResultado,
                        asesorResultado,
                        totalVentaResultado,
                        comisionResultado
                );
                ventas.add(venta);
            }
        }

        return ventas;
    }
    
    
    public List<Venta> obtenerVentasPorProducto(String producto) {
    List<Venta> ventas = new ArrayList<>();
    Document consulta = new Document("producto", producto);

    try (MongoCursor<Document> cursor = coleccion.find(consulta).iterator()) {
        while (cursor.hasNext()) {
            Document documento = cursor.next();

            String clienteResultado = documento.getString("cliente");
            String asesorResultado = documento.getString("asesor");

            // Obtén el valor del documento y verifica el tipo
            Object valorObject = documento.get("valor");
            double totalVentaResultado = 0.0;
            if (valorObject instanceof Number) {
                totalVentaResultado = ((Number) valorObject).doubleValue();
            }

            // Obtén el valor del documento y verifica el tipo
            Object comisionObject = documento.get("comision");
            double comisionResultado = 0.0;
            if (comisionObject instanceof Number) {
                comisionResultado = ((Number) comisionObject).doubleValue();
            }

            Venta venta = new Venta(
                    clienteResultado,
                    producto,
                    asesorResultado,
                    totalVentaResultado,
                    comisionResultado
            );
            ventas.add(venta);
        }
    }

    return ventas;
}

public List<Venta> obtenerVentasPorAsesor(String asesor) {
    List<Venta> ventas = new ArrayList<>();
    Document consulta = new Document("asesor", asesor);

    try (MongoCursor<Document> cursor = coleccion.find(consulta).iterator()) {
        while (cursor.hasNext()) {
            Document documento = cursor.next();

            String clienteResultado = documento.getString("cliente");
            String productoResultado = documento.getString("producto");

            // Obtén el valor del documento y verifica el tipo
            Object valorObject = documento.get("valor");
            double totalVentaResultado = 0.0;
            if (valorObject instanceof Number) {
                totalVentaResultado = ((Number) valorObject).doubleValue();
            }

            // Obtén el valor del documento y verifica el tipo
            Object comisionObject = documento.get("comision");
            double comisionResultado = 0.0;
            if (comisionObject instanceof Number) {
                comisionResultado = ((Number) comisionObject).doubleValue();
            }

            Venta venta = new Venta(
                    clienteResultado,
                    productoResultado,
                    asesor,
                    totalVentaResultado,
                    comisionResultado
            );
            ventas.add(venta);
        }
    }

    return ventas;
}


}
