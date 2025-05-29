package br.com.olbertzmuraki.java_application_futebol; 

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final JogadorRepository jogadorRepository; // Necessário para associar pagamento a um jogador

    public PagamentoService(PagamentoRepository pagamentoRepository, JogadorRepository jogadorRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.jogadorRepository = jogadorRepository;
    }

    @Transactional
    public Optional<Pagamento> registrarPagamento(Integer codJogador, Pagamento pagamento) {
        Optional<Jogador> jogadorOptional = jogadorRepository.findById(codJogador);
        if (jogadorOptional.isPresent()) {
            Jogador jogador = jogadorOptional.get();
            pagamento.setJogador(jogador); // Associa o pagamento ao jogador
            return Optional.of(pagamentoRepository.save(pagamento));
        }
        return Optional.empty(); // Jogador não encontrado
    }

    @Transactional(readOnly = true)
    public List<Pagamento> listarPagamentosPorJogador(Integer codJogador) {
        return pagamentoRepository.findByJogadorId(codJogador);
    }

    @Transactional(readOnly = true)
    public Optional<Pagamento> buscarPagamentoPorId(Integer id) {
        return pagamentoRepository.findById(id);
    }

}