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
</head>
<body>

    <h2>Iniciar Sesion</h2>

    <form action="<%= request.getContextPath() %>/student" method="post"> 
        
        <div>
            <label for="username">Usuario:</label>
            <input type="text" id="username" name="username" required>
        </div>
        
        <br>
        
        <div>
            <label for="password">Contraseña:</label>
            <input type="password" id="password" name="password" required>
        </div>
        
        <br>
        
        <input type="hidden" name="request" value="login">
        <button type="submit">Entrar</button>
        
        <hr>
        
        <a href="register.jsp">Registrarse</a>
        
    </form>

</body>
</html>