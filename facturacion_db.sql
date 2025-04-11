CREATE DATABASE IF NOT EXISTS facturacion_db;
USE facturacion_db;

CREATE OR REPLACE table User(
	id bigint auto_increment PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    fullname VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

CREATE OR REPLACE TABLE Cliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombreRazon VARCHAR(255) NOT NULL,
    rncCedula VARCHAR(20) NOT NULL UNIQUE,
    cuentaContable VARCHAR(50) NOT NULL,
    estado TINYINT DEFAULT 1
);

CREATE OR REPLACE table Articulo (
	id bigint AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(255) NOT NULL,
	precioUnitario DECIMAL(10,2) NOT NULL CHECK (precioUnitario >= 0),
    estado TINYINT DEFAULT 1
);

CREATE OR REPLACE table AsientoContable (
	id bigint auto_increment primary key,
	descripcion VARCHAR(255),
	clienteId bigint references Cliente(id),
	cuentaContable VARCHAR(50) not null,
	-- 1 Debito, 2 Credito 
	tipoMovimiento tinyint not NULL,
	fecha DATETIME default CURRENT_TIMESTAMP,
	monto DECIMAL(10,2) NOT null,
	estado tinyint not NULL,
);

CREATE OR REPLACE table Vendedor (
	id bigint auto_increment primary key,
	nombre Varchar(255) not null,
	porcientoComision DECIMAL(5,4) not null,
	estado tinyint not NULL,
)

CREATE OR REPLACE table Factura (
	id bigint auto_increment primary key,
	vendedorId bigint not null foreign key references Vendedor(id),
	clienteId bigint not null foreign key references Cliente(id),
	fecha DateTime default CURRENT_TIMESTAMP,
    comentario VARCHAR(255) NOT NULL,
)

CREATE OR REPLACE table DetalleFactura (
	id bigint auto_increment primary key,
	facturaId bigint not null references Factura(id),
	articuloId bigint not null,
	cantidad INT not null,
	precioUnitario DECIMAL(10,2) not null CHECK (precioUnitario >= 0),
)