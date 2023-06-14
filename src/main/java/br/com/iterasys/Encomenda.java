// 1 - Pacote, em java vai ter um pacote, apenas um pacote
package br.com.iterasys;

// 2 - IMportação de Bibliotecas


// 3 Classe, geralmente o mesmo nome do arquivo
// subistantivo é
public class Encomenda {
    //3.1 attributos

    //3.2 Funções e métodos

    public static int calcularBarrasdeChocolatesPorCaixa(int barras)
    {
        byte quantidadePorCaixa = 12;
        int caixas = barras / quantidadePorCaixa;
        int unidades = barras % quantidadePorCaixa;
        System.out.println("Quantidade de Caixas: " + caixas);
        System.out.println("Unidades :" + unidades);
        return caixas;
    }//Fim do calcularbarrasdechocolate
}
