package br.com.cervejariabaphomet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cervejariabaphomet.bo.EstiloBO;
import br.com.cervejariabaphomet.entity.Estilo;
import br.com.cervejariabaphomet.exception.KeyNotFoundException;

@RestController
@RequestMapping("estilo")
public class EstiloController {

	@Autowired
	private EstiloBO estiloBO;
	
	@GetMapping("listar")
	public ResponseEntity<List<Estilo>> listar() {
		return new ResponseEntity<List<Estilo>>(estiloBO.listar(), HttpStatus.OK);
	}

	@GetMapping("pesquisar/{id}")
	public ResponseEntity<Estilo> pesquisar(@PathVariable int id){
		return new ResponseEntity<Estilo>(estiloBO.pesquisar(id), HttpStatus.OK);
	}
	
	@Transactional
	@PostMapping("inserir")
	public ResponseEntity<Estilo> cadastrar(@RequestBody Estilo estilo) {
		try {
			estiloBO.inserir(estilo);
			return new ResponseEntity<Estilo>(estilo, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Estilo>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Transactional
	@PutMapping("atualizar/{id}")
	public ResponseEntity<Estilo> atualizar(@RequestBody Estilo estilo, @PathVariable int id){
		try {
			estilo.setId(id);
			estiloBO.atualizar(estilo, id);
			return new ResponseEntity<Estilo>(estilo, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Estilo>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Transactional
	@DeleteMapping("deletar/{id}")
	public ResponseEntity<Estilo> deletar(@PathVariable int id){
		try {
			estiloBO.remover(id);
			return new ResponseEntity<Estilo>(HttpStatus.OK);
		} catch (KeyNotFoundException e) {
			return new ResponseEntity<Estilo>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<Estilo>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
