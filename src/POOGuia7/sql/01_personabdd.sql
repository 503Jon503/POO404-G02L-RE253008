-- =========================================================
-- Script de la Guia de Laboratorio #7 (Procedimiento, Parte 1 y Parte 5)
-- Base de datos usada por el paquete "modelos" y "conexiones"
-- =========================================================

create database if not exists personabdd;
use personabdd;

create table ocupaciones(
  id_ocupacion int(11) not null auto_increment,
  ocupacion varchar(50) not null,
  primary key (`id_ocupacion`)
) engine=innodb auto_increment=4 default charset=latin1;

create table persona (
  id_persona int(11) not null auto_increment,
  nombre_persona varchar(100) not null,
  edad_persona int(11) not null,
  telefono_persona varchar(9) not null,
  sexo_persona varchar(50) not null,
  id_ocupacion int(11) not null,
  fecha_nac date not null,
  primary key (`id_persona`),
  key id_ocupacion (id_ocupacion)
) engine=innodb auto_increment=16 default charset=latin1;

alter table persona
  add constraint persona_ibfk_1
  foreign key (id_ocupacion)
  references ocupaciones (id_ocupacion);

insert into `ocupaciones` (`id_ocupacion`, `ocupacion`)
values (1, 'doctor'), (2, 'emprendedor'), (3, 'profesor');

insert into persona (id_persona, nombre_persona, edad_persona, telefono_persona,
                      sexo_persona, id_ocupacion, fecha_nac) values
(2, 'alejandro pineda', 45, '7722-4455', 'masculino', 1, '1999-01-05'),
(12, 'fernando calderón', 21, '7667-7890', 'masculino', 2, '2001-05-07'),
(15, 'emerson torres', 22, '7123-9800', 'masculino', 3, '1999-08-03');

-- Parte 5: diferencia entre campos nulos y vacios
create table Empleados (
  Codigo int primary key,
  Nombre varchar(25),
  Apellidos varchar(25),
  Telefono varchar(9)
);

insert into Empleados values
(1,'Roberto Mario','Rodríguez','2589-8585'),
(2,'Maria Gabriela','Carranza','7895-7858'),
(3,'José Fernando','Martinez','2698-4576');

insert into empleados values(5,'','Torres',null);
insert into empleados values(6,null,'Rodriguez',null);
