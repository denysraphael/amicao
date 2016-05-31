<%-- 
    Document   : cadastroRaca
    Created on : 24/05/2016, 15:16:00
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
            <h1>Cadastro Raca</h1>
            <div>Nome: <input type="text" name="nome"></div>
            <div>Classificacao: <input type="text" name="classificacao"></div>
            <div><input type="submit" value="Enviar"></div>
        </form>
    </body>
</html>
