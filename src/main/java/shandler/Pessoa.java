package shandler;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Pessoa {
    private String nome;
    private int idade;
    private String endereco;

    public Pessoa(String nome, int idade, String endereco) {
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public static void main(String[] args) {
        Gson gson = new Gson();

        // Criar uma lista de pessoas
        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(new Pessoa("João", 25, "Rua A"));
        pessoas.add(new Pessoa("Maria", 30, "Rua B"));

        // Converter a lista de pessoas em JSON e gravar em um arquivo
        try (FileWriter writer = new FileWriter("pessoas.json")) {
            gson.toJson(pessoas, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Ler o arquivo JSON e converter de volta para objetos Java
        try {
            Pessoa[] pessoasArray = gson.fromJson(new FileReader("pessoas.json"), Pessoa[].class);
            for (Pessoa pessoa : pessoasArray) {
                System.out.println(pessoa.getNome() + " - " + pessoa.getIdade() + " - " + pessoa.getEndereco());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
