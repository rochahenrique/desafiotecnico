package com.teamcubation.desafiotecnico.repository;

import com.teamcubation.desafiotecnico.model.Transacao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransacaoRepository {

    private List<Transacao> transacoes = new ArrayList<>();

    public synchronized void adicionarTransacao(Transacao transacao){
        transacoes.add(transacao);
    }


}
