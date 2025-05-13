<%-- 
    Document   : dashboard
    Created on : 11 may 2025, 0:03:06
    Author     : horahenaripo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (request.getSession().getAttribute("login") == null) {
        response.sendRedirect("router.jsp?msg=Debes iniciar sesion para continuar&redir_to=login.jsp"); 
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Menu de la Aplicacion</title>
</head>
<body>
    <center>
        <h2>Menu de la Aplicacion</h2> 
        <p>Control de cursos</p>
        <table border="1">
            <tr>
                <td><h1><a href="web/usuario/agregar.jsp">Agregar</a></h1></td>
            </tr>
            <tr>
                <td><h1><a href="web/usuario/buscar.jsp">Buscar</a></h1></td>
            </tr>
            <tr>
                <td><h1><a href="web/usuario/modificar.jsp">Modificar</a></h1></td>
            </tr>
            <tr>
                <td><h1><a href="web/usuario/eliminar.jsp">Eliminar</a></h1></td>
            </tr>
            <tr>
                <td><h1><a href="usuario?accion=listartodo">Listar todo</a></h1></td>
            </tr>
            <tr>
                <td><h1><a href="ejemplosesion?accion=logout">Salir</a></h1></td>
            </tr>
        </table>
    </center>
</body>
</html>