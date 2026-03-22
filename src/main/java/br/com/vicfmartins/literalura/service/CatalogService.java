package br.com.vicfmartins.literalura.service;

import br.com.vicfmartins.literalura.client.GutendexClient;
import br.com.vicfmartins.literalura.client.dto.GutendexAuthorDto;
import br.com.vicfmartins.literalura.client.dto.GutendexBookDto;
import br.com.vicfmartins.literalura.domain.Author;
import br.com.vicfmartins.literalura.domain.Book;
import br.com.vicfmartins.literalura.repository.AuthorRepository;
import br.com.vicfmartins.literalura.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatalogService {

    private final GutendexClient gutendexClient;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public CatalogService(
            GutendexClient gutendexClient,
            BookRepository bookRepository,
            AuthorRepository authorRepository
    ) {
        this.gutendexClient = gutendexClient;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Transactional
    public Optional<Book> searchAndSaveBookByTitle(String title) {
        List<GutendexBookDto> results = gutendexClient.searchBooksByTitle(title);

        if (results.isEmpty()) {
            return Optional.empty();
        }

        GutendexBookDto selectedBook = results.get(0);
        Optional<Book> existingBook = bookRepository.findByGutendexId(selectedBook.id());

        if (existingBook.isPresent()) {
            return existingBook;
        }

        Book book = new Book(
                selectedBook.id(),
                selectedBook.title(),
                firstLanguage(selectedBook.languages()),
                selectedBook.download_count()
        );

        List<GutendexAuthorDto> authors = selectedBook.authors() == null ? List.of() : selectedBook.authors();

        for (GutendexAuthorDto authorDto : authors) {
            Author author = authorRepository.findByNameIgnoreCase(authorDto.name())
                    .map(existingAuthor -> {
                        existingAuthor.updateLifeSpan(authorDto.birth_year(), authorDto.death_year());
                        return existingAuthor;
                    })
                    .orElseGet(() -> new Author(
                            authorDto.name(),
                            authorDto.birth_year(),
                            authorDto.death_year()
                    ));

            book.addAuthor(author);
        }

        return Optional.of(bookRepository.save(book));
    }

    @Transactional
    public List<Book> listRegisteredBooks() {
        return bookRepository.findAllWithAuthors();
    }

    @Transactional
    public List<Author> listRegisteredAuthors() {
        return authorRepository.findAllWithBooks();
    }

    @Transactional
    public List<Author> listAuthorsAliveInYear(Integer year) {
        return authorRepository.findAliveInYear(year);
    }

    @Transactional
    public List<Book> listBooksByLanguage(String language) {
        return bookRepository.findAllByLanguage(language);
    }

    private String firstLanguage(List<String> languages) {
        if (languages == null || languages.isEmpty()) {
            return "N/A";
        }

        return languages.get(0);
    }
}
