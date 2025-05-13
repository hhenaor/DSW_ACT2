<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <title>Eliminar curso</title>
        <link rel="stylesheet" href="../style.css"/>
    </head>

    <body>

        <h1>mpNotes</h1>

        <form action="<%= request.getContextPath()%>/course" method="post">

            <h2>Eliminar curso</h2>

            <div>
                <label for="courseId">ID del curso a eliminar:</label>
                <input type="number" id="courseId" name="course_id" required min="1" placeholder="Ingresa ID del curso">
                <p>No sabes cual es el ID? Puedes buscarlo <a href="search.jsp">aqui</a></p>
            </div>

            <input type="hidden" name="request" value="remove">
            <button type="submit">Eliminar curso</button>

            <hr>

            <p>No quieres eliminar un curso? <a href="../dashboard.jsp">Regresa al menu</a></p>

        </form>

    </body>
    
</html>
