# API de Pedidos

API REST desenvolvida com **Java**, **Spring Boot**, **Spring Security**, **JWT**, **JPA/Hibernate** e **SQLite**, utilizando princípios de **Clean Architecture**.

## Tecnologias

- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- JWT
- SQLite
- Maven

---

## Funcionalidades

- Cadastro de usuários
- Autenticação com JWT
- Cadastro de produtos
- Listagem paginada de produtos
- Criação de pedidos
- Consulta de pedido por ID

---

## Autenticação

Após realizar o login, utilize o token JWT retornado em todas as rotas protegidas.

Exemplo:

```http
Authorization: Bearer SEU_TOKEN
```

---

# Endpoints

## Cadastro de usuário

### POST `/register`

### Body

```json
{
    "name": "joao",
    "email": "joao@gmail.com",
    "password": "12345678"
}
```

---

## Login

### POST `/login`

### Body

```json
{
    "email": "joao@gmail.com",
    "password": "12345678"
}
```

### Resposta

```json
{
    "token": "jwt_token"
}
```

---

## Cadastrar produto

### POST `/product`

### Headers

```http
Authorization: Bearer TOKEN
```

### Body

```json
{
    "name": "Celular",
    "price": 500,
    "description": "Lenovo"
}
```

---

## Listar produtos

### GET `/product?page=0&size=10`

### Headers

```http
Authorization: Bearer TOKEN
```

### Query Parameters

| Parâmetro | Descrição |
| ---------- | --------- |
| page | Número da página |
| size | Quantidade de registros por página |

---

## Criar pedido

### POST `/orders`

### Headers

```http
Authorization: Bearer TOKEN
```

### Body

```json
[
    {
        "productId": 1,
        "quantity": 7,
        "unitPrice": 500
    },
    {
        "productId": 2,
        "quantity": 7,
        "unitPrice": 500
    },
    {
        "productId": 3,
        "quantity": 7,
        "unitPrice": 500
    },
    {
        "productId": 4,
        "quantity": 2,
        "unitPrice": 500
    }
]
```

---

## Buscar pedido por ID

### GET `/orders/{id}`

Exemplo

```http
GET /orders/1
```

### Headers

```http
Authorization: Bearer TOKEN
```

### Exemplo de resposta

```json
{
    "orderId": 1,
    "products": [
        {
            "productId": 1,
            "name": "Notebook",
            "price": 1000,
            "description": "Lenovo",
            "quantity": 7
        },
        {
            "productId": 2,
            "name": "Geladeira",
            "price": 1000,
            "description": "Lenovo",
            "quantity": 7
        }
    ],
    "totalPrice": 14000,
    "totalQuantity": 14
}
```

---

# Estrutura do Projeto

```
src
├── app
│   ├── dtos
│   └── usecase
│
├── domain
│   ├── entities
│   ├── repository
│   ├── exceptions
│   └── valuesobject
│
├── infra
│   ├── http
│   ├── persistence
│   └── security
│
└── ApiApplication
```

---

# Executando o projeto

Clone o repositório:

```bash
git clone <url-do-repositorio>
```

Entre na pasta:

```bash
cd api
```

Execute:

```bash
./mvnw spring-boot:run
```

ou

```bash
mvn spring-boot:run
```

A aplicação estará disponível em:

```
http://localhost:8080
```

---

# Fluxo da API

```
Usuário
    │
    ▼
Controller
    │
    ▼
Use Case
    │
    ▼
Repository (Domain)
    │
    ▼
Repository JPA
    │
    ▼
SQLite
```

---

# Arquitetura

O projeto segue os princípios da **Clean Architecture**, separando responsabilidades em:

- **Domain**: regras de negócio e entidades.
- **Application**: casos de uso e DTOs.
- **Infrastructure**: persistência, HTTP, autenticação e integrações.

Essa separação permite baixo acoplamento, maior facilidade de testes e evolução da aplicação.