/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.exceptions;

public class AnimalInexistenteException extends Exception {
    public AnimalInexistenteException(Exception e) {
        super(e);
    }
    
    public AnimalInexistenteException() {
        super("O animal não foi encontrado!");
    }
}
