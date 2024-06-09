package com.teamcubation.desafiotecnico.controller;


import com.teamcubation.desafiotecnico.dto.EstatisticaDto;
import com.teamcubation.desafiotecnico.model.Transacao;
import com.teamcubation.desafiotecnico.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
public class EstatisticaController {

    @Autowired
    private TransacaoService transacaoService;

    @GetMapping
    public EstatisticaDto getEstatisticas(){
        return transacaoService.calcularEstatistica();
    }

}
