package br.com.cadastroproduto.exception.handler.validation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldMessage {

	private String fieldName;
	private String message;
}
