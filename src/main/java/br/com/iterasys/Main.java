package br.com.iterasys;

import chatGPT.CalculadoraGPT;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        chamarEncomenda();
        Calculadora.somarInteiros(12, 20);
        Calculadora.subtrairInteiro(7,5);
        Calculadora.multiplicarInteiro(5,5);
        Calculadora.dividirInteiro(25,0);
        Calculadora.dividirInteiro(25,5);

    }
        public static void chamarEncomenda(){
            int barras = 30;
            Encomenda.calcularBarrasdeChocolatesPorCaixa(barras);
        }



}