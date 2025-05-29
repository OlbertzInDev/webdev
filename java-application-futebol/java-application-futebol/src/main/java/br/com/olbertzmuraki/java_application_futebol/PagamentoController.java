package br.com.olbertzmuraki.java_application_futebol; // Adapte o pacote

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") 
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    // Endpoint para criar um pagamento para um jogador específico
    @PostMapping("/jogadores/{codJogador}/pagamentos")
    public ResponseEntity<Pagamento> registrarPagamento(@PathVariable Integer codJogador, @RequestBody Pagamento pagamento) {
        Optional<Pagamento> novoPagamentoOpt = pagamentoService.registrarPagamento(codJogador, pagamento);
        return novoPagamentoOpt
                .map(p -> new ResponseEntity<>(p, HttpStatus.CREATED))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)); // Ou uma mensagem de erro mais específica
    }

    // Endpoint para listar todos os pagamentos de um jogador específico
    @GetMapping("/jogadores/{codJogador}/pagamentos")
    public ResponseEntity<List<Pagamento>> listarPagamentosPorJogador(@PathVariable Integer codJogador) {
        List<Pagamento> pagamentos = pagamentoService.listarPagamentosPorJogador(codJogador);
        if (pagamentos.isEmpty()) {
            return ResponseEntity.ok(pagamentos);
        }
        return ResponseEntity.ok(pagamentos);
    }

    @GetMapping("/pagamentos/{id}")
    public ResponseEntity<Pagamento> buscarPagamentoPorId(@PathVariable Integer id) {
        Optional<Pagamento> pagamento = pagamentoService.buscarPagamentoPorId(id);
        return pagamento.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }

}