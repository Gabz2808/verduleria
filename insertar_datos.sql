-- insertar registros en las tabla
-- insertar roles
CREATE OR REPLACE PROCEDURE InsertarRol(
    p_nombre IN VARCHAR2,
    p_id_rol OUT NUMBER
) AS
BEGIN
    INSERT INTO Roles (nombre)
    VALUES (p_nombre)
    RETURNING id_rol INTO p_id_rol;
    
    COMMIT;
END;

DECLARE 
    v_id_rol NUMBER;
BEGIN
    InsertarRol('Usuario', v_id_rol);
    DBMS_OUTPUT.PUT_LINE('ID del rol insertado: ' || v_id_rol);
    InsertarRol('Administrador', v_id_rol);
    DBMS_OUTPUT.PUT_LINE('ID del rol insertado: ' || v_id_rol);
END;

SELECT * FROM ROLES

--Insertar Usuarios
CREATE OR REPLACE PROCEDURE InsertarUsuario(
    p_nombre IN VARCHAR2,
    p_correo IN VARCHAR2,
    p_contraseña IN VARCHAR2,
    p_id_rol IN NUMBER,
    p_id_usuario OUT NUMBER
) AS
BEGIN
    INSERT INTO Usuarios (nombre, correo, contraseña, id_rol)
    VALUES (p_nombre, p_correo, p_contraseña, p_id_rol)
    RETURNING id_usuario INTO p_id_usuario;
    
    COMMIT;
END;

DECLARE 
    v_id_usuario NUMBER;
BEGIN
    InsertarUsuario('Juan', 'juan@ejemplo.com', '123456', 1, v_id_usuario);
    DBMS_OUTPUT.PUT_LINE('ID del usuario Juan: ' || v_id_usuario);
    
    InsertarUsuario('Maria', 'Maria@ejemplo.com', '123456', 1, v_id_usuario);
    DBMS_OUTPUT.PUT_LINE('ID del usuario Maria: ' || v_id_usuario);
    
    InsertarUsuario('Marvin', 'maria@ejemplo.com', '123456', 2, v_id_usuario);
    DBMS_OUTPUT.PUT_LINE('ID del usuario Marvin: ' || v_id_usuario);
    
    InsertarUsuario('Tatiana', 'Tatiana@ejemplo.com', '123456', 1, v_id_usuario);
    DBMS_OUTPUT.PUT_LINE('ID del usuario Tatiana: ' || v_id_usuario);
END;

-- insertar clientes
CREATE OR REPLACE PROCEDURE InsertarCliente(
    p_nombre IN VARCHAR2,
    p_telefono IN VARCHAR2,
    p_direccion IN VARCHAR2,
    p_id_cliente OUT NUMBER
) AS
BEGIN
    INSERT INTO Clientes (nombre, telefono, direccion)
    VALUES (p_nombre, p_telefono, p_direccion)
    RETURNING id_cliente INTO p_id_cliente;
    
    COMMIT;
END;

DECLARE 
    v_id_cliente NUMBER;
BEGIN
    InsertarCliente('Ana', '87234567', 'Alajuela, Costa Rica', v_id_cliente);
    DBMS_OUTPUT.PUT_LINE('ID del cliente Ana: ' || v_id_cliente);
    
    InsertarCliente('Javier', '89987654', 'Heredia, Costa Rica', v_id_cliente);
    DBMS_OUTPUT.PUT_LINE('ID del cliente Javier: ' || v_id_cliente);
    
    InsertarCliente('María', '84561234', 'Cartago, Costa Rica', v_id_cliente);
    DBMS_OUTPUT.PUT_LINE('ID del cliente María: ' || v_id_cliente);
    
    InsertarCliente('Fernando', '87894561', 'Puntarenas, Costa Rica', v_id_cliente);
    DBMS_OUTPUT.PUT_LINE('ID del cliente Fernando: ' || v_id_cliente);
    
    InsertarCliente('Laura', '85673219', 'Limón, Costa Rica', v_id_cliente);
    DBMS_OUTPUT.PUT_LINE('ID del cliente Laura: ' || v_id_cliente);
END;

-- insertar categorías de productos
CREATE OR REPLACE PROCEDURE InsertarCategoria(
    p_nombre IN VARCHAR2,
    p_id_categoria OUT NUMBER
) AS
BEGIN
    INSERT INTO Categorias (nombre)
    VALUES (p_nombre)
    RETURNING id_categoria INTO p_id_categoria;
    
    COMMIT;
END;

DECLARE 
    v_id_categoria NUMBER;
