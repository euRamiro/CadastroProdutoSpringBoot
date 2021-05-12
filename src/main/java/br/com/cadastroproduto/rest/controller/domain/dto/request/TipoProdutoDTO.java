package br.com.cadastroproduto.rest.controller.domain.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.cadastroproduto.persistence.entity.TipoProdutoEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoProdutoDTO {

	@NotNull(message = "descrição é obrigatório")
	@Size(min = 3, max = 100)
	String descricao;

	public TipoProdutoEntity build() {
		TipoProdutoEntity tipoProdutoEntity = new TipoProdutoEntity().setDescricao(this.descricao);
		return tipoProdutoEntity;
	}
}
