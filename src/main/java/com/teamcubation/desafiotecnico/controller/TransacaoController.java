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

    //Trata solicitações POST, retorna 201 created se for válido ou 422 quando não é válido
    @PostMapping
    public ResponseEntity<Transacao> adicionarTransacao(@RequestBody Transacao transacao){
        boolean adicionada = transacaoService.adicionarTransacao(transacao);
        if(adicionada){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }

    //Trata solicitações DELLETE  e retorna 200 OK quando válido
    @DeleteMapping
    public ResponseEntity<Transacao> limparTransacoes(){
        transacaoService.limparTransacoes();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
