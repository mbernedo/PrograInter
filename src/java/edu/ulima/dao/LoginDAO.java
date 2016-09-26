/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulima.dao;

import edu.ulima.bean.Pokemon;
import edu.ulima.bean.Tipo;
import edu.ulima.interfa.LoginIf;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author migue_000
 */
public class LoginDAO implements LoginIf {

    private final String url = "jdbc:mysql://localhost:3306/pokemon";

    private Connection getConnection() {
        Connection con = null;

        Properties prop = new Properties();
        // Cargar las credenciales
        prop.put("user", "root");
        prop.put("password", "root");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, prop);
        } catch (ClassNotFoundException e) {
            System.out.println("========================");
            System.out.println("===> Revisa tu classpath <===");
            System.out.println("========================");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("========================");
            System.out.println("===> Revisa tus parametros de conexion <===");
            System.out.println("========================");
            e.printStackTrace();
        }
        return con;
    }

    @Override
    public List<Pokemon> obtenerPokemones() {
        List<Pokemon> lista = new ArrayList<>();
        String sql = "select p1.idPokemones, p1.nombre, p1.idTipo, p2.tipo from pokemones p1 join tipos p2 on p1.idTipo=p2.idTipo";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int cod = rs.getInt(1);
                String nombre = rs.getString(2);
                int tipo = rs.getInt(3);
                String nomTipo = rs.getString(4);
                Pokemon poke = new Pokemon();
                poke.setId(cod);
                poke.setNombre(nombre);
                poke.getTipo().setId(tipo);
                poke.getTipo().setTipo(nomTipo);
                lista.add(poke);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            try {
                stmt.close();
                rs.close();
                con.close();
            } catch (Exception e) {
            }
        }
        return lista;
    }

    @Override
    public List<Tipo> obtenerTipos() {
        List<Tipo> listTipo = new ArrayList<>();
        Tipo tipo;
        String sql = "SELECT tipo FROM tipos";
        PreparedStatement stmt = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                tipo = new Tipo();
                String nomTipo = rs.getString(1);
                tipo.setTipo(nomTipo);
                listTipo.add(tipo);
            }
        } catch (Exception e) {
        }
        return listTipo;
    }
    
    

    @Override
    public void insertarPokemon(Pokemon pok) {
        String sql = "INSERT INTO pokemones (idPokemones, nombre, idTipo) VALUES (?, ?, ?)";
        PreparedStatement stmt = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, pok.getId());
            stmt.setString(2, pok.getNombre());
            stmt.setInt(3, pok.getTipo().getId());
            int rc = stmt.executeUpdate();
            System.out.println("Cantidad de registros " + rc);
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }

    @Override
    public void borrarPokemon(int pos) {
        String sql = "DELETE FROM pokemones where idPokemones=?";
        PreparedStatement stmt = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, pos);
            int cont = stmt.executeUpdate();
            System.out.println("Cantidad de registros " + cont);
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }

    @Override
    public void modificarPokemon(int id, String nombre) {
        String sql = "UPDATE pokemones SET nombre=? where idPokemones=?";
        PreparedStatement stmt = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt= con.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setInt(2, id);
            int rc = stmt.executeUpdate();
            System.out.println("Cantidad de registros " + rc);
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }
}
