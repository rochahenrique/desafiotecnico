package com.teamcubation.desafiotecnico.repository;

import com.teamcubation.desafiotecnico.model.Transacao;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TransacaoRepository {

    private List<Transacao> transacoes = new ArrayList<>();

    //adiciona uma transação a lista de transações, método sincronizado para que somente adicionado uma transação por vez na lista.
    public synchronized void adicionarTransacao(Transacao transacao){
        transacoes.add(transacao);
    }

    //Limpa todas as transações da lista de transações, método sincronizado para que somente uma lista seja limpa de cada vez.
    public synchronized void limparTransacoes(){
        transacoes.clear();
    }

    //retorna uma lista de transações que ocorreram nos últimos 60 segundos
    public synchronized List<Transacao> encontraTransacaoNosUltimos60Segundos(){
        OffsetDateTime umMinutoAtras = OffsetDateTime.now().minusMinutes(1);
        return transacoes.stream()
                .filter(t -> t.getDataHora().isAfter(umMinutoAtras))
                .collect(Collectors.toList());
    }


}
