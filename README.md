# Music API REST 

## Descrição

Esta é uma API RESTful desenvolvida com Spring Boot para o gerenciamento de um catálogo de música. O projeto implementa uma arquitetura em camadas (Controller, Service, Repository) para gerenciar entidades de Artistas, seus Álbuns e as Músicas de cada álbum.

Esta aplicação foi desenvolvida como um projeto prático para demonstrar conceitos de desenvolvimento backend, incluindo persistência de dados com Spring Data JPA, design de API REST, validação de dados, tratamento global de exceções e consultas avançadas com paginação e filtros.

## Funcionalidades

* **Arquitetura em Camadas:** Separação clara de responsabilidades entre Controladores (API), Serviços (Lógica de Negócio) e Repositórios (Acesso a Dados).
* **CRUD Completo:** Operações Create, Read, Update e Delete para todas as entidades principais: `Artista`, `Album`, e `Musica`.
* **Relacionamentos de Dados:** Gerenciamento de relacionamentos 1:N (Um-para-Muitos) entre `Artista` -> `Album` e `Album` -> `Musica`.
* **DTO (Data Transfer Objects):** Utilização do padrão DTO para desacoplar a API das entidades de persistência e para validação de entrada (`CreateDTO`).
* **Validação:** Validação de dados de entrada no nível do DTO usando `jakarta.validation` (ex: `@NotBlank`, `@Min`).
* **Tratamento de Exceções:** Manipulação global de exceções (`@ControllerAdvice`) para retornar respostas de erro consistentes e padronizadas (ex: 404 Not Found, 400 Bad Request).
* **Consultas Avançadas:** Implementação de paginação, ordenação e filtragem dinâmica no endpoint de listagem de artistas.

## Tecnologias Utilizadas

* **Java 21**
* **Spring Boot 3.x**
* **Spring Web:** Para a camada de API REST.
* **Spring Data JPA:** Para persistência de dados.
* **H2 Database:** Banco de dados em memória (configurado para persistência em arquivo).
* **Spring Validation:** Para validação de DTOs.
* **Maven:** Para gerenciamento de dependências e build do projeto.

## Pré-requisitos

* JDK 21 (ou superior)
* Apache Maven 3.9 (ou superior)

## Executando o Projeto

1.  Clone este repositório:
    ```sh
    git clone https://github.com/brunotesckemartins/music-api-rest.git
    ```

2.  Navegue até o diretório raiz do projeto:
    ```sh
    cd dev
    ```

3.  Compile e empacote o projeto usando o Maven:
    ```sh
    mvn clean install
    ```

4.  Execute a aplicação:
    ```sh
    java -jar target/dev-0.0.1-SNAPSHOT.jar
    ```

5.  Alternativamente, você pode executar a classe principal `com.music_api.dev.DevApplication` diretamente da sua IDE (IntelliJ).

A aplicação estará disponível em `http://localhost:8080`.

## Configuração do Banco de Dados

O projeto está configurado para usar um banco de dados H2 persistido em arquivo. O arquivo do banco (`organizador-musica-db.mv.db`) será criado no diretório raiz do projeto na primeira execução.

O console H2 está habilitado e pode ser acessado em:
`http://localhost:8080/h2-console`

**Configurações para o Console H2:**
* **JDBC URL:** `jdbc:h2:file:./organizador-musica-db`
* **User Name:** `admin`
* **Password:** `123`

---

## Documentação da API (Endpoints)

### Artista

`POST /artistas`
* Cria um novo artista.
* **Corpo da Requisição (`ArtistaCreateDTO`):**
    ```json
    {
        "nome": "string",
        "genero": "string"
    }
    ```

`GET /artistas`
* Lista todos os artistas com paginação, ordenação e filtro.
* **Parâmetros de Consulta (Query Params):**
    * `page` (int, opcional, default: 0): O número da página.
    * `size` (int, opcional, default: 10): O tamanho da página.
    * `sortBy` (String, opcional, default: "nome"): O campo para ordenação.
    * `direction` (String, opcional, default: "asc"): A direção da ordenação (`asc` ou `desc`).
    * `generoFiltro` (String, opcional): Filtra artistas pelo gênero (busca parcial, case-insensitive).

`GET /artistas/{id}`
* Busca um artista específico pelo seu UUID.

`PUT /artistas/{id}`
* Atualiza um artista existente.
* **Corpo da Requisição (`ArtistaCreateDTO`):**
    ```json
    {
        "nome": "string",
        "genero": "string"
    }
    ```

`DELETE /artistas/{id}`
* Exclui um artista pelo seu UUID.

---

### Album

`POST /albuns`
* Cria um novo álbum para um artista existente.
* **Corpo da Requisição (`AlbumCreateDTO`):**
    ```json
    {
        "titulo": "string",
        "anoLancamento": 2025,
        "artistaId": "uuid-do-artista"
    }
    ```

`GET /albuns`
* Lista todos os álbuns.

`GET /albuns/{id}`
* Busca um álbum específico pelo seu UUID.

`PUT /albuns/{id}`
* Atualiza um álbum existente.
* **Corpo da Requisição (`AlbumCreateDTO`):**
    ```json
    {
        "titulo": "string",
        "anoLancamento": 2025,
        "artistaId": "uuid-do-artista"
    }
    ```

`DELETE /albuns/{id}`
* Exclui um álbum pelo seu UUID.

---

### Musica

`POST /musicas`
* Cria uma nova música para um álbum existente.
* **Corpo da Requisição (`MusicaCreateDTO`):**
    ```json
    {
        "titulo": "string",
        "duracaoEmSegundos": 300,
        "albumId": "uuid-do-album"
    }
    ```

`GET /musicas`
* Lista todas as músicas.

`GET /musicas/{id}`
* Busca uma música específica pelo seu UUID.

`PUT /musicas/{id}`
* Atualiza uma música existente.
* **Corpo da Requisição (`MusicaCreateDTO`):**
    ```json
    {
        "titulo": "string",
        "duracaoEmSegundos": 300,
        "albumId": "uuid-do-album"
    }
    ```

`DELETE /musicas/{id}`
* Exclui uma música pelo seu UUID.
