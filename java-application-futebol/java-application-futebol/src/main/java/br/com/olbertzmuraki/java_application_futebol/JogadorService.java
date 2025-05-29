package br.com.olbertzmuraki.java_application_futebol; // Adapte o pacote

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JogadorService {

    private final JogadorRepository jogadorRepository;

    public JogadorService(JogadorRepository jogadorRepository) {
        this.jogadorRepository = jogadorRepository;
    }

    @Transactional
    public Jogador criarJogador(Jogador jogador) {
        return jogadorRepository.save(jogador);
    }

    @Transactional(readOnly = true)
    public List<Jogador> listarTodosJogadores() {
        return jogadorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Jogador> buscarJogadorPorId(Integer id) {
        return jogadorRepository.findById(id);
    }

    // --- NOVO MÉTODO PARA ATUALIZAR JOGADOR ---
    @Transactional
    public Optional<Jogador> atualizarJogador(Integer id, Jogador jogadorDetalhes) {
        Optional<Jogador> jogadorOptional = jogadorRepository.findById(id);

        if (jogadorOptional.isPresent()) {
            Jogador jogadorExistente = jogadorOptional.get();
            // Atualiza os campos do jogadorExistente com os detalhes recebidos
            // Não atualize o ID.
            if (jogadorDetalhes.getNome() != null) {
                jogadorExistente.setNome(jogadorDetalhes.getNome());
            }
            if (jogadorDetalhes.getEmail() != null) {
                jogadorExistente.setEmail(jogadorDetalhes.getEmail());
            }
            if (jogadorDetalhes.getDataNascimento() != null) {
                jogadorExistente.setDataNascimento(jogadorDetalhes.getDataNascimento());
            }
            // A lista de pagamentos geralmente é gerenciada por endpoints específicos de pagamento
            // ou por lógicas mais complexas, então evitamos sobrescrevê-la diretamente aqui.

            return Optional.of(jogadorRepository.save(jogadorExistente));
        } else {
            return Optional.empty(); // Jogador não encontrado para atualizar
        }
    }

    // --- NOVO MÉTODO PARA DELETAR JOGADOR ---
    @Transactional
    public boolean deletarJogador(Integer id) {
        if (jogadorRepository.existsById(id)) {
            jogadorRepository.deleteById(id);
            // Devido ao CascadeType.ALL e orphanRemoval=true na entidade Jogador para a lista de pagamentos,
            // os pagamentos associados a este jogador também serão removidos do banco.
            return true; // Deleção bem-sucedida
        }
        return false; // Jogador não encontrado para deletar
    }
}