package br.com.cadastroproduto.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cadastroproduto.persistence.entity.TipoProdutoEntity;

public interface TipoProdutoRepository extends JpaRepository<TipoProdutoEntity, Long>{

	Page<TipoProdutoEntity> findByDescricao(Pageable pageable, String descricao);
}
