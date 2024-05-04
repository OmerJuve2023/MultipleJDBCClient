package com.javatest.multiplejdbcclient.subscriber;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class SubscriberTest {

    @InjectMocks
    private SubscriberService subscriberService;

    @Mock
    private JdbcClient jdbcClient;

    @Mock
    private JdbcClient.StatementSpec statementSpec;

    @Mock
    private JdbcClient.MappedQuerySpec<Subscriber> querySpec;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetSubscribers() {
        // Datos de prueba
        Subscriber subscriber1 = new Subscriber(1, "Name 1", "Email 1");
        Subscriber subscriber2 = new Subscriber(2, "Name 2", "Email 2");
        List<Subscriber> subscribers = Arrays.asList(subscriber1, subscriber2);

        // Configura el comportamiento esperado del mock jdbcClient
        when(jdbcClient.sql(any(String.class))).thenReturn(statementSpec);
        // Establece que cuando se llama al método sql() del mock jdbcClient con cualquier cadena como argumento, debe devolver el mock statementSpec

        when(statementSpec.query(Subscriber.class)).thenReturn(querySpec);
        // Establece que cuando se llama al método query() del mock statementSpec con la clase Subscriber como argumento, debe devolver el mock querySpec

        when(querySpec.list()).thenReturn(subscribers);
        // Establece que cuando se llama al método list() del mock querySpec, debe devolver la lista de suscriptores

        // Llama al método a probar: findALlSubscribers
        List<Subscriber> result = subscriberService.findALlSubscribers();

        // Verifica que los resultados obtenidos sean los esperados
        assertEquals(subscribers, result);
        // Comprueba que la lista de suscriptores devuelta sea igual a la lista de suscriptores esperada
    }

}
