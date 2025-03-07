-- insertar registros en las tablas

-- insertar roles
insert into Roles (nombre) VALUES
('Administrador');
insert into Roles (nombre) VALUES
('Empleado');

-- insertar usuarios
insert into Usuarios (nombre, correo, contraseña, id_rol) VALUES
('Juan', 'juan@ejemplo.com', '123456', 1);
insert into Usuarios (nombre, correo, contraseña, id_rol) VALUES
('Maria', 'Maria@ejemplo.com', '123456', 1);
insert into Usuarios (nombre, correo, contraseña, id_rol) VALUES
('Marvin', 'maria@ejemplo.com', '123456', 2);
insert into Usuarios (nombre, correo, contraseña, id_rol) VALUES
('Tatiana', 'Tatiana@ejemplo.com', '123456', 1);

-- insertar clientes
insert into Clientes (nombre, telefono, direccion) VALUES
('Ana', '87234567', 'Alajuela, Costa Rica');
insert into Clientes (nombre, telefono, direccion) VALUES
('Javier', '89987654', 'Heredia, Costa Rica');
insert into Clientes (nombre, telefono, direccion) VALUES
('María', '84561234', 'Cartago, Costa Rica');
insert into Clientes (nombre, telefono, direccion) VALUES
('Fernando', '87894561', 'Puntarenas, Costa Rica');
insert into Clientes (nombre, telefono, direccion) VALUES
('Laura', '85673219', 'Limón, Costa Rica');

-- insertar categorías de productos
insert into Categorias (nombre) VALUES
('Frutas');
insert into Categorias (nombre) VALUES
('Verduras');
insert into Categorias (nombre) VALUES
('Hortalizas');
insert into Categorias (nombre) VALUES
('Legumbres');

-- insertar productos
insert into Productos (nombre, descripcion, precio, id_categoria) VALUES
('Manzana', 'Fruta roja.', 500, 1);
insert into Productos (nombre, descripcion, precio, id_categoria) VALUES
('Banano', 'Fruta dulce y rica en potasio.', 300, 1);
insert into Productos (nombre, descripcion, precio, id_categoria) VALUES
('Naranja', 'Cítrico, alto en vitamina C.', 400, 1);
insert into Productos (nombre, descripcion, precio, id_categoria) VALUES
('Zanahoria', 'Verdura crujiente, alta en vitamina A.', 250, 2);
insert into Productos (nombre, descripcion, precio, id_categoria) VALUES
('Papa', 'Tubérculo, con nutrientes como hierro y calcio.', 600, 2);
insert into Productos (nombre, descripcion, precio, id_categoria) VALUES
('Lechuga', 'Hortaliza fresca, base de muchas ensaladas.', 350, 3);
insert into Productos (nombre, descripcion, precio, id_categoria) VALUES
('Tomate', 'Fruto usado en ensaladas y salsas.', 450, 3);
insert into Productos (nombre, descripcion, precio, id_categoria) VALUES
('Pepino', 'Hortaliza con alto contenido de agua.', 300, 3);
insert into Productos (nombre, descripcion, precio, id_categoria) VALUES
('Frijoles', 'Legumbre rica en proteínas y fibra.', 900, 4);
insert into Productos (nombre, descripcion, precio, id_categoria) VALUES
('Lentejas', 'Legumbre ideal para sopas.', 850, 4);

-- insertar inventario
insert into Inventario (id_producto, cantidad, fecha_vencimiento) VALUES
(1, 50, TO_DATE('31-12-2025', 'DD-MM-YYYY'));
insert into Inventario (id_producto, cantidad, fecha_vencimiento) VALUES
(2, 30, TO_DATE('30-11-2025', 'DD-MM-YYYY'));
insert into Inventario (id_producto, cantidad, fecha_vencimiento) VALUES
(3, 40, TO_DATE('15-10-2025', 'DD-MM-YYYY'));
insert into Inventario (id_producto, cantidad, fecha_vencimiento) VALUES
(4, 60, TO_DATE('20-09-2025', 'DD-MM-YYYY'));
insert into Inventario (id_producto, cantidad, fecha_vencimiento) VALUES
(5, 80, TO_DATE('01-11-2025', 'DD-MM-YYYY'));
insert into Inventario (id_producto, cantidad, fecha_vencimiento) VALUES
(6, 70, TO_DATE('25-08-2025', 'DD-MM-YYYY'));
insert into Inventario (id_producto, cantidad, fecha_vencimiento) VALUES
(7, 55, TO_DATE('01-12-2025', 'DD-MM-YYYY'));
insert into Inventario (id_producto, cantidad, fecha_vencimiento) VALUES
(8, 65, TO_DATE('10-12-2025', 'DD-MM-YYYY'));
insert into Inventario (id_producto, cantidad, fecha_vencimiento) VALUES
(9, 100, TO_DATE('15-11-2025', 'DD-MM-YYYY'));
insert into Inventario (id_producto, cantidad, fecha_vencimiento) VALUES
(10, 90, TO_DATE('05-10-2025', 'DD-MM-YYYY'));

