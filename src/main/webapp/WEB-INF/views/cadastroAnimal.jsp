<%-- 
    Document   : CadastrarAnimal
    Created on : 03/05/2016, 10:34:19
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
            <h1>Cadastro Animal</h1>
            <h3>Informações do Dono</h3>
            <div>E-mail: <input type="email" name="email"></div>
            <div>Senha: <input type="password" name="senha"></div>
            <h3>Informações do Animal</h3>
            <div>Nome: <input type="text" name="nome"></div>
            <div>Raca: <input type="text" name="racaAnimal"></div>
            <div>Classificação: <input type="text" name="classifAnimal"></div>
            <div>Caracteristicas: <input type="text" name="caracteristicas"></div>
            <div>Data: <input type="date" name="dataNascimento"></div>
            <div><input type="submit" value="Enviar"></div>
        </form>
    </body>
</html>
