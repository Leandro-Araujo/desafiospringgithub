package com.github.leandro.desafiospringgithub.services;

import com.github.leandro.desafiospringgithub.dtos.TransacaoDTO;
import com.github.leandro.desafiospringgithub.exceptions.ValidacoesException;
import com.github.leandro.desafiospringgithub.models.Transacao;
import com.github.leandro.desafiospringgithub.repositories.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransacaoService {
    private final TransacaoRepository transacaoRepository;

    @Autowired
    public TransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public void save(TransacaoDTO transacaoDTO) throws ValidacoesException {
        Transacao transacao = new Transacao();
        transacao.setValor(transacaoDTO.getValor());
        transacao.setDataHora(transacaoDTO.getDataHora());
        transacaoRepository.save(transacao);
    }
}
