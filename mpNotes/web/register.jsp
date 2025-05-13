<%-- 
    Document   : register
    Created on : 12 may 2025, 17:28:35
    Author     : horahenaripo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registro</title>
</head>
<body>

    <h2>Registrar Nuevo Usuario</h2>

    <form action="<%= request.getContextPath() %>/student" method="post">
        
        <div>
            <label for="username">Nombre de Usuario:</label>
            <input type="text" id="username" name="username" required>
        </div>
        
        <br>
        
        <div>
            <label for="password">Contraseña:</label>
            <input type="password" id="password" name="password" required>
        </div>
        
        <br>
        
        <div>
            <label for="name">Nombre Completo:</label>
            <input type="text" id="name" name="name" required>
        </div>
        
        <br>
        
        <div>
            <label for="email">Correo Electrónico:</label>
            <input type="email" id="email" name="email" required>
        </div>
        
        <br>
        
        <input type="hidden" name="request" value="insert">
        <button type="submit">Crear Cuenta</button>
        
    </form>

    <br> 

    <p> ya estás registrado? <a href="login.jsp">haz click aqui</a> </p>

</body>
</html>