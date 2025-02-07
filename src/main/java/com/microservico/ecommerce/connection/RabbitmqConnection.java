package com.microservico.ecommerce.connection;

import org.example.constantes.RabbitmqConstantes;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.Queue;

@Component
public class RabbitmqConnection {

    private static final String NOME_EXCHANGE = "amq.direct";

    private AmqpAdmin amqpAdmin;

    // responsavel por concetar ao rabbitmq e criar as filas
    public RabbitmqConnection(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    private Queue fila(String nomeFila) {
        return new Queue(nomeFila, true, false, false);
    }

    private DirectExchange trocaDireta() {
        return new DirectExchange(NOME_EXCHANGE);
    }

    private Binding relacionamento(Queue fila, DirectExchange troca) {
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
    }

    @PostConstruct
    private void adiciona() {
        Queue filaPedidos = this.fila(RabbitmqConstantes.FILA_PEDIDOS);
        Queue filaEmail = this.fila(RabbitmqConstantes.FILA_CONFIRMACAO_PEDIDOS_EMAIL);

        DirectExchange troca = this.trocaDireta();

        Binding ligacao = this.relacionamento(filaPedidos, troca);

        //criando as filas no rabbitmq
        this.amqpAdmin.declareQueue(filaPedidos);
        this.amqpAdmin.declareQueue(filaEmail);

        //criando a exchange no rabbitmq
        this.amqpAdmin.declareExchange(troca);

        //relaciona à exchange à fila
        this.amqpAdmin.declareBinding(ligacao);
    }
}
