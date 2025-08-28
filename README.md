# Collaborative List API

![Java](https://img.shields.io/badge/Java-21-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.5-green.svg)
![MySQL](https://img.shields.io/badge/MySQL-8.0-orange.svg)
![Maven](https://img.shields.io/badge/Maven-3.8-red.svg)

A **Collaborative List API** é uma aplicação robusta desenvolvida com Spring Boot, projetada para facilitar a criação e o gerenciamento de listas compartilhadas em tempo real. Ideal para equipes, famílias ou qualquer grupo que precise organizar tarefas, compras ou ideias de forma colaborativa.

## Funcionalidades

*   **Autenticação de Usuários:** Registro e login seguros para acesso à API.
*   **Gerenciamento de Salas:** Crie e gerencie salas colaborativas, cada uma com seu próprio conjunto de listas.
*   **Listas Compartilhadas:** Dentro de cada sala, os usuários podem criar e organizar múltiplas listas.
*   **Itens de Lista:** Adicione, visualize e remova itens de qualquer lista.
*   **Colaboração em Tempo Real:** Permite que múltiplos usuários interajam com as mesmas listas simultaneamente.

## Tecnologias Utilizadas

*   **Backend:**
    *   Java 21
    *   Spring Boot 3.5.5 (Web, Data JPA, Security, Validation, DevTools)
    *   Lombok
*   **Banco de Dados:**
    *   MySQL (via `mysql-connector-j`)
*   **Gerenciamento de Dependências:**
    *   Maven

## Como Executar

### Pré-requisitos

Certifique-se de ter os seguintes softwares instalados em sua máquina:

*   **Java Development Kit (JDK) 21**
*   **Apache Maven 3.8+**
*   **MySQL Server 8.0+**

### Configuração do Banco de Dados

1.  **Crie um banco de dados MySQL** chamado `collaborative_list`.
2.  **Configure as variáveis de ambiente** para as credenciais do seu banco de dados. É altamente recomendável não hardcodar senhas no `application.properties`.

    Você pode criar um arquivo `.env` (e adicioná-lo ao `.gitignore`) ou configurar diretamente no seu ambiente:

    ```bash
    export DB_URL="jdbc:mysql://localhost:3306/collaborative_list?createDatabaseIfNotExist=true"
    export DB_USER="your_mysql_username"
    export DB_PASSWORD="your_mysql_password"
    ```

    Alternativamente, você pode modificar `src/main/resources/application.properties` para usar variáveis de ambiente:

    ```properties
    spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3306/collaborative_list?createDatabaseIfNotExist=true}
    spring.datasource.username=${DB_USER:root}
    spring.datasource.password=${DB_PASSWORD:Jphb@032636}
    ```
    **Nota:** O valor `Jphb@032636` é um exemplo. **Substitua-o pela sua senha real ou use variáveis de ambiente.**

### Executando a Aplicação

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/your-username/collaborative-list.git
    cd collaborative-list
    ```
2.  **Compile o projeto:**
    ```bash
    ./mvnw clean install
    ```
3.  **Execute a aplicação:**
    ```bash
    ./mvnw spring-boot:run
    ```

    A aplicação estará disponível em `http://localhost:8080`.

## Estrutura do Projeto

*   `src/main/java/com/jpdev/collaborative_list/`: Contém o código-fonte principal da aplicação.
    *   `controller/`: Classes que lidam com as requisições HTTP e definem os endpoints da API.
    *   `dto/`: Objetos de Transferência de Dados (DTOs) para comunicação entre camadas.
    *   `entity/`: Entidades JPA que mapeiam as tabelas do banco de dados.
    *   `exception/`: Classes de exceção personalizadas.
    *   `repository/`: Interfaces de repositório para acesso a dados (Spring Data JPA).
    *   `service/`: Classes de serviço que contêm a lógica de negócio.
*   `src/main/resources/`: Contém arquivos de configuração e recursos estáticos.
    *   `application.properties`: Configurações da aplicação (porta, banco de dados, etc.).
*   `pom.xml`: Arquivo de configuração do Maven, define dependências e plugins.

## Endpoints da API

A API é organizada em módulos para autenticação, gerenciamento de salas, listas e itens.

### Autenticação

*   **`POST /register`**
    *   **Descrição:** Registra um novo usuário no sistema.
    *   **Corpo da Requisição:**
        ```json
        {
            "username": "novo_usuario",
            "password": "senha_segura"
        }
        ```
*   **`POST /login`**
    *   **Descrição:** Autentica um usuário existente e retorna uma mensagem de sucesso.
    *   **Corpo da Requisição:**
        ```json
        {
            "username": "usuario_existente",
            "password": "sua_senha"
        }
        ```

### Salas (Rooms)

*   **`POST /rooms/create`**
    *   **Descrição:** Cria uma nova sala colaborativa.
    *   **Corpo da Requisição:**
        ```json
        {
            "name": "Minha Nova Sala"
        }
        ```
*   **`DELETE /rooms/{roomId}/delete`**
    *   **Descrição:** Exclui uma sala existente pelo seu ID.
*   **`GET /rooms`**
    *   **Descrição:** Retorna uma lista de todas as salas disponíveis.
*   **`POST /rooms/join`**
    *   **Descrição:** Permite que um usuário entre em uma sala usando um código de sala.
    *   **Corpo da Requisição:**
        ```json
        {
            "roomCode": "CODIGO_DA_SALA"
        }
        ```
*   **`GET /rooms/{roomId}/code`**
    *   **Descrição:** Retorna o código de acesso de uma sala específica.
*   **`GET /rooms/{roomId}`**
    *   **Descrição:** Retorna os detalhes de uma sala específica pelo seu ID.

### Listas (Lists)

*   **`GET /lists/{roomId}`**
    *   **Descrição:** Retorna todas as listas associadas a uma sala específica.
*   **`POST /lists/{roomId}/create`**
    *   **Descrição:** Cria uma nova lista dentro de uma sala.
    *   **Corpo da Requisição:**
        ```json
        {
            "name": "Lista de Compras"
        }
        ```
*   **`GET /lists/id/{listId}`**
    *   **Descrição:** Retorna os detalhes de uma lista específica pelo seu ID.

### Itens (Items)

*   **`POST /items/{roomId}/{listId}/create`**
    *   **Descrição:** Adiciona um novo item a uma lista específica dentro de uma sala.
    *   **Corpo da Requisição:**
        ```json
        {
            "name": "Comprar Leite"
        }
        ```
*   **`GET /items/{listId}`**
    *   **Descrição:** Retorna todos os itens de uma lista específica.
*   **`GET /items/id/{itemId}`**
    *   **Descrição:** Retorna os detalhes de um item específico pelo seu ID.
*   **`DELETE /items/{itemId}`**
    *   **Descrição:** Exclui um item de uma lista pelo seu ID.

## Geral
        ```json
    {
        "roomID": 1,
        "roomCode": "T8MH0X",
        "creatorName": "Joao Pedro",
        "creationTime": "2025-08-28T17:38:30.33781",
        "lists": [
            {
                "listId": 1,
                "listName": "Compras",
                "items": [
                    {
                        "itemId": 1,
                        "itemName": "Leite"
                    },
                    {
                        "itemId": 2,
                        "itemName": "Arroz"
                    },
                    {
                        "itemId": 3,
                        "itemName": "Refrigerante"
                    },
                    {
                        "itemId": 4,
                        "itemName": "Carne"
                    }
                ]
            }
        ]
    }
        ```

## Atualizações
1. *Criar um FrontEnd.*
2. *Adaptar algumas funcionalidades.*
2. *Melhorias futuras.*

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.

## Licença

Este projeto está licenciado sob a licença GPL V3.
