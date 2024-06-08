package com.github.leandro.desafiospringgithub.models;

import com.github.leandro.desafiospringgithub.exceptions.ValidacoesException;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "transacao")
@Builder
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private BigDecimal valor;
    private OffsetDateTime dataHora;

    public Transacao(){

    }

    public Transacao(Long id, BigDecimal valor, OffsetDateTime dataHora) {
        this.id = id;
        this.valor = valor;
        this.dataHora = dataHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) throws ValidacoesException {
        if (valor.signum() < 0) throw new ValidacoesException("Oops valor negativo");
        this.valor = valor;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(OffsetDateTime dataHora) throws ValidacoesException {
        OffsetDateTime hoje = OffsetDateTime.now();
        if (dataHora.isAfter(hoje)) throw new ValidacoesException("Oops, data incorreta");
        this.dataHora = dataHora;
    }
}
