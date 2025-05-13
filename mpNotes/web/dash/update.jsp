<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <title>Modificar curso</title>
        <link rel="stylesheet" href="../style.css"/>
    </head>

    <body>

        <h1>mpnotes</h1>

        <form action="<%= request.getContextPath()%>/course" method="post">

            <h2>Modificar curso</h2>

            <div>
                <label for="courseId">Ingresa el ID del curso a modificar:</label>
                <input type="number" id="courseId" name="course_id" placeholder="5">
                <p>No sabes cual es el ID? Puedes buscarlo <a href="search.jsp">aqui</a></p>
            </div>

            <div>
                <label for="name">Nombre corto:</label>
                <input type="text" id="name" name="name" required placeholder="Cal">
            </div>

            <div>
                <label for="fullName">Nombre completo:</label>
                <input type="text" id="fullName" name="full_name" required placeholder="Calculo diferencial">
            </div>

            <div>
                <label for="description">Descripcion:</label>
                <textarea id="description" name="description" required placeholder="Resumen del curso"></textarea>
            </div>

            <div>
                <label for="knowledgeArea">Area de conocimiento:</label>
                <input type="text" id="knowledgeArea" name="knowledge_area" required placeholder="Matematicas">
            </div>

            <div>
                <label for="career">Carrera asociada:</label>
                <input type="text" id="career" name="career" required placeholder="Software">
            </div>

            <div>
                <label for="credits">Creditos:</label>
                <input type="number" id="credits" name="credits" required min="0" placeholder="3">
            </div>

            <div>
                <label for="thematicContent">Contenido tematico:</label>
                <textarea id="thematicContent" name="thematic_content" required placeholder="Temas del curso"></textarea>
            </div>

            <div>
                <label for="semester">Semestre:</label>
                <input type="text" id="semester" name="semester" required placeholder="Segundo">
            </div>

            <div>
                <label for="professor">Nombre del profesor:</label>
                <input type="text" id="professor" name="professor" required placeholder="Juan perez">
            </div>

            <input type="hidden" name="request" value="update">
            <button type="submit">Actualizar curso</button>

            <hr>
            <p>No quieres actualizar un curso? <a href="../dashboard.jsp">Regresa al menu</a></p>

        </form>

    </body>

</html>