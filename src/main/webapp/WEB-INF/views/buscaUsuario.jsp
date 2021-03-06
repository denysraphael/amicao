<%-- 
    Document   : buscarUsuarioPorNome
    Created on : 03/05/2016, 10:28:42
    Author     : -Denys
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Buscar Usuário</title>
    </head>
    <body>
        <h1>Buscar Usuário</h1>
        <form action="buscar" method="GET">
            <h3>Escolha uma opção de busca:</h3>
            <div>
                <input type="radio" name="tipo" value="nome"> Nome
                <input type="radio" name="tipo" value="email"> E-mail
                <input type="radio" name="tipo" value="todos"> Listar Todos
            </div>
            <div>
                <input type="text" name="dado">
                <input type="submit" value="Buscar">
            </div>
        </form>

        <h3>Resultados:</h3>
        <c:if test="${not empty lists}">
            <ul>
                <c:forEach var="listValue" items="${lists}">
                    <li>${listValue}</li>
                    </c:forEach>
            </ul>

            <div>
                <form action="atualizar" method="POST">
                    <h3>Atualizar Usuário</h3>
                    <div>Email Atual: <input type="text" name="emailAtual"></div>
                    <div>Novo E-mail: <input type="email" name="email"></div>
                    <div>Novo Nome: <input type="text" name="nome"></div>
                    <div>Nova Senha: <input type="password" name="senha"></div>
                    <div>Novo Telefone: <input type="text" name="telefone"></div>
                    <div><input type="submit" value="Atualizar"></div>
                </form>

                <form action="desativar" method="POST">
                    <h3>Desativar Usuário</h3>
                    <h5>Informe o e-mail e a senha do usuário que deseja desativar</h5>
                    <div>E-mail: <input type="email" name="email"></div>
                    <div>Senha: <input type="password" name="senha"></div>
                    <div><input type="submit" value="Desativar"></div>
                </form>
            </div>
        </c:if>
    </body>
</html>

