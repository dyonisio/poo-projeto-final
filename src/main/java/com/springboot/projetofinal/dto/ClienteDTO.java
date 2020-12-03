package com.springboot.projetofinal.dto;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class ClienteDTO {
    @NotBlank(message = "Nome obrigatório!")
    @Length(min=4,max=40, message = "Nome deve ter no mínimo 4 e no máximo 40 caracteres!")
    private String nome;
    
    @NotBlank(message = "Endereço obrigatório!")
    @Length(min=4,max=40, message = "Endereço deve ter no mínimo 4 e no máximo 40 caracteres!")
    private String endereco;

    @NotBlank(message = "CPF Obrigatório!")
    @Length(min=11,max=14, message = "CPF deve ter no mínimo 14 e no máximo 14 caracteres!")
    private String cpf;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
