<%-- 
    Document   : buscarUsuarioPorNome
    Created on : 03/05/2016, 10:28:42
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
        <form action="buscarPorNome" method="POST">
            <h1>Buscar Usuario</h1>
            <div>Nome: <input type="text" name="nome"></div>
            <div><input type="submit" value="Enviar"></div>
        </form>
    </body>
</html>
