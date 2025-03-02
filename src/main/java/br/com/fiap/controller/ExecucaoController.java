package br.com.fiap.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fiap.business.ExecucaoBusiness;
import br.com.fiap.exception.ReponseBusinessException;
import br.com.fiap.model.ExecucaoModel;
import br.com.fiap.repository.ExecucaoRepository;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/execucao")
public class ExecucaoController {

	@Autowired
	public ExecucaoRepository repository;
	
	@Autowired
	public ExecucaoBusiness execucaoBusiness;
	
	@GetMapping()
	@ApiOperation("Retorna uma lista de execu��es")
	public ResponseEntity<List<ExecucaoModel>> findAll() {

		List<ExecucaoModel> execucao = repository.findAll();
		return ResponseEntity.ok(execucao);
	}
	
	@GetMapping("/{id}")
	@ApiOperation("Retorna uma execu��o apartir do identificador")
	public ResponseEntity<ExecucaoModel> findById(@PathVariable("id") int id) {

		ExecucaoModel execucaoModel = repository.findById(id).get();
		return ResponseEntity.ok(execucaoModel);
	}
	
	@PostMapping()
	@ApiOperation("Salva uma nova execu��o")
	public ResponseEntity save(@RequestBody @Valid ExecucaoModel execucaoModel) throws ReponseBusinessException {

		execucaoBusiness.applyBusiness(execucaoModel);
		
		repository.save(execucaoModel);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(execucaoModel.getId_execucao()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/{id}")
	@ApiOperation("Atualiza uma execu��o apartir do identificador")
	public ResponseEntity update(@PathVariable("id") int id, @RequestBody @Valid ExecucaoModel execucaoModel) throws ReponseBusinessException {

		execucaoBusiness.applyBusiness(execucaoModel);
		
		execucaoModel.setId_execucao(id);
		repository.save(execucaoModel);

		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation("Exclui uma execu��o apartir do identificador")
	public ResponseEntity deleteById(@PathVariable("id") int id) {

		repository.deleteById(id);

		return ResponseEntity.noContent().build();
	}
}
