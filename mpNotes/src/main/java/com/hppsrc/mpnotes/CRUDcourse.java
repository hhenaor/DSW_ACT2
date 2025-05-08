/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hppsrc.mpnotes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author horahenaripo
 */
public class CRUDcourse {

    private course course;
    private ConexionBaseDeDatos conexion;

    public CRUDcourse(ConexionBaseDeDatos conexion) {
        this.conexion = conexion;
    }

    public void agregarCourse(course nuevoCourse) throws Exception {

        if (nuevoCourse.getUser_id() == null || nuevoCourse.getUser_id().isEmpty()
                || nuevoCourse.getName() == null || nuevoCourse.getName().isEmpty()
                || nuevoCourse.getFull_name() == null || nuevoCourse.getFull_name().isEmpty()
                || nuevoCourse.getDescription() == null || nuevoCourse.getDescription().isEmpty()
                || nuevoCourse.getKnowledge_area() == null || nuevoCourse.getKnowledge_area().isEmpty()
                || nuevoCourse.getCareer() == null || nuevoCourse.getCareer().isEmpty()
                || nuevoCourse.getThematic_content() == null || nuevoCourse.getThematic_content().isEmpty()
                || nuevoCourse.getSemester() == null || nuevoCourse.getSemester().isEmpty()
                || nuevoCourse.getProfessor() == null || nuevoCourse.getProfessor().isEmpty()) {
            throw new Exception("Todos los campos del curso son necesarios para agregar");
        }

        String sqlInsert = "INSERT INTO courses (user_id, name, full_name, description, knowledge_area, career, credits, thematic_content, semester, professor) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement sentenciaSQL = null;

        try {
            sentenciaSQL = this.conexion.crearSentencia(sqlInsert);

            sentenciaSQL.setString(1, nuevoCourse.getUser_id());
            sentenciaSQL.setString(2, nuevoCourse.getName());
            sentenciaSQL.setString(3, nuevoCourse.getFull_name());
            sentenciaSQL.setString(4, nuevoCourse.getDescription());
            sentenciaSQL.setString(5, nuevoCourse.getKnowledge_area());
            sentenciaSQL.setString(6, nuevoCourse.getCareer());
            sentenciaSQL.setInt(7, nuevoCourse.getCredits());
            sentenciaSQL.setString(8, nuevoCourse.getThematic_content());
            sentenciaSQL.setString(9, nuevoCourse.getSemester());
            sentenciaSQL.setString(10, nuevoCourse.getProfessor());

            this.conexion.actualizar(sentenciaSQL);

        } catch (Exception ex) {
            throw new Exception("Error al Agregar el Curso " + nuevoCourse.getName() + "\nExplicacion: " + ex.getMessage());
        } finally {
            if (sentenciaSQL != null) {
                try {
                    sentenciaSQL.close();
                } catch (SQLException e) {
                }
            }
            this.conexion.desconectar();
        }
    }

    public void modificarCourse(course courseAModificar) throws Exception {

        if (courseAModificar.getCourse_id() <= 0) {
            throw new Exception("El ID del Curso es Necesario para Modificar");
        }

        String sqlUpdate = "UPDATE courses SET user_id = ?, name = ?, full_name = ?, description = ?, knowledge_area = ?, career = ?, credits = ?, thematic_content = ?, semester = ?, professor = ? WHERE course_id = ?";

        PreparedStatement sentenciaSQL = null;

        try {
            sentenciaSQL = this.conexion.crearSentencia(sqlUpdate);

            sentenciaSQL.setString(1, courseAModificar.getUser_id());
            sentenciaSQL.setString(2, courseAModificar.getName());
            sentenciaSQL.setString(3, courseAModificar.getFull_name());
            sentenciaSQL.setString(4, courseAModificar.getDescription());
            sentenciaSQL.setString(5, courseAModificar.getKnowledge_area());
            sentenciaSQL.setString(6, courseAModificar.getCareer());
            sentenciaSQL.setInt(7, courseAModificar.getCredits());
            sentenciaSQL.setString(8, courseAModificar.getThematic_content());
            sentenciaSQL.setString(9, courseAModificar.getSemester());
            sentenciaSQL.setString(10, courseAModificar.getProfessor());
            sentenciaSQL.setInt(11, courseAModificar.getCourse_id());

            this.conexion.actualizar(sentenciaSQL);

        } catch (Exception ex) {
            throw new Exception("Error al Modificar el Curso " + courseAModificar.getName() + "\nExplicacion: " + ex.getMessage());
        } finally {
            if (sentenciaSQL != null) {
                try {
                    sentenciaSQL.close();
                } catch (SQLException e) {
                }
            }
            this.conexion.desconectar();
        }
    }

