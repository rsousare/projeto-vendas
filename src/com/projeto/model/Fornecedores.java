package com.projeto.model;

public class Fornecedores extends com.projeto.model.Clientes {

    private String cnpj;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return this.getNome();
    }
}