BEGIN
    InsertarCategoria('Frutas', v_id_categoria);
    DBMS_OUTPUT.PUT_LINE('ID de la categoría Frutas: ' || v_id_categoria);
    
    InsertarCategoria('Verduras', v_id_categoria);
    DBMS_OUTPUT.PUT_LINE('ID de la categoría Verduras: ' || v_id_categoria);
    
    InsertarCategoria('Hortalizas', v_id_categoria);
    DBMS_OUTPUT.PUT_LINE('ID de la categoría Hortalizas: ' || v_id_categoria);
    
    InsertarCategoria('Legumbres', v_id_categoria);
    DBMS_OUTPUT.PUT_LINE('ID de la categoría Legumbres: ' || v_id_categoria);
END;

-- insertar productos
CREATE OR REPLACE PROCEDURE InsertarProducto(
    p_nombre IN VARCHAR2,
    p_descripcion IN VARCHAR2,
    p_precio IN DECIMAL,
    p_id_categoria IN NUMBER,
    p_id_producto OUT NUMBER
) AS
BEGIN
    INSERT INTO Productos (nombre, descripcion, precio, id_categoria)
    VALUES (p_nombre, p_descripcion, p_precio, p_id_categoria)
    RETURNING id_producto INTO p_id_producto;
    
    COMMIT;
END;

DECLARE 
    v_id_producto NUMBER;
BEGIN
    InsertarProducto('Manzana', 'Fruta roja.', 500, 1, v_id_producto);
    DBMS_OUTPUT.PUT_LINE('ID del producto Manzana: ' || v_id_producto);
    
    InsertarProducto('Banano', 'Fruta dulce y rica en potasio.', 300, 1, v_id_producto);
    DBMS_OUTPUT.PUT_LINE('ID del producto Banano: ' || v_id_producto);
    
    InsertarProducto('Naranja', 'Cítrico, alto en vitamina C.', 400, 1, v_id_producto);
    DBMS_OUTPUT.PUT_LINE('ID del producto Naranja: ' || v_id_producto);
    
    InsertarProducto('Zanahoria', 'Verdura crujiente, alta en vitamina A.', 250, 2, v_id_producto);
    DBMS_OUTPUT.PUT_LINE('ID del producto Zanahoria: ' || v_id_producto);
    
    InsertarProducto('Papa', 'Tubérculo, con nutrientes como hierro y calcio.', 600, 2, v_id_producto);
    DBMS_OUTPUT.PUT_LINE('ID del producto Papa: ' || v_id_producto);
    
    InsertarProducto('Lechuga', 'Hortaliza fresca, base de muchas ensaladas.', 350, 3, v_id_producto);
    DBMS_OUTPUT.PUT_LINE('ID del producto Lechuga: ' || v_id_producto);
    
    InsertarProducto('Tomate', 'Fruto usado en ensaladas y salsas.', 450, 3, v_id_producto);
    DBMS_OUTPUT.PUT_LINE('ID del producto Tomate: ' || v_id_producto);
    
    InsertarProducto('Pepino', 'Hortaliza con alto contenido de agua.', 300, 3, v_id_producto);
    DBMS_OUTPUT.PUT_LINE('ID del producto Pepino: ' || v_id_producto);
    
    InsertarProducto('Frijoles', 'Legumbre rica en proteínas y fibra.', 900, 4, v_id_producto);
    DBMS_OUTPUT.PUT_LINE('ID del producto Frijoles: ' || v_id_producto);
    
    InsertarProducto('Lentejas', 'Legumbre ideal para sopas.', 850, 4, v_id_producto);
    DBMS_OUTPUT.PUT_LINE('ID del producto Lentejas: ' || v_id_producto);
END;

-- insertar inventario
CREATE OR REPLACE PROCEDURE InsertarInventario(
    p_id_producto IN NUMBER,
    p_cantidad IN NUMBER,
    p_fecha_vencimiento IN DATE,
    p_id_inventario OUT NUMBER
) AS
BEGIN
    INSERT INTO Inventario (id_producto, cantidad, fecha_vencimiento)
    VALUES (p_id_producto, p_cantidad, p_fecha_vencimiento)
    RETURNING id_inventario INTO p_id_inventario;
    
    COMMIT;
END;

DECLARE 
    v_id_inventario NUMBER;
