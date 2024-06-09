package com.teamcubation.desafiotecnico.service;


import com.teamcubation.desafiotecnico.model.Transacao;
import com.teamcubation.desafiotecnico.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    public boolean adicionarTransacao(Transacao transacao){
        if (transacao.getValor() < 0 || transacao.getDataHora().isAfter(OffsetDateTime.now())){
            return  false;
        }
        transacaoRepository.adicionarTransacao(transacao);
        return true;
    }

    public void limparTransacoes(){
        transacaoRepository.limparTransacoes();
    }
}
