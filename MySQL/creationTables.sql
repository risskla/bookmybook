#Creation des tables BookMyBook :

drop table Book;
create table Book (
	id int(10) auto_increment, 
	titre varchar(255),
	auteur varchar(50),
	editeur varchar(50),
	isbn varchar(20), 
	pays varchar(50),
	genre varchar(255),
	anneePubli int(4),
	resume text, 
primary key(id));

#Exemples :
INSERT INTO Book VALUES (null,'Harry Potter I','J. K. Rowling','Bloomsbury Publishing','0000000000000','England','Fantastique',1997,'Harry has a scare');
INSERT INTO Book VALUES (null,'Harry Potter II','J. K. Rowling','Bloomsbury Publishing','0000000000000','England','Fantastique',1997,'Harry has a scare');
INSERT INTO Book VALUES (null,'Harry Potter III','J. K. Rowling','Bloomsbury Publishing','0000000000000','England','Fantastique',1997,'Harry has a scare');

drop table User;
create table User (
	id int(10) auto_increment,
	login varchar(20),
	mdp varchar(20),
	mail varchar(50), 
	role int(1),
	primary key(id)

);

#Exemples :
INSERT INTO User VALUES (null,'mey68','0000','ulysse.meyer@gmail.com','0');
INSERT INTO User VALUES (null,'admin','admin','admin@gmail.com','1');


drop table Evaluation;
create table Evaluation (
	id int(10) auto_increment,
	livreId int(10), 
	userId int(10), 
	note int(1),
	qualite int(1),
	interet int(1),
	lecture int(1),
	souhaitAuteur int(1),
	recommand int(1),
	primary key (id), 
	foreign key (livreId) references Book(id) on delete cascade,
	foreign key (userId) references User(id) on delete cascade
);

#Exemples :
INSERT INTO Evaluation VALUES (null,1,1,4,4,4,4,4,4);

drop table MatchReader;
create table MatchReader (
	id int(10) auto_increment,
	userSourceId int(10), 
	userPlusProcheId int(10), 
	userPlusLoinId int(10),
	evaluationId int(10),
	primary key (id), 
	foreign key (userSourceId) references User(id) on delete cascade,
	foreign key (userPlusProcheId) references User(id) on delete cascade,
	foreign key (userPlusLoinId) references User(id) on delete cascade,
	foreign key (evaluationId) references Evaluation(id) on delete cascade
);

drop table MatchBook;
create table MatchBook (
	id int(10) auto_increment,
	userSourceId int(10), 
	livreSuggereId int(10), 
	evaluationId int(10),
	primary key (id), 
	foreign key (userSourceId) references User(id) on delete cascade,
	foreign key (livreSuggereId) references Book(id) on delete cascade,
	foreign key (evaluationId) references Evaluation(id) on delete cascade
);


commit;