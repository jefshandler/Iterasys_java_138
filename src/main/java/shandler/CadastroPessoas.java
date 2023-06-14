package shandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CadastroPessoas extends JFrame {
    private List<Pessoa> pessoas = new ArrayList<>();

    private JLabel labelNome = new JLabel("Nome:");
    private JTextField campoNome = new JTextField(20);
    private JLabel labelIdade = new JLabel("Idade:");
    private JTextField campoIdade = new JTextField(3);
    private JLabel labelEndereco = new JLabel("Endereço:");
    private JTextField campoEndereco = new JTextField(30);
    private JButton botaoAdicionar = new JButton("Adicionar");
    private JButton botaoListar = new JButton("Listar");

    public CadastroPessoas() {
        super("Cadastro de Pessoas");

        // Configurar layout
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);

        // Adicionar componentes
        add(labelNome, constraints);
        constraints.gridx = 1;
        add(campoNome, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(labelIdade, constraints);
        constraints.gridx = 1;
        add(campoIdade, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(labelEndereco, constraints);
        constraints.gridx = 1;
        add(campoEndereco, constraints);
        constraints.gridx = 0;
        constraints.gridy = 3;
        add(botaoAdicionar, constraints);
        constraints.gridx = 1;
        add(botaoListar, constraints);

        // Adicionar ação ao botão Adicionar
        botaoAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = campoNome.getText();
                int idade = Integer.parseInt(campoIdade.getText());
                String endereco = campoEndereco.getText();
                Pessoa pessoa = new Pessoa(nome, idade, endereco);
                pessoas.add(pessoa);

                // Serializar lista de pessoas em formato JSON
                Gson gson = new Gson();
                String json = gson.toJson(pessoas);

                // Gravar JSON em arquivo
                try (FileWriter writer = new FileWriter("pessoas.json")) {
                    writer.write(json);
                    JOptionPane.showMessageDialog(CadastroPessoas.this, "Pessoa adicionada com sucesso e arquivo gravado!");
                    campoNome.setText("");
                    campoIdade.setText("");
                    campoEndereco.setText("");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(CadastroPessoas.this, "Erro ao gravar arquivo: " + ex.getMessage());
                }
            }
        });

        // Adicionar ação ao botão Listar
        botaoListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ler JSON do arquivo
                try (FileReader reader = new FileReader("pessoas.json")) {
                    Gson gson = new Gson();
                    pessoas = gson.fromJson(reader, new TypeToken<List<Pessoa>>(){}.getType());
                    StringBuilder sb = new StringBuilder();
                    for (Pessoa pessoa : pessoas) {
                        sb.append(pessoa.getNome()).append(" - ").append(pessoa.getIdade()).append(" - ").append(pessoa.getEndereco()).append("\n");
                    }
                    JOptionPane.showMessageDialog(CadastroPessoas.this, sb.toString());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(CadastroPessoas.this, "Erro ao ler arquivo: " + ex.getMessage());
                }
            }
        });

        // Configurar janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CadastroPessoas();
    }
}