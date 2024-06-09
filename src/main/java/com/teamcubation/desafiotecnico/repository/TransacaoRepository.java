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

    public synchronized void adicionarTransacao(Transacao transacao){
        transacoes.add(transacao);
    }

    public synchronized void limparTransacoes(){
        transacoes.clear();
    }

    public synchronized List<Transacao> encontraTransacaoNosUltimos60Segundos(){
        OffsetDateTime umMinutoAtras = OffsetDateTime.now().minusMinutes(1);
        return transacoes.stream()
                .filter(t -> t.getDataHora().isAfter(umMinutoAtras))
                .collect(Collectors.toList());
    }


}
