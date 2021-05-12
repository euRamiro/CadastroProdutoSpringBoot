package br.com.cadastroproduto.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.cadastroproduto.persistence.entity.enuns.UnidadeMedidaEmun;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity(name = "produto")
public class ProdutoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	String descricao;
	Integer quantidade;
	String lote;
	UnidadeMedidaEmun unidadeMedida;
	@ManyToOne
	TipoProdutoEntity tipoProduto;
}
