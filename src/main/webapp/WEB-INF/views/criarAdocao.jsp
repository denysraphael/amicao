<%-- 
    Document   : criarAdocao
    Created on : 24/06/2016, 15:20:13
    Author     : -Denys
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Criar Adoção</title>
    </head>
    <body>
        <form action="cadastrar" method="POST">
            <h1>Iniciar uma Adoção</h1>
            <div>Descrição: <input type="text" name="descricao"></div>
            <h3>Informações do Doador</h3>
            <div>E-mail: <input type="email" name="email"></div>
            <div>Senha: <input type="password" name="senha"></div>
            <h3>Informações do Animal</h3>
            <div>Nome: <input type="text" name="nomeAnimal"></div>
            <div><input type="submit" value="Criar"></div>
        </form>
    </body>
</html>
