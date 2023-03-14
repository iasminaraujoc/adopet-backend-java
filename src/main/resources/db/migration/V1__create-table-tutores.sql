create table tutores(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    foto varchar(100) not null unique,
    telefone varchar(11) not null unique,
    cidade varchar(100) not null,
    sobre varchar(500) not null,

    primary key(id)

);