    public void eliminarCourse(int courseId) throws Exception {

        if (courseId <= 0) {
            throw new Exception("El ID del Curso es Necesario para Eliminar");
        }

        String sqlDelete = "DELETE FROM courses WHERE course_id = ?";

        PreparedStatement sentenciaSQL = null;

        try {
            sentenciaSQL = this.conexion.crearSentencia(sqlDelete);

            sentenciaSQL.setInt(1, courseId);

            this.conexion.actualizar(sentenciaSQL);

        } catch (Exception ex) {
            throw new Exception("Error al Eliminar el Curso con ID " + courseId + "\nExplicacion: " + ex.getMessage());
        } finally {
            if (sentenciaSQL != null) {
                try {
                    sentenciaSQL.close();
                } catch (SQLException e) {
                }
            }
            this.conexion.desconectar();
        }
    }
    
    public static course[] listarTodosLosCourses() throws Exception {
        course cursoActual;
        ConexionBaseDeDatos conexion = null;

        String sqlSelect = "SELECT course_id, user_id, name, full_name, description, knowledge_area, career, credits, thematic_content, semester, professor FROM courses";

        ResultSet resultado = null;
        PreparedStatement sentenciaSQL = null;
        course[] listado = null;

        try {
            conexion = new ConexionBaseDeDatos();

            sentenciaSQL = conexion.crearSentencia(sqlSelect);

            resultado = conexion.consultar(sentenciaSQL);

            resultado.last();

            listado = new course[resultado.getRow()];

            resultado.beforeFirst();

            int i = 0;

            while (resultado.next()) {
                cursoActual = new course(
                    resultado.getInt("course_id"),
                    resultado.getString("user_id"),
                    resultado.getString("name"),
                    resultado.getString("full_name"),
                    resultado.getString("description"),
                    resultado.getString("knowledge_area"),
                    resultado.getString("career"),
                    resultado.getInt("credits"),
                    resultado.getString("thematic_content"),
                    resultado.getString("semester"),
                    resultado.getString("professor")
                );
                listado[i] = cursoActual;
                i++;
            }

             if (listado.length <= 0) {
                throw new Exception("Error al Listar los Cursos: La BD esta vacia o no hay cursos");
             }

            return listado;

        } catch (Exception ex) {
             throw new Exception(ex.getMessage() + " Error al listar cursos.");
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

    public course consultarCourse(int courseId) throws Exception {
        if (courseId <= 0) {
            throw new Exception("El ID del Curso es Necesario para Consultar");
        }

        String sqlSelect = "SELECT course_id, user_id, name, full_name, description, knowledge_area, career, credits, thematic_content, semester, professor FROM courses WHERE course_id = ?";

        ResultSet resultado = null;
        PreparedStatement sentenciaSQL = null;
        course encontrado = null;

        try {
            conexion = new ConexionBaseDeDatos();
            sentenciaSQL = conexion.crearSentencia(sqlSelect);
            sentenciaSQL.setInt(1, courseId);

            resultado = conexion.consultar(sentenciaSQL);

            if (resultado.next()) {
                encontrado = new course(
                        resultado.getInt("course_id"),
                        resultado.getString("user_id"),
                        resultado.getString("name"),
                        resultado.getString("full_name"),
                        resultado.getString("description"),
                        resultado.getString("knowledge_area"),
                        resultado.getString("career"),
                        resultado.getInt("credits"),
                        resultado.getString("thematic_content"),
                        resultado.getString("semester"),
                        resultado.getString("professor")
                );
            }
            return encontrado;

        } catch (Exception ex) {
            throw new Exception("Error al consultar curso con ID " + courseId + ": " + ex.getMessage());
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
