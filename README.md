# LiterAlura

LiterAlura
Um catálogo de livros interativo desenvolvido em Java que consome a API Gutendex e persiste dados em PostgreSQL.
Sobre o Projeto
LiterAlura é uma aplicação de console que permite buscar, armazenar e consultar informações sobre mais de 70.000 livros do Projeto Gutenberg. O sistema oferece funcionalidades para gerenciar um catálogo pessoal de livros e autores, com diversas opções de consulta e filtros.
Funcionalidades

Buscar livro pelo título: Consulta a API Gutendex e salva o livro no banco de dados
Listar livros registrados: Exibe todos os livros armazenados no catálogo
Listar autores: Mostra todos os autores cadastrados com seus respectivos livros
Buscar autores vivos em determinado ano: Filtra autores que estavam vivos em um ano específico
Listar livros por idioma: Busca livros em português, inglês, espanhol ou francês

Tecnologias Utilizadas

Java - Linguagem de programação principal
Spring Boot - Framework para desenvolvimento da aplicação
Spring Data JPA - Persistência de dados
PostgreSQL - Banco de dados relacional
Maven - Gerenciamento de dependências
Gutendex API - Fonte de dados dos livros

Pré-requisitos

JDK 17 ou superior
PostgreSQL instalado e configurado
Maven
IDE de sua preferência (recomendado IntelliJ IDEA)

Como Executar

Clone o repositório:

bashgit clone https://github.com/seu-usuario/literalura.git

Configure o banco de dados PostgreSQL:

sqlCREATE DATABASE literalura;

Configure as credenciais no arquivo application.properties:

propertiesspring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update

Execute o projeto:

bashmvn spring-boot:run
```

## Exemplos de Uso

### Buscando um livro
```
Escolha uma opção: 1
Digite o nome do livro: Dom Casmurro

Título: Dom Casmurro
Autor: Machado de Assis
Idioma: pt
Downloads: 5432
```

### Listando autores vivos em um ano específico
```
Escolha uma opção: 4
Digite o ano: 1800

Autor: Jane Austen
Nascimento: 1775
Falecimento: 1817
Livros: [Emma]
Aprendizados
Este projeto foi desenvolvido como parte do desafio LiterAlura, aplicando conceitos de:

Consumo de APIs REST
Persistência de dados com JPA
Relacionamentos entre entidades
Consultas customizadas em banco de dados
Tratamento de dados JSON
Arquitetura em camadas

Melhorias Futuras

Implementar estatísticas de livros
Top 10 livros mais baixados
Busca de autor por nome
Listagem de autores com filtros avançados
Interface gráfica
Sistema de favoritos


Este projeto foi desenvolvido como parte do programa educacional da Alura.

Para dúvidas ou sugestões, entre em contato através do Discord ou fórum da Alura.

Desenvolvido como parte do Challenge Backend da Alura
