/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.exceptions;

public class AdocaoExistenteException extends Exception {
    public AdocaoExistenteException(Exception e) {
        super(e);
    }
    
    public AdocaoExistenteException() {
        super("A adoção já está cadastrado no sistema!");
    }
}
