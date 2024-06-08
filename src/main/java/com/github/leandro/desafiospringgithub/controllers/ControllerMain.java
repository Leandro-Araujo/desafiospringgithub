package com.github.leandro.desafiospringgithub.controllers;

import com.github.leandro.desafiospringgithub.dtos.TransacaoDTO;
import com.github.leandro.desafiospringgithub.exceptions.ValidacoesException;
import com.github.leandro.desafiospringgithub.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Void> realizarTransacao(@RequestBody TransacaoDTO transacaoDTO){
        try {
            transacaoService.save(transacaoDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (ValidacoesException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @DeleteMapping("/transacao")
    public ResponseEntity<Void> deletarTransacoes(){
        transacaoService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
