<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (request.getSession().getAttribute("login") == null) {
        response.sendRedirect("router.jsp?msg=Debes iniciar sesion para continuar&redir_to=login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Dashboard</title>
</head>
<body>
        <h2>Menu de la Aplicacion</h2>
        <p>Control de cursos</p>
        <table border="1">
            <tr>
                <td><h1><a href="dash/create.jsp">Agregar</a></h1></td>
            </tr>
            <tr>
                <td><h1><a href="dash/remove.jsp">Eliminar</a></h1></td>
            </tr>
            <tr>
                <td><h1><a href="dash/update.jsp">Modificar</a></h1></td>
            </tr>
            <tr>
                <td><h1><a href="dash/search.jsp">Buscar</a></h1></td>
            </tr>
            <tr>
                <td><h1><a href="<%= request.getContextPath() %>/student?request=exit">Cerrar sesion</a></h1></td>
            </tr>
            <tr>
                <td><h1><a href="<%= request.getContextPath() %>/student?request=delete_user">Eliminar usuario</a></h1></td>
            </tr>
        </table>
</body>
</html>
