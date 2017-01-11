package edu.ulima.dao;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import edu.ulima.beans.Archivo;
import edu.ulima.beans.Respuesta;
import edu.ulima.beans.Usuario;
import edu.ulima.beans.Valores;
import edu.ulima.interfaces.ServiceIF;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mbernedo
 */
public class ServiceDAO implements ServiceIF {

    MongoClient mongoClient = null;
    DB db = null;

    private DB getConnection() {
        try {
            // OJO: aqui los mongoD no estan con seguridad
            mongoClient = new MongoClient(
                    new MongoClientURI("mongodb://grupo4:progra@ds111748.mlab.com:11748/progra"));
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
        // USar una Base de datos
        db = mongoClient.getDB("progra");
        return db;
    }

    @Override
    public boolean login(Usuario usuario) {
        db = getConnection();
        boolean rpta = false;
        Usuario user = null;
        DBCollection coll = db.getCollection("excel");
        BasicDBObject query = new BasicDBObject("usuario", usuario.getUsuario())
                .append("password", usuario.getClave());
        // En mongoDb el equivalente de ResultSet es DBCursor
        // Recupear TODA la coleccion
        DBCursor cursor = coll.find(query);
        while (cursor.hasNext()) {
            DBObject obj = cursor.next();
            user = new Usuario();
            String cuenta = (String) obj.get("usuario");
            String clave = (String) obj.get("password");
            user.setUsuario(cuenta);
            user.setClave(clave);
            System.out.println(obj);
        }
        if (user != null) {
            rpta = true;
            cursor.close();
            mongoClient.close();
        }
        return rpta;
    }

    @Override
    public Respuesta registro(Usuario usuario) {
        BasicDBObject doc = new BasicDBObject("usuario", usuario.getUsuario())
                .append("password", usuario.getClave())
                .append("excel", usuario.getExcel());
        Respuesta respuesta = new Respuesta();
        try {
            // Obtener la conexion
            db = getConnection();
            // Obtener la coleccion
            DBCollection coll = db.getCollection("excel");
            // Insertar
            BasicDBObject filtro = new BasicDBObject("usuario", usuario.getUsuario());
            DBObject obj = coll.findOne(filtro);
            if (obj != null) {
                respuesta.setCod(2);
                respuesta.setMsg("Usuario ya existe");
            } else {
                coll.insert(doc);
                respuesta.setCod(1);
                respuesta.setMsg("Registro correcto");
                respuesta.setUsuario(usuario);
            }
        } catch (Exception e) {
            respuesta.setCod(0);
            respuesta.setMsg("Registro incorrecto");
        }

        // finalmente cerrar la conexion
        mongoClient.close();
        return respuesta;
    }

    @Override
    public boolean guardarExcel(Usuario usuario) {
        boolean rpta = false;
        try {
            db = getConnection();
            DBCollection coll = db.getCollection("excel");
            // Condicion de actualizacion
            BasicDBObject filtro = new BasicDBObject("usuario", usuario.getUsuario());
            // Valor a cambiar
            BasicDBList celdas = new BasicDBList();
            List<Valores> vals = usuario.getExcel().getCeldas();
            for (Valores val : vals) {
                celdas.add(new BasicDBObject("pos", val.getPos())
                        .append("val", val.getVal()));
            }
            BasicDBList docs = new BasicDBList();
            BasicDBObject doc = new BasicDBObject("excel", new BasicDBObject("nomArc", usuario.getExcel().getNomArc()))
                    .append("celdas", celdas);
            docs.add(doc);
            BasicDBObject archivo = new BasicDBObject("$push", doc);
            //BasicDBObject archivo = new BasicDBObject("$set",
            //       new BasicDBObject("excel", docs));
            // Actualizar el primero que encuentra !!
            coll.update(filtro, archivo);
            rpta = true;
        } catch (Exception e) {
        }
        mongoClient.close();
        return rpta;
    }

    @Override
    public List<Archivo> getDocs(Usuario usuario) {
        db = getConnection();
        Archivo archivo;
        List<Archivo> docs = new ArrayList<>();
        DBCollection coll = db.getCollection("excel");
        BasicDBObject query = new BasicDBObject("usuario", usuario.getUsuario());
        // En mongoDb el equivalente de ResultSet es DBCursor
        // Recupear TODA la coleccion
        DBCursor cursor = coll.find(query);
        while (cursor.hasNext()) {
            DBObject obj = cursor.next();
            archivo = new Archivo();
            BasicDBList arcs = (BasicDBList) obj.get("excel");
            BasicDBObject[] arreglo = arcs.toArray(new BasicDBObject[0]);
            for (BasicDBObject basicDBObject : arreglo) {
                String nomArc = (String) basicDBObject.get("nomArc");
                archivo.setNomArc(nomArc);
                docs.add(archivo);
            }
        }
        return docs;
    }
}
