package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entidades.Produto;
import com.example.demo.servicos.ProdutoServico;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoServico servico;
	
	@PostMapping
	public ResponseEntity<String> insert(@RequestBody Produto produto){
		Produto prod = servico.insert(produto);
		return prod !=  null ? new ResponseEntity<>("Produto criado com sucesso", HttpStatus.CREATED) : new ResponseEntity<>("Erro ao criar produto", HttpStatus.BAD_REQUEST);
		
	}
	
	@GetMapping
	public ResponseEntity<List<Produto>> findAll(){
		List<Produto> list = servico.findAll();
		return !list.isEmpty() ?  new ResponseEntity<>(list, HttpStatus.OK) 
							   : new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/produto-jpa-id/{id}")
	public ResponseEntity<Optional<Produto>> findById(@PathVariable Integer id){
		Optional<Produto> prod = servico.findById(id);
		return prod.isPresent() ?  new ResponseEntity<>(prod, HttpStatus.OK) 
							    : new ResponseEntity<>(prod, HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/produto-jpql-nome/{nome}")
	public ResponseEntity<Optional<Produto>> findByNameJpql(@PathVariable String nome){
		Optional<Produto> prod = servico.findByNameJpql(nome);
		return prod.isPresent() ?  new ResponseEntity<>(prod, HttpStatus.OK) 
							    : new ResponseEntity<>(prod, HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/produto-sql-nome/{nome}")
	public ResponseEntity<Optional<Produto>> findByNameSql(@PathVariable String nome){
		Optional<Produto> prod = servico.findByNameSql(nome);
		return prod.isPresent() ?  new ResponseEntity<>(prod, HttpStatus.OK) 
							    : new ResponseEntity<>(prod, HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/produto-update")
	public ResponseEntity<String> update(@RequestBody Produto produto){
		Produto prod = servico.insert(produto);
		return prod !=  null ? new ResponseEntity<>("Produto atualizado com sucesso", HttpStatus.OK) 
							 : new ResponseEntity<>("Erro ao atualizar produto", HttpStatus.BAD_REQUEST);
		
	}
	
	@DeleteMapping("/produto-delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id){
		servico.delete(id);
		Optional<Produto> prod = servico.findById(id);
		return !prod.isPresent() ? new ResponseEntity<>("Produto deletado com sucesso", HttpStatus.OK) 
							 : new ResponseEntity<>("Erro ao deletar produto", HttpStatus.BAD_REQUEST);
		
	}

}
