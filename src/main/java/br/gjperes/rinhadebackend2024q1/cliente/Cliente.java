package br.gjperes.rinhadebackend2024q1.cliente;

import br.gjperes.rinhadebackend2024q1.transacao.Transacao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Persistable;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
public class Cliente implements Persistable<Long>, Serializable {
    @Serial
    private static final long serialVersionUID = -8686269006693353289L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotNull private int limite;
    @NotNull private int saldo;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Transacao> transacoes;

    public boolean temLimiteDisponivel(int valor) {
        int novoSaldo = saldo - valor;

        if (novoSaldo < 0) {
            return novoSaldo < (limite * -1);
        }
        return true;
    }

    public void subtrairSaldo(int valor) {
        if (valor < 0) {
            throw new IllegalArgumentException();
        }

        if (temLimiteDisponivel(valor)) {
            saldo = saldo - valor;
        }
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return false;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLimite() {
        return limite;
    }

    public void setLimite(int limite) {
        this.limite = limite;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<Transacao> transacoes) {
        this.transacoes = transacoes;
    }
}
