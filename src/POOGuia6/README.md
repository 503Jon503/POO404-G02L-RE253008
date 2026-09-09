# POOGuia6 — POO404 (Universidad Don Bosco)

Guía de Laboratorio #6: Creación de Interfaces gráficas con SWING.

## Estructura del proyecto

```
POOGuia6/
├── pom.xml
├── src/
│   ├── aplicacion/
│   │   └── Principal.java          -> clase con el main()
│   ├── interfaceswing/
│   │   ├── frmPersona.java         -> Parte 1 y Parte 3 del procedimiento
│   │   ├── frmBotonesRadio.java    -> Parte 4 del procedimiento
│   │   └── recursos/
│   │       ├── man.png
│   │       ├── img1.jpeg
│   │       ├── img2.jpeg
│   │       ├── img3.jpeg
│   │       └── question-icon.jpeg
│   └── datos/
│       └── personas.csv            -> Ejercicios Complementarios
```

Los paquetes son carpetas reales dentro de `src/` (`aplicacion`,
`interfaceswing`, `interfaceswing/recursos`, `datos`), tal como se pide en
el paso 3 de la guía, para poder arrastrarlas directo a tu repositorio.

## Contenido de cada archivo

- **aplicacion/Principal.java**: método `main`. Instancia y muestra
  `frmPersona`. Deja comentadas las líneas para probar `frmBotonesRadio`,
  igual que se hace en el paso 67 de la guía.
- **interfaceswing/frmPersona.java**: formulario con los campos `txtId`,
  `txtNombre`, `txtEdad`, `txtTelefono`, `cmbSexo`, botones
  `btnObtenerDatos`/`btnLimpiar`, tabla `tblDatos` con su
  `DefaultTableModel`, y los 3 métodos de los Ejercicios Complementarios.
- **interfaceswing/frmBotonesRadio.java**: formulario con 3
  `JRadioButton` (`rbtOpcion1/2/3`) agrupados en `buttonGroup1`, que
  cambian la imagen mostrada en `lblImagen`.
- **interfaceswing/recursos/**: imágenes usadas por los formularios.
- **datos/personas.csv**: archivo CSV con 3 personas de ejemplo,
  compatible con las columnas de `tblDatos` (Id, Nombres, Edad,
  Telefono, Sexo).
- **pom.xml**: proyecto Maven; `sourceDirectory` apunta a `src` (no al
  layout estándar `src/main/java`) para respetar la estructura de
  paquetes pedida por la guía.

## Dónde tuve que investigar/decidir por mi cuenta

1. **Recursos gráficos (imágenes)**: la guía referencia archivos
   (`man.png`, `img1.jpeg`, `img2.jpeg`, `img3.jpeg`,
   `question-icon.jpeg`) que forman parte de un paquete de recursos
   adjunto a la práctica original, el cual no fue proporcionado en el
   PDF. Generé imágenes *placeholder* (rectángulos de color con texto)
   con las mismas rutas y nombres exactos, para que el código compile y
   funcione igual. Puedes reemplazarlas por las imágenes reales de tu
   práctica sin tocar el código, siempre que conserves los mismos
   nombres de archivo.
2. **Utilidad del archivo CSV** (pedido explícitamente por el
   Ejercicio Complementario): un CSV (*Comma-Separated Values*) es un
   formato de texto plano estandarizado por el RFC 4180, que representa
   datos tabulares separando cada valor con una coma y cada fila con un
   salto de línea. Su ventaja es la interoperabilidad: permite mover
   datos entre hojas de cálculo, bases de datos y aplicaciones sin
   depender de un formato binario propietario. Esto está documentado
   también como comentario en `frmPersona.java`.
3. **Cuándo invocar los métodos del Ejercicio Complementario**: la guía
   solo especifica que el método (a) debe invocarse desde el
   constructor. Para los métodos (b) y (c), decidí (y lo indico en
   comentarios en el código) invocarlos ambos dentro de
   `btnObtenerDatos()`: primero (c) para insertar/actualizar la fila en
   `tblDatos` según el Id, y luego (b) para volcar la tabla completa al
   CSV, manteniendo el archivo siempre sincronizado con lo que el
   usuario ve en pantalla.
4. **Diseño del formulario sin el editor visual del IDE**: como no se usa
   NetBeans/IntelliJ, el layout de `pnlPersona` y `pnlImagenes` se
   construyó a mano con `GridBagLayout` en vez del `GroupLayout` que
   genera el diseñador visual, buscando reproducir la misma disposición
   visual mostrada en las capturas de la guía.
