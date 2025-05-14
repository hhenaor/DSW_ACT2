<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (request.getSession().getAttribute("login") != null) {
        response.sendRedirect("router.jsp?msg=Ya inicio sesion&redir_to=dashboard.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="style.css"/>
    </head>

    <body>

        <h1>mpNotes</h1>

        <form action="<%= request.getContextPath()%>/student" method="post">

            <h2>Iniciar sesion</h2>

            <div>
                <label for="username">Usuario:</label>
                <input type="text" id="username" name="username" required placeholder="Ingresa tu usuario">
            </div>

            <div>
                <label for="password">Contrasena:</label>
                <input type="password" id="password" name="password" required placeholder="Ingresa tu contrasena">
            </div>

            <input type="hidden" name="request" value="login">
            <button type="submit">Entrar</button>

            <hr>

            <p>No tienes usuario? <a href="register.jsp">Registrate aqui</a></p>

        </form>

    </body>

</html>
