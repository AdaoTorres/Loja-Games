package com.lojagames.lojagames.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.lojagames.lojagames.model.ProdutosModel;

	@Repository
	public interface ProdutoRepository extends JpaRepository<ProdutosModel, Long> {

	public List<ProdutosModel> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);

	/**
	 * Método Personalizado - Buscar todos os Produtos cujo o preço seja maior do
	 * que um valor digitado ordenado pelo preço em ordem crescente
	 * 
	 * MySQL: select * from tb_produtos where preco > preco order by preco;
	 */

	public List<ProdutosModel> findByPrecoGreaterThanOrderByPreco(BigDecimal preco);

	/**
	 * Método Personalizado - Buscar todos os Produtos cujo o preço seja menor do
	 * que um valor digitado ordenado pelo preço em ordem decrescente
	 * 
	 * MySQL: select * from tb_produtos where preco < preco order by preco desc;
	 */

	public List<ProdutosModel> findByPrecoLessThanOrderByPrecoDesc(BigDecimal preco);

}
	
	
	
	package com.lojagames.lojagames.repository;

	import java.util.List;

	import org.springframework.data.jpa.repository.JpaRepository;
	import org.springframework.data.repository.query.Param;
	import org.springframework.stereotype.Repository;

	import com.lojagames.lojagames.model.CategoriaModel;



		@Repository
		public interface CategoriaRepository extends JpaRepository <CategoriaModel, Long> {  
		
		public List <CategoriaModel> findAllByTipoContainingIgnoreCase(@Param("tipo")String tipo);

	}
