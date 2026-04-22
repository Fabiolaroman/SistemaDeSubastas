CREATE DATABASE db_subastas;

USE db_subastas;

CREATE TABLE t_coleccionista(
    id VARCHAR(10) PRIMARY KEY ,
    nombre VARCHAR(25) NOT NULL,
    apellidos VARCHAR(50) NOT NULL,
    cedula VARCHAR(10) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    correo VARCHAR(50) NOT NULL,
    contrasenia VARCHAR(25) NOT NULL,
    puntuacion DOUBLE NOT NULL,
    direccion VARCHAR(25) NOT NULL
)

CREATE TABLE t_vendedor(
    id VARCHAR(10) PRIMARY KEY,
    nombre VARCHAR(25) NOT NULL,
    apellidos VARCHAR(50) NOT NULL,
    cedula VARCHAR(10) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    correo VARCHAR(50) NOT NULL,
    contrasenia VARCHAR(25) NOT NULL,
    puntuacion DOUBLE NOT NULL,
    direccion VARCHAR(25) NOT NULL
)

CREATE TABLE t_moderador(
    id VARCHAR(10) PRIMARY KEY,
    nombre VARCHAR(25) NOT NULL,
    apellidos VARCHAR(50) NOT NULL,
    cedula VARCHAR(10) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    correo VARCHAR(50) NOT NULL,
    contrasenia VARCHAR(25) NOT NULL
)

CREATE TABLE t_subasta(
    id VARCHAR(10) PRIMARY KEY ,
    id_usuario_creador VARCHAR(10) NOT NULL,
    FOREIGN KEY (id_usuario_creador) REFERENCES t_vendedor(id),
    precio_minimo DOUBLE NOT NULL,
    esta_activa BOOLEAN NOT NULL
)

CREATE TABLE t_oferta(
    id VARCHAR(10) PRIMARY KEY,
    monto DOUBLE NOT NULL,
    id_coleccionista VARCHAR(10) not null,
    FOREIGN KEY (id_coleccionista) REFERENCES t_coleccionista(id),
    id_subasta VARCHAR(10) NOT NULL,
    FOREIGN KEY (id_subasta) REFERENCES t_subasta(id)
)

CREATE TABLE t_item(
    id VARCHAR(10) PRIMARY KEY,
    nombre  VARCHAR(50) NOT NULL,
    descripcion VARCHAR(100) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    fecha_compra DATE NOT NULL,
    id_subasta VARCHAR(10),
    FOREIGN KEY (id_subasta) REFERENCES t_subasta(id),
    id_coleccionista VARCHAR(10),
    FOREIGN KEY (id_coleccionista) REFERENCES t_coleccionista(id)
)

CREATE TABLE t_interes(
    id VARCHAR(10) PRIMARY KEY,
    nombre VARCHAR(10) NOT NULL
)

CREATE TABLE t_coleccionista_interes(
    id_coleccionista VARCHAR(10) NOT NULL,
    FOREIGN KEY (id_coleccionista) REFERENCES t_coleccionista(id),
    id_interes VARCHAR(10) NOT NULL,
    FOREIGN KEY (id_interes) REFERENCES t_interes(id)
)