BEGIN
    InsertarInventario(1, 50, TO_DATE('31-12-2025', 'DD-MM-YYYY'), v_id_inventario);
    DBMS_OUTPUT.PUT_LINE('ID de inventario para producto 1: ' || v_id_inventario);
    
    InsertarInventario(2, 30, TO_DATE('30-11-2025', 'DD-MM-YYYY'), v_id_inventario);
    DBMS_OUTPUT.PUT_LINE('ID de inventario para producto 2: ' || v_id_inventario);
    
    InsertarInventario(3, 40, TO_DATE('15-10-2025', 'DD-MM-YYYY'), v_id_inventario);
    DBMS_OUTPUT.PUT_LINE('ID de inventario para producto 3: ' || v_id_inventario);
    
    InsertarInventario(4, 60, TO_DATE('20-09-2025', 'DD-MM-YYYY'), v_id_inventario);
    DBMS_OUTPUT.PUT_LINE('ID de inventario para producto 4: ' || v_id_inventario);
    
    InsertarInventario(5, 80, TO_DATE('01-11-2025', 'DD-MM-YYYY'), v_id_inventario);
    DBMS_OUTPUT.PUT_LINE('ID de inventario para producto 5: ' || v_id_inventario);
    
    InsertarInventario(6, 70, TO_DATE('25-08-2025', 'DD-MM-YYYY'), v_id_inventario);
    DBMS_OUTPUT.PUT_LINE('ID de inventario para producto 6: ' || v_id_inventario);
    
    InsertarInventario(7, 55, TO_DATE('01-12-2025', 'DD-MM-YYYY'), v_id_inventario);
    DBMS_OUTPUT.PUT_LINE('ID de inventario para producto 7: ' || v_id_inventario);
    
    InsertarInventario(8, 65, TO_DATE('10-12-2025', 'DD-MM-YYYY'), v_id_inventario);
    DBMS_OUTPUT.PUT_LINE('ID de inventario para producto 8: ' || v_id_inventario);
    
    InsertarInventario(9, 100, TO_DATE('15-11-2025', 'DD-MM-YYYY'), v_id_inventario);
    DBMS_OUTPUT.PUT_LINE('ID de inventario para producto 9: ' || v_id_inventario);
    
    InsertarInventario(10, 90, TO_DATE('05-10-2025', 'DD-MM-YYYY'), v_id_inventario);
    DBMS_OUTPUT.PUT_LINE('ID de inventario para producto 10: ' || v_id_inventario);
END;

-- insertar proveedores
CREATE OR REPLACE PROCEDURE InsertarProveedor(
    p_nombre IN VARCHAR2,
    p_contacto IN VARCHAR2,
    p_telefono IN VARCHAR2,
    p_direccion IN VARCHAR2,
    p_id_proveedor OUT NUMBER
) AS
BEGIN
    INSERT INTO Proveedores (nombre, contacto, telefono, direccion)
    VALUES (p_nombre, p_contacto, p_telefono, p_direccion)
    RETURNING id_proveedor INTO p_id_proveedor;
    
    COMMIT;
END;

DECLARE 
    v_id_proveedor NUMBER;
BEGIN
    InsertarProveedor('Proveedor A', 'Contacto A', '1234567890', 'Direccion A', v_id_proveedor);
    DBMS_OUTPUT.PUT_LINE('ID del proveedor insertado: ' || v_id_proveedor);
    
    InsertarProveedor('Proveedor B', 'Contacto B', '0987654321', 'Direccion B', v_id_proveedor);
    DBMS_OUTPUT.PUT_LINE('ID del proveedor insertado: ' || v_id_proveedor);
    
    InsertarProveedor('Proveedor C', 'Contacto C', '1122334455', 'Direccion C', v_id_proveedor);
    DBMS_OUTPUT.PUT_LINE('ID del proveedor insertado: ' || v_id_proveedor);
    
    InsertarProveedor('Proveedor D', 'Contacto D', '6677889900', 'Direccion D', v_id_proveedor);
    DBMS_OUTPUT.PUT_LINE('ID del proveedor insertado: ' || v_id_proveedor);
END;

-- insertar compras
CREATE OR REPLACE PROCEDURE InsertarCompra(
    p_id_proveedor IN NUMBER,
    p_total IN DECIMAL,
    p_id_compra OUT NUMBER
) AS
BEGIN
    INSERT INTO Compras (id_proveedor, total)
    VALUES (p_id_proveedor, p_total)
    RETURNING id_compra INTO p_id_compra;
    
    COMMIT;
END;

DECLARE 
    v_id_compra NUMBER;
