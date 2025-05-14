package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUDstudent {

    private student student;
    private ConexionBaseDeDatos conexion;

    public CRUDstudent(ConexionBaseDeDatos conexion) {
        this.conexion = conexion;
    }

    public void agregarStudent(student nuevoStudent) throws Exception {
        if (nuevoStudent.getUsername() == null || nuevoStudent.getUsername().isEmpty()) {
            throw new Exception("El username del estudiante es necesario");
        }

        String sqlInsert = "INSERT INTO students (username, password, name, email) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement sentenciaSQL = this.conexion.crearSentencia(sqlInsert);

            sentenciaSQL.setString(1, nuevoStudent.getUsername());
            sentenciaSQL.setString(2, nuevoStudent.getPassword());
            sentenciaSQL.setString(3, nuevoStudent.getName());
            sentenciaSQL.setString(4, nuevoStudent.getEmail());

            this.conexion.actualizar(sentenciaSQL);

        } catch (Exception ex) {
            throw new Exception("Error al Agregar el Estudiante " + nuevoStudent.getUsername() + "\nExplicacion: " + ex.getMessage());
        } finally {
            this.conexion.desconectar();
        }
    }

    public void modificarStudent(student studentAModificar) throws Exception {
        if (studentAModificar.getUsername() == null || studentAModificar.getUsername().isEmpty()) {
            throw new Exception("El Username del Estudiante es Necesario para Modificar");
        }

        String sqlUpdate = "UPDATE students SET password = ?, name = ?, email = ? WHERE username = ?";

        try {
            PreparedStatement sentenciaSQL = this.conexion.crearSentencia(sqlUpdate);

            sentenciaSQL.setString(1, studentAModificar.getPassword());
            sentenciaSQL.setString(2, studentAModificar.getName());
            sentenciaSQL.setString(3, studentAModificar.getEmail());
            sentenciaSQL.setString(4, studentAModificar.getUsername());

            this.conexion.actualizar(sentenciaSQL);

        } catch (Exception ex) {
            throw new Exception("Error al Modificar el Estudiante " + studentAModificar.getUsername() + "\nExplicacion: " + ex.getMessage());
        } finally {
            this.conexion.desconectar();
        }
    }

    public void eliminarStudent(String username) throws Exception {
        if (username == null || username.isEmpty()) {
            throw new Exception("El Username del Estudiante es Necesario para Eliminar");
        }

        String sqlDelete = "DELETE FROM students WHERE username = ?";

        try {
            PreparedStatement sentenciaSQL = this.conexion.crearSentencia(sqlDelete);

            sentenciaSQL.setString(1, username);

            this.conexion.actualizar(sentenciaSQL);

        } catch (Exception ex) {
            throw new Exception("Error al Eliminar el Estudiante " + username + "\nExplicacion: " + ex.getMessage());
        } finally {
            this.conexion.desconectar();
        }
    }

    public static student iniciarSesion(String username, String password) throws Exception {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new Exception("El Username y el Password del Estudiante son Necesarios");
        }

        student encontrado = null;
        ConexionBaseDeDatos conexion = null;

        String sqlSelect = "SELECT username, password, name, email FROM students WHERE username = ? AND password = ?";

        try {
            conexion = new ConexionBaseDeDatos();

            PreparedStatement sentenciaSQL = conexion.crearSentencia(sqlSelect);

            sentenciaSQL.setString(1, username);
            sentenciaSQL.setString(2, password);

            ResultSet resultado = conexion.consultar(sentenciaSQL);

            if (resultado.next()) {
                String name = resultado.getString("name");
                String email = resultado.getString("email");
                encontrado = new student(username, password, name, email);

                return encontrado;

            } else {
                throw new Exception("Error en el ID o el Password estan Errados");
            }

        } catch (Exception ex) {
            throw new Exception(ex.getMessage() + "Error en el ID o el Password estan Errados");
        } finally {
            if (conexion != null) {
                conexion.desconectar();
            }
        }
    }

    public static student[] listarTodosLosStudents() throws Exception {
        student estudianteActual;
        ConexionBaseDeDatos conexion = null;

        String sqlSelect = "SELECT username, password, name, email FROM students";

        ResultSet resultado = null;
        PreparedStatement sentenciaSQL = null;
        student[] listado = null;

        try {
            conexion = new ConexionBaseDeDatos();

            sentenciaSQL = conexion.crearSentencia(sqlSelect);

            resultado = conexion.consultar(sentenciaSQL);

            resultado.last();

            listado = new student[resultado.getRow()];

            resultado.beforeFirst();

            int i = 0;

            while (resultado.next()) {
                estudianteActual = new student(
                        resultado.getString("username"),
                        resultado.getString("password"),
                        resultado.getString("name"),
                        resultado.getString("email")
                );
                listado[i] = estudianteActual;
                i++;
            }

            if (listado.length <= 0) {
                throw new Exception("Error al Listar los Estudiantes ");
            }

            return listado;

        } catch (Exception ex) {
            throw new Exception(ex.getMessage() + "La BD esta vacia");
        } finally {
            if (resultado != null) {
                try {
                    resultado.close();
                } catch (SQLException e) {
                }
            }
            if (sentenciaSQL != null) {
                try {
                    sentenciaSQL.close();
                } catch (SQLException e) {
                }
            }

            if (conexion != null) {
                conexion.desconectar();
            }
        }
    }
}
