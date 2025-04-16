CREATE DATABASE IF NOT EXISTS facturacion_db;
USE facturacion_db;

CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_comercial VARCHAR(255) NOT NULL,
    rnc_cedula VARCHAR(20) NOT NULL UNIQUE,
    cuenta_contable VARCHAR(50) NOT NULL,
    estado ENUM('Activo', 'Inactivo') DEFAULT 'Activo'
);


CREATE TABLE articulos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(255) NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL CHECK (precio_unitario >= 0),
    estado ENUM('Activo', 'Inactivo') DEFAULT 'Activo'
);


CREATE TABLE vendedores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    porciento_comision DECIMAL(5,2) NOT NULL CHECK (porciento_comision >= 0 AND porciento_comision <= 100),
    estado ENUM('Activo', 'Inactivo') DEFAULT 'Activo'
);


CREATE TABLE facturas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    vendedor_id INT NOT NULL,
    cliente_id INT NOT NULL,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    comentario TEXT
);


CREATE TABLE factura_detalle (
    id INT AUTO_INCREMENT PRIMARY KEY,
    factura_id INT NOT NULL,
    articulo_id INT NOT NULL,
    cantidad INT NOT NULL CHECK (cantidad > 0),
    precio_unitario DECIMAL(10,2) NOT NULL CHECK (precio_unitario >= 0),
    subtotal DECIMAL(10,2) GENERATED ALWAYS AS (cantidad * precio_unitario) STORED
);

CREATE TABLE asientos_contables (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion TEXT NOT NULL,
    cliente_id INT NOT NULL,
    cuenta VARCHAR(50) NOT NULL,
    tipo_movimiento ENUM('DB', 'CR') NOT NULL,  -- DB = Débito, CR = Crédito
    fecha_asiento DATETIME DEFAULT CURRENT_TIMESTAMP,
    monto DECIMAL(10,2) NOT NULL CHECK (monto >= 0),
    estado ENUM('Activo', 'Inactivo') DEFAULT 'Activo'
);

CREATE TABLE facturas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    vendedor_id INT,
    cliente_id INT,
    fecha DATE NOT NULL,
    comentario VARCHAR(255),
    articulo_id INT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL
);

