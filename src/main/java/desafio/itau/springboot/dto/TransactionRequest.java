package desafio.itau.springboot.dto;

import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

public class TransactionRequest {

    @NotNull

    private double valor;

    @NotNull

    private OffsetDateTime dataHora;


    public double getValor() {
        return valor;
    }

    public @NotNull OffsetDateTime getDataHora() {
        return dataHora;
    }
}
