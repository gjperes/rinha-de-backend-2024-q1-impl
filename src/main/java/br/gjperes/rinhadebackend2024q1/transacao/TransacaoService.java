package br.gjperes.rinhadebackend2024q1.transacao;

import br.gjperes.rinhadebackend2024q1.cliente.Cliente;
import br.gjperes.rinhadebackend2024q1.cliente.ClienteService;
import br.gjperes.rinhadebackend2024q1.cliente.Extrato;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;

@Service
@Transactional(readOnly = true)
public class TransacaoService {
    private final ClienteService clienteService;
    private final TransacaoRepository repository;

    public TransacaoService(ClienteService clienteService, TransacaoRepository repository) {
        this.clienteService = clienteService;
        this.repository = repository;
    }

    @Transactional
    public TransacaoResponse efetuar(@NotNull Cliente cliente, @Valid @NotNull TransacaoRequest request) {
        if (!TipoTransacao.valido(request.tipo())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (!cliente.temLimiteDisponivel(request.valor())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Cliente clienteAtualizado = clienteService.subtrairSaldo(cliente, request.valor());
        repository.save(Transacao.criar(request));

        return new TransacaoResponse(clienteAtualizado);
    }

    public Extrato consultarExtrato(@NotNull Cliente cliente) {
        var saldo = new Extrato.Saldo(cliente.getSaldo(), cliente.getLimite(), ZonedDateTime.now());

        PageRequest pageConsultarDezUltimasTransacoes = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "realizada_em"));
        var ultimasTransacoes = repository.findAllByCliente(cliente, pageConsultarDezUltimasTransacoes)
                .stream()
                .map(Extrato.HistoricoTransacao::new)
                .toList();

        return new Extrato(saldo, ultimasTransacoes);
    }
}
