# 📋 Gerenciador de Tarefas ESIG

Este é um projeto *fullstack* desenvolvido como parte do Desafio Técnico para a vaga de desenvolvedor na ESIG. O sistema consiste em uma aplicação web para o gerenciamento de tarefas, permitindo o cadastro, edição, remoção e conclusão de atividades, com um sistema seguro de autenticação.

O projeto foi arquitetado com foco em boas práticas de engenharia de software, separação de responsabilidades e uso de tecnologias modernas no ecossistema Java e JavaScript.

---

## 🚀 Tecnologias Utilizadas

### Backend (API REST)
* **Java 21** (LTS)
* **Spring Boot 3.4**: Framework principal.
* **Spring Security + JWT**: Autenticação *stateless* segura via Tokens.
* **Spring Data JPA**: Camada de persistência e ORM.
* **PostgreSQL**: Banco de dados relacional.
* **Hibernate**: Implementação JPA.
* **Lombok**: Redução de *boilerplate* code.
* **Swagger / OpenAPI**: Documentação automática da API.
* **Maven**: Gerenciamento de dependências.

### Frontend (SPA)
* **Angular 18+**: Framework web moderno.
* **Standalone Components**: Arquitetura modular sem `NgModules`.
* **TypeScript**: Tipagem estática e segurança no código.
* **Bootstrap 5**: Estilização responsiva e componentes visuais.
* **RxJS**: Programação reativa para consumo de APIs.

### DevOps & Deploy
* **Docker**: Containerização da aplicação (Backend e Frontend).
* **Render**: Plataforma de Nuvem utilizada para o deploy.

---

## ⚙️ Funcionalidades

### 🔐 Autenticação e Segurança
* **Login e Registro**: Endpoints públicos para criação de conta e acesso.
* **Proteção via JWT**: Todas as rotas de negócio são protegidas. O token deve ser enviado no cabeçalho `Authorization` de cada requisição.
* **Criptografia**: Senhas salvas com hash seguro (**BCrypt**).
* **CORS Configurado**: Permite comunicação segura entre o Frontend e o Backend hospedados em domínios diferentes.

### 📝 Gestão de Tarefas
* **CRUD Completo**: Criar, Ler, Atualizar e Deletar tarefas.
* **Listagem com Filtros Dinâmicos**: Busca inteligente por ID, Título, Responsável e Situação (utilizando *JPA Specifications*).
* **Conclusão Rápida**: Funcionalidade para marcar tarefas como "Concluída" diretamente na listagem.
* **Validações**: Campos obrigatórios e regras de negócio validadas no Backend e Frontend.

---

## 🏗️ Arquitetura do Projeto

O projeto segue uma arquitetura em camadas bem definida:

### Backend
1.  **Controllers**: Exposição dos endpoints REST (`/api/tarefas`, `/auth`).
2.  **DTOs (Records)**: Transferência de dados segura, desacoplando a API das entidades do banco.
3.  **Services**: Regras de negócio, validação de tokens e lógica de segurança.
4.  **Repositories**: Acesso a dados com **Spring Data JPA** e **Specifications** para consultas dinâmicas.

### Frontend
1.  **Pages**: Componentes de visualização (Login, Lista, Formulário).
2.  **Services**: Centralizam a comunicação HTTP com a API.
3.  **Interceptors**: Injeção automática do Token JWT em todas as requisições.
4.  **Guards**: Proteção de rotas no navegador (impede acesso sem login).

---

## Projeto já conta com deploy
1. Swagger - https://gerenciadordetarefas-f8g8.onrender.com/swagger-ui/index.html
2. Projeto Completo - https://gerenciadordetarefas-front.onrender.com/

👨‍💻 Autor
Desenvolvido por Samuel

https://www.linkedin.com/in/samuel-gomes-dev/

https://github.com/Samuelz47
