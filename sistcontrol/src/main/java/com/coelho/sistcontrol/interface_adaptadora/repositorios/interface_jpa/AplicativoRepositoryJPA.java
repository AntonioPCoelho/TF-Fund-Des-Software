package com.coelho.sistcontrol.interface_adaptadora.repositorios.interface_jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades.Aplicativo;

@Repository
public interface AplicativoRepositoryJPA extends JpaRepository<Aplicativo, Long> {
}