BEGIN
    InsertarCompra(1, 1500.00, v_id_compra);
    DBMS_OUTPUT.PUT_LINE('ID de la compra insertada: ' || v_id_compra);
    
    InsertarCompra(2, 2000.00, v_id_compra);
    DBMS_OUTPUT.PUT_LINE('ID de la compra insertada: ' || v_id_compra);
    
    InsertarCompra(3, 1800.00, v_id_compra);
    DBMS_OUTPUT.PUT_LINE('ID de la compra insertada: ' || v_id_compra);
    
    InsertarCompra(4, 2200.00, v_id_compra);
    DBMS_OUTPUT.PUT_LINE('ID de la compra insertada: ' || v_id_compra);
    
    InsertarCompra(1, 2500.00, v_id_compra);
    DBMS_OUTPUT.PUT_LINE('ID de la compra insertada: ' || v_id_compra);
    
    InsertarCompra(2, 3000.00, v_id_compra);
    DBMS_OUTPUT.PUT_LINE('ID de la compra insertada: ' || v_id_compra);
    
    InsertarCompra(3, 1700.00, v_id_compra);
    DBMS_OUTPUT.PUT_LINE('ID de la compra insertada: ' || v_id_compra);
END;

-- insertar detalle de compras
CREATE OR REPLACE PROCEDURE InsertarDetalleCompra(
    p_id_compra IN NUMBER,
    p_id_producto IN NUMBER,
    p_cantidad IN NUMBER,
    p_precio_unitario IN DECIMAL,
    p_id_detalle_compra OUT NUMBER
) AS
BEGIN
    INSERT INTO Detalle_Compras (id_compra, id_producto, cantidad, precio_unitario)
    VALUES (p_id_compra, p_id_producto, p_cantidad, p_precio_unitario)
    RETURNING id_detalle_compra INTO p_id_detalle_compra;
    
    COMMIT;
END;

DECLARE 
    v_id_detalle_compra NUMBER;
BEGIN
    InsertarDetalleCompra(1, 5, 20, 600, v_id_detalle_compra);
    DBMS_OUTPUT.PUT_LINE('ID del detalle de compra insertado: ' || v_id_detalle_compra);
    
    InsertarDetalleCompra(2, 7, 15, 450, v_id_detalle_compra);
    DBMS_OUTPUT.PUT_LINE('ID del detalle de compra insertado: ' || v_id_detalle_compra);
    
    InsertarDetalleCompra(3, 3, 10, 400, v_id_detalle_compra);
    DBMS_OUTPUT.PUT_LINE('ID del detalle de compra insertado: ' || v_id_detalle_compra);
    
    InsertarDetalleCompra(4, 2, 25, 300, v_id_detalle_compra);
    DBMS_OUTPUT.PUT_LINE('ID del detalle de compra insertado: ' || v_id_detalle_compra);
    
    InsertarDetalleCompra(5, 8, 30, 300, v_id_detalle_compra);
    DBMS_OUTPUT.PUT_LINE('ID del detalle de compra insertado: ' || v_id_detalle_compra);
    
    InsertarDetalleCompra(6, 10, 12, 850, v_id_detalle_compra);
    DBMS_OUTPUT.PUT_LINE('ID del detalle de compra insertado: ' || v_id_detalle_compra);
    
    InsertarDetalleCompra(7, 6, 18, 350, v_id_detalle_compra);
    DBMS_OUTPUT.PUT_LINE('ID del detalle de compra insertado: ' || v_id_detalle_compra);
    
    InsertarDetalleCompra(8, 1, 22, 500, v_id_detalle_compra);
    DBMS_OUTPUT.PUT_LINE('ID del detalle de compra insertado: ' || v_id_detalle_compra);
    
    InsertarDetalleCompra(9, 4, 10, 250, v_id_detalle_compra);
    DBMS_OUTPUT.PUT_LINE('ID del detalle de compra insertado: ' || v_id_detalle_compra);
    
    InsertarDetalleCompra(10, 9, 50, 900, v_id_detalle_compra);
    DBMS_OUTPUT.PUT_LINE('ID del detalle de compra insertado: ' || v_id_detalle_compra);
END;

-- insertar ventas
CREATE OR REPLACE PROCEDURE InsertarVenta(
    p_id_cliente IN NUMBER,
    p_total IN DECIMAL,
    p_id_venta OUT NUMBER
) AS
BEGIN
    INSERT INTO Ventas (id_cliente, total)
    VALUES (p_id_cliente, p_total)
    RETURNING id_venta INTO p_id_venta;
    
    COMMIT;
END;

DECLARE 
    v_id_venta NUMBER;
