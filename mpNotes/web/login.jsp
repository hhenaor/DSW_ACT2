<%-- 
    Document   : index
    Created on : 10 may 2025, 23:13:31
    Author     : horahenaripo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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