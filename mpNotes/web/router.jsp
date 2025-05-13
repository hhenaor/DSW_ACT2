<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <title>Mensaje del sistema</title>
        <%
            String msg = request.getParameter("msg");
            String redirTo = request.getParameter("redir_to");
            String destinoUrl = (redirTo != null && !redirTo.isEmpty()) ? redirTo : "login.jsp";
        %>
        <script>
            function autoRedirect() {
                window.location.href = '<%= destinoUrl%>';
            }
            setTimeout(autoRedirect, 5000);
        </script>
        <link rel="stylesheet" href="style.css"/>
    </head>

    <body>

        <h1>mpNotes</h1>

        <div class="focus">

            <h2>Mensaje del sistema</h2>

            <%
                if (msg != null && !msg.isEmpty()) {
                    out.println("<p>" + msg + "</p>");
                } else {
                    out.println("<p>No se establecio un mensaje</p>");
                }
            %>

            <hr>

            <p>Seras redireccionado en unos momentos. <a href="<%= destinoUrl%>">Haz clic aqui para ir ahora.</a></p>

        </div>

    </body>

</html>
