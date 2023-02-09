package com.lojagames.lojagames.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

	@Entity // Entidade da classe --> define a classe
	@Table(name = "tb_categoria")
	public class CategoriaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Para comentar que ele será auto incremento, serve como PK
	private Long id;
	
	@NotBlank (message = "O atríbuto tipo é obrigatório!") // Não aceita espaço vazio
	@Size(min = 5, max = 100, message = "O atríbuto tipo deve conter o minímo 5 e no máximo 100 caracteres")
	private String tipo; 
	
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.REMOVE)  
    @JsonIgnoreProperties("categoria")
    private List<ProdutosModel> produtos;

	public List<ProdutosModel> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutosModel> produtos) {
		this.produtos = produtos;
	}

	public Long getId() { //servem para ter retornos
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
	
	
	package com.lojagames.lojagames.controller;

	import java.util.List;

	import javax.validation.Valid;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.CrossOrigin;
	import org.springframework.web.bind.annotation.DeleteMapping;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.PutMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;

	import com.lojagames.lojagames.model.CategoriaModel;
	import com.lojagames.lojagames.repository.CategoriaRepository;

	@RestController
	@RequestMapping("/categoria")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public class CategoriaController {

		@Autowired
		private CategoriaRepository categoriaRepository;

		@GetMapping
		public ResponseEntity<List<com.lojagames.lojagames.model.CategoriaModel>> getAll() {
			return ResponseEntity.ok(categoriaRepository.findAll());
		}

		@GetMapping("/{id}")
		public ResponseEntity<com.lojagames.lojagames.model.CategoriaModel> getById(@PathVariable Long id) {
			return categoriaRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
					.orElse(ResponseEntity.notFound().build());
		}

		@PostMapping
		public ResponseEntity<CategoriaModel> post(@Valid @RequestBody CategoriaModel categoria){
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(categoriaRepository.save(categoria));
		}

		@PutMapping
		public ResponseEntity<com.lojagames.lojagames.model.CategoriaModel> putCategoria(@Valid @RequestBody com.lojagames.lojagames.model.CategoriaModel categoria) {

			return categoriaRepository.findById(categoria.getId())
					.map(resposta -> ResponseEntity.ok().body(categoriaRepository.save(categoria)))
					.orElse(ResponseEntity.notFound().build());
		}

		@DeleteMapping("/{id}")
		public ResponseEntity<?> deleteCategoria(@PathVariable Long id) {

			return categoriaRepository.findById(id).map(resposta -> {
				categoriaRepository.deleteById(id);
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}).orElse(ResponseEntity.notFound().build());
		}

	}
}
