<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Eliminar Curso</title>
</head>
<body>

    <h2>Eliminar Curso</h2>

    <form action="<%= request.getContextPath() %>/course" method="post">

        <div>
            <label for="courseId">ID del Curso a Eliminar:</label>
            <input type="number" id="courseId" name="course_id" required min="1">
            <p>no sabes que id es? <a href="search.jsp">buscalo aqui</a></p>
        </div>
        
        <input type="hidden" name="request" value="remove">
        <button type="submit">Eliminar Curso</button>

    </form>

    <p><a href="../dashboard.jsp">regresar al dashboard</a></p>

</body>
</html>
