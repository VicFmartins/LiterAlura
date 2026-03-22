package br.com.vicfmartins.literalura.repository;

import br.com.vicfmartins.literalura.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("""
            select distinct b
            from Book b
            left join fetch b.authors
            where b.gutendexId = :gutendexId
            """)
    Optional<Book> findByGutendexId(@Param("gutendexId") Long gutendexId);

    @Query("select distinct b from Book b left join fetch b.authors order by b.title asc")
    List<Book> findAllWithAuthors();

    @Query("""
            select distinct b
            from Book b
            left join fetch b.authors
            where lower(b.language) = lower(:language)
            order by b.title asc
            """)
    List<Book> findAllByLanguage(@Param("language") String language);
}
