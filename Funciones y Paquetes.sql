/*Funcion para obtener el precio del producto*/
CREATE OR REPLACE FUNCTION GetPrecioProducto(p_producto_id IN NUMBER) RETURN NUMBER IS
    v_precio NUMBER;
BEGIN
    SELECT precio
    INTO v_precio
    FROM productos
    WHERE id_producto = p_producto_id;
    
    RETURN v_precio;
END;

/*Funcion para obtener el nombre de la categoria*/
CREATE OR REPLACE FUNCTION GetNombreCategoria(p_categoria_id IN NUMBER) RETURN VARCHAR2 IS
    v_nombre_categoria VARCHAR2(100);
BEGIN
    SELECT nombre
    INTO v_nombre_categoria
    FROM categorias
    WHERE id_categoria = p_categoria_id;
    
    RETURN v_nombre_categoria;
END;

/*Funcion para obtener el nombre del cliente*/
CREATE OR REPLACE FUNCTION GetNombreCliente(p_cliente_id IN NUMBER) RETURN VARCHAR2 IS
    v_nombre_cliente VARCHAR2(100);
BEGIN
    SELECT nombre
    INTO v_nombre_cliente
    FROM clientes
    WHERE id_cliente = p_cliente_id;
    
    RETURN v_nombre_cliente;
END;

/*Funcion para obtener el nombre del proveedor*/
CREATE OR REPLACE FUNCTION GetNombreProveedor(p_proveedor_id IN NUMBER) RETURN VARCHAR2 IS
    v_nombre_proveedor VARCHAR2(100);
BEGIN
    SELECT nombre
    INTO v_nombre_proveedor
    FROM proveedores
    WHERE id_proveedor = p_proveedor_id;
    
    RETURN v_nombre_proveedor;
END;

/*Funcion para obtener el total de ventas de un producto*/
CREATE OR REPLACE FUNCTION GetTotalVenta(p_producto_id IN NUMBER) RETURN NUMBER IS
    v_total_venta NUMBER;
BEGIN
    SELECT SUM(dv.cantidad * dv.precio_unitario)
    INTO v_total_venta
    FROM detalle_ventas dv
    JOIN ventas v ON dv.id_venta = v.id_venta
    WHERE dv.id_producto = p_producto_id;
    
    RETURN v_total_venta;
END;

/*Funcion para obtener el total de compras de un producto*/
CREATE OR REPLACE FUNCTION GetTotalCompra(p_producto_id IN NUMBER) RETURN NUMBER IS
    v_total_compra NUMBER;
BEGIN
    SELECT SUM(dc.cantidad * dc.precio_unitario)
    INTO v_total_compra
    FROM detalle_compras dc
    JOIN compras c ON dc.id_compra = c.id_compra
    WHERE dc.id_producto = p_producto_id;
    
    RETURN v_total_compra;
END;

/*Funcion para calcular el impuesto de venta IVA*/
CREATE OR REPLACE FUNCTION CalcularImpuestoVenta(p_monto_venta IN NUMBER) RETURN NUMBER IS
    v_impuesto NUMBER;
BEGIN
    v_impuesto := p_monto_venta * 0.13;
    
    RETURN v_impuesto;
END;

/*Funcion para calcular el descuento de la compra (10%)*/
CREATE OR REPLACE FUNCTION CalcularDescuentoCompra(p_monto_compra IN NUMBER) RETURN NUMBER IS
    v_descuento NUMBER;
BEGIN
    v_descuento := p_monto_compra * 0.10;
    
    RETURN v_descuento;
END;

/*Funcion para obtener la cantidad de stock de un producto*/
CREATE OR REPLACE FUNCTION GetStockProducto(p_producto_id IN NUMBER) RETURN NUMBER IS
    v_stock NUMBER;
BEGIN
    SELECT cantidad
    INTO v_stock
    FROM inventario
    WHERE id_producto = p_producto_id;
    
    RETURN v_stock;
END;

/*Funcion para obtener la cantidad de ventas que se realizaron por cliente*/
CREATE OR REPLACE FUNCTION GetCantidadVentasCliente(p_cliente_id IN NUMBER) RETURN NUMBER IS
    v_cantidad_ventas NUMBER;
BEGIN
    SELECT SUM(dv.cantidad)
    INTO v_cantidad_ventas
    FROM detalle_ventas dv
    JOIN ventas v ON dv.id_venta = v.id_venta
    WHERE v.id_cliente = p_cliente_id;
    
    RETURN v_cantidad_ventas;
