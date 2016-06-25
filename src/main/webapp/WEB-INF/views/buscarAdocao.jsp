<%-- 
    Document   : buscarAdocao
    Created on : 24/06/2016, 17:21:18
    Author     : -Denys
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Buscar Adoção</title>
    </head>
    <body>
        <h1>Buscar Adoção</h1>
        <form action="buscar" method="GET">
            <h3>Escolha uma opção de busca:</h3>
            <div>
                <input type="radio" name="tipo" value="descricao"> Descrição
                <input type="radio" name="tipo" value="nomeAnimal"> Nome do Animal
                <input type="radio" name="tipo" value="raca"> Raça
                <input type="radio" name="tipo" value="todos"> Listar Todos
            </div>
            <div>
                <input type="text" name="descricao">
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

            <h3>Qual adoção você tem interesse?</h3>
            <form action="realizarAdocao" method="POST">
                <div>
                    <h3>Gostaria de adotar um animal?</h3>
                    <div>Código da Adoção: <input type="text" name="codAdocao"></div>
                    <h3>Informações do Adotante</h3>
                    <div>E-mail: <input type="email" name="email"></div>
                    <div>Senha: <input type="password" name="senha"></div>
                    <div><input type="submit" value="Adotar"></div>
                </div>
            </form>
        </c:if>
    </body>
</html>
