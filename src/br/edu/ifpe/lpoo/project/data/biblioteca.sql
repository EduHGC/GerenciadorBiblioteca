create database testelivro;

use testelivro;

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

create table periodico(
	id_periodico int primary key,
    issn varchar(13) unique,
    volume int not null,
    edicao int not null,
    foreign key (id_periodico) references item_acervo (id_item)
);

create table exemplar(
	id_exemplar int auto_increment primary key,
    id_item int not null,
    registro varchar(10) unique not null,
    tipo_item enum('LIVRO', 'PERIODICO'),
    status_exemplar enum('DISPONIVEL','EMPRESTADO','RESERVADO', 'DANIFICADO', 'EXTRAVIADO', 'EM_MANUTENCAO'),
    foreign key (id_item) references item_acervo (id_item)
);

create table ebook(
	id_ebook int primary key,
    isbn varchar(13) unique,
    url varchar(255) not null,
    formato_digital enum('PDF', 'EPUB', 'MOBI') not null,
    foreign key (id_ebook) references item_acervo (id_item)
);
create table licenca_ebook(
	id_licenca int auto_increment primary key,
    id_ebook int,
    acessos_ativos int,
    max_acesso int,
    codigo varchar(10) unique,
    data_expiracao date not null,
	data_aquisicao date not null,
    status_ebook enum('DISPONIVEL', 'INDISPONIVEL'),
    foreign key (id_ebook) references ebook (id_ebook)
);

create table pessoa(
	cpf varchar(11) primary key,
    nome varchar(255) not null,
    email varchar(255) unique not null
);

create table membro(
	id_membro int auto_increment primary key,
    cpf varchar(11) not null,
    tipo_membro enum('ALUNO', 'PROFESSOR', 'PESQUISADOR'),
    matricula varchar(30) unique not null,
    debito decimal(6, 2),
    status_membro enum('ATIVO', 'INATIVO', 'SUSPENSO', 'BLOQUEADO'),
    foreign key (cpf) references pessoa (cpf)
);

create table funcionario(
	id_funcionario int auto_increment primary key,
    cpf varchar(11) not null,
    matricula varchar(30) not null,
    tipo_funcionario enum ('BIBLIOTECARIO', 'CHEFE'),
    foreign key (cpf) references pessoa (cpf)
);

create table emprestimo(
	id_emprestimo int auto_increment primary key,
    id_exemplar int not null,
    id_membro int not null,
    id_bibliotecario int not null,
    data_emprestimo date not null,
    data_devolucao date not null,
    data_real_devolucao date,
    status_emprestimo enum('ABERTO', 'FINALIZADO', 'ATRASADO'),
    foreign key (id_exemplar) references exemplar (id_exemplar),
    foreign key (id_membro) references membro (id_membro),
    foreign key (id_bibliotecario) references funcionario (id_funcionario)
);

CREATE TABLE reserva(
	id_reserva INT AUTO_INCREMENT PRIMARY KEY,
    id_membro INT NOT NULL,
    id_exemplar int not null,
    id_funcionario int not null,
	data_reserva Date not null,
	data_expiracao Date not null,
    status_reserva enum ('ATIVA', 'EXPIRADA', 'FINALIZADA'),
    foreign key (id_membro) references membro (id_membro),
    foreign key (id_exemplar) references exemplar (id_exemplar),
    foreign key (id_funcionario) references funcionario (id_funcionario)
);

create table multa(
	id_multa int auto_increment primary key,
    id_emprestimo int not null,
    data_multa date not null,
    valor decimal(6,2) not null,
    status_multa enum('ABERTO', 'PAGO'),
    foreign key (id_emprestimo) references emprestimo (id_emprestimo)
);