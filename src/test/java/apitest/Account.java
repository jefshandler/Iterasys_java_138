package apitest;

import com.google.gson.Gson;
import entities.AccountEntity;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

// classe
public class Account {
    //3.1 - atributos
    String userId;
    String ct = "application/json"; // contentType da API
    String jsonBody; //guardar o json que sera enviado
    String uri = "https://bookstore.toolsqa.com/Account/v1/"; // endereço base
    Response resposta; //vai guardar o retorino da API
    String token; //guardar o token  - autenticação do usuário

    //3.1.1 Instanciar classes externas
    Gson gson = new Gson(); //instanciar objeto que converte classe para json
    // Dados do usu�rio

    //3.2 m�todos e fun��es

    // Método #1 - Criar usu�rio
    @Test(priority = 1)
    public void testCreateUser(){
        //Arrange - Configura
        AccountEntity account = new AccountEntity(); //Instancia a endidade usuario
        account.userName = "Chaninho0"; // entrada e sa�da( resultado esperado)
        account.password = "Aa!123456"; // entrada
        //Dados de entrada e saida
//        String userName = "charlie"; //nome do usu�rio
//        String password = "123456"; //senha

        jsonBody = gson.toJson(account); // Converte a Entidade usu�rio em formato Json
        //Act - Executa

        //dado - quando - ent�o
        //given - when - then

        resposta = (Response)
        given()      // dado
                .contentType(ct)    // tipo do conteudo
                .log().all()                        // registre tudo na ida
                .body(jsonBody)    // corpo da mensagem que será enviada
        .when() // quando
                .post(uri + "User")
                // Assert - Valida
        .then() // então
                .log().all()        // registre tudo na volta
                .statusCode(201) // valide a comunicação
                .body("username", is(account.userName)) // valida o usuario
        .extract()
                ; // fim da linha do REST-assured

        // extrair o userID (identificação do usuário)

        userId = resposta.jsonPath().getString("userID");
        System.out.println("UserID extraido: " + userId);


    } // fim do método de criação de usuário

    @Test(priority = 2)
    public void testGenerateToken(){
        //Arrange - Configura
        // Dados de entrada sao fornecidos pela AccountEntity
        // Resultado esperado é que ele receba um token

        //Act - Executa
        resposta = (Response)
        given()
                .contentType(ct)
                .log().all()                        // registre tudo na ida
                .body(jsonBody)    // corpo da mensagem que será enviada
        .when()
                .post(uri + "GenerateToken")
        .then()
                .log().all()
                .statusCode(200)
                .body("status", is("Success")) //valida Status = successo
                .body("result", is("User authorized successfully."))
        .extract()
        ; // fim da linha do REST-assured
        // extração do token
        token = resposta.jsonPath().getString("token");
        System.out.println("Token extraido: " + token);
        // Assert - Valida
        Assert.assertTrue(token.length() != 0);
    }
}