END;

/*Funcion para obtener la cantidad total de compras realizadas por un proveedor*/
CREATE OR REPLACE FUNCTION GetCantidadComprasProveedor(p_proveedor_id IN NUMBER) RETURN NUMBER IS
    v_cantidad_compras NUMBER;
BEGIN
    SELECT SUM(dc.cantidad)
    INTO v_cantidad_compras
    FROM detalle_compras dc
    JOIN compras c ON dc.id_compra = c.id_compra
    WHERE c.id_proveedor = p_proveedor_id;
    
    RETURN v_cantidad_compras;
END;

/*Funcion para obtener el promedio de las ventas*/
CREATE OR REPLACE FUNCTION GetPromedioVentas(p_producto_id IN NUMBER) RETURN NUMBER IS
    v_promedio_ventas NUMBER;
BEGIN
    SELECT AVG(dv.cantidad * dv.precio_unitario)
    INTO v_promedio_ventas
    FROM detalle_ventas dv
    JOIN ventas v ON dv.id_venta = v.id_venta
    WHERE dv.id_producto = p_producto_id;
    
    RETURN v_promedio_ventas;
END;

/*Funcion para obtener el promedio de compras de un producto*/
CREATE OR REPLACE FUNCTION GetPromedioCompras(p_producto_id IN NUMBER) RETURN NUMBER IS
    v_promedio_compras NUMBER;
BEGIN
    SELECT AVG(dc.cantidad * dc.precio_unitario)
    INTO v_promedio_compras
    FROM detalle_compras dc
    JOIN compras c ON dc.id_compra = c.id_compra
    WHERE dc.id_producto = p_producto_id;
    
    RETURN v_promedio_compras;
END;

/*Funcion para obtener el producto mas vendido*/
CREATE OR REPLACE FUNCTION GetProductoMasVendido RETURN NUMBER IS
    v_producto_id NUMBER;
BEGIN
    SELECT id_producto
    INTO v_producto_id
    FROM (
        SELECT id_producto, SUM(cantidad) AS total_vendido
        FROM detalle_ventas
        GROUP BY id_producto
        ORDER BY total_vendido DESC
    ) WHERE ROWNUM = 1;
    
    RETURN v_producto_id;
END;

/*Funcion para obtener el producto mas comprado*/
CREATE OR REPLACE FUNCTION GetProductoMasComprado RETURN NUMBER IS
    v_producto_id NUMBER;
BEGIN
    SELECT id_producto
    INTO v_producto_id
    FROM (
        SELECT id_producto, SUM(cantidad) AS total_comprado
        FROM detalle_compras
        GROUP BY id_producto
        ORDER BY total_comprado DESC
    ) WHERE ROWNUM = 1;
    
    RETURN v_producto_id;
END;

/*Paquetes*/
/*ProductosPkg*/
CREATE OR REPLACE PACKAGE ProductosPkg AS
    FUNCTION GetPrecioProducto(id_producto NUMBER) RETURN DECIMAL;
    FUNCTION GetStockProducto(id_producto NUMBER) RETURN NUMBER;
    FUNCTION GetProductoMasVendido RETURN NUMBER;
    FUNCTION GetProductoMasComprado RETURN NUMBER;
END ProductosPkg;
/

CREATE OR REPLACE PACKAGE BODY ProductosPkg AS
    FUNCTION GetPrecioProducto(id_producto NUMBER) RETURN DECIMAL IS
        v_precio DECIMAL(10,2);
    BEGIN
        SELECT precio INTO v_precio FROM Productos WHERE id_producto = id_producto;
        RETURN v_precio;
    END;
    
    FUNCTION GetStockProducto(id_producto NUMBER) RETURN NUMBER IS
        v_stock NUMBER;
    BEGIN
        SELECT cantidad INTO v_stock FROM Inventario WHERE id_producto = id_producto;
        RETURN v_stock;
    END;
    
    FUNCTION GetProductoMasVendido RETURN NUMBER IS
        v_producto NUMBER;
    BEGIN
        SELECT id_producto INTO v_producto FROM Detalle_Ventas
        GROUP BY id_producto ORDER BY SUM(cantidad) DESC FETCH FIRST 1 ROW ONLY;
        RETURN v_producto;
    END;
    
    FUNCTION GetProductoMasComprado RETURN NUMBER IS
        v_producto NUMBER;
    BEGIN
        SELECT id_producto INTO v_producto FROM Detalle_Compras
        GROUP BY id_producto ORDER BY SUM(cantidad) DESC FETCH FIRST 1 ROW ONLY;
        RETURN v_producto;
    END;
