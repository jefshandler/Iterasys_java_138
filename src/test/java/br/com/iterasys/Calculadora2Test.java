// 1 - Pacote, em java vai ter um pacote, apenas um pacote
package br.com.iterasys;

// 2 - Importação de Bibliotecas
import juntos.Calculadora2;
import org.testng.Assert;
import org.testng.annotations.Test;

// 3 Classe, geralmente o mesmo nome do arquivo
public class Calculadora2Test {
    // 3.1 - Atributos
    // 3.2 - Método e Funções

    @Test
    public void testeSomar(){
      //Triple AAA, Arrange, Action, Assert

      // configura - Arrange
      double num1 = 5;
      double num2 = 7;

      double resultadoEsperado = 12;
      // execute  Act
        double resultadoObtido = Calculadora2.somar(num1,num2);

      // Valida
        Assert.assertEquals(resultadoObtido, resultadoEsperado);
    }
}




