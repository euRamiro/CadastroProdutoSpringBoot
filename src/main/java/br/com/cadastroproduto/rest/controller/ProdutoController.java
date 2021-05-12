package br.com.cadastroproduto.rest.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.cadastroproduto.persistence.entity.ProdutoEntity;
import br.com.cadastroproduto.rest.controller.domain.dto.request.ProdutoDTO;
import br.com.cadastroproduto.rest.service.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public String index() {
		return "produto rodando... :)";
	}

	@PostMapping
	public ResponseEntity<ProdutoEntity> save(@RequestBody @Validated ProdutoDTO novoProduto) {
		ProdutoEntity produtonovo = produtoService.save(novoProduto.build());

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produtonovo.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProdutoEntity> edit(@RequestBody ProdutoDTO editar, @PathVariable Long id) {
		ProdutoEntity produtoAlterar = produtoService.edit(editar.build(), id);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produtoAlterar.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		produtoService.delete(id);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/buscartodos")
	public Page<ProdutoEntity> buscarTodos(
			@PageableDefault(size = 5, sort = "id", direction = Direction.DESC) Pageable pageable) {
		Page<ProdutoEntity> produtoAllPages = produtoService.buscarTodos(pageable);
		List<ProdutoEntity> produtoList = produtoAllPages.getContent();
		Page<ProdutoEntity> produtoPagePrint = new PageImpl<>(produtoList, pageable,
				produtoAllPages.getTotalElements());
		return produtoPagePrint;
	}

	@GetMapping("/buscartodospordescricao/{descricao}")
	public Page<ProdutoEntity> buscartodospordescricao(
			@PageableDefault(size = 5, sort = "descricao", direction = Direction.ASC) Pageable pageable,
			@PathVariable String descricao) {
		Page<ProdutoEntity> produtoAllPages = produtoService.buscarPorDescricao(pageable, descricao);
		List<ProdutoEntity> produtoList = produtoAllPages.getContent();
		Page<ProdutoEntity> produtoPagePrint = new PageImpl<>(produtoList, pageable,
				produtoAllPages.getTotalElements());

		return produtoPagePrint;
	}

	@GetMapping("/buscartodosporlote/{lote}")
	public Page<ProdutoEntity> buscarporlote(
			@PageableDefault(size = 5, sort = "descricao", direction = Direction.ASC) Pageable pageable,
			@PathVariable String lote) {
		Page<ProdutoEntity> produtoAllPages = produtoService.buscarPorLote(pageable, lote);
		List<ProdutoEntity> produtoList = produtoAllPages.getContent();
		Page<ProdutoEntity> produtoPagePrint = new PageImpl<>(produtoList, pageable, produtoAllPages.getTotalElements());
		
		return produtoPagePrint;
	}

}
