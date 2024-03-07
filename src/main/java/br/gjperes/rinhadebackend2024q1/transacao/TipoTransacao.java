package br.gjperes.rinhadebackend2024q1.transacao;

import java.util.Arrays;
import java.util.List;

public enum TipoTransacao {
    CREDITO("c"),
    DEBITO("d");

    public final String sigla;

    TipoTransacao(String sigla) {
        this.sigla = sigla;
    }

    public String sigla() {
        return this.sigla;
    }

    public static List<String> siglasValidas() {
        return Arrays
                .stream(TipoTransacao.values())
                .map(TipoTransacao::sigla)
                .toList();
    }

    public static boolean valido(String sigla) {
        return TipoTransacao.siglasValidas().contains(sigla);
    }
}