BEGIN
    InsertarVenta(1, 12000, v_id_venta);
    DBMS_OUTPUT.PUT_LINE('ID de la venta insertada: ' || v_id_venta);
    
    InsertarVenta(2, 7550, v_id_venta);
    DBMS_OUTPUT.PUT_LINE('ID de la venta insertada: ' || v_id_venta);
    
    InsertarVenta(3, 9800, v_id_venta);
    DBMS_OUTPUT.PUT_LINE('ID de la venta insertada: ' || v_id_venta);
    
    InsertarVenta(4, 15500, v_id_venta);
    DBMS_OUTPUT.PUT_LINE('ID de la venta insertada: ' || v_id_venta);
    
    InsertarVenta(5, 6000, v_id_venta);
    DBMS_OUTPUT.PUT_LINE('ID de la venta insertada: ' || v_id_venta);
    
    InsertarVenta(1, 18000, v_id_venta);
    DBMS_OUTPUT.PUT_LINE('ID de la venta insertada: ' || v_id_venta);
    
    InsertarVenta(2, 25000, v_id_venta);
    DBMS_OUTPUT.PUT_LINE('ID de la venta insertada: ' || v_id_venta);
    
    InsertarVenta(3, 9500, v_id_venta);
    DBMS_OUTPUT.PUT_LINE('ID de la venta insertada: ' || v_id_venta);
END;

-- insertar detalle de ventas
CREATE OR REPLACE PROCEDURE InsertarDetalleVenta(
    p_id_venta IN NUMBER,
    p_id_producto IN NUMBER,
    p_cantidad IN NUMBER,
    p_precio_unitario IN DECIMAL,
    p_id_detalle_venta OUT NUMBER
) AS
BEGIN
    INSERT INTO Detalle_Ventas (id_venta, id_producto, cantidad, precio_unitario)
    VALUES (p_id_venta, p_id_producto, p_cantidad, p_precio_unitario)
    RETURNING id_detalle_venta INTO p_id_detalle_venta;
    
    COMMIT;
END;

DECLARE 
    v_id_detalle_venta NUMBER;
BEGIN
    InsertarDetalleVenta(1, 1, 5, 1200, v_id_detalle_venta);
    DBMS_OUTPUT.PUT_LINE('ID del detalle de venta insertado: ' || v_id_detalle_venta);
    
    InsertarDetalleVenta(2, 3, 8, 580, v_id_detalle_venta);
    DBMS_OUTPUT.PUT_LINE('ID del detalle de venta insertado: ' || v_id_detalle_venta);
    
    InsertarDetalleVenta(3, 4, 10, 400, v_id_detalle_venta);
    DBMS_OUTPUT.PUT_LINE('ID del detalle de venta insertado: ' || v_id_detalle_venta);
    
    InsertarDetalleVenta(4, 5, 12, 800, v_id_detalle_venta);
    DBMS_OUTPUT.PUT_LINE('ID del detalle de venta insertado: ' || v_id_detalle_venta);
    
    InsertarDetalleVenta(5, 6, 6, 750, v_id_detalle_venta);
    DBMS_OUTPUT.PUT_LINE('ID del detalle de venta insertado: ' || v_id_detalle_venta);
    
    InsertarDetalleVenta(6, 7, 15, 900, v_id_detalle_venta);
    DBMS_OUTPUT.PUT_LINE('ID del detalle de venta insertado: ' || v_id_detalle_venta);
    
    InsertarDetalleVenta(7, 8, 20, 600, v_id_detalle_venta);
    DBMS_OUTPUT.PUT_LINE('ID del detalle de venta insertado: ' || v_id_detalle_venta);
    
    InsertarDetalleVenta(8, 9, 10, 850, v_id_detalle_venta);
    DBMS_OUTPUT.PUT_LINE('ID del detalle de venta insertado: ' || v_id_detalle_venta);
END;

-- insertar pagosCREATE OR REPLACE PROCEDURE InsertarPago(
    p_id_venta IN NUMBER,
    p_metodo_pago IN VARCHAR2,
    p_monto IN DECIMAL,
    p_id_pago OUT NUMBER
) AS
BEGIN
    INSERT INTO Pagos (id_venta, metodo_pago, monto)
    VALUES (p_id_venta, p_metodo_pago, p_monto)
    RETURNING id_pago INTO p_id_pago;
    
    COMMIT;
END;

DECLARE 
    v_id_pago NUMBER;
