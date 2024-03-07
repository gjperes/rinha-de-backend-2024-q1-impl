package br.gjperes.rinhadebackend2024q1.cliente;

import br.gjperes.rinhadebackend2024q1.transacao.Transacao;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.List;

public record Extrato(Saldo saldo, @JsonProperty("ultimas_transacoes") List<HistoricoTransacao> ultimasTransacoes) {
    public record Saldo(int total, int limite, @JsonProperty("data_extrato") ZonedDateTime dataExtrato) {}
    public record HistoricoTransacao(int valor, String tipo, String descricao, @JsonProperty("realizada_em") ZonedDateTime realizadaEm) {
        public HistoricoTransacao(Transacao transacao) {
            this(transacao.getValor(), transacao.getTipo(), transacao.getDescricao(), transacao.getRealizadaEm());
        }
    }
}
