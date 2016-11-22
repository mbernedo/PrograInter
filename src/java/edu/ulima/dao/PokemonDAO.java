/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulima.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import edu.ulima.bean.Pokemon;
import edu.ulima.bean.Tipo;
import edu.ulima.interfaces.PokemonIf;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author migue_000
 */
public class PokemonDAO implements PokemonIf {

    MongoClient mongoClient = null;
    DB db = null;

    private DB getConnection() {
        try {
            mongoClient = new MongoClient("localhost", 27017);
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
        db = mongoClient.getDB("pokemones");

        return db;
    }

    @Override
    public List<Pokemon> obtenerPokemones(int combo) {
        List<Pokemon> lista = new ArrayList<>();
        db = getConnection();
        Pokemon poke;
        DBCursor cursor;
        DBCollection coll = db.getCollection("pokemon");
        BasicDBObject query = new BasicDBObject();
        query.put("tipo_id", combo);
        // En mongoDb el equivalente de ResultSet es DBCursor
        // Recupear TODA la coleccion
        if (combo == 0) {
            cursor = coll.find();
        } else {
            cursor = coll.find(query);
        }
        while (cursor.hasNext()) {
            DBObject obj = cursor.next();
            Double id = (Double) obj.get("id");
            Double cod = (Double) obj.get("codigo_pokedex");
            String nombre = (String) obj.get("nombre");
            Double hp = (Double) obj.get("hp");
            Double tipo_id = (Double) obj.get("tipo_id");
            poke = new Pokemon();
            poke.setId(id.intValue());
            poke.setCod_pokedex(cod.intValue());
            poke.setNombre(nombre);
            poke.setHp(hp.intValue());
            poke.getTipo().setIdTipo(tipo_id.intValue());
            lista.add(poke);
        }
        cursor.close();
        mongoClient.close();
        return lista;
    }

    @Override
    public List<Tipo> obtenerTipos() {
        List<Tipo> listTipo = new ArrayList<>();
        Tipo tipo;
        db = getConnection();
        DBCursor cursor;
        DBCollection coll = db.getCollection("tipo");
        /*BasicDBObject query = new BasicDBObject();
        query.put("tipo_id", combo);*/
        cursor = coll.find();
        while (cursor.hasNext()) {
            DBObject obj = cursor.next();
            Double id = (Double) obj.get("id");
            String nombre = (String) obj.get("nombre");
            tipo = new Tipo();
            tipo.setIdTipo(id.intValue());
            tipo.setNomTipo(nombre);
            listTipo.add(tipo);
        }
        cursor.close();
        mongoClient.close();
        return listTipo;

    }

    @Override
    public void borrarPokemon(int id) {
        db = getConnection();
        DBCollection coll = db.getCollection("pokemon");
        // Condicion de actualizacion
        BasicDBObject filtro = new BasicDBObject("id", id);
        // Borrar el objeto que tenga el id ingresado
        coll.remove(filtro);
        mongoClient.close();
    }

    @Override
    public void insertarPokemon(Pokemon pok) {
        BasicDBObject doc = new BasicDBObject("id", (double) pok.getId())
                .append("codigo_pokedex", (double) pok.getCod_pokedex())
                .append("nombre", pok.getNombre())
                .append("hp", (double) pok.getHp())
                .append("tipo_id", (double) pok.getTipo().getIdTipo());
        // Obtener la conexion
        db = getConnection();
        // Obtener la coleccion
        DBCollection coll = db.getCollection("pokemon");
        // Insertar
        coll.insert(doc);
        // finalmente cerrar la conexion
        mongoClient.close();
    }

    @Override
    public void modificarPokemon(int id, Pokemon pok) {
        db = getConnection();
        DBCollection coll = db.getCollection("pokemon");
        // Condicion de actualizacion
        BasicDBObject filtro = new BasicDBObject("id", id);
        // Valor a cambiar
        BasicDBObject doc = new BasicDBObject("$set",
                new BasicDBObject("codigo_pokedex", (double) pok.getCod_pokedex())
                .append("nombre", pok.getNombre())
                .append("hp", (double) pok.getHp())
                .append("tipo_id", (double) pok.getTipo().getIdTipo()));
        // Actualizar el primero que encuentra !!!
        //coll.update(filtro, doc);
        coll.update(filtro, doc);
        mongoClient.close();
    }

    @Override
    public Pokemon getPokemon(int id) {
        Pokemon poke = new Pokemon();
        db = getConnection();
        DBCollection coll = db.getCollection("pokemon");
        BasicDBObject query = new BasicDBObject();
        query.put("id", id);
        // En mongoDb el equivalente de ResultSet es DBCursor
        // Recupear TODA la coleccion
        DBCursor cursor = coll.find(query);
        while (cursor.hasNext()) {
            DBObject obj = cursor.next();
            Double cod = (Double) obj.get("codigo_pokedex");
            String nombre = (String) obj.get("nombre");
            Double hp = (Double) obj.get("hp");
            Double tipo_id = (Double) obj.get("tipo_id");
            poke = new Pokemon();
            poke.setCod_pokedex(cod.intValue());
            poke.setNombre(nombre);
            poke.setHp(hp.intValue());
            poke.getTipo().setIdTipo(tipo_id.intValue());
        }
        cursor.close();
        mongoClient.close();
        return poke;

    }
    /*
    @Override
    public List<Integer> getCodigo() {
        List<Integer> codigos = new ArrayList<>();
        String sql = "SELECT codigo_pokedex FROM pokemones";
        PreparedStatement stmt = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int codPoke = rs.getInt(1);
                codigos.add(codPoke);
            }
        } catch (Exception e) {
        } finally {
            try {
                stmt.close();
                rs.close();
                con.close();
            } catch (Exception e) {
            }
        }
        return codigos;

    }*/
}
