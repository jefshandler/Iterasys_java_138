package br.com.iterasys;

import org.testng.Assert;
import org.testng.annotations.Test;

import static br.com.iterasys.AreasAula.*;

public class AreasAulaTest {

    @Test
    public void testarCalcularQuadrado(){
        // Configura
        double lado = 3;
        double resultadoEsperado = 9;

        // Executa
        double resultadoAtual = AreasAula.calcularQuadrado(lado);

        // Valida
        Assert.assertEquals(resultadoAtual, resultadoEsperado);
    }
    @Test
    public void testarCalcularRetangulo() {
        // Configura
        double largura = 3;
        double comprimento = 4;
        double resultadoEsperado = 12;

        // Executa
        double resultadoAtual = AreasAula.calcularRetangulo(largura, comprimento);

        // Valida
        Assert.assertEquals(resultadoAtual, resultadoEsperado);
    }

    @Test
    public void testarCalcularTriangulo() {
        // Configura
        double base = 3;
        double altura = 4;
        double resultadoEsperado = 6; // A f�rmula da �rea do tri�ngulo � (base*altura)/2

        // Executa
        double resultadoAtual = AreasAula.calcularTriangulo(base, altura);

        // Valida
        Assert.assertEquals(resultadoAtual, resultadoEsperado);
    }

    @Test
    public void testarCalcularCirculo() {
        // Configura
        double raio = 3;
        double resultadoEsperado = Math.PI * Math.pow(raio, 2); // A f�rmula da �rea do c�rculo � ?r�

        // Executa
        double resultadoAtual = AreasAula.calcularCirculo(raio);

        // Valida
        Assert.assertEquals(resultadoAtual, resultadoEsperado);
    }

}