-- insertar proveedores
INSERT INTO Proveedores (nombre, contacto, telefono, direccion) VALUES 
('Proveedor1', 'proveedor1@ejemplo.com', '22005846', 'San Isidro, Alajuela');
INSERT INTO Proveedores (nombre, contacto, telefono, direccion) VALUES 
('Proveedor2', 'proveedor2@ejemplo.com', '22348901', 'Naranjo, Alajuela');
INSERT INTO Proveedores (nombre, contacto, telefono, direccion) VALUES 
('Proveedor3', 'proveedor3@ejemplo.com', '22567432', 'Puriscal, San José');
INSERT INTO Proveedores (nombre, contacto, telefono, direccion) VALUES 
('Proveedor4', 'proveedor4@ejemplo.com', '22658976', 'Cartago, Costa Rica');

-- insertar compras
insert into Compras (id_proveedor, total) VALUES
(1, 1500.00);
insert into Compras (id_proveedor, total) VALUES
(2, 2000.00);
insert into Compras (id_proveedor, total) VALUES
(3, 1800.00);
insert into Compras (id_proveedor, total) VALUES
(4, 2200.00);
insert into Compras (id_proveedor, total) VALUES
(1, 2500.00);
insert into Compras (id_proveedor, total) VALUES
(2, 3000.00);
insert into Compras (id_proveedor, total) VALUES
(3, 1700.00);

-- insertar detalle de compras
insert into Detalle_Compras (id_compra, id_producto, cantidad, precio_unitario) VALUES
(1, 5, 20, 600);
insert into Detalle_Compras (id_compra, id_producto, cantidad, precio_unitario) VALUES
(2, 7, 15, 450);
insert into Detalle_Compras (id_compra, id_producto, cantidad, precio_unitario) VALUES
(3, 3, 10, 400);
insert into Detalle_Compras (id_compra, id_producto, cantidad, precio_unitario) VALUES
(4, 2, 25, 300);
insert into Detalle_Compras (id_compra, id_producto, cantidad, precio_unitario) VALUES
(5, 8, 30, 300);
insert into Detalle_Compras (id_compra, id_producto, cantidad, precio_unitario) VALUES
(6, 10, 12, 850);
insert into Detalle_Compras (id_compra, id_producto, cantidad, precio_unitario) VALUES
(7, 6, 18, 350);
insert into Detalle_Compras (id_compra, id_producto, cantidad, precio_unitario) VALUES
(8, 1, 22, 500);
insert into Detalle_Compras (id_compra, id_producto, cantidad, precio_unitario) VALUES
(9, 4, 10, 250);
insert into Detalle_Compras (id_compra, id_producto, cantidad, precio_unitario) VALUES
(10, 9, 50, 900);

-- insertar ventas
insert into Ventas (id_cliente, total) VALUES
(1, 12000);
insert into Ventas (id_cliente, total) VALUES
(2, 7550);
insert into Ventas (id_cliente, total) VALUES
(3, 9800);
insert into Ventas (id_cliente, total) VALUES
(4, 15500);
insert into Ventas (id_cliente, total) VALUES
(5, 6000);
insert into Ventas (id_cliente, total) VALUES
(1, 18000);
insert into Ventas (id_cliente, total) VALUES
(2, 25000);
insert into Ventas (id_cliente, total) VALUES
(3, 9500);

-- insertar detalle de ventas
insert into Detalle_Ventas (id_venta, id_producto, cantidad, precio_unitario) VALUES
(1, 1, 5, 1200);
insert into Detalle_Ventas (id_venta, id_producto, cantidad, precio_unitario) VALUES
(2, 3, 8, 580);
insert into Detalle_Ventas (id_venta, id_producto, cantidad, precio_unitario) VALUES
(3, 4, 10, 400);
insert into Detalle_Ventas (id_venta, id_producto, cantidad, precio_unitario) VALUES
(4, 5, 12, 800);
insert into Detalle_Ventas (id_venta, id_producto, cantidad, precio_unitario) VALUES 
5, 6, 6, 750);
insert into Detalle_Ventas (id_venta, id_producto, cantidad, precio_unitario) VALUES
(6, 7, 15, 900);
insert into Detalle_Ventas (id_venta, id_producto, cantidad, precio_unitario) VALUES
(7, 8, 20, 600);
insert into Detalle_Ventas (id_venta, id_producto, cantidad, precio_unitario) VALUES
(8, 9, 10, 850);

-- insertar pagos
insert into Pagos (id_venta, metodo_pago, monto) VALUES
(1, 'Efectivo', 12000);
insert into Pagos (id_venta, metodo_pago, monto) VALUES
(2, 'Tarjeta', 7550);
insert into Pagos (id_venta, metodo_pago, monto) VALUES
(3, 'Transferencia', 9800);
insert into Pagos (id_venta, metodo_pago, monto) VALUES
(4, 'Efectivo', 15500);
insert into Pagos (id_venta, metodo_pago, monto) VALUES
(5, 'Tarjeta', 6000);
insert into Pagos (id_venta, metodo_pago, monto) VALUES
(6, 'Efectivo', 18000);
insert into Pagos (id_venta, metodo_pago, monto) VALUES
(7, 'Transferencia', 25000);
insert into Pagos (id_venta, metodo_pago, monto) VALUES
(8, 'Tarjeta', 9500);

