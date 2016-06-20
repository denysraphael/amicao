<%-- 
    Document   : CadastroUsuario
    Created on : 02/05/2016, 15:04:32
    Author     : -Denys
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form:form method="POST" action="cadastrar" modelAttribute="user">
            <h1>Cadastro Usuario</h1>
            <div>
                <form:label path="name">Nome: </form:label>
                <form:input path="name"/>
            </div>
            <div>
                <form:label path="email">E-mail: </form:label>
                <form:input path="email"/>
            </div>
            <div>
                <form:label path="phone">Telefone: </form:label>
                <form:input path="phone"/>
            </div>
            <div>
                <form:label path="password">Senha: </form:label>
                <form:input path="password"/>
            </div>
            <div><input type="submit" value="Enviar"></div>
        </form:form>
    </body>
</html>