END ProductosPkg;
/

/*CategoriasPkg*/
CREATE OR REPLACE PACKAGE CategoriasPkg AS
    FUNCTION GetNombreCategoria(id_categoria NUMBER) RETURN VARCHAR2;
END CategoriasPkg;
/

CREATE OR REPLACE PACKAGE BODY CategoriasPkg AS
    FUNCTION GetNombreCategoria(id_categoria NUMBER) RETURN VARCHAR2 IS
        v_nombre VARCHAR2(100);
    BEGIN
        SELECT nombre INTO v_nombre FROM Categorias WHERE id_categoria = id_categoria;
        RETURN v_nombre;
    END;
END CategoriasPkg;
/

/*ClientesPkg*/
CREATE OR REPLACE PACKAGE ClientesPkg AS
    FUNCTION GetNombreCliente(id_cliente NUMBER) RETURN VARCHAR2;
    FUNCTION GetCantidadVentasCliente(id_cliente NUMBER) RETURN NUMBER;
END ClientesPkg;
/

CREATE OR REPLACE PACKAGE BODY ClientesPkg AS
    FUNCTION GetNombreCliente(id_cliente NUMBER) RETURN VARCHAR2 IS
        v_nombre VARCHAR2(100);
    BEGIN
        SELECT nombre INTO v_nombre FROM Clientes WHERE id_cliente = id_cliente;
        RETURN v_nombre;
    END;
    
    FUNCTION GetCantidadVentasCliente(id_cliente NUMBER) RETURN NUMBER IS
        v_cantidad NUMBER;
    BEGIN
        SELECT COUNT(*) INTO v_cantidad FROM Ventas WHERE id_cliente = id_cliente;
        RETURN v_cantidad;
    END;
END ClientesPkg;
/

/*ProveedoresPkg*/
CREATE OR REPLACE PACKAGE ProveedoresPkg AS
    FUNCTION GetNombreProveedor(id_proveedor NUMBER) RETURN VARCHAR2;
    FUNCTION GetCantidadComprasProveedor(id_proveedor NUMBER) RETURN NUMBER;
END ProveedoresPkg;
/

CREATE OR REPLACE PACKAGE BODY ProveedoresPkg AS
    FUNCTION GetNombreProveedor(id_proveedor NUMBER) RETURN VARCHAR2 IS
        v_nombre VARCHAR2(100);
    BEGIN
        SELECT nombre INTO v_nombre FROM Proveedores WHERE id_proveedor = id_proveedor;
        RETURN v_nombre;
    END;
    
    FUNCTION GetCantidadComprasProveedor(id_proveedor NUMBER) RETURN NUMBER IS
        v_cantidad NUMBER;
    BEGIN
        SELECT COUNT(*) INTO v_cantidad FROM Compras WHERE id_proveedor = id_proveedor;
        RETURN v_cantidad;
    END;
END ProveedoresPkg;
/

/*VentasPkg*/
CREATE OR REPLACE PACKAGE VentasPkg AS
    FUNCTION GetTotalVenta(id_venta NUMBER) RETURN DECIMAL;
    FUNCTION CalcularImpuestoVenta(id_venta NUMBER) RETURN DECIMAL;
END VentasPkg;
/

CREATE OR REPLACE PACKAGE BODY VentasPkg AS
    FUNCTION GetTotalVenta(id_venta NUMBER) RETURN DECIMAL IS
        v_total DECIMAL(10,2);
    BEGIN
        SELECT total INTO v_total FROM Ventas WHERE id_venta = id_venta;
        RETURN v_total;
    END;
    
    FUNCTION CalcularImpuestoVenta(id_venta NUMBER) RETURN DECIMAL IS
        v_total DECIMAL(10,2);
    BEGIN
        SELECT total * 0.13 INTO v_total FROM Ventas WHERE id_venta = id_venta;
        RETURN v_total;
    END;
END VentasPkg;
/

/*ComprasPkg*/
CREATE OR REPLACE PACKAGE ComprasPkg AS
    FUNCTION GetTotalCompra(id_compra NUMBER) RETURN DECIMAL;
    FUNCTION CalcularDescuentoCompra(id_compra NUMBER) RETURN DECIMAL;
END ComprasPkg;
/

