/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upe.amicao.exceptions;

public class RacaExistenteException extends Exception {
    public RacaExistenteException(Exception e) {
        super(e);
    }
    
    public RacaExistenteException() {
        super("A raça já está cadastrado no sistema!");
    }
}
