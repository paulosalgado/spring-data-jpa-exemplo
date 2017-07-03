package br.com.paulosalgado.springdatajpa.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.paulosalgado.springdatajpa.model.Produto;
import br.com.paulosalgado.springdatajpa.repository.Produtos;

@RestController
@RequestMapping("/produtos")
public class ProdutosResource {
	
	@Autowired
	private Produtos produtos;// <<< Repositório de produtos.
	
	@GetMapping("/{id}")
	public Produto buscar(@PathVariable Long id) {
		return produtos.findOne(id);
	}
	
//	@GetMapping
//	public List<Produto> pesquisar() {
//		return produtos.findAll();
//	}
	
//	@GetMapping
//	public List<Produto> pesquisar(
//			@RequestParam(defaultValue = "nome") String ordenacao, 
//			@RequestParam(defaultValue = "ASC") Sort.Direction direcao) {
//		
//		return produtos.findAll(new Sort(direcao, ordenacao));
//	}
	
//	@GetMapping
//	public Page<Produto> pesquisar(
//			@RequestParam(defaultValue = "0") int pagina, 
//			@RequestParam(defaultValue = "3") int porPagina, 
//			@RequestParam(defaultValue = "nome") String ordenacao, 
//			@RequestParam(defaultValue = "ASC") Sort.Direction direcao) {
//		
//		return produtos.findAll(new PageRequest(pagina, porPagina, new Sort(direcao, ordenacao)));
//	}
	
	@GetMapping
	public List<Produto> pesquisar(@RequestParam String nome) {
		return produtos.findByNomeStartingWithIgnoreCaseOrderByNome(nome);
	}
	
	@PostMapping
	public Produto salvar(@RequestBody Produto produto) {
		return produtos.save(produto);
	}
	
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) {
		produtos.delete(id);
	}
	
}
