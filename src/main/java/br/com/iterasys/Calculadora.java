package br.com.iterasys;

public class Calculadora {
    public static int somarInteiros(int num1, int num2){
        int resultado = num1 + num2;
        System.out.println("Soma de "+ num1 + " + " + num2 + " = " + resultado);
        return resultado;
    }
    public static int subtrairInteiro(int num1, int num2){
        int resultado = num1 - num2;
        System.out.println("Subtrair de "+ num1 + " - " + num2 + " = " + resultado);
        return resultado;
    }

    public static int multiplicarInteiro(int num1, int num2){
        int resultado = num1 * num2;
        System.out.println("Multiplicar de "+ num1 + " * " + num2 + " = " + resultado);
        return resultado;
    }
    public static double dividirInteiro(double num1, double num2) {
        if (num2 == 0) {
            System.out.println("Ops... divisão por zero não é permitida.");
            return Double.NaN;
        }

        double resultado = num1 / num2;
        System.out.println("Dividir de " + num1 + " / " + num2 + " = " + resultado);
        return resultado;
    }


}
