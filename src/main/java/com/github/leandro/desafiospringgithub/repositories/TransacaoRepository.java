package com.github.leandro.desafiospringgithub.repositories;

import com.github.leandro.desafiospringgithub.models.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
