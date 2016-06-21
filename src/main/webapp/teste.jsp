<%-- 
    Document   : CadastroUsuario
    Created on : 02/05/2016, 15:04:32
    Author     : -Denys
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <title>User Register</title>

        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.19/angular.js"></script>
        <script type="text/javascript">
            var app = angular.module("aplicacao", []);

            app.controller("ControladorUsuario", ["$http", function ($http) {
                    this.enviar = function () {
                        console.dir(this.usuario);
                        
                        var dados = {"nome" : this.usuario.nome,
                                    "email" : this.usuario.email,
                                    "telefone" : this.usuario.telefone,
                                    "senha" : this.usuario.senha,
                                    "animais" : null,
                                    "adocoesAnunciadas" : null,
                                    "adocoesInteressadas" : null,
                                    "adocoesRealizadas" : null,
                                    "ativo" : true};

                        var response = $http.post("cadastrar", dados);
                        console.dir(response);
                    };
                }]);
        </script>
    </head>
    <body ng-app="aplicacao">
        <h1>Cadastro Usuário</h1>
        <form ng-controller="ControladorUsuario as contUser">
            Nome: <input type="text" name="nome" ng-model="contUser.usuario.nome">
            Email: <input type="email" name="email" ng-model="contUser.usuario.email">
            Telefone: <input type="texte" name="telefone" ng-model="contUser.usuario.telefone">
            Senha: <input type="password" name="senha" ng-model="contUser.usuario.senha">
            <input type="button" value="Cadastrar" ng-click="contUser.enviar()"></div>
        </form>
    </body>
</html>