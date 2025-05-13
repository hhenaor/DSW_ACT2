/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author horahenaripo
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionBaseDeDatos {

    protected String driver = "com.mysql.cj.jdbc.Driver";
    protected String ip = "localhost";
    protected String url = "jdbc:mysql://";
    protected int port = 3306;
    protected String user = "root";
    protected String pass = "";
    protected String db = "mpnotes_db";
    private Connection conexion;
    private PreparedStatement sql_st;
    private ResultSet rows;

    public ConexionBaseDeDatos() throws Exception {
        url = url + ip + ":" + port + "/" + db;
        this.conectar();
    }

    public ConexionBaseDeDatos(
            String driver,
            String ip,
            String url,
            int port,
            String user,
            String pass,
            String db
    ) throws Exception {

        this.driver = driver;
        this.ip = ip;
        this.url = url;
        this.port = port;
        this.user = user;
        this.pass = pass;
        this.db = db;

        this.conectar();

    }

    public void conectar() throws Exception {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            throw new Exception("Error de Driver " + ex.getMessage());
        }
        try {
            conexion = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            throw new Exception("Error de Conexion \n Codigo:" + ex.getErrorCode() + " Explicacion:" + ex.getMessage());
        }

    }

    public int actualizar(PreparedStatement sql_st) throws Exception {
        try {
            int res = sql_st.executeUpdate();
            return res;
        } catch (SQLException ex) {
            throw new SQLException("Error al ejecutar sentencia BD Conexion \n Codigo:" + ex.getErrorCode() + " Explicacion:" + ex.getMessage());
        }
    }

    public ResultSet consultar(PreparedStatement sentencia) throws Exception {
        try {
            ResultSet filasBD = sentencia.executeQuery(); // solo para Select
            return filasBD;
        } catch (SQLException ex) {
            throw new SQLException("Error al ejecutar sentencia BD Conexion "
                    + ex.getMessage());
        }
    }

    public void desconectar() {
        try {
            conexion.close();
        } catch (SQLException ex) {
        } finally {
            conexion = null;
        }
    }

    public PreparedStatement crearSentencia(String sql) throws Exception {
        try {
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            return sentencia;
        } catch (SQLException ex) {
            throw new SQLException("Error de Sentencia DB \n Codigo:" + ex.getErrorCode() + " Explicacion:" + ex.getMessage());
        }
    }

    // Setters
    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public void setSql_st(PreparedStatement sql_st) {
        this.sql_st = sql_st;
    }

    public void setRows(ResultSet rows) {
        this.rows = rows;
    }

    // Getters
    public String getDriver() {
        return driver;
    }

    public String getIp() {
        return ip;
    }

    public String getUrl() {
        return url;
    }

    public int getPort() {
        return port;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public String getDb() {
        return db;
    }

    public Connection getConexion() {
        return conexion;
    }

    public PreparedStatement getSql_st() {
        return sql_st;
    }

    public ResultSet getRows() {
        return rows;
    }

}
