package br.com.cadastroproduto.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.cadastroproduto.exception.model.ProdutoNaoEncontradoException;
import br.com.cadastroproduto.persistence.entity.ProdutoEntity;
import br.com.cadastroproduto.persistence.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;

	public ProdutoEntity save(ProdutoEntity produto) {
		return produtoRepository.save(produto);
	}

	public ProdutoEntity edit(ProdutoEntity produtoAlterar, Long id) {
		produtoAlterar.setId(id);
		return produtoRepository.save(produtoAlterar);
	}

	public void delete(Long id) {
		produtoRepository.deleteById(id);
	}

	public Page<ProdutoEntity> buscarTodos(Pageable pageable) {
		Page<ProdutoEntity> produtoPage = produtoRepository.findAll(pageable);
		if (produtoPage.getTotalElements() == 0) {
			throw new ProdutoNaoEncontradoException("nenhum produto encontrado!  :(");
		}
		return produtoPage;
	}

	public Page<ProdutoEntity> buscarPorDescricao(Pageable pageable, String descricao) {
		Page<ProdutoEntity> produtoPage = produtoRepository.findByDescricao(pageable, descricao);
		if (produtoPage.getTotalElements() == 0) {
			throw new ProdutoNaoEncontradoException("nenhum produto encontrado!  :(  buscarPorDescricao");
		}
		return produtoPage;
	}

	public Page<ProdutoEntity> buscarPorLote(Pageable pageable, String lote) {
		Page<ProdutoEntity> produtoPage = produtoRepository.findByLote(pageable, lote);
		if (produtoPage.getTotalElements() == 0) {
			throw new ProdutoNaoEncontradoException("nenum produto encontrado!  :(   buscarPorLote");
		}
		return produtoPage;
	}

}
