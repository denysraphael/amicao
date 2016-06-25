<%-- 
    Document   : CadastroUsuario
    Created on : 02/05/2016, 15:04:32
    Author     : -Denys
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <title>User Register</title>
    </head>
    <body>
        <h1>Tela Principal</h1>
        <div>
            <h3>Usuário</h3>
            <form action="usuario/cadastro">
                <input type="submit" value="Cadastrar Usuário" >
            </form>
            <form action="usuario/busca">
                <input type="submit" value="Buscar Usuário" >
            </form>
        </div>
        <div>
            <h3>Animal</h3>
            <form action="animal/cadastro">
                <input type="submit" value="Cadastrar Animal" >
            </form>
            <form action="animal/busca">
                <input type="submit" value="Buscar Animal" >
            </form>
        </div>
        <div>
            <h3>Adoção</h3>
            <form action="adocao/cadastro">
                <input type="submit" value="Criar Adoção" >
            </form>
            <form action="adocao/busca">
                <input type="submit" value="Buscar Adoção" >
            </form>
        </div>
    </body>
</html>