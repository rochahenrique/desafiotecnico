package com.teamcubation.desafiotecnico.service;


import com.teamcubation.desafiotecnico.dto.EstatisticaDto;
import com.teamcubation.desafiotecnico.model.Transacao;
import com.teamcubation.desafiotecnico.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

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

    public EstatisticaDto calcularEstatistica(){
        List<Transacao> transacoes = transacaoRepository.encontraTransacaoNosUltimos60Segundos();
        DoubleSummaryStatistics stats = transacoes.stream()
                .collect(Collectors.summarizingDouble(Transacao::getValor));

        return new EstatisticaDto(
                stats.getCount(),
                stats.getSum(),
                stats.getAverage(),
                stats.getMin(),
                stats.getMax()
        );
    }
}
