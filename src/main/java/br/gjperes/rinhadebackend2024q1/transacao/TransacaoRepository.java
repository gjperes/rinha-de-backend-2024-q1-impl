package br.gjperes.rinhadebackend2024q1.transacao;

import br.gjperes.rinhadebackend2024q1.cliente.Cliente;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, UUID> {
    List<Transacao> findAllByCliente(Cliente cliente, PageRequest page);
}
