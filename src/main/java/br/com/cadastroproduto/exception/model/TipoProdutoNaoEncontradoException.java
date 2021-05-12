package br.com.cadastroproduto.exception.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TipoProdutoNaoEncontradoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TipoProdutoNaoEncontradoException(String message) {
		super(message);
	}

}
