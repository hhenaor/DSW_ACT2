<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Buscar Curso</title>
</head>
<body>

    <h2>Buscar Curso</h2>

    <form action="<%= request.getContextPath() %>/course" method="post">

        <p>Para buscar un curso por su ID exacto, es recomendable dejar los otros campos vacíos. Si llenas otros campos, todos deben coincidir para encontrar el curso.</p>
        <div>
            <label for="courseId">Buscar por ID del Curso:</label>
            <input type="number" id="courseId" name="course_id" min="1">
        </div>
        <hr>

        <div>
            <label for="name">Buscar por Nombre Corto:</label>
            <input type="text" id="name" name="name">
        </div>
        <br>

        <div>
            <label for="fullName">Buscar por Nombre Completo:</label>
            <input type="text" id="fullName" name="full_name">
        </div>
        <br>

        <div>
            <label for="knowledgeArea">Buscar por Area de Conocimiento:</label>
            <input type="text" id="knowledgeArea" name="knowledge_area">
        </div>
        <br>

        <div>
            <label for="credits">Buscar por Numero de Creditos</label>
            <input type="text" id="credits" name="credits">
        </div>
        <br>

        <div>
            <label for="career">Buscar por Carrera:</label>
            <input type="text" id="career" name="career">
        </div>
        <br>

        <div>
            <label for="professor">Buscar por Profesor:</label>
            <input type="text" id="professor" name="professor">
        </div>
        <br>

        <input type="hidden" name="request" value="search">

        <button type="submit">Buscar</button>

    </form>

    <br>
    <p><a href="<%= request.getContextPath() %>/dashboard.jsp">regresar al dashboard</a></p>

</body>
</html>
