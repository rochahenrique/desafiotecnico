package com.teamcubation.desafiotecnico;

import com.teamcubation.desafiotecnico.controller.TransacaoController;
import com.teamcubation.desafiotecnico.model.Transacao;
import com.teamcubation.desafiotecnico.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransacaoControllerTest {

    @Mock
    private TransacaoService transacaoService;

    @InjectMocks
    private TransacaoController transacaoController;


    //Testa se a adição de uma transação válida retorna 201 created
    @Test
    public void testAdicionarTransacao_Sucesso() {
        Transacao transacao = new Transacao();
        transacao.setValor(100);
        transacao.setDataHora(OffsetDateTime.now().minusMinutes(1));

        when(transacaoService.adicionarTransacao(transacao)).thenReturn(true);

        ResponseEntity<Transacao> response = transacaoController.adicionarTransacao(transacao);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    //Testa se a adição de uma transação inválida retorna 422 Unprocessable Entity
    @Test
    public void testAdicionarTransacao_Falha() {
        Transacao transacao = new Transacao();
        transacao.setValor(-100);
        transacao.setDataHora(OffsetDateTime.now().minusMinutes(1));

        when(transacaoService.adicionarTransacao(transacao)).thenReturn(false);

        ResponseEntity<Transacao> response = transacaoController.adicionarTransacao(transacao);
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    }

    //Testa se a limpeza de transações retorna 200 OK
    @Test
    public void testLimparTransacoes() {
        ResponseEntity<Transacao> response = transacaoController.limparTransacoes();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(transacaoService, times(1)).limparTransacoes();
    }
}
