<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.course"%>
<%@page import="jakarta.servlet.http.HttpSession"%>

<%
    String msg = (String) request.getAttribute("msg");

    if (msg == null) {
        msg = request.getParameter("msg");
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
        <link rel="stylesheet" href="style.css"/>
    </head>

    <body>

        <h1>mpNotes</h1>

        <div class="focus">

            <h2>Resultados de Busqueda</h2>

            <% if (msg != null && !msg.isEmpty()) {%>
            <h5><%= msg%></h5>
            <hr>
            <% } %>

            <h3>Detalles del Curso Encontrado:</h3>
            
            <p><strong>ID de curso:</strong> <%= singleCourse.getCourse_id()%></p>
            <p><strong>ID de usuario:</strong> <%= singleCourse.getUser_id()%></p>
            <p><strong>Nombre corto:</strong> <%= singleCourse.getName()%></p>
            <p><strong>Nombre completo:</strong> <%= singleCourse.getFull_name()%></p>
            <p><strong>Descripcion:</strong> <%= singleCourse.getDescription()%></p>
            <p><strong>Area de conocimiento:</strong> <%= singleCourse.getKnowledge_area()%></p>
            <p><strong>Carrera:</strong> <%= singleCourse.getCareer()%></p>
            <p><strong>Creditos:</strong> <%= singleCourse.getCredits()%></p>
            <p><strong>Contenido tematico:</strong> <%= singleCourse.getThematic_content()%></p>
            <p><strong>Semestre:</strong> <%= singleCourse.getSemester()%></p>
            <p><strong>Profesor:</strong> <%= singleCourse.getProfessor()%></p>

            <hr>
            
            <p><a href="dash/search.jsp">Hacer otra busqueda</a></p>
            <p><a href="dashboard.jsp">Regresar al menu principal</a></p>

        </div>

    </body>

</html>
