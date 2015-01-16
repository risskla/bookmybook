#Creation des tables BookMyBook :

#drop table Book;
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

#drop table User;
create table User (
	id int(10) auto_increment,
	login varchar(20),
	mdp varchar(20),
	mail varchar(50), 
	role int(1),
    nom varchar(50),
	prenom varchar(50),
	age int(100),
	sexe varchar(10),
	adresse varchar(255),
	codepostale int(5),
	ville varchar(255),
	telephone varchar(20),
	primary key(id)

);

#Exemples :
INSERT INTO User VALUES (null,'mey68','0000','ulysse.meyer@gmail.com','0','Meyer', 'Ulysse', '22', 'H', '21 rue du dépôt', '60280', 'Margny-Les-Compiègne', '0624567682');
INSERT INTO User VALUES (null,'admin','admin','admin@gmail.com','1', 'Jean', 'Troll', '14', 'H', 'rue des ours', '75003', 'Paris', '0666666666');


#drop table Evaluation;
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
#INSERT INTO Evaluation VALUES (null,1,1,4,4,4,4,4,4);

#drop table MatchReader;
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

#drop table MatchBook;
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

#drop table AdminParameters;
create table AdminParameters (
	id int(10) auto_increment,
    algoMatchBook int(1), 
	algoMatchReader int(1), 
	dateSaisie date,
	primary key (id)
);

create table calculMatchUser1 (
user int(10), 
book int(10), 
ecartNoteGlobale int(1)
);

create table calculMatchUser2 (
user int(10), 
moyenneEcart double
);

commit;