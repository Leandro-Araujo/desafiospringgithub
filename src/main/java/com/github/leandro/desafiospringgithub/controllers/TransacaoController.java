package com.github.leandro.desafiospringgithub.controllers;

import com.github.leandro.desafiospringgithub.comparators.ValorComparator;
import com.github.leandro.desafiospringgithub.dtos.TransacaoDTO;
import com.github.leandro.desafiospringgithub.dtos.TransacaoResponseDTO;
import com.github.leandro.desafiospringgithub.exceptions.ValidacoesException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class TransacaoController {
    public List<TransacaoDTO> transacaoDTOS = new ArrayList<>();

    @PostMapping("/transacao")
    public ResponseEntity<Void> realizarTransacao(@RequestBody TransacaoDTO transacaoDTO){
        try {
            if(transacaoDTO.getValor().signum() < 0) throw new ValidacoesException("Oops valor negativo");
            if (transacaoDTO.getDataHora().isAfter(OffsetDateTime.now())) throw new ValidacoesException("Oops, data incorreta");
            transacaoDTOS.add(transacaoDTO);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (ValidacoesException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @DeleteMapping("/transacao")
    public ResponseEntity<Void> deletarTransacoes(){
        transacaoDTOS = new ArrayList<>();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/estatistica")
    public ResponseEntity<TransacaoResponseDTO> calculaEstatistica(){
        OffsetDateTime horaAtual = OffsetDateTime.now();
        List<TransacaoDTO> transacoes = transacaoDTOS.stream().filter(transacao -> transacao.getDataHora().isAfter(horaAtual.minusSeconds(60)) || transacao.getDataHora().isEqual(horaAtual.minusSeconds(60))).toList();
        int count = transacoes.size();
        BigDecimal sum = new BigDecimal(0);
        BigDecimal avg = new BigDecimal(0);
        BigDecimal min = new BigDecimal(0);
        BigDecimal max = new BigDecimal(0);
        if (count > 0){
            min = transacoes.stream().min(new ValorComparator()).get().getValor();
            max = transacoes.stream().max(new ValorComparator()).get().getValor();
            for (TransacaoDTO transacao : transacoes) {
                sum = sum.add(transacao.getValor());
            }
            avg = avg.add(sum).divide(BigDecimal.valueOf(count));
        }
        TransacaoResponseDTO transacaoResponseDTO = TransacaoResponseDTO.builder()
                .count(count)
                .sum(sum)
                .avg(avg)
                .min(min)
                .max(max)
                .build();
        return new ResponseEntity<TransacaoResponseDTO>(transacaoResponseDTO, HttpStatus.OK);
    }
}
