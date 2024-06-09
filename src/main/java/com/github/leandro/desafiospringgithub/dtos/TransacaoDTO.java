package com.github.leandro.desafiospringgithub.dtos;

import jakarta.annotation.Nonnull;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Date;

@ToString
@Getter
public class TransacaoDTO {
    @Nonnull
    private BigDecimal valor;
    private OffsetDateTime dataHora;

    public TransacaoDTO(BigDecimal valor, OffsetDateTime dataHora) throws Exception {
        if (valor == null){
            throw new Exception();
        }
        if (dataHora == null ){
            throw new Exception();
        }
        this.valor = valor;
        this.dataHora = dataHora;
    }
}
