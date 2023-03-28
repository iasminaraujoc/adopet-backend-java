create table pets(

    id bigint not null auto_increment,
    abrigo_id bigint not null,
    nome varchar(100) not null,
    idade varchar(20) not null,
    porte varchar(7) not null,
    descricao varchar(500) not null,
    cidade varchar(100) not null,

    primary key(id),
    constraint FK_PetAbrigo foreign key(abrigo_id) references abrigos(id)

);