-- insertar devoluciones
insert into Devoluciones (id_venta, motivo) VALUES
(1, 'Cambio por otro producto');
insert into Devoluciones (id_venta, motivo) VALUES
(2, 'Producto defectuoso');
insert into Devoluciones (id_venta, motivo) VALUES
(3, 'Cancelación de la compra');
insert into Devoluciones (id_venta, motivo) VALUES
(4, 'Producto no deseado');
insert into Devoluciones (id_venta, motivo) VALUES
(5, 'Error en la cantidad');

-- insertar gastos
insert into Gastos (descripcion, monto, fecha) VALUES
('Pago de electricidad', 15000, TO_DATE('01-03-2025', 'DD-MM-YYYY'));
insert into Gastos (descripcion, monto, fecha) VALUES
('Compra de suministros', 8000, TO_DATE('05-03-2025', 'DD-MM-YYYY'));
insert into Gastos (descripcion, monto, fecha) VALUES
('Mantenimiento de equipo', 2500, TO_DATE('10-03-2025', 'DD-MM-YYYY'));
insert into Gastos (descripcion, monto, fecha) VALUES
('Pago de salarios', 30000, TO_DATE('15-03-2025', 'DD-MM-YYYY'));
insert into Gastos (descripcion, monto, fecha) VALUES
('Compra de mobiliario', 12000, TO_DATE('20-03-2025', 'DD-MM-YYYY'));

-- insertar promociones
insert into Promociones (id_producto, descuento, fecha_inicio, fecha_fin) VALUES
(1, 100, TO_DATE('01-05-2025', 'DD-MM-YYYY'), TO_DATE('15-05-2025', 'DD-MM-YYYY'));
insert into Promociones (id_producto, descuento, fecha_inicio, fecha_fin) VALUES
(2, 150, TO_DATE('01-06-2025', 'DD-MM-YYYY'), TO_DATE('30-06-2025', 'DD-MM-YYYY'));
insert into Promociones (id_producto, descuento, fecha_inicio, fecha_fin) VALUES
(3, 50, TO_DATE('01-07-2025', 'DD-MM-YYYY'), TO_DATE('15-07-2025', 'DD-MM-YYYY'));
insert into Promociones (id_producto, descuento, fecha_inicio, fecha_fin) VALUES
(4, 200, TO_DATE('01-08-2025', 'DD-MM-YYYY'), TO_DATE('15-08-2025', 'DD-MM-YYYY'));

-- insertar reportes
insert into Reportes (tipo, descripcion) VALUES
('Ventas', 'Reporte diario de ventas');
insert into Reportes (tipo, descripcion) VALUES
('Inventario', 'Reporte de existencias por producto');
insert into Reportes (tipo, descripcion) VALUES
('Devoluciones', 'Reporte de devoluciones realizadas');
insert into Reportes (tipo, descripcion) VALUES
('Gastos', 'Reporte mensual de gastos');

-- insertar alertas
insert into Alertas (tipo, mensaje) VALUES
('Stock Bajo', 'El stock de Zanahoria está por debajo del mínimo');
insert into Alertas (tipo, mensaje) VALUES
('Producto Vencido', 'La Papa ha vencido');
insert into Alertas (tipo, mensaje) VALUES
('Stock Bajo', 'El stock de Lechuga está por debajo del mínimo');
insert into Alertas (tipo, mensaje) VALUES
('Producto Vencido', 'Los tomates están en mal estado');

-- insertar historial de precios
insert into Historial_Precios (id_producto, precio_anterior, precio_nuevo) VALUES
(1, 1000, 1200);
insert into Historial_Precios (id_producto, precio_anterior, precio_nuevo) VALUES
(2, 600, 580);
insert into Historial_Precios (id_producto, precio_anterior, precio_nuevo) VALUES
(3, 450, 400);
insert into Historial_Precios (id_producto, precio_anterior, precio_nuevo) VALUES
(4, 900, 800);

-- insertar historial de stock
insert into Historial_Stock (id_producto, cantidad_anterior, cantidad_nueva) VALUES
(1, 80, 75);
insert into Historial_Stock (id_producto, cantidad_anterior, cantidad_nueva) VALUES
(2, 50, 45);
insert into Historial_Stock (id_producto, cantidad_anterior, cantidad_nueva) VALUES
(3, 100, 90);
insert into Historial_Stock (id_producto, cantidad_anterior, cantidad_nueva) VALUES
(4, 120, 110);

-- insertar logs del sistema
insert into Logs (id_usuario, accion) VALUES
(1, 'Actualización de inventario');
insert into Logs (id_usuario, accion) VALUES
(2, 'Eliminación de producto');
insert into Logs (id_usuario, accion) VALUES
(3, 'Inicio de sesión');
insert into Logs (id_usuario, accion) VALUES
(4, 'Cambio de precio');
insert into Logs (id_usuario, accion) VALUES
(1, 'Generación de reporte de ventas');

