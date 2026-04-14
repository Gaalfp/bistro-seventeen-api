# 🍽️ Bistro Seventeen API - Tech Challenge (Fase 1)

Este repositório contém a implementação do backend do **Bistro Seventeen**, um sistema unificado de gestão para restaurantes. O projeto é parte do *Tech Challenge (Fase 1)* da Pós-Graduação em Arquitetura e Desenvolvimento Java da FIAP.

O domínio central desta fase é o **Gerenciamento de Usuários**, englobando perfis de Donos de Restaurante e Clientes, com regras estritas de segurança, criptografia de senhas e persistência relacional.

##  Arquitetura

O projeto foi construído utilizando os conceitos de **Arquitetura Hexagonal (Ports and Adapters)** e **Clean Architecture**, visando o alto desacoplamento e a testabilidade das regras de negócio.

* **Core (Domínio e Use Cases):** Contém as regras de negócio puras (validação de login, unicidade, inativação), totalmente isoladas de frameworks externos. As soluções foram desenhadas baseadas na especificação da JDK 17.
* **Ports (Portas):** Interfaces de entrada (`InputPorts`) e saída (`OutputPorts`) que ditam os contratos do sistema.
* **Adapters (Adaptadores):** * *Input:* Controladores REST (`UsuarioController`, `AuthController`).
    * *Output:* Repositórios Spring Data JPA (`UsuarioAdapterRepository`) e serviços de criptografia (`BCryptAdapter`).

## Tecnologias Utilizadas

* **Linguagem:** Java (JDK 21)
* **Framework:** Spring Boot 3.x
* **Banco de Dados:** PostgreSQL
* **Segurança:** BCrypt (Spring Security Crypto)
* **Mapeamento:** MapStruct
* **Testes:** JUnit 5, Mockito, JaCoCo
* **Documentação:** Swagger (SpringDoc OpenAPI)
* **Infraestrutura:** Docker & Docker Compose

## Pré-requisitos e Execução

Para executar o projeto localmente, você precisará do **Docker** e do **Docker Compose** instalados.

1. Clone este repositório:
   ```bash
   git clone https://github.com/Gaalfp/bistro-seventeen-api.git
   cd bistro-seventeen-api
2. Suba a infraestrutura do banco de dados:
   
   O banco de dados PostgreSQL está configurado para inicializar com scripts de seed automáticos (01-create-schema.sql até 04-creat-users-test.sql).   
   ```bash
   cd docker
   docker-compose up -d
   
3. Execute a aplicação:

   Pela sua IDE de preferência ou via Maven wrapper:
   ```bash
   ./mvnw spring-boot:run


## Documentação da API (Swagger)

A API foi documentada seguindo o padrão OpenAPI 3.0. Quando a aplicação estiver em execução, acesse a interface interativa do Swagger através do link:

 http://localhost:8080/swagger-ui/index.html
 
## Cobertura de Testes e Postman

O projeto foi submetido a rigorosos testes unitários focados na camada Core, garantindo o funcionamento blindado dos Casos de Uso e regras de negócio.

Para visualizar o relatório de cobertura do JaCoCo, acesse: target/site/jacoco/index.html após rodar um mvn test.


Testes de Integração E2E (Postman):
Na raiz do projeto (ou na pasta /docs), encontra-se o arquivo bistro-seventeen-collection.json. Ele contém a suíte completa de testes para importação no Postman, incluindo testes de Cadastro (Sucesso e Erro), Atualização, Troca de Senha, Busca por Termo Parcial e Autenticação. A collection possui scripts de automação que populam o ID do usuário em variáveis de ambiente dinamicamente.


## Autores

1. Felipe Sora RM370766
2. Gabriel Pinto RM374005
3. Jeniffer Bandeira RM371396
4. Marco Antônio Gomes RM372323
5. Ricardo Aguirra RM373817