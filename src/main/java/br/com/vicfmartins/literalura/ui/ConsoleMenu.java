package br.com.vicfmartins.literalura.ui;

import br.com.vicfmartins.literalura.domain.Author;
import br.com.vicfmartins.literalura.domain.Book;
import br.com.vicfmartins.literalura.service.CatalogService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class ConsoleMenu implements CommandLineRunner {

    private final CatalogService catalogService;

    public ConsoleMenu(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @Override
    public void run(String... args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int option;

            do {
                printMenu();
                option = readInt(scanner, "Escolha uma opcao: ");
                handleOption(option, scanner);
            } while (option != 0);
        }
    }

    private void handleOption(int option, Scanner scanner) {
        switch (option) {
            case 1 -> searchBook(scanner);
            case 2 -> listBooks();
            case 3 -> listAuthors();
            case 4 -> listAuthorsAliveInYear(scanner);
            case 5 -> listBooksByLanguage(scanner);
            case 0 -> System.out.println("Encerrando LiterAlura.");
            default -> System.out.println("Opcao invalida. Tente novamente.");
        }
    }

    private void searchBook(Scanner scanner) {
        System.out.print("Digite o titulo do livro: ");
        String title = scanner.nextLine();

        catalogService.searchAndSaveBookByTitle(title)
                .ifPresentOrElse(
                        this::printSavedBook,
                        () -> System.out.println("Nenhum livro encontrado para o titulo informado.")
                );
    }

    private void listBooks() {
        List<Book> books = catalogService.listRegisteredBooks();

        if (books.isEmpty()) {
            System.out.println("Nenhum livro registrado ate o momento.");
            return;
        }

        System.out.println("Livros registrados:");
        books.forEach(book -> {
            System.out.println("- Titulo: " + book.getTitle());
            System.out.println("  Autores: " + formatAuthors(book));
            System.out.println("  Idioma: " + book.getLanguage());
            System.out.println("  Downloads: " + safeNumber(book.getDownloadCount()));
        });
    }

    private void listAuthors() {
        List<Author> authors = catalogService.listRegisteredAuthors();

        if (authors.isEmpty()) {
            System.out.println("Nenhum autor registrado ate o momento.");
            return;
        }

        System.out.println("Autores registrados:");
        authors.forEach(author -> {
            System.out.println("- " + author.getName() + " (" + safeYear(author.getBirthYear()) + " - " + safeYear(author.getDeathYear()) + ")");
            System.out.println("  Livros: " + formatBooks(author));
        });
    }

    private void listAuthorsAliveInYear(Scanner scanner) {
        int year = readInt(scanner, "Digite o ano que deseja consultar: ");
        List<Author> authors = catalogService.listAuthorsAliveInYear(year);

        if (authors.isEmpty()) {
            System.out.println("Nenhum autor encontrado para o ano informado.");
            return;
        }

        System.out.println("Autores vivos em " + year + ":");
        authors.forEach(author -> {
            System.out.println("- " + author.getName() + " (" + safeYear(author.getBirthYear()) + " - " + safeYear(author.getDeathYear()) + ")");
            System.out.println("  Livros: " + formatBooks(author));
        });
    }

    private void listBooksByLanguage(Scanner scanner) {
        System.out.print("Digite o idioma (ex.: pt, en, es, fr): ");
        String language = scanner.nextLine();
        List<Book> books = catalogService.listBooksByLanguage(language);

        if (books.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o idioma informado.");
            return;
        }

        System.out.println("Livros encontrados:");
        books.forEach(book -> {
            System.out.println("- Titulo: " + book.getTitle());
            System.out.println("  Autores: " + formatAuthors(book));
            System.out.println("  Downloads: " + safeNumber(book.getDownloadCount()));
        });
    }

    private void printSavedBook(Book book) {
        System.out.println("Livro salvo com sucesso:");
        System.out.println("Titulo: " + book.getTitle());
        System.out.println("Autores: " + formatAuthors(book));
        System.out.println("Idioma: " + book.getLanguage());
        System.out.println("Downloads: " + safeNumber(book.getDownloadCount()));
    }

    private String formatAuthors(Book book) {
        return book.getAuthors().stream()
                .map(Author::getName)
                .sorted()
                .collect(Collectors.joining(", "));
    }

    private String formatBooks(Author author) {
        return author.getBooks().stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .map(Book::getTitle)
                .collect(Collectors.joining(", "));
    }

    private String safeYear(Integer year) {
        return year == null ? "N/A" : year.toString();
    }

    private String safeNumber(Integer value) {
        return value == null ? "N/A" : value.toString();
    }

    private int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String rawValue = scanner.nextLine();

            try {
                return Integer.parseInt(rawValue);
            } catch (NumberFormatException ex) {
                System.out.println("Valor invalido. Digite um numero.");
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("===== LiterAlura =====");
        System.out.println("1 - Buscar livro pelo titulo");
        System.out.println("2 - Listar livros registrados");
        System.out.println("3 - Listar autores registrados");
        System.out.println("4 - Listar autores vivos em determinado ano");
        System.out.println("5 - Listar livros por idioma");
        System.out.println("0 - Sair");
    }
}
