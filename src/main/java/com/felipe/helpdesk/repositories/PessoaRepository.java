package com.felipe.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipe.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
