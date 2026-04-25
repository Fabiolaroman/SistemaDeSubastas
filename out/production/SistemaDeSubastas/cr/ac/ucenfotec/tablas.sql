CREATE
DATABASE db_subastas;

USE
db_subastas;

CREATE TABLE t_coleccionista
(
    id               VARCHAR(10) PRIMARY KEY,
    nombre           VARCHAR(25) NOT NULL,
    apellidos        VARCHAR(50) NOT NULL,
    cedula           VARCHAR(10) NOT NULL,
    fecha_nacimiento DATE        NOT NULL,
    correo           VARCHAR(50) NOT NULL,
    contrasenia      VARCHAR(25) NOT NULL,
    puntuacion DOUBLE NOT NULL,
    direccion        VARCHAR(25) NOT NULL
)

INSERT INTO t_coleccionista VALUES ('C-1', 'Coleccionista de Prueba', '1', '111111111', '2000-01-01', 'coleccionista@prueba.com', '12345', 5.0, 'San Jose')

CREATE TABLE t_vendedor
(
    id               VARCHAR(10) PRIMARY KEY,
    nombre           VARCHAR(25) NOT NULL,
    apellidos        VARCHAR(50) NOT NULL,
    cedula           VARCHAR(10) NOT NULL,
    fecha_nacimiento DATE        NOT NULL,
    correo           VARCHAR(50) NOT NULL,
    contrasenia      VARCHAR(25) NOT NULL,
    puntuacion DOUBLE NOT NULL,
    direccion        VARCHAR(25) NOT NULL
)

INSERT INTO t_vendedor VALUES ('V-1', 'Vendedor de Prueba', '1', '222222222', '2000-01-01', 'vendedor@prueba.com', '12345', 5.0, 'San Jose')

CREATE TABLE t_moderador
(
    id               VARCHAR(10) PRIMARY KEY,
    nombre           VARCHAR(25) NOT NULL,
    apellidos        VARCHAR(50) NOT NULL,
    cedula           VARCHAR(10) NOT NULL,
    fecha_nacimiento DATE        NOT NULL,
    correo           VARCHAR(50) NOT NULL,
    contrasenia      VARCHAR(25) NOT NULL
)

INSERT INTO t_moderador VALUES ('M-1', 'Vendedor de Prueba', '1', '222222222', '2000-01-01', 'vendedor@prueba.com', '12345')

CREATE TABLE t_subasta
(
    id               VARCHAR(10) PRIMARY KEY,
    id_vendedor      VARCHAR(10),
    FOREIGN KEY (id_vendedor) REFERENCES t_vendedor (id),
    id_coleccionista VARCHAR(10),
    FOREIGN KEY (id_coleccionista) REFERENCES t_coleccionista (id),
    precio_minimo DOUBLE NOT NULL,
    esta_activa      BOOLEAN NOT NULL fechaVencimiento TIMESTAMP NOT NULL,
    fechaVencimiento TIMESTAMP NOT NULL
)

INSERT INTO t_subasta VALUES ('S-1' , 'V-1', null, 30, TRUE, '2026-04-24 18:00:00')

CREATE TABLE t_oferta
(
    id VARCHAR(10) PRIMARY KEY,
    monto DOUBLE NOT NULL,
    id_coleccionista VARCHAR(10) NOT NULL ,
    FOREIGN KEY (id_coleccionista) REFERENCES t_coleccionista (id),
    id_subasta       VARCHAR(10) NOT NULL,
    FOREIGN KEY (id_subasta) REFERENCES t_subasta (id)
)
INSERT INTO t_oferta VALUES ('O-1', 40, 'C-1', 'S-1')


CREATE TABLE t_item
(
    id               VARCHAR(10) PRIMARY KEY,
    nombre           VARCHAR(50)  NOT NULL,
    descripcion      VARCHAR(100) NOT NULL,
    estado           VARCHAR(50)  NOT NULL,
    fecha_compra     DATE         NOT NULL,
    id_subasta       VARCHAR(10),
    FOREIGN KEY (id_subasta) REFERENCES t_subasta (id),
    id_coleccionista VARCHAR(10),
    FOREIGN KEY (id_coleccionista) REFERENCES t_coleccionista (id)
)

INSERT INTO t_item VALUES ('I-1', 'ITEM 1', 'ITEM EN SUBASTA', 'NUEVO', '2000-01-01', 'S-1', null)
INSERT INTO t_item VALUES ('I-2', 'ITEM 2', 'ITEM EN SUBASTA', 'USADO', '2000-01-01', 'S-1', null)
INSERT INTO t_item VALUES ('I-3', 'ITEM 3', 'ITEM EN COLECCION', 'USADO', '2000-01-01', null, 'C-1')

CREATE TABLE t_interes
(
    id     VARCHAR(10) PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
)
INSERT INTO t_interes values ('1', 'RADIOS'), ('2', 'TELEFONOS'), ('3', 'POKEMON'), ('4', 'HOTWHEELS'), ('5', 'COMICS'), ('6', 'ESTAMPILLAS'), ('7', 'NUMISMATICA')

CREATE TABLE t_coleccionista_interes
(
    id_coleccionista VARCHAR(10) NOT NULL,
    FOREIGN KEY (id_coleccionista) REFERENCES t_coleccionista (id),
    id_interes       VARCHAR(10) NOT NULL,
    FOREIGN KEY (id_interes) REFERENCES t_interes (id)
)