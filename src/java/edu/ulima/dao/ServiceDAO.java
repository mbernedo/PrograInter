package edu.ulima.dao;

import edu.ulima.beans.Juego;
import edu.ulima.beans.Respuesta;
import edu.ulima.beans.Usuario;
import edu.ulima.interfaces.ServiceIF;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mbernedo
 */
public class ServiceDAO implements ServiceIF {

    private final String url = "jdbc:mysql://localhost:3306/michidb";

    private Connection getConnection() {
        Connection con = null;
        Properties prop = new Properties();
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
    public boolean login(Usuario usuario) {
        Usuario user = null;
        String sql = "SELECT usuario, clave FROM tbusuarios where usuario=? and clave=?";
        PreparedStatement stmt = null;
        Connection con = null;
        ResultSet rs = null;
        boolean rpta = false;
        try {
            con = getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getClave());
            rs = stmt.executeQuery();
            while (rs.next()) {
                user = new Usuario();
                String username = rs.getString(1);
                String clave = rs.getString(2);
                user.setUsuario(username);
                user.setClave(clave);
            }
            if (user != null) {
                rpta = true;
            }

        } catch (Exception e) {
        } finally {
            try {
                stmt.close();
                rs.close();
                con.close();
            } catch (Exception e) {
            }
            return rpta;
        }
    }

    @Override
    public Respuesta registro(Usuario usuario) {
        String sql = "INSERT INTO tbusuarios(usuario, clave) VALUES (?, ?)";
        PreparedStatement stmt = null;
        Connection con = null;
        Respuesta respuesta = new Respuesta();
        try {
            con = getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getClave());
            int rc = stmt.executeUpdate();
            System.out.println("Cantidad de registros " + rc);
            respuesta.setCod(1);
            respuesta.setMsg("Registro correcto");
            respuesta.setUsuario(usuario);
        } catch (SQLIntegrityConstraintViolationException e) {
            respuesta = new Respuesta();
            respuesta.setCod(2);
            respuesta.setMsg("Usuario ya existe");
        } catch (Exception e) {
            respuesta.setCod(0);
            respuesta.setMsg("Registro incorrecto");
            System.out.println(e.toString());
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
            return respuesta;
        }
    }

    @Override
    public void uniqueConstraint() throws Exception {
        String sql = "ALTER TABLE tbusuarios ADD UNIQUE INDEX usuario_UNIQUE (usuario ASC)";
        PreparedStatement stmt = null;
        Connection con = null;
        con = getConnection();
        stmt = con.prepareStatement(sql);
        stmt.executeUpdate();
        try {
            stmt.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    @Override
    public List<Juego> getJuegos(String user) {
        List<Juego> juegos = new ArrayList<>();
        Juego jugadores;
        String sql = "select gamer1, gamer2, resultado from tbjuego where gamer1= ? or gamer2 = ?";
        PreparedStatement stmt = null;
        Connection con = null;
        ResultSet rs = null;
        try{
            con = getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setString(1, user);
            stmt.setString(2, user);
            rs = stmt.executeQuery();
            while (rs.next()) {                
                jugadores = new Juego();
                String player1 = rs.getString(1);
                String player2 = rs.getString(2);
                jugadores.setGamer1(player1);
                jugadores.setGamer2(player2);
                juegos.add(jugadores);
            }
        } catch (SQLException ex) {
            
        }
        return juegos;
    }

}
