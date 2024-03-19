package br.gjperes.rinhadebackend2024q1.transacao;

import br.gjperes.rinhadebackend2024q1.cliente.Cliente;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Persistable;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table
public class Transacao implements Persistable<UUID>, Serializable {
    @Serial
    private static final long serialVersionUID = -3587679866598742495L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotNull
    private int valor;
    @NotNull
    private String tipo;
    @NotNull
    private String descricao;
    @NotNull
    @Column(updatable = false)
    private ZonedDateTime realizadaEm;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Transient
    private boolean isNew = false;

    public static Transacao criar(TransacaoRequest request) {
        Transacao transacao = new Transacao();
        transacao.setValor(request.valor());
        transacao.setDescricao(request.descricao());
        transacao.setTipo(request.tipo());
        transacao.setRealizadaEm(ZonedDateTime.now());
        transacao.isNew = true;

        return transacao;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ZonedDateTime getRealizadaEm() {
        return realizadaEm;
    }

    public void setRealizadaEm(ZonedDateTime realizadaEm) {
        this.realizadaEm = realizadaEm;
    }
}
