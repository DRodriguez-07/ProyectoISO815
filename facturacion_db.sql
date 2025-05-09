CREATE DATABASE IF NOT EXISTS facturacion_db;
USE facturacion_db;

CREATE table User(
    id bigint auto_increment PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    fullname VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    estado TINYINT DEFAULT 0
);

CREATE TABLE Cliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_razon VARCHAR(255) NOT NULL,
    rnc_cedula VARCHAR(20) NOT NULL UNIQUE,
    cuenta_contable INT NOT NULL,
    estado TINYINT DEFAULT 1
);

CREATE table Articulo (
    id bigint AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(255) NOT NULL,
    precio_unitario DECIMAL(10,2) DEFAULT 0 NOT NULL CHECK (precio_unitario >= 0),
    estado TINYINT DEFAULT 1
);

CREATE table Asiento_Contable (
    id bigint auto_increment primary key,
    descripcion VARCHAR(255),
    cliente_id bigint references Cliente(id),
    cuenta_contable INT not null,
    fecha DATETIME default CURRENT_TIMESTAMP,
    fecha_desde DATETIME,
    fecha_hasta DATETIME,
    monto DECIMAL(10,2) NOT null,
    estado tinyint not NULL
);

CREATE table Vendedor (
    id bigint auto_increment primary key,
    nombre Varchar(255) not null,
    porciento_comision DECIMAL(5,4) not null,
    estado tinyint not NULL
)

CREATE table Factura (
    id bigint auto_increment primary key,
    vendedor_id bigint not null references Vendedor(id),
    cliente_id bigint not null references Cliente(id),
    fecha DateTime default CURRENT_TIMESTAMP,
    comentario VARCHAR(255) NOT NULL
)

CREATE table Detalle_Factura (
    id bigint auto_increment primary key,
    factura_id bigint not null references Factura(id),
    articulo_id bigint not null,
    cantidad INT not null,
    precio_unitario DECIMAL(10,2) DEFAULT 0 not null CHECK (precio_unitario >= 0)
)