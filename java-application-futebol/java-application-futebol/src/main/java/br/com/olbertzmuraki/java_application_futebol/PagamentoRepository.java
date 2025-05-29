package br.com.olbertzmuraki.java_application_futebol; 

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
    List<Pagamento> findByJogadorId(Integer codJogador);
    List<Pagamento> findByAnoAndMes(Integer ano, Integer mes);
    List<Pagamento> findByJogadorIdAndAnoAndMes(Integer codJogador, Integer ano, Integer mes);
}