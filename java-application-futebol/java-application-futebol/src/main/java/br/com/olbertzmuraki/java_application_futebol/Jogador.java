package br.com.olbertzmuraki.java_application_futebol;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "jogador")
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "cod_jogador")
    private Integer id;

    @Column(name = "nome", length = 60, nullable = false)
    private String nome;

    @Column(name = "email", length = 60, unique = true) 
    private String email;

    @Column(name = "datanasc")
    private LocalDate dataNascimento; 

    // Relacionamento: Um Jogador para Muitos Pagamentos
    @OneToMany(mappedBy = "jogador", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Pagamento> pagamentos = new ArrayList<>();

    // Construtores
    public Jogador() {
    }

    public Jogador(String nome, String email, LocalDate dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    // Métodos utilitários
    public void addPagamento(Pagamento pagamento) {
        pagamentos.add(pagamento);
        pagamento.setJogador(this);
    }

    public void removePagamento(Pagamento pagamento) {
        pagamentos.remove(pagamento);
        pagamento.setJogador(null);
    }

    // toString, equals, hashCode
    @Override
    public String toString() {
        return "Jogador{" +
               "id=" + id +
               ", nome='" + nome + '\'' +
               ", email='" + email + '\'' +
               ", dataNascimento=" + dataNascimento +
               '}';
    }
}