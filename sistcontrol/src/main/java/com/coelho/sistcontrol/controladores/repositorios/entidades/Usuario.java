package com.coelho.sistcontrol.controladores.repositorios.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Usuario {

    @Id
    private String usuario;

    private String senha;

    // Getters e Setters
}