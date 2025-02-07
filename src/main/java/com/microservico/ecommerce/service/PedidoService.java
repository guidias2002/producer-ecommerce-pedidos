package com.microservico.ecommerce.service;

import com.microservico.ecommerce.entity.PedidoEntity;
import com.microservico.ecommerce.repository.PedidoRepository;
import org.example.dto.PedidoDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.constantes.RabbitmqConstantes;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void salvarPedido(PedidoDto pedidoDto) {
        PedidoEntity novoPedido = new PedidoEntity(pedidoDto.getProduto(), pedidoDto.getPreco(), pedidoDto.getEmailCliente());

        pedidoRepository.save(novoPedido);
        rabbitTemplate.convertAndSend(RabbitmqConstantes.FILA_CONFIRMACAO_PEDIDOS_EMAIL, pedidoDto);
    }
}
