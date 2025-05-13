<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.course"%>
<%@page import="jakarta.servlet.http.HttpSession"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.nio.charset.StandardCharsets"%>

<%
    String msg = (String) request.getAttribute("msg");

    if (msg == null) {
        msg = request.getParameter("msg");
        if (msg != null) {
            msg = URLDecoder.decode(msg, StandardCharsets.UTF_8.toString());
        }
    }

    course singleCourse = (course) request.getAttribute("singleCourse");

    course[] courseList = null;
    session = request.getSession(false);
    if (session != null) {
        Object searchResultsAttribute = session.getAttribute("course.searchResults");
        if (searchResultsAttribute != null && searchResultsAttribute instanceof course[]) {
            courseList = (course[]) searchResultsAttribute;
        }
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Resultados de Busqueda</title>
</head>
<body>

    <h2>Resultados de Busqueda</h2>

    <% if (msg != null && !msg.isEmpty()) { %>
        <p><%= msg %></p>
        <hr>
    <% } %>

    <% if (singleCourse != null) { %>
        <h3>Detalles del Curso Encontrado:</h3>
        <p><strong>ID:</strong> <%= singleCourse.getCourse_id() %></p>
        <p><strong>Usuario ID:</strong> <%= singleCourse.getUser_id() %></p>
        <p><strong>Nombre Corto:</strong> <%= singleCourse.getName() %></p>
        <p><strong>Nombre Completo:</strong> <%= singleCourse.getFull_name() %></p>
        <p><strong>Descripcion:</strong> <%= singleCourse.getDescription() %></p>
        <p><strong>Area de Conocimiento:</strong> <%= singleCourse.getKnowledge_area() %></p>
        <p><strong>Carrera:</strong> <%= singleCourse.getCareer() %></p>
        <p><strong>Creditos:</strong> <%= singleCourse.getCredits() %></p>
        <p><strong>Contenido Tematico:</strong> <%= singleCourse.getThematic_content() %></p>
        <p><strong>Semestre:</strong> <%= singleCourse.getSemester() %></p>
        <p><strong>Profesor:</strong> <%= singleCourse.getProfessor() %></p>

    <% } else if (courseList != null && courseList.length > 0) { %>
        <h3>Coincidencias Encontradas:</h3>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre Corto</th>
                    <th>Nombre Completo</th>
                    <th>Profesor</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <% for (course c : courseList) { %>
                    <tr>
                        <td><%= c.getCourse_id() %></td>
                        <td><%= c.getName() %></td>
                        <td><%= c.getFull_name() %></td>
                        <td><%= c.getProfessor() %></td>
                        <td>
                            <a href="<%= request.getContextPath() %>/course?request=single&course_id=<%= c.getCourse_id() %>">Ver Detalles</a>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>

    <% } else { %>
        <p>No se encontraron resultados para mostrar.</p>
    <% } %>

    <br>
    <p><a href="dash/search.jsp">hacer otra busqueda</a></p>
    <p><a href="dashboard.jsp">regresar al dashboard</a></p>

</body>
</html>