BEGIN
    InsertarPago(1, 'Efectivo', 12000, v_id_pago);
    DBMS_OUTPUT.PUT_LINE('ID del pago insertado: ' || v_id_pago);
    
    InsertarPago(2, 'Tarjeta', 7550, v_id_pago);
    DBMS_OUTPUT.PUT_LINE('ID del pago insertado: ' || v_id_pago);
    
    InsertarPago(3, 'Transferencia', 9800, v_id_pago);
    DBMS_OUTPUT.PUT_LINE('ID del pago insertado: ' || v_id_pago);
    
    InsertarPago(4, 'Efectivo', 15500, v_id_pago);
    DBMS_OUTPUT.PUT_LINE('ID del pago insertado: ' || v_id_pago);
    
    InsertarPago(5, 'Tarjeta', 6000, v_id_pago);
    DBMS_OUTPUT.PUT_LINE('ID del pago insertado: ' || v_id_pago);
    
    InsertarPago(6, 'Efectivo', 18000, v_id_pago);
    DBMS_OUTPUT.PUT_LINE('ID del pago insertado: ' || v_id_pago);
    
    InsertarPago(7, 'Transferencia', 25000, v_id_pago);
    DBMS_OUTPUT.PUT_LINE('ID del pago insertado: ' || v_id_pago);
    
    InsertarPago(8, 'Tarjeta', 9500, v_id_pago);
    DBMS_OUTPUT.PUT_LINE('ID del pago insertado: ' || v_id_pago);
END;

-- insertar devoluciones
CREATE OR REPLACE PROCEDURE InsertarDevolucion(
    p_id_venta IN NUMBER,
    p_motivo IN VARCHAR2,
    p_id_devolucion OUT NUMBER
) AS
BEGIN
    INSERT INTO Devoluciones (id_venta, motivo)
    VALUES (p_id_venta, p_motivo)
    RETURNING id_devolucion INTO p_id_devolucion;
    
    COMMIT;
END;

DECLARE 
    v_id_devolucion NUMBER;
BEGIN
    InsertarDevolucion(1, 'Cambio por otro producto', v_id_devolucion);
    DBMS_OUTPUT.PUT_LINE('ID de la devolución insertado: ' || v_id_devolucion);
    
    InsertarDevolucion(2, 'Producto defectuoso', v_id_devolucion);
    DBMS_OUTPUT.PUT_LINE('ID de la devolución insertado: ' || v_id_devolucion);
    
    InsertarDevolucion(3, 'Cancelación de la compra', v_id_devolucion);
    DBMS_OUTPUT.PUT_LINE('ID de la devolución insertado: ' || v_id_devolucion);
    
    InsertarDevolucion(4, 'Producto no deseado', v_id_devolucion);
    DBMS_OUTPUT.PUT_LINE('ID de la devolución insertado: ' || v_id_devolucion);
    
    InsertarDevolucion(5, 'Error en la cantidad', v_id_devolucion);
    DBMS_OUTPUT.PUT_LINE('ID de la devolución insertado: ' || v_id_devolucion);
END;

-- insertar gastos
CREATE OR REPLACE PROCEDURE InsertarGasto(
    p_descripcion IN VARCHAR2,
    p_monto IN DECIMAL,
    p_fecha IN DATE,
    p_id_gasto OUT NUMBER
) AS
BEGIN
    INSERT INTO Gastos (descripcion, monto, fecha)
    VALUES (p_descripcion, p_monto, p_fecha)
    RETURNING id_gasto INTO p_id_gasto;
    
    COMMIT;
END;

DECLARE 
    v_id_gasto NUMBER;
BEGIN
    InsertarGasto('Pago de electricidad', 15000, TO_DATE('01-03-2025', 'DD-MM-YYYY'), v_id_gasto);
    DBMS_OUTPUT.PUT_LINE('ID del gasto insertado: ' || v_id_gasto);
    
    InsertarGasto('Compra de suministros', 8000, TO_DATE('05-03-2025', 'DD-MM-YYYY'), v_id_gasto);
    DBMS_OUTPUT.PUT_LINE('ID del gasto insertado: ' || v_id_gasto);
    
    InsertarGasto('Mantenimiento de equipo', 2500, TO_DATE('10-03-2025', 'DD-MM-YYYY'), v_id_gasto);
    DBMS_OUTPUT.PUT_LINE('ID del gasto insertado: ' || v_id_gasto);
    
    InsertarGasto('Pago de salarios', 30000, TO_DATE('15-03-2025', 'DD-MM-YYYY'), v_id_gasto);
    DBMS_OUTPUT.PUT_LINE('ID del gasto insertado: ' || v_id_gasto);
    
    InsertarGasto('Compra de mobiliario', 12000, TO_DATE('20-03-2025', 'DD-MM-YYYY'), v_id_gasto);
    DBMS_OUTPUT.PUT_LINE('ID del gasto insertado: ' || v_id_gasto);
