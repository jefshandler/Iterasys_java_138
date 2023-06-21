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
    AccountEntity account = new AccountEntity(); //Instancia a endidade usuario
    // Dados do usu�rio

    //3.2 m�todos e fun��es

    // Método #1 - Criar usu�rio
    @Test(priority = 1)
    public void testCreateUser(){
        //Arrange - Configura

        account.userName = "Kpula7"; // entrada e sa�da( resultado esperado)
        account.password = "Aa!123456"; // entrada
        //Dados de entrada e saida
//        String userName = "charlie"; //nome do usu�rio
//        String password = "123456"; //senha

        jsonBody = gson.toJson(account); // Converte a Entidade usu�rio em formato Json
        //Act - Executa

        //dado - quando - ent�o
        //given - when - then
        System.out.println("\u001B[32mIniciando o teste de criação de usuário\u001B[0m");

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
        String expectedStatus = "Success";
        String expectedResult = "User authorized successfully.";
        int expectedStatusCode = 200;
        // Dados de entrada sao fornecidos pela AccountEntity
        // Resultado esperado é que ele receba um token
        System.out.println("\u001B[32mIniciando o teste de criação de token\u001B[0m");

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
                .statusCode(expectedStatusCode)
                .body("status", is(expectedStatus)) //valida Status = successo
                .body("result", is(expectedResult))
        .extract()
        ; // fim da linha do REST-assured
        // extração do token
        token = resposta.jsonPath().getString("token");
        System.out.println("Token extraido: " + token);
        // Assert - Valida
        Assert.assertTrue(token.length() != 0);
    } // fim do método de geração de token

    @Test(priority = 3)
    public void testAutorized(){
        // Configura
        // Dados de Entrada
        // --> Fornecido pelo AccountEntity do método TestCreateUser
        System.out.println("\u001B[32mIniciando o teste de Autorização de Usuário\u001B[0m");
        // Dados de Saida - resultado esperado
        // status code = 200
        // response body = true

        // Executa
        given()
                .contentType(ct)
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri + "Authorized")
        .then()
                .log().all()
                .statusCode(200)
                //.body(equalTo(true))
        ;
        // Valida
    }
    @Test(priority = 4)
    public void testResearchNotUserAuthorized(){
        System.out.println("\u001B[32mIniciando o teste de usuário não Autorizado \u001B[0m");
        given()
                .contentType(ct)
                .log().all()
        .when()
                .get(uri + "User/" + userId)
        .then()
                .log().all()
                .statusCode(401)
                .body("code",is("1200"))
                .body("message",is("User not authorized!"))
        ;
    }
    @Test(priority = 5)
    public void testResearchUserAuthorized(){
        System.out.println("\u001B[32mIniciando o teste de consulta usuário Autorizado \u001B[0m");
        given()
                .log().all()
                .contentType(ct)
                .header("Authorization", "Bearer "+ token)
        .when()
                .get(uri + "User/" + userId)
        .then()
                .log().all()
                .statusCode(200)
                .body("userId", is(userId))
                .body("username",is(account.userName))
        ;
    }
    @Test(priority = 6)
    public void testDeleteUser(){
        System.out.println("\u001B[32mIniciando o teste para Deletar usuário Autorizado \u001B[0m");
        //Configura
        // Dados de entrada vem do metodo de terstes da ciação do usuário
        // Resultado esperado é o código e mensagem de sucesso na exclução do usuário

        // Executa
        given()
                .log().all()
                .contentType(ct)
                .header("Authorization", "Bearer "+ token)
                .when()
                .delete(uri + "User/" + userId)
                // validação
                .then()
                .log().all()
                .statusCode(204)
        ;
    }

}