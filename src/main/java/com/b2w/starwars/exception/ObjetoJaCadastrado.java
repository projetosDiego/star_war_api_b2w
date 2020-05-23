package com.b2w.starwars.exception;

public class ObjetoJaCadastrado extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2577815816704031312L;
	
	public ObjetoJaCadastrado(String mensagem) {
		super(mensagem);
	}

}
