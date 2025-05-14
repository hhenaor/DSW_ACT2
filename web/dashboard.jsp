<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (request.getSession().getAttribute("login") == null) {
        response.sendRedirect("router.jsp?msg=Debes iniciar sesion para continuar&redir_to=login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <title>Dashboard</title>
        <link rel="stylesheet" href="style.css"/>
    </head>

    <body>

        <h1>mpNotes</h1>

        <div class="focus">

            <h2>Menu de la aplicacion</h2>
            <h5>Control de cursos</h5>

            <h3><a href="dash/create.jsp">Agregar curso</a></h3>
            <h3><a href="dash/remove.jsp">Eliminar curso</a></h3>
            <h3><a href="dash/update.jsp">Modificar curso</a></h3>
            <h3><a href="dash/search.jsp">Buscar curso</a></h3>

            <hr>

            <h3><a href="<%= request.getContextPath()%>/student?request=exit">Cerrar sesion</a></h3>
            <h3><a href="<%= request.getContextPath()%>/student?request=remove">Eliminar usuario</a></h3>

        </div>

    </body>

</html>
