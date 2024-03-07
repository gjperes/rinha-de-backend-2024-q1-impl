package br.gjperes.rinhadebackend2024q1.transacao;

import br.gjperes.rinhadebackend2024q1.cliente.Cliente;
import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column private int valor;
    @Column
    private String tipo;
    @Column private String descricao;
    @Column private ZonedDateTime realizadaEm;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public static Transacao criar(TransacaoRequest request) {
        Transacao transacao = new Transacao();
        transacao.setValor(request.valor());
        transacao.setDescricao(request.descricao());
        transacao.setTipo(request.tipo());
        transacao.setRealizadaEm(ZonedDateTime.now());

        return transacao;
    }

    public UUID getId() {
        return id;
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
