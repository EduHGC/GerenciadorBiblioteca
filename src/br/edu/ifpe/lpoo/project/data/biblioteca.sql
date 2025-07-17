use biblioteca;

create table item_acervo(
	id_item int auto_increment primary key,
    titulo varchar(255) not null,
    autor varchar(255) not null,
    editora varchar(255) not null,
    ano_publicacao int not null,
    genero varchar(255) not null,
    idioma varchar(255) not null,
    numero_pagina int not null
);

create table livro(
	id_livro int primary key,
    isbn varchar(13) unique,
    foreign key (id_livro) references item_acervo (id_item)
);

create table exemplar(
	id_exemplar int auto_increment primary key,
    id_item int not null,
    registro varchar(10) unique not null,
    tipo_item enum('LIVRO', 'PERIODICO'),
    status_exemplar enum('DISPONIVEL','EMPRESTADO','RESERVADO', 'DANIFICADO', 'EXTRAVIADO', 'MANUTENCAO'),
    foreign key (id_item) references item_acervo (id_item)
);

create table usuario(
	id_usuario int auto_increment primary key,
    cpf varchar(11) not null unique,
    matricula varchar(30) unique not null,
    nome varchar(255) not null,
    email varchar(255) not null unique,
    telefone varchar (11) not null unique,
    departamento varchar(255) not null,
    tipo_usuario varchar(50) not null,
    status_usuario varchar(50) not null,
    instituicao varchar(255),
    debito decimal(6, 2)
);

create table funcionario(
	id_funcionario int auto_increment primary key,
    cpf varchar(11) unique not null,
    nome varchar(255) not null,
    email varchar(255) not null unique,
    matricula varchar(30) not null,
	tipo_funcionario varchar(50),
    ativo boolean not null,
    status_funcionario varchar(52) not null
);

create table emprestimo(
	id_emprestimo int auto_increment primary key,
    id_exemplar int not null,
    id_usuario int not null,
    id_bibliotecario int not null,
    data_emprestimo date not null,
    data_devolucao date not null,
    data_real_devolucao date,
   	status_emprestimo varchar(50) not null,,
    foreign key (id_exemplar) references exemplar (id_exemplar),
    foreign key (id_usuario) references usuario (id_usuario),
    foreign key (id_bibliotecario) references funcionario (id_funcionario)
);

CREATE TABLE atualizar_sistema (
    id int PRIMARY KEY,
    data_ultima_atualizacao date NOT NULL
);

INSERT INTO atualizar_sistema (id, data_ultima_atualizacao) VALUES (1, '2025-01-01');

