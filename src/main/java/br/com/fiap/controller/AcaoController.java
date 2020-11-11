package br.com.fiap.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fiap.model.AcaoModel;
import br.com.fiap.repository.AcaoRepository;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/acao")
public class AcaoController {
	
	@Autowired
	public AcaoRepository repository;
	
	@GetMapping()
	@ApiOperation("Retorna uma lista de ações")
	public ResponseEntity<List<AcaoModel>> findAll() {

		List<AcaoModel> acoes = repository.findAll();
		return ResponseEntity.ok(acoes);
	}
	
	@GetMapping("/{id}")
	@ApiOperation("Retorna uma ação apartir do identificador")
	public ResponseEntity<AcaoModel> findById(@PathVariable("id") int id) {

		AcaoModel acaoModel = repository.findById(id).get();
		return ResponseEntity.ok(acaoModel);
	}
	
	@PostMapping()
	@ApiOperation("Salva uma nova ação")
	public ResponseEntity save(@RequestBody @Valid AcaoModel acaoModel) {

		repository.save(acaoModel);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(acaoModel.getId_acao()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/{id}")
	@ApiOperation("Atualiza uma ação apartir do identificador")
	public ResponseEntity update(@PathVariable("id") int id, @RequestBody @Valid AcaoModel acaoModel) {

		acaoModel.setId_acao(id);
		repository.save(acaoModel);

		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation("Exclui uma ação apartir do identificador")
	public ResponseEntity deleteById(@PathVariable("id") int id) {

		repository.deleteById(id);

		return ResponseEntity.noContent().build();
	}
}
