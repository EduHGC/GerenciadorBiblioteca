# Gerenciador de Biblioteca

Sistema para gerenciamento de bibliotecas, com foco em controle de empréstimos de exemplares, cadastro e atualização de dados via operações CRUD, além do gerenciamento de usuários e funcionários (bibliotecários).

## Descrição

O Gerenciador de Biblioteca é uma aplicação que permite:

- Realizar empréstimos e devoluções de exemplares;
- Executar operações CRUD (criar, ler, atualizar, deletar) para livros, usuários e funcionários;
- Controlar o acesso de bibliotecários e gerenciar seus dados cadastrais;
- Manter um histórico de movimentações para cada usuário;
- Realizar a administração geral do sistema por meio de um usuário com perfil de administrador.

## Como utilizar

### 1. Instalar as dependências necessárias

- Baixe e instale o **JDK** (Java Development Kit) em sua máquina;
- Utilize uma IDE compatível com projetos Java, como **Eclipse**, **IntelliJ IDEA** ou **NetBeans**;
- Instale o **MySQL** como sistema gerenciador de banco de dados (SGBD).

### 2. Preparar o banco de dados

- No MySQL, crie um banco de dados com o nome `sgdb`;
- Execute o script localizado em `pacote data`, no arquivo `biblioteca.sql`, para criar as tabelas e dados iniciais.

### 3. Executar a aplicação

- Abra o projeto na IDE de sua preferência;
- Localize e execute o arquivo `Main`, que está no pacote `main`.

### 4. Acessar o sistema

- Faça login utilizando o seguinte usuário administrador padrão:

  - **Email:** `adm@biblioteca.edu.br`
  - **Senha:** `Adm12345@`

### 5. Atualizar dados do administrador

- Após o primeiro login, acesse a seção **Funcionários**;
- Atualize o email, senha e os dados cadastrais do administrador conforme necessário para maior segurança.

---

Este sistema é ideal para bibliotecas que buscam uma solução simples para gerenciar acervos, usuários e movimentações de forma prática e segura.
