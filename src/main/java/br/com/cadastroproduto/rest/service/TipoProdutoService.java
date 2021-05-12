package br.com.cadastroproduto.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.cadastroproduto.exception.model.TipoProdutoNaoEncontradoException;
import br.com.cadastroproduto.persistence.entity.TipoProdutoEntity;
import br.com.cadastroproduto.persistence.repository.TipoProdutoRepository;

@Service
public class TipoProdutoService {

	@Autowired
	private TipoProdutoRepository tipoProdutoRepository;

	public TipoProdutoEntity save(TipoProdutoEntity novoTipoProduto) {
		return tipoProdutoRepository.save(novoTipoProduto);
	}

	public TipoProdutoEntity edit(TipoProdutoEntity tipoProdutoAlterar, Long id) {
		tipoProdutoAlterar.setId(id);
		return tipoProdutoRepository.save(tipoProdutoAlterar);
	}

	public void delete(Long id) {
		tipoProdutoRepository.deleteById(id);
	}

	public Page<TipoProdutoEntity> buscarTodos(Pageable pageable) {
		Page<TipoProdutoEntity> tipoProdutoPage = tipoProdutoRepository.findAll(pageable);
		if (tipoProdutoPage.getTotalElements() == 0) {
			throw new TipoProdutoNaoEncontradoException("nenhum Tipo Produto encontrado!  :(");
		}
		return tipoProdutoPage;
	}

	public Page<TipoProdutoEntity> buscarTodosPorDescricao(Pageable pageable, String descricao) {
		Page<TipoProdutoEntity> tipoProdutoPage = tipoProdutoRepository.findByDescricao(pageable, descricao);
		if (tipoProdutoPage.getTotalElements() == 0) {
			throw new TipoProdutoNaoEncontradoException("Descrição do Tipo de Produto não encontrada!   :(");
		}
		return tipoProdutoPage;
	}

	public Integer teste() {
		Integer div = 2 / 0;
		return div;
	}

}