CREATE OR REPLACE PACKAGE BODY ComprasPkg AS
    FUNCTION GetTotalCompra(id_compra NUMBER) RETURN DECIMAL IS
        v_total DECIMAL(10,2);
    BEGIN
        SELECT total INTO v_total FROM Compras WHERE id_compra = id_compra;
        RETURN v_total;
    END;
    
    FUNCTION CalcularDescuentoCompra(id_compra NUMBER) RETURN DECIMAL IS
        v_descuento DECIMAL(10,2);
    BEGIN
        SELECT total * 0.10 INTO v_descuento FROM Compras WHERE id_compra = id_compra;
        RETURN v_descuento;
    END;
END ComprasPkg;
/

/*ReportesPkg*/
CREATE OR REPLACE PACKAGE ReportesPkg AS
    FUNCTION GetPromedioVentas RETURN DECIMAL;
    FUNCTION GetPromedioCompras RETURN DECIMAL;
END ReportesPkg;
/

CREATE OR REPLACE PACKAGE BODY ReportesPkg AS
    FUNCTION GetPromedioVentas RETURN DECIMAL IS
        v_promedio DECIMAL(10,2);
    BEGIN
        SELECT AVG(total) INTO v_promedio FROM Ventas;
        RETURN v_promedio;
    END;
    
    FUNCTION GetPromedioCompras RETURN DECIMAL IS
        v_promedio DECIMAL(10,2);
    BEGIN
        SELECT AVG(total) INTO v_promedio FROM Compras;
        RETURN v_promedio;
    END;
END ReportesPkg;
/

/* InventarioPkg */
CREATE OR REPLACE PACKAGE InventarioPkg AS
    FUNCTION ActualizarStock(id_producto NUMBER, cantidad NUMBER) RETURN VARCHAR2;
    FUNCTION GetStockMinimoAlerta RETURN SYS_REFCURSOR;
END InventarioPkg;
/

CREATE OR REPLACE PACKAGE BODY InventarioPkg AS
    FUNCTION ActualizarStock(id_producto NUMBER, cantidad NUMBER) RETURN VARCHAR2 IS
    BEGIN
        UPDATE Inventario SET cantidad = cantidad + cantidad WHERE id_producto = id_producto;
        RETURN 'Stock actualizado correctamente';
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN 'Producto no encontrado';
    END;

    FUNCTION GetStockMinimoAlerta RETURN SYS_REFCURSOR IS
        v_cursor SYS_REFCURSOR;
    BEGIN
        OPEN v_cursor FOR SELECT id_producto, cantidad FROM Inventario WHERE cantidad < 10;
        RETURN v_cursor;
    END;
END InventarioPkg;
/

/* UsuariosPkg */
CREATE OR REPLACE PACKAGE UsuariosPkg AS
    FUNCTION ValidarUsuario(username VARCHAR2, password VARCHAR2) RETURN BOOLEAN;
    FUNCTION GetRolUsuario(id_usuario NUMBER) RETURN VARCHAR2;
END UsuariosPkg;
/

CREATE OR REPLACE PACKAGE BODY UsuariosPkg AS
    FUNCTION ValidarUsuario(username VARCHAR2, password VARCHAR2) RETURN BOOLEAN IS
        v_count NUMBER;
    BEGIN
        SELECT COUNT(*) INTO v_count FROM Usuarios WHERE nombre = username AND contrasena = password;
        RETURN v_count > 0;
    END;

    FUNCTION GetRolUsuario(id_usuario NUMBER) RETURN VARCHAR2 IS
        v_rol VARCHAR2(50);
    BEGIN
        SELECT id_rol INTO v_rol FROM Usuarios WHERE id_usuario = id_usuario;
        RETURN v_rol;
    END;
END UsuariosPkg;
/

/* AlertasPkg */
CREATE OR REPLACE PACKAGE AlertasPkg AS
    PROCEDURE RegistrarAlerta(mensaje VARCHAR2);
    FUNCTION GetAlertasRecientes RETURN SYS_REFCURSOR;
END AlertasPkg;
/

CREATE OR REPLACE PACKAGE BODY AlertasPkg AS
    PROCEDURE RegistrarAlerta(mensaje VARCHAR2) IS
    BEGIN
        INSERT INTO Alertas (mensaje, fecha) VALUES (mensaje, SYSDATE);
    END;

    FUNCTION GetAlertasRecientes RETURN SYS_REFCURSOR IS
        v_cursor SYS_REFCURSOR;
    BEGIN
        OPEN v_cursor FOR SELECT * FROM Alertas WHERE fecha > SYSDATE - 7;
        RETURN v_cursor;
    END;
END AlertasPkg;
/







