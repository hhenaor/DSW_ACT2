package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CRUDcourse {

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
            throw new Exception("Error al Agregar el Curso " + nuevoCourse.getName() + "\nExplicacion: " + ex.getMessage(), ex);
        } finally {
            if (sentenciaSQL != null) {
                try {
                    sentenciaSQL.close();
                } catch (SQLException e) {
                }
            }
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
            throw new Exception("Error al Modificar el Curso " + courseAModificar.getName() + "\nExplicacion: " + ex.getMessage(), ex);
        } finally {
            if (sentenciaSQL != null) {
                try {
                    sentenciaSQL.close();
                } catch (SQLException e) {
                }
            }
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
            throw new Exception("Error al Eliminar el Curso con ID " + courseId + "\nExplicacion: " + ex.getMessage(), ex);
        } finally {
            if (sentenciaSQL != null) {
                try {
                    sentenciaSQL.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public static course consultarCourse(int courseId) throws Exception {
        if (courseId <= 0) {
            throw new Exception("El ID del Curso es Necesario para Consultar");
        }

        String sqlSelect = "SELECT course_id, user_id, name, full_name, description, knowledge_area, career, credits, thematic_content, semester, professor FROM courses WHERE course_id = ?";

        ResultSet resultado = null;
        PreparedStatement sentenciaSQL = null;
        course encontrado = null;
        ConexionBaseDeDatos conexionLocal = null;

        try {
            conexionLocal = new ConexionBaseDeDatos();
            sentenciaSQL = conexionLocal.crearSentencia(sqlSelect);
            sentenciaSQL.setInt(1, courseId);

            resultado = conexionLocal.consultar(sentenciaSQL);

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
            throw new Exception("Error al consultar curso con ID " + courseId + ": " + ex.getMessage(), ex);
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
            if (conexionLocal != null) {
                conexionLocal.desconectar();
            }
        }
    }

    public static course[] courseFinder(course searchCriteria, String userId) throws Exception {
        if (userId == null || userId.isEmpty()) {
            throw new Exception("El ID de Usuario es Necesario para la Busqueda");
        }

        StringBuilder sqlSelect = new StringBuilder("SELECT course_id, user_id, name, full_name, description, knowledge_area, career, credits, thematic_content, semester, professor FROM courses WHERE user_id = ?");

        Object[] parameters = new Object[10];
        int paramCount = 0;

        parameters[paramCount++] = userId;

        if (searchCriteria != null) {
            if (searchCriteria.getCourse_id() > 0) {
                sqlSelect.append(" AND course_id = ?");
                parameters[paramCount++] = searchCriteria.getCourse_id();
            }

            if (searchCriteria.getName() != null && !searchCriteria.getName().isEmpty()) {
                sqlSelect.append(" AND name LIKE ?");
                parameters[paramCount++] = "%" + searchCriteria.getName() + "%";
            }

            if (searchCriteria.getFull_name() != null && !searchCriteria.getFull_name().isEmpty()) {
                sqlSelect.append(" AND full_name LIKE ?");
                parameters[paramCount++] = "%" + searchCriteria.getFull_name() + "%";
            }

            if (searchCriteria.getDescription() != null && !searchCriteria.getDescription().isEmpty()) {
                 sqlSelect.append(" AND description LIKE ?");
                 parameters[paramCount++] = "%" + searchCriteria.getDescription() + "%";
            }

            if (searchCriteria.getKnowledge_area() != null && !searchCriteria.getKnowledge_area().isEmpty()) {
                sqlSelect.append(" AND knowledge_area LIKE ?");
                parameters[paramCount++] = "%" + searchCriteria.getKnowledge_area() + "%";
            }

            if (searchCriteria.getCareer() != null && !searchCriteria.getCareer().isEmpty()) {
                sqlSelect.append(" AND career LIKE ?");
                parameters[paramCount++] = "%" + searchCriteria.getCareer() + "%";
            }

            if (searchCriteria.getThematic_content() != null && !searchCriteria.getThematic_content().isEmpty()) {
                sqlSelect.append(" AND thematic_content LIKE ?");
                parameters[paramCount++] = "%" + searchCriteria.getThematic_content() + "%";
            }

            if (searchCriteria.getSemester() != null && !searchCriteria.getSemester().isEmpty()) {
                sqlSelect.append(" AND semester LIKE ?");
                parameters[paramCount++] = "%" + searchCriteria.getSemester() + "%";
            }

            if (searchCriteria.getProfessor() != null && !searchCriteria.getProfessor().isEmpty()) {
                sqlSelect.append(" AND professor LIKE ?");
                parameters[paramCount++] = "%" + searchCriteria.getProfessor() + "%";
            }
        }

        ResultSet resultado = null;
        PreparedStatement sentenciaSQL = null;
        List<course> coursesEncontrados = new ArrayList<>();
        ConexionBaseDeDatos conexionLocal = null;

        try {
            conexionLocal = new ConexionBaseDeDatos();
            sentenciaSQL = conexionLocal.crearSentencia(sqlSelect.toString());

            for (int i = 0; i < paramCount; i++) {
                sentenciaSQL.setObject(i + 1, parameters[i]);
            }

            resultado = conexionLocal.consultar(sentenciaSQL);

            while (resultado.next()) {
                course encontrado = new course(
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
                coursesEncontrados.add(encontrado);
            }

            return coursesEncontrados.toArray(new course[0]);

        } catch (Exception ex) {
            throw new Exception("Error al consultar los Cursos: " + ex.getMessage(), ex);
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
            if (conexionLocal != null) {
                conexionLocal.desconectar();
            }
        }
    }
}
