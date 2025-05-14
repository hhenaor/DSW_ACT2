<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <title>Crear nuevo curso</title>
        <link rel="stylesheet" href="../style.css"/>
    </head>

    <body>

        <h1>mpNotes</h1>

        <form action="<%= request.getContextPath()%>/course" method="post">

            <h2>Crear nuevo curso</h2>

            <div>
                <label for="name">Nombre corto:</label>
                <input type="text" id="name" name="name" required placeholder="Ingresa nombre corto del curso">
            </div>

            <div>
                <label for="fullName">Nombre completo:</label>
                <input type="text" id="fullName" name="full_name" required placeholder="Ingresa nombre completo del curso">
            </div>

            <div>
                <label for="description">Descripcion:</label>
                <textarea id="description" name="description" required placeholder="Describe brevemente el curso"></textarea>
            </div>

            <div>
                <label for="knowledgeArea">Area de conocimiento:</label>
                <input type="text" id="knowledgeArea" name="knowledge_area" required placeholder="Ej: Matematicas, Fisica, etc">
            </div>

            <div>
                <label for="career">Carrera:</label>
                <input type="text" id="career" name="career" required placeholder="Ej: Ingenieria de Software">
            </div>

            <div>
                <label for="credits">Creditos:</label>
                <input type="number" id="credits" name="credits" required min="0" placeholder="Numero de creditos">
            </div>

            <div>
                <label for="thematicContent">Contenido tematico:</label>
                <textarea id="thematicContent" name="thematic_content" required placeholder="Describe los temas del curso"></textarea>
            </div>

            <div>
                <label for="semester">Semestre:</label>
                <input type="text" id="semester" name="semester" required placeholder="Ej: 1, 2, 3...">
            </div>

            <div>
                <label for="professor">Profesor:</label>
                <input type="text" id="professor" name="professor" required placeholder="Nombre del profesor asignado">
            </div>

            <input type="hidden" name="request" value="insert">
            <button type="submit">Guardar curso</button>

            <hr>

            <p>No quieres registrar un curso? <a href="../dashboard.jsp">Regresa al menu</a></p>

        </form>

    </body>

</html>
