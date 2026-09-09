-- =========================================================
-- Base de datos personabdd, requerida por la Guia de Laboratorio #8.
-- Es la misma BDD creada en la Guia #7 (JDBC); se incluye aqui de nuevo
-- para que este proyecto sea autocontenido.
-- =========================================================

create database if not exists personabdd;
use personabdd;

create table if not exists ocupaciones(
  id_ocupacion int(11) not null auto_increment,
  ocupacion varchar(50) not null,
  primary key (`id_ocupacion`)
) engine=innodb auto_increment=4 default charset=latin1;

create table if not exists persona (
  id_persona int(11) not null auto_increment,
  nombre_persona varchar(100) not null,
  edad_persona int(11) not null,
  telefono_persona varchar(9) not null,
  sexo_persona varchar(50) not null,
  id_ocupacion int(11) not null,
  fecha_nac date not null,
  primary key (`id_persona`),
  key id_ocupacion (id_ocupacion),
  constraint persona_ibfk_1 foreign key (id_ocupacion) references ocupaciones (id_ocupacion)
) engine=innodb auto_increment=16 default charset=latin1;

insert into ocupaciones (id_ocupacion, ocupacion)
values (1, 'doctor'), (2, 'emprendedor'), (3, 'profesor')
on duplicate key update ocupacion=values(ocupacion);

insert into persona (id_persona, nombre_persona, edad_persona, telefono_persona,
                      sexo_persona, id_ocupacion, fecha_nac) values
(2, 'alejandro pineda', 45, '7722-4455', 'masculino', 1, '1999-01-05'),
(12, 'fernando calderón', 21, '7667-7890', 'masculino', 2, '2001-05-07'),
(15, 'emerson torres', 22, '7123-9800', 'masculino', 3, '1999-08-03')
on duplicate key update nombre_persona=values(nombre_persona);
