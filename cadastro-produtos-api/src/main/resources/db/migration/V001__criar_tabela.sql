create table produtos(
	id bigint not null auto_increment,
	nome_do_item varchar(30) not null,
	unidade_de_medida varchar(20) not null,
	quantidade bigint,
	preco float not null,
	produto_perecivel boolean not null,
	data_de_validade date,
	data_de_fabricacao date not null,
	primary key(id)
)engine=InnoDB default charset=utf8;