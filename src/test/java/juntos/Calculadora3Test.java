package juntos;

import org.testng.TestNG;
import report.CustomReporter;
import util.ReadCsvFile;
import util.JSONReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class Calculadora3Test {

    @DataProvider(name = "csvData")
    public Object[][] csvData() {
        String csvFile = "csv/matriz.csv";
        return ReadCsvFile.csvData(csvFile);
    }

    @Test(dataProvider = "csvData")
    public void testeMultiplicarCsv(double num1, double num2, double resultadoEsperado) {
        double resultadoAtual = Calculadora2.multiplicar(num1, num2);
        Assert.assertEquals(resultadoAtual, resultadoEsperado);
    }
    @DataProvider(name = "jsonData")
    public Object[][] jsonData() {
        String jsonFile = "json/matriz.json";
        return JSONReader.readData(jsonFile);
    }

    @Test(dataProvider = "jsonData")
    public void testeMultiplicarJson(double num1, double num2, double resultadoEsperado) {
        double resultadoAtual = Calculadora2.multiplicar(num1, num2);
        Assert.assertEquals(resultadoAtual, resultadoEsperado,
                "A multiplicação de " + num1 + " por " + num2 + " falhou: esperava-se " + resultadoEsperado + ", mas obteve-se " + resultadoAtual);
    }
    public static void main(String[] args) {
        TestNG testng = new TestNG();
        testng.setTestClasses(new Class[]{Calculadora3Test.class});
        testng.addListener(new CustomReporter()); // Adicionando o CustomReporter como um listener do TestNG
        testng.run();
    }
}


