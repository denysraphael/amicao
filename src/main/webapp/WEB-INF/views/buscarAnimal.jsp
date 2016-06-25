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
        <title>Buscar Animal</title>
    </head>
    <body>
        <h1>Buscar Animal</h1>
        <form action="buscar" method="GET">
            <h3>Escolha uma opção de busca:</h3>
            <div>
                <input type="radio" name="tipo" value="nome"> Nome
                <input type="radio" name="tipo" value="raca"> Raça
                <input type="radio" name="tipo" value="caracteristicas"> Características
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
                    <h3>Atualizar Animal</h3>
                    <div>Código: <input type="text" name="codigo"></div>
                    <div>Novo Nome: <input type="text" name="nome"></div>
                    <div>Nova Raça: <input type="text" name="raca"></div>
                    <div>Novas Características: <input type="text" name="caracteristicas"></div>
                    <div><input type="submit" value="Atualizar"></div>
                </form>
            </div>
        </c:if>
    </body>
</html>

