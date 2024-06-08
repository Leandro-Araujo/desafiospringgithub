package com.github.leandro.desafiospringgithub.controllers;

import com.github.leandro.desafiospringgithub.dtos.TransacaoDTO;
import com.github.leandro.desafiospringgithub.exceptions.ValidacoesException;
import com.github.leandro.desafiospringgithub.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Validated
public class ControllerMain {
    private final TransacaoService transacaoService;

    @Autowired
    public ControllerMain(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }


    @PostMapping("/transacao")
    public ResponseEntity<String> realizarTransacao(@RequestBody TransacaoDTO transacaoDTO){
        try {
            transacaoService.save(transacaoDTO);
            return new ResponseEntity<>("Transacao realizada com sucesso", HttpStatus.OK);
        } catch (ValidacoesException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
