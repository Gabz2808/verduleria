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


