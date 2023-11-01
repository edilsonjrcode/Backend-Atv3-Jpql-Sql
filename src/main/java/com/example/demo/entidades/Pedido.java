package com.example.demo.entidades;

import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_pedido")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String endereco;

	@ManyToMany
	@JoinTable(name = "tb_pedido_produto", joinColumns = @JoinColumn(name="id_pedido"), inverseJoinColumns = @JoinColumn(name="id_produto"))
	private Set<Produto> produto;

	Pedido(){}

	public Pedido(Integer id, String endereco, Set<Produto> produto) {
		super();
		this.id = id;
		this.endereco = endereco;
		this.produto = produto;
	}
	
	public String getEndereco() {
		return endereco;
	}
	public Set<Produto> getProduto() {
		return produto;
	}
	

}
