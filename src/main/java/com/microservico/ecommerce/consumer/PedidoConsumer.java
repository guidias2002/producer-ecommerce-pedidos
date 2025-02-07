package com.microservico.ecommerce.consumer;

import com.microservico.ecommerce.service.PedidoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.example.constantes.RabbitmqConstantes;
import org.example.dto.PedidoDto;

@Component
public class PedidoConsumer {

    @Autowired
    private PedidoService pedidoService;

    @RabbitListener(queues = RabbitmqConstantes.FILA_PEDIDOS)
    private void consumirPedido(PedidoDto pedidoDto) {
        pedidoService.salvarPedido(pedidoDto);
    }
}
