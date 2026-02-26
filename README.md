# 📚 LiterAlura - Catálogo de Livros

Este é um projeto desenvolvido como parte do desafio da formação Java e Spring Framework do programa **ONE (Oracle Next Education)** em parceria com a **Alura**. 

O LiterAlura é uma aplicação de linha de comando (CLI) que atua como um catálogo interativo de livros. Ele consome a [API Gutendex](https://gutendex.com/) para buscar informações sobre livros e autores, e persiste esses dados em um banco de dados relacional local para consultas e geração de estatísticas.

## ⚙️ Funcionalidades

A aplicação oferece um menu interativo no console com as seguintes opções:

1. **Buscar livro pelo título:** Faz uma requisição à API Gutendex, exibe os dados do livro e salva o livro e seu respectivo autor no banco de dados (evitando duplicidade de autores).
2. **Listar livros registrados:** Retorna todos os livros que já foram buscados e salvos no banco de dados local.
3. **Listar autores registrados:** Retorna todos os autores extraídos dos livros salvos no banco de dados.
4. **Listar autores vivos em um determinado ano:** Utiliza *Derived Queries* do Spring Data JPA para filtrar e listar os autores armazenados que estavam vivos em um ano específico informado pelo usuário.
5. **Listar livros em um determinado idioma:** Filtra os livros salvos no banco por idioma (ex: `pt`, `en`, `es`, `fr`) e exibe a quantidade total (estatística) de livros encontrados naquele idioma.

## 🛠️ Tecnologias Utilizadas

* **Java 17+**
* **Spring Boot 3**
* **Spring Data JPA / Hibernate**
* **PostgreSQL**
* **Jackson Databind**
* **Java HttpClient**
* **Maven**

## 🚀 Como Executar o Projeto

### Pré-requisitos
* Java JDK 17 ou superior instalado.
* PostgreSQL instalado e rodando localmente.
* IDE de sua preferência (IntelliJ IDEA, Eclipse, VS Code).

### Passo a Passo

1. **Clone o repositório:**
   ```bash
   git clone [https://github.com/SEU_USUARIO/literalura.git](https://github.com/SEU_USUARIO/literalura.git)
