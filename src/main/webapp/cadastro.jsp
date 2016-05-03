<%-- 
    Document   : cadastro
    Created on : 02/05/2016, 14:57:40
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
        <form action="cadastrar" method="POST">
            <h1>Cadastro Usuario</h1>
            <div>Nome: <input type="text" name="nome"></div>
            <div>Email: <input type="email" name="email"></div>
            <div>Telefone: <input type="telefone" name="telefone"></div>
            <div>Senha: <input type="password" name="nome"></div>
            <div><input type="submit" value="Enviar"></div>
        </form>
    </body>
</html>
