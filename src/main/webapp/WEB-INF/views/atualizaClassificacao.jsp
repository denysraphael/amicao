<%-- 
    Document   : atualizaClassificacao
    Created on : 04/06/2016, 17:04:15
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
            <div>Nome Atual: <input type="text" name="nomeAtual"></div>
            <div>Novo Nome: <input type="text" name="nomeAtualizar"></div>
            <div><input type="submit" value="Enviar"></div>
        </form>
    </body>
</html>
