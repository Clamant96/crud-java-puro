CREATE DATABASE desafio_flashsafe;

USE desafio_flashsafe;

CREATE TABLE usuario (
	id bigint auto_increment,
    nome VARCHAR(255) NOT NULL,
    idade int(11) NOT NULL,
    sexo varchar(1) NOT NULL,
    
    constraint pk_usuario primary key (id)
);