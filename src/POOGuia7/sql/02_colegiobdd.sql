-- =========================================================
-- Script para los Ejercicios Complementarios (seccion IV de la guia)
-- Nombre de BDD elegido libremente (la guia lo permite): colegiobdd
-- =========================================================

create database if not exists colegiobdd;
use colegiobdd;

create table alumno (
  Cod_alumno int primary key,
  Nombre varchar(80) not null,
  Apellido varchar(80) not null,
  Edad int not null,
  Direccion varchar(100)
);

create table materia (
  Cod_materia int primary key,
  Nombre varchar(25) not null,
  Descripcion varchar(100)
);

-- Tabla intermedia N a N entre alumno y materia
create table alumno_materia (
  Cod_alumno int not null,
  Cod_materia int not null,
  primary key (Cod_alumno, Cod_materia),
  constraint fk_am_alumno foreign key (Cod_alumno) references alumno (Cod_alumno)
      on delete cascade,
  constraint fk_am_materia foreign key (Cod_materia) references materia (Cod_materia)
      on delete cascade
);

-- Datos de ejemplo para poder probar el reporte del ejercicio 3
insert into alumno (Cod_alumno, Nombre, Apellido, Edad, Direccion) values
(1, 'Ana', 'Gomez', 20, 'San Salvador'),
(2, 'Luis', 'Perez', 22, 'Soyapango'),
(3, 'Carla', 'Ramirez', 19, 'Santa Tecla');

insert into materia (Cod_materia, Nombre, Descripcion) values
(1, 'POO404', 'Programacion Orientada a Objetos'),
(2, 'BD201', 'Bases de Datos'),
(3, 'MAT101', 'Matematica Basica');

insert into alumno_materia (Cod_alumno, Cod_materia) values
(1,1), (1,2), (2,1), (2,3), (3,2);
