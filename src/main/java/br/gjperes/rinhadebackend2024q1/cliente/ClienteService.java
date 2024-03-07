package br.gjperes.rinhadebackend2024q1.cliente;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ClienteService {
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public Optional<Cliente> buscarClientePorId(@NotNull @Positive int id) {
        return repository.findById((long) id);
    }

    @Transactional
    public Cliente subtrairSaldo(@NotNull Cliente cliente, @NotNull @Positive int valor) {
        cliente.subtrairSaldo(valor);
        return repository.save(cliente);
    }
}
