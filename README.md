# API de Pedidos

API REST desenvolvida com **Java**, **Spring Boot**, **Spring Security**, **JWT**, **JPA/Hibernate** e **SQLite**, seguindo os princípios da **Clean Architecture**.

## Tecnologias

- Java 24
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
- Login com autenticação JWT
- Cadastro de produtos
- Listagem paginada de produtos
- Criação de pedidos
- Consulta de pedidos por ID

---

## Arquitetura

O projeto foi desenvolvido seguindo os princípios da **Clean Architecture**, separando responsabilidades entre as camadas da aplicação.

```
src
├── app
│   ├── dtos
│   └── usecase
│
├── domain
│   ├── entities
│   ├── repository
│   ├── gateway
│   ├── exceptions
│   └── valuesobject
│
├── infra
│   ├── auth
│   ├── http
│   ├── persistence
│   └── security
│
├── config
└── ApiApplication
```

### Fluxo da requisição

```
Cliente
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

## Autenticação

Após realizar o login, envie o token JWT em todas as rotas protegidas.

```http
Authorization: Bearer SEU_TOKEN
```

---

# Endpoints

## POST `/register`

### Body

```json
{
    "name": "João",
    "email": "joao@gmail.com",
    "password": "12345678"
}
```

---

## POST `/login`

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

## POST `/product`

### Headers

```http
Authorization: Bearer TOKEN
```

### Body

```json
{
    "name": "Notebook",
    "price": 4500,
    "description": "Lenovo"
}
```

---

## GET `/product?page=0&size=10`

### Headers

```http
Authorization: Bearer TOKEN
```

### Query Parameters

| Parâmetro | Descrição |
|-----------|-----------|
| page | Número da página |
| size | Quantidade de registros |

---

## POST `/orders`

### Headers

```http
Authorization: Bearer TOKEN
```

### Body

```json
[
    {
        "productId": 1,
        "quantity": 2,
        "unitPrice": 4500
    },
    {
        "productId": 2,
        "quantity": 1,
        "unitPrice": 1200
    }
]
```

---

## GET `/orders/{id}`

### Exemplo

```http
GET /orders/1
```

### Headers

```http
Authorization: Bearer TOKEN
```

### Resposta

```json
{
    "orderId": 1,
    "products": [
        {
            "productId": 1,
            "name": "Notebook",
            "price": 4500,
            "description": "Lenovo",
            "quantity": 2
        }
    ],
    "totalPrice": 9000,
    "totalQuantity": 2
}
```

---

# Executando o projeto

Clone o repositório.

```bash
git clone <url-do-repositorio>
```

Entre na pasta do projeto.

```bash
cd api
```

Execute a aplicação.

```bash
./mvnw spring-boot:run
```

ou

```bash
mvn spring-boot:run
```

A API estará disponível em:

```
http://localhost:8080
```

---

# Estrutura do projeto

```
src
├── app
│   ├── dtos
│   └── usecase
│
├── config
│
├── domain
│   ├── entities
│   ├── exceptions
│   ├── gateway
│   ├── repository
│   └── valuesobject
│
├── infra
│   ├── auth
│   ├── http
│   ├── persistence
│   └── security
│
└── ApiApplication
```

---

# Objetivos do projeto

Este projeto foi desenvolvido para praticar conceitos como:

- Clean Architecture
- Spring Security
- JWT
- JPA/Hibernate
- Value Objects
- Repository Pattern
- Casos de Uso (Use Cases)
- Separação entre domínio e infraestrutura