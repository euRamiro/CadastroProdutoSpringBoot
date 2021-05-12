package br.com.cadastroproduto.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity(name = "tipo_produto")
public class TipoProdutoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	String descricao;
}
