# POOGuia7 — JDBC (POO404, Universidad Don Bosco)

Proyecto Maven con la resolucion completa de la Guia de Laboratorio #7 (JDBC)
y de sus Ejercicios Complementarios.

## Estructura

```
POOGuia7/
├── pom.xml                      # Dependencia mysql-connector-j 9.0.0
├── sql/
│   ├── 01_personabdd.sql        # BDD del procedimiento (persona, ocupaciones, Empleados)
│   └── 02_colegiobdd.sql        # BDD de los Ejercicios Complementarios (alumno, materia, alumno_materia)
└── src/main/java/
    ├── modelos/
    │   ├── VerPersonas.java       # Parte 2
    │   ├── Testing.java           # Parte 4 (validacion telefono/fecha con regex)
    │   ├── IngresoDatos.java      # Parte 4 (alta de persona via JOptionPane)
    │   └── VerificarNulos.java    # Parte 5 (diferencia NULL vs vacio)
    ├── conexiones/
    │   └── Conexion.java          # Parte 5 (encapsula la conexion JDBC)
    ├── aplicacion/
    │   ├── Principal.java             # Menu de consola para probar Partes 2 a 5
    │   └── MenuComplementario.java    # Menu de consola del CRUD de Ejercicios Complementarios
    ├── negocio/
    │   ├── Alumno.java
    │   └── Materia.java
    ├── dao/
    │   ├── AlumnoDAO.java
    │   ├── MateriaDAO.java
    │   └── AlumnoMateriaDAO.java   # tabla intermedia + reporte del ejercicio 3
    └── validaciones/
        └── ValidadorDatos.java     # clase dedicada a validar datos de usuario
```

## Como se organizo

- El **Procedimiento** (Partes 1 a 5 de la guia) esta implementado tal cual,
  respetando nombres de clases, metodos y campos del PDF, en los paquetes
  `modelos`, `aplicacion` y `conexiones`.
- Los **Ejercicios Complementarios** (seccion IV) agregan los paquetes
  `negocio` (entidades Alumno/Materia), `dao` (CRUD contra MySQL) y
  `validaciones` (clase exclusiva de validacion, pedida en las Notas de la
  guia). Reutilizan la clase `conexiones.Conexion` agregandole un
  constructor sobrecargado para poder apuntar a una base de datos distinta
  de `personabdd`.

## Puntos donde se investigo/decidio algo no especificado por la guia

1. **Nombre de la base de datos del ejercicio complementario**: la guia dice
   "con el nombre que desee", se eligio `colegiobdd`.
2. **Rango de edad valido para un alumno**: la guia no lo define. Se uso
   15–100 anios como rango razonable (edad minima aproximada de ingreso a
   educacion media/superior en El Salvador, y un techo amplio para cubrir
   educacion continua/adultos). Esta implementado en
   `validaciones.ValidadorDatos`.
3. **Llave foranea en `alumno_materia`**: se agrego `on delete cascade`
   para que al borrar un alumno o materia no queden registros huerfanos en
   la tabla intermedia (no especificado por la guia).
4. **Codigos de alumno/materia**: se validan como enteros positivos, ya que
   son llave primaria ingresada manualmente por el usuario (la guia no
   indica auto_increment para estas tablas).
