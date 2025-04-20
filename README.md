
# ✅ Desafio Sea – ToDo List com Cadastro de Clientes

Projeto desenvolvido como parte do desafio técnico da Sea. A aplicação permite o cadastro, listagem, edição e exclusão de clientes, além de adicionar e-mails/telefones individualmente e realizar a consulta de endereços via CEP através do serviço do [ViaCEP](https://viacep.com.br).

---

## ⚙️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **Spring Security + JWT**
- **H2 Database (dev)**
- **Maven**
- **Docker**
- **Swagger/OpenAPI 3**
- **Mockito + JUnit 5**

---

## 🚀 Funcionalidades

- Autenticação com JWT (`/login`)
- Cadastro de clientes com:
  - Nome, CPF, endereço, telefones e e-mails
- Adição de e-mail ou telefone de forma individual
- Validação automática de campos
- Consulta de CEP via API do ViaCEP
- Proteção de endpoints com perfis (`ADMIN` e `USER`)
- Tratamento global de exceções com mensagens claras
- Documentação completa com Swagger

---

## 📂 Estrutura do Projeto

A arquitetura segue os princípios da **Arquitetura Hexagonal (Ports & Adapters)**.

```
src/
├── main/
│   ├── java/
│   │   └── com.todolist.sea/
│   │       ├── adapter/
│   │       │   ├── in/
│   │       │   │   └── web/
│   │       │   │       ├── controller/              → Controllers REST (Cliente, Endereço, Auth)
│   │       │   │       ├── dto/                     → Data Transfer Objects (Entrada e Saída)
│   │       │   │       ├── mapper/                  → Conversores entre DTOs e Domínio
│   │       │   │       └── exception/               → Handler global de exceções
│   │       │   └── out/
│   │       │       ├── persistence/
│   │       │       │   ├── entity/                  → Entidades JPA (banco de dados)
│   │       │       │   ├── mapper/                  → Conversores JPA ↔ Domínio
│   │       │       │   └── repository/              → Interfaces Spring Data JPA
│   │       │       └── security/                    → Utilitários JWT e serviço de autenticação
│   │       ├── application/
│   │       │   ├── port/
│   │       │   │   ├── in/                          → Casos de uso (input ports)
│   │       │   │   └── out/                         → Interfaces de acesso externo (gateways)
│   │       │   └── usecase/                         → Implementações dos casos de uso
│   │       ├── config/                              → Configurações gerais, segurança e Swagger
│   │       ├── domain/                              → Modelo de domínio (regras de negócio)
│   │       └── BackEndSeaApplication.java           → Classe principal do Spring Boot
│
├── resources/
│   ├── application.properties                        → Arquivo de configuração da aplicação
│   ├── static/                                       → Arquivos estáticos (se aplicável)
│   └── templates/                                    → Templates para view engines (ex: Thymeleaf)
│
└── test/
    └── java/
        └── com.todolist.sea/                         → Testes unitários e de integração
```

---

## 📄 Como Executar o Projeto

### ✅ Pré-requisitos

- Java 17+
- Maven
- Docker (opcional)

### 🔨 Rodar localmente com Maven

```bash
# Na raiz do projeto
mvn clean install
```
./mvnw spring-boot:run


A aplicação subirá em: [http://localhost:8080](http://localhost:8080)

### 🐳 Rodar com Docker

```bash
docker build -t todolist-sea .
docker run -p 8080:8080 todolist-sea
```

---

## 🔐 Endpoints protegidos

Todos os endpoints `/clientes/**` requerem token de acesso.

Use `/login` com:

```json
{
  "login": "admin",
  "senha": "123qwe!@#"
}
ou
{
  "login": "usuario",
  "senha": "123qwe123"
}
```


---

## 📑 Swagger (Documentação)

Acesse a documentação em:  
📌 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---


## 📬 Contato

Projeto desenvolvido por **Edson Matheus Augusto Sousa Impelliziere** para a avaliação técnica da Sea.

---
