create table adocoes(

    id bigint not null auto_increment,
    animal_id bigint not null,
    tutor_id bigint not null,
    data_adocao date not null,

    primary key(id),
    constraint FK_AdocaoPet foreign key(animal_id) references pets(id),
    constraint FK_AdocaoTutor foreign key(tutor_id) references tutores(id)

);