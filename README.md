# LiterAlura

![Java](https://img.shields.io/badge/Java-17-ED8B00?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.5-6DB33F?logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-4169E1?logo=postgresql&logoColor=white)
![Status](https://img.shields.io/badge/status-MVP%20funcional-0a7ea4)

Aplicação de console em Java com Spring Boot que consome a API [Gutendex](https://gutendex.com/) para buscar livros do Projeto Gutenberg, persistir os dados em PostgreSQL e permitir consultas simples via menu interativo.

## Visão Geral

O projeto LiterAlura foi pensado para praticar fundamentos importantes de backend:

- consumo de API REST;
- desserialização de JSON;
- modelagem de entidades;
- persistência com JPA;
- consultas com Spring Data;
- organização em camadas.

Nesta versão, o repositório deixa de ser apenas descritivo e passa a incluir uma base executável de MVP.

## Funcionalidades

- Buscar livro por título na API Gutendex e salvar no banco
- Evitar duplicidade de livros já importados
- Listar livros registrados localmente
- Listar autores cadastrados
- Buscar autores vivos em um ano informado
- Listar livros por idioma
- Executar tudo por um menu de console simples

## Fluxo da Aplicação

```text
Usuário escolhe uma opção no console
-> aplicação consulta a API Gutendex quando necessário
-> resposta JSON é convertida em DTOs
-> serviço mapeia DTOs para entidades
-> dados são persistidos no PostgreSQL
-> aplicação exibe o resultado formatado no terminal
```

## Estrutura do Projeto

```text
.
├── pom.xml
├── README.md
└── src
    └── main
        ├── java
        │   └── br/com/vicfmartins/literalura
        │       ├── client
        │       ├── domain
        │       ├── repository
        │       ├── service
        │       ├── ui
        │       └── LiteraluraApplication.java
        └── resources
            ├── application.properties
            └── application-example.properties
```

## Stack Utilizada

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- Gutendex API

## Como Executar

### 1. Clonar o repositório

```bash
git clone <url-do-repositorio>
cd LiterAlura
```

### 2. Criar o banco PostgreSQL

```sql
CREATE DATABASE literalura;
```

### 3. Configurar as credenciais

Copie o arquivo de exemplo e ajuste os valores:

```bash
cp src/main/resources/application-example.properties src/main/resources/application-local.properties
```

Se estiver no Windows PowerShell:

```powershell
Copy-Item src/main/resources/application-example.properties src/main/resources/application-local.properties
```

Você também pode usar variáveis de ambiente:

- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`

### 4. Executar a aplicação

```bash
mvn spring-boot:run
```

## Menu Disponível

```text
1 - Buscar livro pelo título
2 - Listar livros registrados
3 - Listar autores registrados
4 - Listar autores vivos em determinado ano
5 - Listar livros por idioma
0 - Sair
```

## Exemplo de Uso

### Buscar um livro

```text
Escolha uma opção: 1
Digite o título do livro: Dom Casmurro

Livro salvo com sucesso:
Título: Dom Casmurro
Autores: Machado de Assis
Idioma: pt
Downloads: 5432
```

### Buscar autores vivos em um ano

```text
Escolha uma opção: 4
Digite o ano: 1810

Autores encontrados:
- Jane Austen (1775 - 1817)
  Livros: Emma
```

## Modelagem Atual

O MVP trabalha com duas entidades principais:

- `Book`
  - identificador local
  - identificador da Gutendex
  - título
  - idioma principal
  - total de downloads

- `Author`
  - identificador local
  - nome
  - ano de nascimento
  - ano de falecimento

Relacionamento:

- muitos autores podem estar ligados a muitos livros

## Próximos Passos

- adicionar paginação e filtros mais ricos;
- buscar autores por nome;
- exibir top livros mais baixados;
- incluir testes automatizados;
- oferecer exportação de relatórios;
- evoluir de console para API ou interface web.

## Aprendizados Reforçados

- integração com APIs externas;
- persistência relacional com JPA;
- consultas derivadas e personalizadas;
- organização de projeto backend em camadas;
- transformação de dados externos em entidades de domínio.

## Observação

Este projeto usa a Gutendex como fonte pública de dados bibliográficos. Os resultados exibidos dependem do retorno disponível na API no momento da consulta.
