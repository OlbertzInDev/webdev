package br.com.olbertzmuraki.java_application_futebol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface JogadorRepository extends JpaRepository<Jogador, Integer> {
    // JpaRepository já fornece: save(), findById(), findAll(), deleteById(), entre outros.
    //Nós poderíamos adicionar outras consultas, mas vamos deixas apenas as padrões
}