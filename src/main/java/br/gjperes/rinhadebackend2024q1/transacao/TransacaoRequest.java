package br.gjperes.rinhadebackend2024q1.transacao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

public record TransacaoRequest(
        @NotNull @Positive int valor,
        @NotBlank @Length(min = 1, max = 1) String tipo,
        @NotBlank @Length(min = 1, max = 10) String descricao
) {

}
