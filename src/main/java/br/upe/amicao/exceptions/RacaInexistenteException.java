/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.exceptions;

public class RacaInexistenteException extends Exception {
    public RacaInexistenteException(Exception e) {
        super(e);
    }
    
    public RacaInexistenteException() {
        super("Raça não encontrado!");
    }
}
