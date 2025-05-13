<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <title>Buscar curso</title>
        <link rel="stylesheet" href="../style.css"/>
    </head>

    <body>

        <h1>mpNotes</h1>

        <form action="<%= request.getContextPath()%>/course" method="post">

            <h2>Buscar curso</h2>

            <!--p>Para buscar un curso por su ID exacto, es recomendable dejar los otros campos vacios. Si llenas otros campos, todos deben coincidir para encontrar el curso.</p>

            <div>
                <label for="courseId">Buscar por ID del curso:</label>
                <input type="number" id="courseId" name="course_id" min="1" placeholder="ID exacto si lo sabes">
            </div>
            
            <hr-->

            <div>
                <label for="name">Buscar por nombre corto:</label>
                <input type="text" id="name" name="name" placeholder="Ej: cal">
            </div>

            <div>
                <label for="fullName">Buscar por nombre completo:</label>
                <input type="text" id="fullName" name="full_name" placeholder="Ej: calculo diferencial">
            </div>

            <div>
                <label for="knowledgeArea">Buscar por area de conocimiento:</label>
                <input type="text" id="knowledgeArea" name="knowledge_area" placeholder="Ej: matematicas">
            </div>

            <div>
                <label for="credits">Buscar por numero de creditos:</label>
                <input type="text" id="credits" name="credits" placeholder="Ej: 3">
            </div>

            <div>
                <label for="career">Buscar por carrera:</label>
                <input type="text" id="career" name="career" placeholder="Ej: software">
            </div>

            <div>
                <label for="professor">Buscar por profesor:</label>
                <input type="text" id="professor" name="professor" placeholder="Ej: juan perez">
            </div>

            <input type="hidden" name="request" value="search">
            <button type="submit">Buscar curso</button>

            <hr>

            <p>No quieres buscar un curso? <a href="../dashboard.jsp">Regresa al menu</a></p>

        </form>

    </body>

</html>
