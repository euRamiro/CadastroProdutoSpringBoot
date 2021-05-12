package br.com.cadastroproduto.exception.handler.validation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.cadastroproduto.exception.model.TipoProdutoNaoEncontradoException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(TipoProdutoNaoEncontradoException.class)
	public ResponseEntity<StandardError> handlerObjectNotFoundException(TipoProdutoNaoEncontradoException e,
			HttpServletRequest req) {
		StandardError error = new StandardError();
		error.setTimestamp(System.currentTimeMillis());
		error.setStatus(404);
		error.setError("não existem dados de paciente...");
		error.setMessage(e.getMessage());
		error.setPath(req.getRequestURI());
		error.setMessageDev("página personalizada explicando o erro.");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<StandardError> handlerObjectNotFoundException(EmptyResultDataAccessException e,
			HttpServletRequest req) {
		StandardError error = new StandardError();
		error.setTimestamp(System.currentTimeMillis());
		error.setStatus(404);
		error.setError("ihhh,   deu ruim!!  :(");
		error.setMessage(e.getMessage());
		error.setPath(req.getRequestURI());
		error.setMessageDev("página personalizada explicando o erro.");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}	
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> handlerObjectNotFoundException(DataIntegrityViolationException e,
			HttpServletRequest req) {
		StandardError error = new StandardError();
		error.setTimestamp(System.currentTimeMillis());
		error.setStatus(500);
		error.setError("Violação de integridade!  :(");
		error.setMessage(e.getMessage());
		error.setPath(req.getRequestURI());
		error.setMessageDev("página personalizada explicando o erro.");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(ArithmeticException.class)
	public ResponseEntity<StandardError> handlerObjectNotFoundException(ArithmeticException e,
			HttpServletRequest req) {
		StandardError error = new StandardError();
		error.setTimestamp(System.currentTimeMillis());
		error.setStatus(400);
		error.setError("erro na divisão...");
		error.setMessage(e.getMessage());
		error.setPath(req.getRequestURI());
		error.setMessageDev("página personalizada explicando o erro.");

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest req) {
		ValidationError error = new ValidationError();

		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			error.addError(x.getField(), x.getDefaultMessage());
		}

		error.setTimestamp(System.currentTimeMillis());
		error.setStatus(422);
		error.setError("inválido...");
		error.setMessage(e.getMessage());
		error.setPath(req.getRequestURI());
		error.setMessageDev("página personalizada explicando o erro.");
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
	}

}
