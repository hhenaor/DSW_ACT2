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
        <title>Registro</title>
        <link rel="stylesheet" href="style.css"/>
    </head>

    <body>

        <h1>mpNotes</h1>

        <form action="<%= request.getContextPath()%>/student" method="post">

            <h2>Registrar nuevo usuario</h2>

            <div>
                <label for="username">Nombre de usuario:</label>
                <input type="text" id="username" name="username" required placeholder="Ingresa nombre de usuario">
            </div>

            <div>
                <label for="password">Contrasena:</label>
                <input type="password" id="password" name="password" required placeholder="Ingresa tu contrasena">
            </div>

            <div>
                <label for="name">Nombre completo:</label>
                <input type="text" id="name" name="name" required placeholder="Ingresa tu nombre completo">
            </div>

            <div>
                <label for="email">Correo electronico:</label>
                <input type="email" id="email" name="email" required placeholder="Ingresa tu correo electronico">
            </div>

            <input type="hidden" name="request" value="insert">
            <button type="submit">Crear cuenta</button>

            <hr>

            <p>Ya tienes cuenta? <a href="login.jsp">Inicia sesion aqui</a></p>

        </form>

    </body>

</html>
