# POOGuia6-conJDBC — Interfaces gráficas y JDBC (POO404, Guía #8)

Proyecto Maven con la resolución completa de la Guía de Laboratorio #8
(procedimiento con `frmPersona`) y su Ejercicio Complementario (mantenimiento
de Alumno, Materia y AlumnoMateria con formularios).

## Estructura

```
POOGuia6-conJDBC/
├── pom.xml                        # Dependencia mysql-connector-j 9.0.0
├── sql/
│   ├── 01_personabdd.sql          # BDD del procedimiento (persona, ocupaciones)
│   └── 02_colegiobdd.sql          # BDD del Ejercicio Complementario
└── src/main/java/com/example/javajframe/
    ├── util/Conexion.java                 # Paso 8
    ├── beans/
    │   ├── PersonaBeans.java              # Pasos 9-11
    │   ├── AlumnoBeans.java               # Ejercicio Complementario
    │   └── MateriaBeans.java              # Ejercicio Complementario
    ├── datos/
    │   ├── PersonasDatos.java             # Paso 15
    │   ├── OcupacionesDatos.java          # Paso 16
    │   ├── AlumnoDatos.java               # Ejercicio Complementario
    │   ├── MateriaDatos.java              # Ejercicio Complementario
    │   └── AlumnoMateriaDatos.java        # Ejercicio Complementario (+ reporte)
    ├── interfaceswing/
    │   ├── frmPersona.java                # Pasos 12-24
    │   ├── frmAlumno.java                 # Ejercicio Complementario
    │   ├── frmMateria.java                # Ejercicio Complementario
    │   ├── frmAlumnoMateria.java          # Ejercicio Complementario (matricula + reporte)
    │   ├── frmMenuPrincipal.java          # Menu para navegar entre formularios
    │   └── recursos/IconoGenerator.java   # Icono decorativo generado por codigo
    └── aplicacion/Main.java               # Punto de entrada (lanza el menu)
```

## Cómo se organizó

- El paquete raíz es `com.example.javajframe`, igual al mostrado en la
  captura de "Source Packages" de la guía, incluyendo el paquete vacío
  `interfaceswing.recursos` (aquí se le dio uso real: genera el icono
  decorativo de la cabecera del formulario).
- `frmPersona.java` reproduce **exactamente** el comportamiento y los
  nombres de campos/métodos pedidos por la guía (`txtId`, `txtNombre`,
  `cmbOcupacion`, `tblDatos`, `btnObtenerDatos`, `btnEliminar`,
  `btnLimpiar`, `LimpiarControles`, `ObtenerFiladeTabla`, `EliminarDatos`).
- El Ejercicio Complementario ("mantenimiento con formularios para Alumno,
  Materia y AlumnoMateria") se resolvió siguiendo el mismo patrón usado en
  `frmPersona`/`PersonasDatos` (formulario + tabla + botones Guardar/Editar,
  Eliminar, Limpiar), pero contra la base de datos `colegiobdd` creada en
  la Guía #7.

## Puntos donde se investigó/decidió algo no especificado por la guía

1. **El proyecto POOGuia6 no fue provisto**: como esta guía indica reutilizar
   un proyecto de una sesión anterior (introducción a JFrame) que no se nos
   compartió, `frmPersona` se construyó desde cero con Swing puro
   (`GridBagLayout`), sin el archivo `.form` que generaría el editor visual
   de NetBeans (ese archivo no es compilable ni legible fuera del IDE). El
   comportamiento, los nombres de variables/métodos y el flujo son
   idénticos a los que pide la guía.
2. **Icono de la cabecera**: la guía muestra una imagen decorativa (personas
   frente a una computadora) que no se puede incluir sin el archivo de
   imagen original. Se generó un icono simple por código en
   `interfaceswing.recursos.IconoGenerator`, cumpliendo la misma función
   decorativa sin depender de un binario externo.
3. **Menú principal (`frmMenuPrincipal`)**: no pedido por la guía, se agregó
   solo para poder abrir cualquiera de los formularios (Persona, Alumno,
   Materia, Matrícula) desde un único punto de entrada (`Main.java`), ya que
   aquí no existe un IDE para ejecutar cada formulario por separado.
4. **Rango de edad válido para un alumno (15–100 años)**: mismo criterio ya
   documentado en el proyecto de la Guía #7 (no especificado por ninguna
   guía; edad mínima aproximada de ingreso a educación media/superior en
   El Salvador, con un techo amplio para educación continua).
5. **`ON DELETE CASCADE`** en las llaves foráneas de `alumno_materia`, para
   evitar registros huérfanos al eliminar un alumno o una materia desde los
   formularios (no especificado por la guía).
