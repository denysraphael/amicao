<%-- 
    Document   : ExcluirAnimal
    Created on : 07/06/2016, 15:11:22
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
            <h1>Excluir Animal</h1>
            <div>Codigo: <input type="codigo" name="codigo"></div>
            <div><input type="submit" value="Enviar"></div>
        </form>
    </body>
</html>
