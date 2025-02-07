package com.microservico.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String produto;
    private double preco;
    private String emailCliente;

    public PedidoEntity(String produto, double preco, String emailCliente) {
        this.produto = produto;
        this.preco = preco;
        this.emailCliente = emailCliente;
    }
}
