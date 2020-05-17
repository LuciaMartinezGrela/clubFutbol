create database clubFutbol;
use clubFutbol;
#drop database clubFutbol;

create table parentesco(
idParentesco int primary key not null auto_increment,
tipo char (6)
);

insert into parentesco values (default, "padre");
insert into parentesco values (default, "madre");
insert into parentesco values (default, "abuela");
insert into parentesco values (default, "abuelo");
insert into parentesco values (default, "tutor");

#drop table parentesco;
#select * from parentesco;

create table tutor(
dniResponsable char(9) primary key,
nombreTutor char (15),
primerApTutor char(15),
segundoApTutor char(15),
direccionPostal char(40),
email char(30),
tlf1 char(9) not null,
tlf2 char(9)

);
insert into tutor values('123456789', 'Sara', 'Gómez', 'Pasantes', 'cuesta de la tapia','sara@gmial.com', '666333111','666333112');
insert into tutor values('123456780', 'Cris', 'Cupeiro', 'Pérez', 'perillo','cris@gmail.com', '666333115','666333116');


create table rolesUsuarios(
usuario char (13),
clave char(13),
tipoUser char(13)
);
insert into rolesUsuarios values('raulvazquez','marearoja', 'usuario');
insert into rolesUsuarios values('l','1','admin');

#select usuario, clave, tipoUser from rolesUsuarios;

# el formato time es ‘HH:MM:SS’
create table entrenamientos(
idEntrenamiento int primary key not null auto_increment,
numCampo int,
dias char (20),
horaInicial time,
horaFinal time,
entrenador1 char (15),
entrenador2 char (15),
asistente char (15)
);

insert into entrenamientos values(default, 1, 'lunes y miércoles', '17:45:00','19:00:00', "Grela", "Tojo", "Antía");
insert into entrenamientos values(default, 2, 'miércoles y viernes', '21:00:00', '22:30:00', 'Grela', 'Fisio','no hay');
insert into entrenamientos values(default, 1, 'miércoles y viernes', '19:30:00', '21:00:00', 'Alicia', 'Claudia','no hay');


create table categorias(
idCat int primary key not null auto_increment,
nombreCat char(10),
temporada char(9),
idEntrenamiento int,
foreign key (idEntrenamiento) references entrenamientos(idEntrenamiento)
);

insert into categorias values(default, 'fem f11', '2019-2020',2);
insert into categorias values(default, 'benjamines','2019-2020',1);
insert into categorias values (default, 'alevines a','2019-2020',3);

create table lateralidad(
idPiernaDominante int primary key not null auto_increment,
predominio char (11)
);

insert into lateralidad values(default, 'diestro');
insert into lateralidad values(default, 'zurdo');
insert into lateralidad values(default, 'ambidiestro');



create table posiciones(
idPosicion int primary key not null auto_increment,
nombrePos char(30)
);

insert into posiciones values(default,'defensa');
insert into posiciones values(default,'mediocentro');
insert into posiciones values(default,'delantero');
insert into posiciones values(default,'portero');
insert into posiciones values(default,'lateral izquierdo');
insert into posiciones values(default,'lateral derecho');
insert into posiciones values(default,'media punta');
insert into posiciones values(default,'extremo');
insert into posiciones values(default,'carrilero');

#select * from posiciones;
#delete from posiciones where idPosicion=5;



create table futbolista(
dniFutbolista char(9)primary key,
nombre char(15),
primerApellido char (15),
segundoApellido char (15),
sexo char (9),
fechadeNacimiento date,
centroEscolar char (40),
alergias char(100),
lesiones text (500),
caracteristicas text (600),
idPiernaDominante int ,
idCategoria int,
idPosicion int,
estado char(4),
urlImagen char(60),
foreign key (idPiernaDominante) references lateralidad (idPiernaDominante),
foreign key (idCategoria) references categorias (idCat),
foreign key (idPosicion) references posiciones (idPosicion)
);


update futbolista set urlImagen='/imgJugador/anxo-couto.jpg' where dniFutbolista='111111111';
update futbolista set urlImagen='/imgJugador/marta-ponte.jpg' where dniFutbolista='222222222';
select*from futbolista;

insert into futbolista values('111111111','Anxo','Couto', 'Gómez','masculino','08-12-3',
 'masculino de plaza pontevedra','ninguna','fractura de húmero', 'niño muy intenso, competitivo y muy buen compañero',1,3,8, 'alta','/imgJugador/anxo-couto.jpg');
 
 insert into futbolista values('222222222','Marta','Ponte', 'Cupeiro','femenino','08-12-10',
 'valle inclan','ninguna','distensión de rodilla sin denominación concreta', 'niña muy trabajadora y luchadora',1,3,2, 'alta', '/imgJugador/marta-ponte.jpg');
 insert into futbolista values('53192037','Lucia','Martínez', 'Grela','femenino','87-03-09',
 'chios','ninguna','menisco', 'le pesa el culo',1,1,2, 'alta','');
select*from categorias;
select*from futbolista;
#alter table futbolista add estado char(4);

create table tutorXfutbolista(
id int not null auto_increment primary key,
dniFutbolista char(9),
dniResponsable char(9),
idParentesco int,

foreign key (dniFutbolista) references futbolista (dniFutbolista),
foreign key (dniResponsable) references tutor (dniResponsable),
foreign key (idParentesco) references parentesco (idParentesco)
);
#drop database clubFutbol;
insert into tutorXfutbolista values(default,'111111111','123456789',2);
insert into tutorXfutbolista values(default,'222222222','123456780',2);

select*from tutorXfutbolista;



#buscar un jugador por categoría
select fut.dniFutbolista, fut.estado,fut.nombre, fut.primerApellido, fut.segundoApellido, fut.sexo,fut.fechadeNacimiento, fut.centroEscolar, 
fut.alergias, fut.lesiones, fut.caracteristicas, lat.predominio, pos.nombrePos
from categorias cat, futbolista fut, lateralidad lat, posiciones pos
where cat.idCat= fut.idCategoria
and lat.idPiernaDominante=fut.idPiernaDominante
and pos.idPosicion=fut.idPosicion
and cat.nombreCat='alevines a';

use clubFutbol;

select fut.alergias from futbolista fut where fut.dniFutbolista='111111111';
select idPiernaDominante from lateralidad where predominio='zurdo';

select fut.dniFutbolista, fut.nombre, fut.primerApellido, fut.segundoApellido, fut.fechadeNacimiento
from futbolista fut
where fut.nombre='marta';

select dniResponsable from tutorxfutbolista where dniFutbolista='222222222';