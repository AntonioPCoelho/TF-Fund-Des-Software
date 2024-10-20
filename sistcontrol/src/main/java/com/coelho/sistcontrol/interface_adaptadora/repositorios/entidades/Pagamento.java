package com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @ManyToOne
    @JoinColumn(name = "assinatura_id")
    private Assinatura assinatura;

    private double valorPago;

    private LocalDate dataPagamento;

    private String promocao;

    // Getters e Setters
}