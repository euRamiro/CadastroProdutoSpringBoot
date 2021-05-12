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

import br.com.cadastroproduto.persistence.entity.TipoProdutoEntity;
import br.com.cadastroproduto.rest.controller.domain.dto.request.TipoProdutoDTO;
import br.com.cadastroproduto.rest.service.TipoProdutoService;

@RestController
@RequestMapping("/tipo")
public class TipoProdutoController {

	@Autowired
	private TipoProdutoService tipoProdutoService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public String index() {
		return "tipo de produto rodando... :)";
	}

	@PostMapping
	public ResponseEntity<TipoProdutoEntity> save(@RequestBody @Validated TipoProdutoDTO novoTipoProduto) {
		TipoProdutoEntity tipoProdutonovo = tipoProdutoService.save(novoTipoProduto.build());

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tipoProdutonovo.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<TipoProdutoEntity> edit(@RequestBody TipoProdutoDTO editar, @PathVariable Long id) {
		TipoProdutoEntity tipoProdutoAlterar = tipoProdutoService.edit(editar.build(), id);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(tipoProdutoAlterar.getId()).toUri();

		return ResponseEntity.created(uri).build();

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		tipoProdutoService.delete(id);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/buscartodos")
	public Page<TipoProdutoEntity> buscarTodos(
			@PageableDefault(size = 5, sort = "id", direction = Direction.DESC) Pageable pageable) {
		Page<TipoProdutoEntity> tipoProdutoPageAllPages = tipoProdutoService.buscarTodos(pageable);
		List<TipoProdutoEntity> tipoProdutoList = tipoProdutoPageAllPages.getContent();
		Page<TipoProdutoEntity> tipoProdutoPagePrint = new PageImpl<>(tipoProdutoList, pageable,
				tipoProdutoPageAllPages.getNumberOfElements());
		return tipoProdutoPagePrint;
	}

	@GetMapping("/buscartodospordescricao/{descricao}")
	public Page<TipoProdutoEntity> buscarTodosPorDescricao(
			@PageableDefault(size = 5, sort = "id", direction = Direction.DESC) Pageable pageable, @PathVariable String descricao) {
		Page<TipoProdutoEntity> tipoProdutoPageAllPages = tipoProdutoService.buscarTodosPorDescricao(pageable,
				descricao);
		List<TipoProdutoEntity> tipoProdutoList = tipoProdutoPageAllPages.getContent();
		Page<TipoProdutoEntity> tipoProdutoPagePrint = new PageImpl<>(tipoProdutoList, pageable,
				tipoProdutoPageAllPages.getTotalElements());
		return tipoProdutoPagePrint;
	}

	@GetMapping("/teste")
	public Integer div() {
		return tipoProdutoService.teste();
	}

}
