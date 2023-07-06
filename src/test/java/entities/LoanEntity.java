package entities;

public class LoanEntity {

    public String userId;
    public ISBN[] collectionOfIsbns; // lista com os c�digos dos livros emprestados

    public String isbn; // c�digo do livro externo

    public static class ISBN {

        private final String isbn; // c�digo do livro interno

        public ISBN(String isbn) {     // Construtor
            this.isbn = isbn;
        }
    }
}
