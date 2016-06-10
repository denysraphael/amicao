<%-- 
    Document   : cadastroAdocao
    Created on : 07/06/2016, 20:42:54
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
            <div>Telefone: <input type="texte" name="telefone"></div>
            <div>Senha: <input type="password" name="senha"></div>
            <div><input type="submit" value="Enviar"></div>
        </form>
    </body>
</html>
