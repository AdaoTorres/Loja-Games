package com.lojagames.lojagames.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity                                             		
@Table(name = "tb_produtos")	
public class ProdutosModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	@NotNull(message = "Nome é obrigatório!")                                       										
	private String nome;
	
	@Size(max=500, message = "A descrição deve ter no máximo 500 caracteres!")
	private String descricao;
	
	@NotNull(message = "Console é obrigatório!")
	private String console;
	
	private int quantidade;
	
	@Column(name = "data_lancamento")
	@JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataLancamento;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@NotNull(message = "Preço é obrigatório!")
	@Positive(message = "O preço deve ser maior do que zero!")
	private BigDecimal preco;
	
	private String foto;

	@ManyToOne
	@JsonIgnoreProperties("produto")
	private CategoriaModel categoria;  

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getConsole() {
		return console;
	}

	public void setConsole(String console) {
		this.console = console;
	}

	public int getQuantidade() {
		return quantidade;
	}

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
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getFoto() {
		return this.foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public LocalDate getDataLancamento() {
		return this.dataLancamento;
	}

	public void setDataLancamento(LocalDate dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public CategoriaModel getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaModel categoria) {
		this.categoria = categoria;
	}

}




