package br.com.cadastroproduto.rest.controller.domain.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.cadastroproduto.persistence.entity.ProdutoEntity;
import br.com.cadastroproduto.persistence.entity.TipoProdutoEntity;
import br.com.cadastroproduto.persistence.entity.enuns.UnidadeMedidaEmun;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

	@NotNull(message = "descrição é obrigatório")
	@Size(min = 3, max = 100)
	String descricao;
	@Min(value = 1, message =  "quantidade deve ser informada!")
	Integer quantidade;
	@Size(min = 5, max = 40, message = "lote deve ser informado!!")
	String lote;
	@NotNull(message = "unidade de medida não informada!")
	UnidadeMedidaEmun unidadeMedida;
	TipoProdutoEntity tipoProduto;

	public ProdutoEntity build() {
		ProdutoEntity produtoEntity = new ProdutoEntity().setDescricao(this.descricao)
				.setQuantidade(this.quantidade)
				.setLote(this.lote)
				.setUnidadeMedida(this.unidadeMedida)
				.setTipoProduto(this.tipoProduto);
		return produtoEntity;
	}
}
