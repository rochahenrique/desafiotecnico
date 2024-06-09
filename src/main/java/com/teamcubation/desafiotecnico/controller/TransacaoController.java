package com.teamcubation.desafiotecnico.controller;

import com.teamcubation.desafiotecnico.model.Transacao;
import com.teamcubation.desafiotecnico.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<Transacao> adicionarTransacao(@RequestBody Transacao transacao){
        boolean adicionada = transacaoService.adicionarTransacao(transacao);
        if(adicionada){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }

    @DeleteMapping
    public ResponseEntity<Transacao> limparTransacoes(){
        transacaoService.limparTransacoes();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
