package br.gjperes.rinhadebackend2024q1.cliente;

import br.gjperes.rinhadebackend2024q1.transacao.TransacaoRequest;
import br.gjperes.rinhadebackend2024q1.transacao.TransacaoResponse;
import br.gjperes.rinhadebackend2024q1.transacao.TransacaoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;
    private final TransacaoService transacaoService;

    public ClienteController(ClienteService clienteService, TransacaoService transacaoService) {
        this.clienteService = clienteService;
        this.transacaoService = transacaoService;
    }

    @PostMapping("{id}/transacoes")
    public ResponseEntity<TransacaoResponse> transacao(@PathVariable @NotNull @Positive long id,
                                                       @RequestBody @Valid @NotNull TransacaoRequest transacao) {
        var response = transacaoService.efetuar(id, transacao);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("{id}/extrato")
    public ResponseEntity<Extrato> extrato(@PathVariable @NotNull @Positive long id) {
        Extrato extrato = transacaoService.consultarExtrato(id);

        return ResponseEntity.ok(extrato);
    }
}
