package com.b2w.starwars.exception;

public class ObjetoNaoEncontradoException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1711999754566083889L;

	public ObjetoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

}