END;

-- insertar promociones
CREATE OR REPLACE PROCEDURE InsertarPromocion(
    p_id_producto IN NUMBER,
    p_descuento IN DECIMAL,
    p_fecha_inicio IN DATE,
    p_fecha_fin IN DATE,
    p_id_promocion OUT NUMBER
) AS
BEGIN
    INSERT INTO Promociones (id_producto, descuento, fecha_inicio, fecha_fin)
    VALUES (p_id_producto, p_descuento, p_fecha_inicio, p_fecha_fin)
    RETURNING id_promocion INTO p_id_promocion;
    
    COMMIT;
END;

DECLARE 
    v_id_promocion NUMBER;
BEGIN
    InsertarPromocion(1, 100, TO_DATE('01-05-2025', 'DD-MM-YYYY'), TO_DATE('15-05-2025', 'DD-MM-YYYY'), v_id_promocion);
    DBMS_OUTPUT.PUT_LINE('ID de la promoción insertada: ' || v_id_promocion);
    
    InsertarPromocion(2, 150, TO_DATE('01-06-2025', 'DD-MM-YYYY'), TO_DATE('30-06-2025', 'DD-MM-YYYY'), v_id_promocion);
    DBMS_OUTPUT.PUT_LINE('ID de la promoción insertada: ' || v_id_promocion);
    
    InsertarPromocion(3, 50, TO_DATE('01-07-2025', 'DD-MM-YYYY'), TO_DATE('15-07-2025', 'DD-MM-YYYY'), v_id_promocion);
    DBMS_OUTPUT.PUT_LINE('ID de la promoción insertada: ' || v_id_promocion);
    
    InsertarPromocion(4, 200, TO_DATE('01-08-2025', 'DD-MM-YYYY'), TO_DATE('15-08-2025', 'DD-MM-YYYY'), v_id_promocion);
    DBMS_OUTPUT.PUT_LINE('ID de la promoción insertada: ' || v_id_promocion);
END;

-- insertar reportes
CREATE OR REPLACE PROCEDURE InsertarReporte(
    p_tipo IN VARCHAR2,
    p_descripcion IN VARCHAR2,
    p_id_reporte OUT NUMBER
) AS
BEGIN
    INSERT INTO Reportes (tipo, descripcion)
    VALUES (p_tipo, p_descripcion)
    RETURNING id_reporte INTO p_id_reporte;
    
    COMMIT;
END;

DECLARE 
    v_id_reporte NUMBER;
BEGIN
    InsertarReporte('Ventas', 'Reporte diario de ventas', v_id_reporte);
    DBMS_OUTPUT.PUT_LINE('ID del reporte insertado: ' || v_id_reporte);
    
    InsertarReporte('Inventario', 'Reporte de existencias por producto', v_id_reporte);
    DBMS_OUTPUT.PUT_LINE('ID del reporte insertado: ' || v_id_reporte);
    
    InsertarReporte('Devoluciones', 'Reporte de devoluciones realizadas', v_id_reporte);
    DBMS_OUTPUT.PUT_LINE('ID del reporte insertado: ' || v_id_reporte);
    
    InsertarReporte('Gastos', 'Reporte mensual de gastos', v_id_reporte);
    DBMS_OUTPUT.PUT_LINE('ID del reporte insertado: ' || v_id_reporte);
END;

-- insertar alertas
CREATE OR REPLACE PROCEDURE InsertarAlerta(
    p_tipo IN VARCHAR2,
    p_mensaje IN VARCHAR2,
    p_id_alerta OUT NUMBER
) AS
BEGIN
    INSERT INTO Alertas (tipo, mensaje)
    VALUES (p_tipo, p_mensaje)
    RETURNING id_alerta INTO p_id_alerta;
    
    COMMIT;
END;

DECLARE 
    v_id_alerta NUMBER;
BEGIN
    InsertarAlerta('Stock Bajo', 'El stock de Zanahoria está por debajo del mínimo', v_id_alerta);
    DBMS_OUTPUT.PUT_LINE('ID de la alerta insertada: ' || v_id_alerta);
    
    InsertarAlerta('Producto Vencido', 'La Papa ha vencido', v_id_alerta);
    DBMS_OUTPUT.PUT_LINE('ID de la alerta insertada: ' || v_id_alerta);
    
    InsertarAlerta('Stock Bajo', 'El stock de Lechuga está por debajo del mínimo', v_id_alerta);
    DBMS_OUTPUT.PUT_LINE('ID de la alerta insertada: ' || v_id_alerta);
    
    InsertarAlerta('Producto Vencido', 'Los tomates están en mal estado', v_id_alerta);
    DBMS_OUTPUT.PUT_LINE('ID de la alerta insertada: ' || v_id_alerta);
