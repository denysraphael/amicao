<%-- 
    Document   : UsuarioAtualizar
    Created on : 02/05/2016, 15:06:26
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
        <form action="atualizar" method="POST">
            <h1>Atualizar Usuario</h1>
            <div>Email: <input type="email" name="email"></div>
            <div>Nome: <input type="text" name="nome"></div>
            <div>Telefone: <input type="text" name="telefone"></div>
            <div>Senha: <input type="password" name="senha"></div>
            <div><input type="submit" value="Enviar"></div>
        </form>
    </body>
</html>
