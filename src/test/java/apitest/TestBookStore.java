package apitest;


import com.google.gson.Gson;
import entities.LoanEntity;
import org.testng.annotations.*;
import org.testng.ITestContext;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class TestBookStore {

    String uri = "https://bookstore.toolsqa.com/BookStore/v1/"; // endere�o base/BookStore/v1/
    String ct = "application/json"; // contentType da API
    TestAccount account = new TestAccount();
    Gson gson = new Gson();
    LoanEntity isbn = new LoanEntity();

    @BeforeClass // Antes da Classe e de todos os testes dentro da classe
    // @BeforeMethod // Antes de cada @Test
    public void setUp(ITestContext context){

        account.testCreateUser(context);    // cria um novo usu�rio
        account.testGenerateToken(context); // gera um novo token
    }

    @AfterClass // Depois da classe
    //@AfterMethod // Depois de cada @Teste
    public void tearDown(){
        account.testDeleteUser(); // excluir o usu�rio
    }
    @Test(priority = 1)
    public void testResearchBooks(ITestContext context){
        System.out.println("\u001B[32mIniciando a consulta de livros\u001B[0m");
        // Configura
        // Dados de entrada n�o s�o requeridos
        // Resultado esperado � apenas o status code 200 e um json
        // Executa
        given()
                .log().all()
                .contentType(ct)
                .header("Authorization", "Bearer "+ context.getAttribute("token"))
                 // Valida
        .when()
                .get(uri + "Books")
        .then()
                .log().all()
        ;
    }

    @Test(priority = 2)
    public void testLoanBooks(ITestContext context){ // Emprestar Livros
        // Configura
        // Dados de Entrada
        // userId vir� pelo context
        System.out.println("\u001B[32mIniciando loca��o de livros\u001B[0m");
        isbn.userId = context.getAttribute("userID").toString(); // c�digo do usu�rio
        isbn.collectionOfIsbns = new LoanEntity.ISBN[] {
                new LoanEntity.ISBN("9781449325862")
        };
        // isbn.isbn = "9781449325862"; // c�digo do livro

        // Dados de Saida
        // statusCode = 201
        // Retorne o isbn do livro emprestado (echo)

        // Executa
        given()
                .log().all()
                .contentType(ct)
                .header("Authorization", "Bearer " + context.getAttribute("token"))
                .body(gson.toJson(isbn))
        .when()
                .post(uri + "Books")
                // Valida
        .then()
                .log().all()
                .statusCode(201)
                .body("isbn", is(isbn.isbn))
        ;
    }
    @Test(priority = 3)
    public void testUpdateLoan(ITestContext context){ // Atualizar quem est� com qual livro
        // Configura
        // Dados de Entrada
        // userId � extraido no BeforeMethod
        System.out.println("\u001B[32mIniciando a troca de livros\u001B[0m");
        String isbnAntigo = "9781449325862"; // Livro a substituir
        String isbnNovo = "9781449331818"; // novo livro a ser emprestado

        // alimentar a classe LoanEntity apenas com o c�digo do usu�rio e o isbn
        isbn = new LoanEntity(); // reiniciando o objeto da classe LoanEntity
        isbn.userId = context.getAttribute("userID").toString(); // c�digo do usu�rio
        isbn.isbn = isbnNovo; // c�digo do livro que estava com o usu�rio


        // Executa
        given()
                .log().all()
                .contentType(ct)
                .header("Authorization", "Bearer " + context.getAttribute("token"))
                .body(gson.toJson(isbn))
        .when()
                .put(uri + "Books/" + isbnAntigo)
                // Valida
        .then()
                .log().all()
                .statusCode(200)
                .body("books[0].isbn",is(isbnNovo))
        ;
    }
    @Test(priority = 4)
    public void testDeleteLoan(ITestContext context){
        //Configura
        // userId vem da beforeClass
        // statuscode 204
        System.out.println("\u001B[32mIniciando a dele��o de livros\u001B[0m");

        // Executa
        given()
                .log().all()
                .contentType(ct)
                .header("Authorization", "Bearer " + context.getAttribute("token"))
        .when()
                .delete(uri + "Books?UserId=" + context.getAttribute("userID"))
                //Valida
        .then()
                .log().all()
                .statusCode(204)
        ;

    }

}
