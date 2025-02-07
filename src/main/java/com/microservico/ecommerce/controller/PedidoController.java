package com.microservico.ecommerce.controller;

import com.microservico.ecommerce.service.RabbitmqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.constantes.RabbitmqConstantes;
import org.example.dto.PedidoDto;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private RabbitmqService rabbitmqService;

    @PostMapping
    public ResponseEntity salvarPedido(@RequestBody PedidoDto pedidoDto) {
        rabbitmqService.enviaMensagem(RabbitmqConstantes.FILA_PEDIDOS, pedidoDto);

        System.out.println(pedidoDto);
        System.out.println("--------------");

        return new ResponseEntity(HttpStatus.OK);
    }
}
