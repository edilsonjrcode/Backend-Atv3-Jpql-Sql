package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entidades.Pedido;

public interface PedidoRepositorioJPA extends JpaRepository<Pedido, Integer> {

}
