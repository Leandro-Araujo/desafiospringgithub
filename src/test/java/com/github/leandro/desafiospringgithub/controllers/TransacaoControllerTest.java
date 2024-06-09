package com.github.leandro.desafiospringgithub.controllers;

import com.github.leandro.desafiospringgithub.dtos.TransacaoDTO;
import com.github.leandro.desafiospringgithub.dtos.TransacaoResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class TransacaoControllerTest {
    TransacaoController transacaoController;

    @BeforeEach
    public void setup(){
        transacaoController = new TransacaoController();
    }


    @Test
    public void verificaSeAdicionouTransacaoAoRealizarTransacao() throws Exception {
        transacaoController.realizarTransacao(new TransacaoDTO(new BigDecimal(10), OffsetDateTime.now()));
        assert(transacaoController.transacaoDTOS.size() == 1);
    }

    @Test
    public void verificaSeLancaExcecaoAoAdicionarTransacaoComValorNegativo() {
        try {
            transacaoController.realizarTransacao(new TransacaoDTO(new BigDecimal(-10), OffsetDateTime.now()));
        } catch (Exception e) {
            assert(e.getMessage().equals("Oops valor negativo"));
        }
    }

    @Test
    public void verificaSeLancaExcecaoAoAdicionarTransacaoComDataFutura() {
        try {
            transacaoController.realizarTransacao(new TransacaoDTO(new BigDecimal(10), OffsetDateTime.now().plusDays(1)));
        } catch (Exception e) {
            assert(e.getMessage().equals("Oops, data incorreta"));
        }
    }

    @Test
    public void verificaSeListaDeTransacoesEhZeradaAoDeletarTransacoes() throws Exception {
        transacaoController.realizarTransacao(new TransacaoDTO(new BigDecimal(10), OffsetDateTime.now()));
        transacaoController.deletarTransacoes();
        assert(transacaoController.transacaoDTOS.size() == 0);
    }

    @Test
    public void verificaSeEstatisticaEhCalculadaCorretamente() throws Exception {
        transacaoController.realizarTransacao(new TransacaoDTO(new BigDecimal(10), OffsetDateTime.now()));
        transacaoController.realizarTransacao(new TransacaoDTO(new BigDecimal(20), OffsetDateTime.now()));

        ResponseEntity<TransacaoResponseDTO> transacaoEstatistica = transacaoController.calculaEstatistica();
        assert (transacaoEstatistica.getBody().getCount() == 2);
        assert (transacaoEstatistica.getBody().getSum().equals(new BigDecimal(30)));
        assert (transacaoEstatistica.getBody().getAvg().equals(new BigDecimal(15)));
        assert (transacaoEstatistica.getBody().getMin().equals(new BigDecimal(10)));
        assert (transacaoEstatistica.getBody().getMax().equals(new BigDecimal(20)));

    }



}
