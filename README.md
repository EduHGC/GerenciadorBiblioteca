# Gerenciador de Biblioteca

Este projeto é um sistema abrangente para o gerenciamento de bibliotecas, focado em otimizar a administração de empréstimos de exemplares, a manutenção de registros (CRUD) de livros e usuários, e a gestão de diferentes perfis de acesso, como e bibliotecários e administrador.

---

## Funcionalidades

O sistema permite:

* **Empréstimo e Devolução de Exemplares:** Gerencie o fluxo de livros emprestados e devolvidos, controlando prazos e status.
* **CRUD de Livros:** Adicione, visualize, atualize e exclua informações sobre os livros disponíveis na biblioteca.
* **Gerenciamento de Usuários:** Cadastre novos usuários, visualize seus dados, atualize informações e gerencie seus históricos de empréstimos.
* **Controle de Bibliotecários:** Gerencie o acesso e as permissões dos bibliotecários, permitindo que eles executem tarefas administrativas essenciais.
* **Sistema de Login:** Dois níveis de acesso para bibliotecario e administradores, garantindo a segurança dos dados.

---

## Primeiros Passos

Siga as instruções abaixo para configurar e executar o Gerenciador de Biblioteca em seu ambiente.

### Pré-requisitos

* **Java Development Kit (JDK):** Certifique-se de ter o JDK instalado. Recomenda-se uma versão compatível com o projeto (por exemplo, JDK 11 ou superior).
* **IDE (Integrated Development Environment):** Utilize uma IDE compatível com Java, como IntelliJ IDEA, Eclipse ou Apache NetBeans.
* **MySQL:** Instale o servidor de banco de dados MySQL em sua máquina.

### Configuração

1.  **Baixe o Projeto:** Clone ou faça o download do repositório do Gerenciador de Biblioteca.

2.  **Configuração do Banco de Dados:**
    * Abra o **MySQL Workbench** ou um cliente MySQL de sua preferência.
    * Execute o script SQL localizado em `data/biblioteca.sql`. Este script irá criar o banco de dados e as tabelas necessárias e popular com alguns dados iniciais.

3.  **Configuração da IDE:**
    * Abra o projeto na sua IDE.
    * Certifique-se de que as dependências do projeto estejam resolvidas (geralmente, a IDE faz isso automaticamente ao importar o projeto). Pode ser necessário configurar o driver JDBC do MySQL no seu projeto se ainda não estiver configurado.

### Execução

1.  **Execute o Arquivo Principal:** Navegue até o pacote `main` e execute o arquivo `Main.java`. Este é o ponto de entrada da aplicação.

2.  **Credenciais Iniciais:** Ao iniciar o sistema, você poderá fazer login com as seguintes credenciais de administrador:
    * **Email:** `adm@biblioteca.edu.br`
    * **Senha:** `Adm12345@`

3.  **Atualização do Administrador:** Após o primeiro login, é **altamente recomendável** que você acesse a seção **"Funcionários"** no sistema e atualize o e-mail, senha e dados cadastrais do administrador para garantir a segurança da sua instalação.

---