END;

-- insertar historial de precios
CREATE OR REPLACE PROCEDURE InsertarHistorialPrecio(
    p_id_producto IN NUMBER,
    p_precio_anterior IN DECIMAL,
    p_precio_nuevo IN DECIMAL,
    p_id_historial OUT NUMBER
) AS
BEGIN
    INSERT INTO Historial_Precios (id_producto, precio_anterior, precio_nuevo)
    VALUES (p_id_producto, p_precio_anterior, p_precio_nuevo)
    RETURNING id_historial INTO p_id_historial;
    
    COMMIT;
END;

DECLARE 
    v_id_historial NUMBER;
BEGIN
    InsertarHistorialPrecio(1, 1000, 1200, v_id_historial);
    DBMS_OUTPUT.PUT_LINE('ID del historial de precio insertado: ' || v_id_historial);
    
    InsertarHistorialPrecio(2, 600, 580, v_id_historial);
    DBMS_OUTPUT.PUT_LINE('ID del historial de precio insertado: ' || v_id_historial);
    
    InsertarHistorialPrecio(3, 450, 400, v_id_historial);
    DBMS_OUTPUT.PUT_LINE('ID del historial de precio insertado: ' || v_id_historial);
    
    InsertarHistorialPrecio(4, 900, 800, v_id_historial);
    DBMS_OUTPUT.PUT_LINE('ID del historial de precio insertado: ' || v_id_historial);
END;

-- insertar historial de stock
CREATE OR REPLACE PROCEDURE InsertarHistorialStock(
    p_id_producto IN NUMBER,
    p_cantidad_anterior IN NUMBER,
    p_cantidad_nueva IN NUMBER,
    p_id_historial OUT NUMBER
) AS
BEGIN
    INSERT INTO Historial_Stock (id_producto, cantidad_anterior, cantidad_nueva)
    VALUES (p_id_producto, p_cantidad_anterior, p_cantidad_nueva)
    RETURNING id_historial INTO p_id_historial;
    
    COMMIT;
END;

DECLARE 
    v_id_historial NUMBER;
BEGIN
    InsertarHistorialStock(1, 80, 75, v_id_historial);
    DBMS_OUTPUT.PUT_LINE('ID del historial de stock insertado: ' || v_id_historial);
    
    InsertarHistorialStock(2, 50, 45, v_id_historial);
    DBMS_OUTPUT.PUT_LINE('ID del historial de stock insertado: ' || v_id_historial);
    
    InsertarHistorialStock(3, 100, 90, v_id_historial);
    DBMS_OUTPUT.PUT_LINE('ID del historial de stock insertado: ' || v_id_historial);
    
    InsertarHistorialStock(4, 120, 110, v_id_historial);
    DBMS_OUTPUT.PUT_LINE('ID del historial de stock insertado: ' || v_id_historial);
END;

-- insertar logs del sistema
CREATE OR REPLACE PROCEDURE InsertarLog(
    p_id_usuario IN NUMBER,
    p_accion IN VARCHAR2,
    p_id_log OUT NUMBER
) AS
BEGIN
    INSERT INTO Logs (id_usuario, accion)
    VALUES (p_id_usuario, p_accion)
    RETURNING id_log INTO p_id_log;
    
    COMMIT;
END;

DECLARE 
    v_id_log NUMBER;
BEGIN
    InsertarLog(1, 'Actualización de inventario', v_id_log);
    DBMS_OUTPUT.PUT_LINE('ID del log insertado: ' || v_id_log);
    
    InsertarLog(2, 'Eliminación de producto', v_id_log);
    DBMS_OUTPUT.PUT_LINE('ID del log insertado: ' || v_id_log);
    
    InsertarLog(3, 'Inicio de sesión', v_id_log);
    DBMS_OUTPUT.PUT_LINE('ID del log insertado: ' || v_id_log);
    
    InsertarLog(4, 'Cambio de precio', v_id_log);
    DBMS_OUTPUT.PUT_LINE('ID del log insertado: ' || v_id_log);
    
    InsertarLog(1, 'Generación de reporte de ventas', v_id_log);
    DBMS_OUTPUT.PUT_LINE('ID del log insertado: ' || v_id_log);
END;