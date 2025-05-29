package br.com.olbertzmuraki.java_application_futebol;

import java.math.BigDecimal; // Para valores monetários

import jakarta.persistence.Column; 
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_pagamento")
    private Integer id;

    @Column(name = "ano", nullable = false)
    private Short ano;

    @Column(name = "mes", nullable = false)
    private Byte mes; 

    @Column(name = "valor", precision = 10, scale = 2, nullable = false) // Ex: 12345678.90
    private BigDecimal valor;

    // Relacionamento: Muitos Pagamentos para Um Jogador
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "cod_jogador", nullable = false) 
    private Jogador jogador;

    // Construtores
    public Pagamento() {
    }

    public Pagamento(Short ano, Byte mes, BigDecimal valor) {
        this.ano = ano;
        this.mes = mes;
        this.valor = valor;
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getAno() {
        return ano;
    }

    public void setAno(Short ano) {
        this.ano = ano;
    }

    public Byte getMes() {
        return mes;
    }

    public void setMes(Byte mes) {
        this.mes = mes;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    // toString 
    @Override
    public String toString() {
        return "Pagamento{" +
               "id=" + id +
               ", ano=" + ano +
               ", mes=" + mes +
               ", valor=" + valor +
               '}';
    }
}