package br.com.cadastroproduto.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cadastroproduto.persistence.entity.ProdutoEntity;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

	Page<ProdutoEntity> findByDescricao(Pageable pageable, String descricao);
	Page<ProdutoEntity> findByLote(Pageable pageable, String lote);
}
