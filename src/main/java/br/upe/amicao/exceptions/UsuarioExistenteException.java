/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.exceptions;

public class UsuarioExistenteException extends Exception {
    
    public UsuarioExistenteException(Exception e) {
        super(e);
    }
    
    public UsuarioExistenteException() {
        super("O usuário já está cadastrado no sistema!");
    }
}
