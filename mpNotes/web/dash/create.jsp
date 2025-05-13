<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Crear Nuevo Curso</title>
</head>
<body>

    <h2>Crear Nuevo Curso</h2>
    <form action="<%= request.getContextPath() %>/course" method="post">

        <div>
            <label for="name">Nombre Corto:</label>
            <input type="text" id="name" name="name" required>
        </div>
        <br>

        <div>
            <label for="fullName">Nombre Completo:</label>
            <input type="text" id="fullName" name="full_name" required>
        </div>
        <br>

        <div>
            <label for="description">Descripcion:</label>
            <textarea id="description" name="description" required></textarea>
        </div>
        <br>

        <div>
            <label for="knowledgeArea">Area de Conocimiento:</label>
            <input type="text" id="knowledgeArea" name="knowledge_area" required>
        </div>
        <br>

        <div>
            <label for="career">Carrera:</label>
            <input type="text" id="career" name="career" required>
        </div>
        <br>

        <div>
            <label for="credits">Creditos:</label>
            <input type="number" id="credits" name="credits" required min="0">
        </div>
        <br>

        <div>
            <label for="thematicContent">Contenido Tematico:</label>
            <textarea id="thematicContent" name="thematic_content" required></textarea>
        </div>
        <br>

        <div>
            <label for="semester">Semestre:</label>
            <input type="text" id="semester" name="semester" required>
        </div>
        <br>

        <div>
            <label for="professor">Profesor:</label>
            <input type="text" id="professor" name="professor" required>
        </div>
        <br>

        <input type="hidden" name="request" value="insert">

        <button type="submit">Guardar Curso</button>

    </form>

    <br>
    <p><a href="../dashboard.jsp">regresar al dashboard</a></p>

</body>
</html>
