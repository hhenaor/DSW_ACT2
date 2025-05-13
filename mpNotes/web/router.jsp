<%-- 
    Document   : router
    Created on : 12 may 2025, 18:09:11
    Author     : horahenaripo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Mensaje del Sistema</title>
    <%
        String msg = request.getParameter("msg");
        String redirTo = request.getParameter("redir_to");

        String destinoUrl = (redirTo != null && !redirTo.isEmpty()) ? redirTo : "login.jsp";

    %>
    <script>
        function autoRedirect() { window.location.href = '<%= destinoUrl %>'; }
        setTimeout(autoRedirect, 5000);
    </script>
</head>
<body>

    <h1>Mensaje del sistema</h1>

    <%
        if (msg != null && !msg.isEmpty()) {
            out.println("<p style=\"color: darkblue;\">" + msg + "</p><hr>");
        } else {
            out.println("<p style=\"color: darkblue;\">No se establecio un mensaje</p><hr>");
        }
    %>

    <p>Seras redireccionado en unos momentos</p>
    <p><a href="<%= destinoUrl %>">Haz clic aqui para ir ahora.</a></p>



</body>
</html>