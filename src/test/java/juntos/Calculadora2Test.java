package juntos;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;
import juntos.Calculadora2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Calculadora2Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new java.io.PrintStream(System.out, true, StandardCharsets.UTF_8.name()));

        // Executar os testes
        org.testng.TestNG testng = new org.testng.TestNG();
        testng.setTestClasses(new Class[] { Calculadora2Test.class });
        testng.run();
    }

    @DataProvider(name = "MassaMultiplicar")
    public Object[][] massaMultiplacar(){
        return new Object[][]{
                {  5,   7, 35 },
                {  2,  10, 20 },
                { 20,   0,  0 },
                { -5,  12,-60 },
                { -5,  -6, 30 }
        }; //fecha o return
    }

    @Test
    public void testeSomar() {
        // Arrange
        double num1 = 5;
        double num2 = 7;
        double resultadoEsperado = 12;

        // Act
        double resultadoAtual = Calculadora2.somar(num1, num2);

        // Assert
        Assert.assertEquals(resultadoAtual, resultadoEsperado);
    }

    @Test
    public void testeSubtrair() {
        // Arrange
        double num1 = 7;
        double num2 = 5;
        double resultadoEsperado = 2;

        // Act
        double resultadoAtual = Calculadora2.subtrair(num1, num2);

        // Assert
        Assert.assertEquals(resultadoAtual, resultadoEsperado);
    }

    @Test
    public void testeMultiplicar() {
        // Arrange
        double num1 = 7;
        double num2 = 5;
        double resultadoEsperado = 35;

        // Act
        double resultadoAtual = Calculadora2.multiplicar(num1, num2);

        // Assert
        Assert.assertEquals(resultadoAtual, resultadoEsperado);
    }
    @Test()
    public void testeMultiplicar22() {
        // Arrange
        double num1 = 10;
        double num2 = 2;
        double resultadoEsperado = 20;

        // Act
        double resultadoAtual = Calculadora2.multiplicar(num1, num2);

        // Assert
        Assert.assertEquals(resultadoAtual, resultadoEsperado);
    }
    @Test
    public void testeMultiplicar2por0() {
        // Arrange
        double num1 = 0;
        double num2 = 2;
        double resultadoEsperado = 0;

        // Act
        double resultadoAtual = Calculadora2.multiplicar(num1, num2);

        // Assert
        Assert.assertEquals(resultadoAtual, resultadoEsperado);
    }
    @Test(dataProvider = "MassaMultiplicar")
    public void testeMultiplicarDDT(double num1, double num2, double resultadoEsperado) {
        // Arrange - Configura
        // os dados são fornecidos para o teste através de uma lista
//        double num1 = 0;
//        double num2 = 2;
//        double resultadoEsperado = 0;

        // Act
        double resultadoAtual = Calculadora2.multiplicar(num1, num2);

        // Assert
        Assert.assertEquals(resultadoAtual, resultadoEsperado);
    }

    @Test
    public void testeDividir() {
        // Arrange
        double num1 = 8;
        double num2 = 2;
        double resultadoEsperado = 4;

        // Act
        double resultadoAtual = Calculadora2.dividir(num1, num2);

        // Assert
        Assert.assertEquals(resultadoAtual, resultadoEsperado);
    }

    @Test
    public void testeDividirPorZero() {
        // Arrange
        double num1 = 8;
        double num2 = 0;
        double resultadoEsperado = Double.NaN;

        // Act
        double resultadoAtual = Calculadora2.dividir(num1, num2);

        // Assert
        Assert.assertEquals(resultadoAtual, resultadoEsperado);
    }
}

