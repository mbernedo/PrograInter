/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javamongodb;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author mbernedo
 */
public class JavaMongoDB {

    MongoClient mongoClient = null;
    DB db = null;
    float suma = 0.0f;
    float suma2 = 0.0f;
    float prom = 0.0f;
    float prom2 = 0.0f;
    int cont = 0;
    int cont2 = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JavaMongoDB t = new JavaMongoDB();
        List<Alumno> lista = t.getNotasXSalon();
        for (Alumno alumno : lista) {
            System.out.println("class_id: " +alumno.getIdClase()+ " --promedio: " + alumno.getPromedio());
        }
    }

    private DB getConnection() {
        try {
            mongoClient = new MongoClient("localhost", 27017);
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
        db = mongoClient.getDB("test");
        return db;
    }

    private int mayorSalon() {
        int mayor = 0;
        List<Alumno> lista = getNotas();
        for (Alumno alumno : lista) {
            if (alumno.getIdClase() > mayor) {
                mayor = alumno.getIdClase();
            }
        }
        return mayor;
    }

    private List<Alumno> getNotas() {
        db = getConnection();
        DBCollection coll = db.getCollection("grades");
        DBCursor cursor = coll.find();
        List<Alumno> lista = new ArrayList<>();
        Alumno alumno;
        while (cursor.hasNext()) {
            DBObject obj = cursor.next();
            alumno = new Alumno();
            for (int i = 0; i < cursor.count(); i++) {
                alumno.setIdAlumno((int) obj.get("student_id"));
                alumno.setIdClase((int) obj.get("class_id"));
                BasicDBList notas = (BasicDBList) obj.get("scores");
                BasicDBObject[] arreglo = notas.toArray(new BasicDBObject[0]);
                for (BasicDBObject basicDBObject : arreglo) {
                    String tipo = (String) basicDBObject.get("type");
                    if (tipo.equalsIgnoreCase("exam") || tipo.equalsIgnoreCase("homework")) {
                        suma += (Double) basicDBObject.get("score");
                        cont++;
                    }
                }
                prom = suma / cont;
                alumno.setPromedio(prom);
                suma = 0.0f;
                cont = 0;
                prom = 0.0f;
            }
            lista.add(alumno);
        }
        cursor.close();
        mongoClient.close();
        return lista;
    }

    private List<Alumno> getNotasXSalon() {
        List<Alumno> lista = getNotas();
        Collections.sort(lista, new Comparator<Alumno>() {
            @Override
            public int compare(Alumno o1, Alumno o2) {
                return new Integer(o2.getIdClase()).compareTo(new Integer(o1.getIdClase()));
            }
        });
        List<Alumno> result = new ArrayList<>();
        Alumno alumno;
        int mayor = mayorSalon();
        for (int i = 0; i < lista.size(); i++) {
            alumno = new Alumno();
            if (lista.get(i).getIdClase() >= mayor) {
                suma2 += lista.get(i).getPromedio();
                cont2++;
            } else {
                alumno.setIdClase(lista.get(i - 1).getIdClase());
                prom2 = suma2 / cont2;
                alumno.setPromedio(prom2);
                result.add(alumno);
                mayor=lista.get(i).getIdClase();
                suma2 = lista.get(i).getPromedio();
                cont2 = 1;
                prom2 = 0.0f;
            }
            if(i+1== lista.size()){
                alumno.setIdClase(lista.get(i - 1).getIdClase());
                prom2 = suma2 / cont2;
                alumno.setPromedio(prom2);
                result.add(alumno);
            }
        }
        Collections.sort(result, new Comparator<Alumno>(){
            @Override
            public int compare(Alumno o1, Alumno o2) {
                return new Double(o2.getPromedio()).compareTo(new Double(o1.getPromedio()));
            }
        });
        return result;
    }
}
