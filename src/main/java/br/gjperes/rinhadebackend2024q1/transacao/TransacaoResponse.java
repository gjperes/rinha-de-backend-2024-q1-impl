package br.gjperes.rinhadebackend2024q1.transacao;

import br.gjperes.rinhadebackend2024q1.cliente.Cliente;

public record TransacaoResponse(int limite, int saldo) {
    TransacaoResponse(Cliente cliente) {
        this(cliente.getLimite(), cliente.getSaldo());
    }
}
