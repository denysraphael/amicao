<%-- 
    Document   : UsuarioExcluir
    Created on : 03/05/2016, 10:19:48
    Author     : -Denys
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="excluir" method="POST">
            <h1>Excluir Usuario</h1>
            <div>Email: <input type="email" name="email"></div>
            <div><input type="submit" value="Enviar"></div>
        </form>
    </body>
</html>
