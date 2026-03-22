package br.com.vicfmartins.literalura.repository;

import br.com.vicfmartins.literalura.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByNameIgnoreCase(String name);

    @Query("select distinct a from Author a left join fetch a.books order by a.name asc")
    List<Author> findAllWithBooks();

    @Query("""
            select distinct a
            from Author a
            left join fetch a.books
            where (a.birthYear is null or a.birthYear <= :year)
              and (a.deathYear is null or a.deathYear >= :year)
            order by a.name asc
            """)
    List<Author> findAliveInYear(@Param("year") Integer year);
}
