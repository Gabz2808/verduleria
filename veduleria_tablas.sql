-- CREACIÓN DE BASE DE DATOS PARA VERDULERÍA LUPITA EN ORACLE 21c

-- TABLA DE ROLES
CREATE TABLE Roles (
    id_rol NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    nombre VARCHAR2(50) UNIQUE NOT NULL
);
-- Insertar datos en la tabla Roles
INSERT INTO Roles (nombre) VALUES ('USER');
INSERT INTO Roles (nombre) VALUES ('ADMIN');

-- TABLA DE USUARIOS
CREATE TABLE Usuarios (
    id_usuario NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    nombre VARCHAR2(100) NOT NULL,
    correo VARCHAR2(100) UNIQUE NOT NULL,
    contraseña VARCHAR2(255) NOT NULL,
    id_rol NUMBER NOT NULL default 1,
    FOREIGN KEY (id_rol) REFERENCES Roles(id_rol)
);

-- TABLA DE CLIENTES
CREATE TABLE Clientes (
    id_cliente NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    nombre VARCHAR2(100) NOT NULL,
    telefono VARCHAR2(20),
    direccion VARCHAR2(255)
);

-- TABLA DE CATEGORÍAS DE PRODUCTOS
CREATE TABLE Categorias (
    id_categoria NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    nombre VARCHAR2(100) UNIQUE NOT NULL
);

-- TABLA DE PRODUCTOS
CREATE TABLE Productos (
    id_producto NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    nombre VARCHAR2(100) NOT NULL,
    descripcion VARCHAR2(255),
    precio DECIMAL(10,2) NOT NULL,
    id_categoria NUMBER NOT NULL,
    FOREIGN KEY (id_categoria) REFERENCES Categorias(id_categoria)
);

-- TABLA DE INVENTARIO
CREATE TABLE Inventario (
    id_inventario NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    id_producto NUMBER NOT NULL,
    cantidad NUMBER NOT NULL,
    fecha_vencimiento DATE,
    FOREIGN KEY (id_producto) REFERENCES Productos(id_producto)
);

-- TABLA DE PROVEEDORES
CREATE TABLE Proveedores (
    id_proveedor NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    nombre VARCHAR2(100) NOT NULL,
    contacto VARCHAR2(100),
    telefono VARCHAR2(20),
    direccion VARCHAR2(255)
);

-- TABLA DE COMPRAS
CREATE TABLE Compras (
    id_compra NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    id_proveedor NUMBER NOT NULL,
    fecha_compra DATE DEFAULT SYSDATE,
    total DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_proveedor) REFERENCES Proveedores(id_proveedor)
);

-- TABLA DE DETALLE DE COMPRAS
CREATE TABLE Detalle_Compras (
    id_detalle_compra NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    id_compra NUMBER NOT NULL,
    id_producto NUMBER NOT NULL,
    cantidad NUMBER NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_compra) REFERENCES Compras(id_compra),
    FOREIGN KEY (id_producto) REFERENCES Productos(id_producto)
);

-- TABLA DE VENTAS
CREATE TABLE Ventas (
    id_venta NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    id_cliente NUMBER,
    fecha_venta DATE DEFAULT SYSDATE,
    total DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente)
);

-- TABLA DE DETALLE DE VENTAS
CREATE TABLE Detalle_Ventas (
    id_detalle_venta NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    id_venta NUMBER NOT NULL,
    id_producto NUMBER NOT NULL,
    cantidad NUMBER NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_venta) REFERENCES Ventas(id_venta),
    FOREIGN KEY (id_producto) REFERENCES Productos(id_producto)
);

-- TABLA DE PAGOS
CREATE TABLE Pagos (
    id_pago NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    id_venta NUMBER NOT NULL,
    metodo_pago VARCHAR2(50) NOT NULL,
    monto DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_venta) REFERENCES Ventas(id_venta)
);

-- TABLA DE DEVOLUCIONES
CREATE TABLE Devoluciones (
    id_devolucion NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    id_venta NUMBER NOT NULL,
    fecha_devolucion DATE DEFAULT SYSDATE,
    motivo VARCHAR2(255),
    FOREIGN KEY (id_venta) REFERENCES Ventas(id_venta)
);

-- TABLA DE GASTOS
CREATE TABLE Gastos (
    id_gasto NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    descripcion VARCHAR2(255) NOT NULL,
    monto DECIMAL(10,2) NOT NULL,
    fecha DATE DEFAULT SYSDATE
);

-- TABLA DE PROMOCIONES
CREATE TABLE Promociones (
    id_promocion NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    id_producto NUMBER NOT NULL,
    descuento DECIMAL(5,2) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    FOREIGN KEY (id_producto) REFERENCES Productos(id_producto)
);

-- TABLA DE REPORTES
CREATE TABLE Reportes (
    id_reporte NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    tipo VARCHAR2(100) NOT NULL,
    fecha_generado DATE DEFAULT SYSDATE,
    descripcion VARCHAR2(255)
);

-- TABLA DE ALERTAS
CREATE TABLE Alertas (
    id_alerta NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    tipo VARCHAR2(100) NOT NULL,
    mensaje VARCHAR2(255) NOT NULL,
    fecha DATE DEFAULT SYSDATE
);

-- TABLA DE HISTORIAL DE PRECIOS
CREATE TABLE Historial_Precios (
    id_historial NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    id_producto NUMBER NOT NULL,
    precio_anterior DECIMAL(10,2) NOT NULL,
    precio_nuevo DECIMAL(10,2) NOT NULL,
    fecha_cambio DATE DEFAULT SYSDATE,
    FOREIGN KEY (id_producto) REFERENCES Productos(id_producto)
);

-- TABLA DE HISTORIAL DE STOCK
CREATE TABLE Historial_Stock (
    id_historial NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    id_producto NUMBER NOT NULL,
    cantidad_anterior NUMBER NOT NULL,
    cantidad_nueva NUMBER NOT NULL,
    fecha_cambio DATE DEFAULT SYSDATE,
    FOREIGN KEY (id_producto) REFERENCES Productos(id_producto)
);

-- TABLA DE LOGS DEL SISTEMA
CREATE TABLE Logs (
    id_log NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    id_usuario NUMBER,
    accion VARCHAR2(255) NOT NULL,
    fecha DATE DEFAULT SYSDATE,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario)
);
