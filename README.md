# Projeto DSList - Intensivão Java Spring

## Descrição

O **DSList** é um projeto desenvolvido com o objetivo de ensinar os fundamentos do desenvolvimento de APIs utilizando **Java** e **Spring Boot**. O foco principal é construir uma aplicação de gerenciamento de listas de jogos, permitindo criar, editar e excluir jogos em uma lista específica. A aplicação utiliza o padrão **DTO (Data Transfer Object)**, **JPA (Java Persistence API)**, e é configurada para trabalhar com diferentes ambientes de banco de dados, como **H2** para testes e **PostgreSQL** para produção.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 2.x**
- **Spring Data JPA**
- **PostgreSQL**
- **H2 (para testes)**
- **Maven**
- **Docker Compose (para configuração de ambientes de contêiner)**

## Endpoints da API

### 1. **Cadastro de Jogo**

- **Rota**: `POST /games`
- **Descrição**: Endpoint criado por mim para cadastrar um novo jogo. Este endpoint recebe os dados do jogo no corpo da requisição e os salva no banco de dados.
  
**Exemplo de corpo da requisição**:

```json
{
  "title": "The Witcher 3: Wild Hunt",
  "score": 4.7,
  "gameYear": 2014,
  "genre": "Role-playing (RPG), Adventure",
  "platforms": "XBox, Playstation, PC",
  "imgUrl": "https://example.com/img.jpg",
  "shortDescription": "A captivating open-world RPG.",
  "longDescription": "The Witcher 3 is an action-packed RPG with a rich story and immersive world."
}
@PostMapping("/games")
public ResponseEntity<GameDTO> insert(@RequestBody GameDTO gameDTO) {
    gameDTO = gameService.insert(gameDTO);
    return ResponseEntity.created(URI.create("/games/" + gameDTO.getId())).body(gameDTO);
}
```
## Contribuições Adicionais

Além dos tópicos ensinados durante o curso, que cobrem apenas a parte de leitura (GET), eu fiz as seguintes implementações:

### 1. Endpoints para Cadastrar Jogos e Listas de Jogos

- **Cadastro de Jogos**: Eu criei o endpoint `POST /games` para permitir que o usuário adicione novos jogos ao sistema. Isso facilita a expansão da lista de jogos.
- **Cadastro de Listas de Jogos**: Com a criação do endpoint `POST /game-lists`, agora é possível organizar jogos em diferentes listas de categorias.

### 2. Deletar Jogos

Eu também implementei o endpoint `DELETE /games/{id}`, permitindo a exclusão de jogos da aplicação. Esse recurso facilita a manutenção e organização da base de dados de jogos.

### 3. Reorganizar Posição dos Jogos nas Listas

O endpoint `PUT /game-lists/{listId}/games/{gameId}/position` foi adicionado por mim para permitir que o usuário possa alterar a posição dos jogos dentro de uma lista. Isso é útil para personalizar a ordem dos jogos nas listas.

## Estrutura do Projeto

A aplicação segue uma arquitetura padrão de **Spring Boot** com as seguintes pastas e arquivos principais:

### `/src/main/java/com/devsuperior/dslist`:

- **/controllers**: Contém os controladores responsáveis pelos endpoints da API.
- **/models**: Contém as entidades que representam os dados no banco de dados.
- **/repositories**: Interfaces de repositórios utilizando Spring Data JPA.
- **/services**: Contém a lógica de negócios, como a inserção, deleção e atualização dos jogos e listas.
- **/dtos**: Objetos de Transferência de Dados (DTOs) utilizados para comunicação entre a API e o cliente.

### `/src/main/resources`:

- **application.properties**: Configurações gerais do Spring Boot.
- **application-test.properties**: Configurações para o ambiente de testes utilizando H2.
- **application-prod.properties**: Configurações para o ambiente de produção utilizando PostgreSQL.
- **import.sql**: Script de inserção inicial de dados no banco de dados.
## Como Executar o Projeto

### 1. Preparar o Ambiente de Desenvolvimento

1. **Clonar o repositório**:

```bash
git clone https://github.com/Joaofelipe14/Games-List.git
cd java-spring-dslist
docker-compose up
mvn clean spring-boot:run
```
