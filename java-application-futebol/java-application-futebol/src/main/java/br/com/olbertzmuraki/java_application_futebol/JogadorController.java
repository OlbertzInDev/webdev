package br.com.olbertzmuraki.java_application_futebol;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jogadores")
public class JogadorController {

    private final JogadorService jogadorService;

    public JogadorController(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    @PostMapping
    public ResponseEntity<Jogador> criarJogador(@RequestBody Jogador jogador) {
        // Para um projeto real, considere usar um DTO (ex: JogadorRequestDTO) aqui
        // e converter para a entidade Jogador no serviço ou controlador.
        Jogador novoJogador = jogadorService.criarJogador(jogador);
        return new ResponseEntity<>(novoJogador, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Jogador>> listarTodosJogadores() {
        List<Jogador> jogadores = jogadorService.listarTodosJogadores();
        // Para um projeto real, considere mapear para uma lista de JogadorResponseDTO.
        return ResponseEntity.ok(jogadores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogador> buscarJogadorPorId(@PathVariable Integer id) {
        Optional<Jogador> jogador = jogadorService.buscarJogadorPorId(id);
        // Considere mapear para JogadorResponseDTO se o jogador for encontrado.
        return jogador.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // --- NOVO ENDPOINT @PutMapping PARA ATUALIZAR JOGADOR ---
    @PutMapping("/{id}")
    public ResponseEntity<Jogador> atualizarJogador(@PathVariable Integer id, @RequestBody Jogador jogadorDetalhes) {
        // Novamente, considere usar DTOs para request e response.
        Optional<Jogador> jogadorAtualizado = jogadorService.atualizarJogador(id, jogadorDetalhes);
        return jogadorAtualizado.map(ResponseEntity::ok)
                                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // --- NOVO ENDPOINT @DeleteMapping PARA DELETAR JOGADOR ---
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarJogador(@PathVariable Integer id) {
        boolean deletado = jogadorService.deletarJogador(id);
        if (deletado) {
            return ResponseEntity.noContent().build(); // HTTP 204 No Content - sucesso, sem corpo de resposta
        } else {
            return ResponseEntity.notFound().build(); // HTTP 404 Not Found - jogador não encontrado
        }
    }
}