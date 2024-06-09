package com.github.leandro.desafiospringgithub.comparators;

import com.github.leandro.desafiospringgithub.dtos.TransacaoDTO;
import java.util.Comparator;

public class ValorComparator implements Comparator<TransacaoDTO> {
    @Override
    public int compare(TransacaoDTO o1, TransacaoDTO o2) {
        return o1.getValor().compareTo(o2.getValor());
    }
}
