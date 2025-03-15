--TRIGGERS

--ActualizarStockVenta
CREATE OR REPLACE TRIGGER ActualizarStockVenta
AFTER INSERT ON Detalle_Ventas
FOR EACH ROW
BEGIN
  UPDATE Inventario
  SET cantidad = cantidad - :new.cantidad
  WHERE id_producto = :new.id_producto;
END;


--ActualizarStockCompra
CREATE OR REPLACE TRIGGER ActualizarStockCompra
AFTER INSERT ON Detalle_Compras
FOR EACH ROW
BEGIN
  UPDATE Inventario
  SET cantidad = cantidad + :new.cantidad
  WHERE id_producto = :new.id_producto;
END;


--GenerarAlertaStockMinimo
CREATE OR REPLACE TRIGGER GenerarAlertaStockMinimo
AFTER UPDATE ON Inventario
FOR EACH ROW
DECLARE
  stock_minimo NUMBER;
BEGIN
  -- Obtener el valor de stock_minimo
  SELECT stock_minimo INTO stock_minimo
  FROM Productos
  WHERE id_producto = :new.id_producto;
  
  -- Verificar si el stock es bajo
  IF :new.cantidad < stock_minimo THEN
    INSERT INTO Alertas (tipo, mensaje)
    VALUES ('Stock Bajo', 'El stock del producto ' || :new.id_producto || ' es bajo');
  END IF;
END;


--ActualizarTotalVenta
CREATE OR REPLACE TRIGGER ActualizarTotalVenta
AFTER UPDATE ON Detalle_Ventas
FOR EACH ROW
DECLARE
  totalVenta DECIMAL(10,2);
BEGIN
  SELECT SUM(precio_unitario * cantidad) INTO totalVenta
  FROM Detalle_Ventas
  WHERE id_venta = :new.id_venta;
  
  UPDATE Ventas
  SET total = totalVenta
  WHERE id_venta = :new.id_venta;
END;


--ActualizarTotalCompra
CREATE OR REPLACE TRIGGER ActualizarTotalCompra
AFTER UPDATE ON Detalle_Compras
FOR EACH ROW
DECLARE
  totalCompra DECIMAL(10,2);
BEGIN
  SELECT SUM(precio_unitario * cantidad) INTO totalCompra
  FROM Detalle_Compras
  WHERE id_compra = :new.id_compra;
  
  UPDATE Compras
  SET total = totalCompra
  WHERE id_compra = :new.id_compra;
END;


--CURSORES

--ObtenerProductosCursor
DECLARE
  CURSOR obtener_productos_cursor IS
    SELECT * FROM Productos;
BEGIN
  FOR producto IN obtener_productos_cursor LOOP
    DBMS_OUTPUT.PUT_LINE('Producto: ' || producto.nombre);
  END LOOP;
END;


--ObtenerCategoriasCursor
DECLARE
  CURSOR obtener_categorias_cursor IS
    SELECT * FROM Categorias;
BEGIN
  FOR categoria IN obtener_categorias_cursor LOOP
    DBMS_OUTPUT.PUT_LINE('Categoría: ' || categoria.nombre);
  END LOOP;
END;


--ObtenerClientesCursor
DECLARE
  CURSOR obtener_clientes_cursor IS
    SELECT * FROM Clientes;
BEGIN
  FOR cliente IN obtener_clientes_cursor LOOP
    DBMS_OUTPUT.PUT_LINE('Cliente: ' || cliente.nombre);
  END LOOP;
END;


--ObtenerProveedoresCursor
DECLARE
  CURSOR obtener_proveedores_cursor IS
    SELECT * FROM Proveedores;
BEGIN
  FOR proveedor IN obtener_proveedores_cursor LOOP
    DBMS_OUTPUT.PUT_LINE('Proveedor: ' || proveedor.nombre);
  END LOOP;
END;


--ObtenerVentasCursor
DECLARE
  CURSOR obtener_ventas_cursor IS
    SELECT * FROM Ventas;
BEGIN
  FOR venta IN obtener_ventas_cursor LOOP
    DBMS_OUTPUT.PUT_LINE('Venta: ' || venta.id_venta);
  END LOOP;
END;


--ObtenerComprasCursor
DECLARE
  CURSOR obtener_compras_cursor IS
    SELECT * FROM Compras;
BEGIN
  FOR compra IN obtener_compras_cursor LOOP
    DBMS_OUTPUT.PUT_LINE('Compra: ' || compra.id_compra);
  END LOOP;
END;


--ObtenerProductosCategoriaCursor
DECLARE
  CURSOR obtener_productos_categoria_cursor IS
    SELECT * FROM Productos WHERE id_categoria = :categoria_id;
BEGIN
  FOR producto IN obtener_productos_categoria_cursor LOOP
    DBMS_OUTPUT.PUT_LINE('Producto: ' || producto.nombre);
  END LOOP;
END;


--ObtenerVentasClienteCursor
DECLARE
  CURSOR obtener_ventas_cliente_cursor IS
    SELECT * FROM Ventas WHERE id_cliente = :cliente_id;
BEGIN
  FOR venta IN obtener_ventas_cliente_cursor LOOP
    DBMS_OUTPUT.PUT_LINE('Venta: ' || venta.id_venta);
  END LOOP;
END;


--ObtenerComprasProveedorCursor
DECLARE
  CURSOR obtener_compras_proveedor_cursor IS
    SELECT * FROM Compras WHERE id_proveedor = :proveedor_id;
BEGIN
  FOR compra IN obtener_compras_proveedor_cursor LOOP
    DBMS_OUTPUT.PUT_LINE('Compra: ' || compra.id_compra);
  END LOOP;
END;


--ObtenerProductosStockMinimoCursor
DECLARE
  CURSOR obtener_productos_stock_minimo_cursor IS
    SELECT nombre, cantidad, stock_minimo
    FROM Productos
    WHERE cantidad < stock_minimo;
BEGIN
  FOR producto IN obtener_productos_stock_minimo_cursor LOOP
    DBMS_OUTPUT.PUT_LINE('Producto: ' || producto.nombre || 
                         ', Stock actual: ' || producto.cantidad || 
                         ', Stock mínimo: ' || producto.stock_minimo);
  END LOOP;
END;


--ObtenerVentasFechaCursor
DECLARE
  CURSOR obtener_ventas_fecha_cursor IS
    SELECT * FROM Ventas WHERE fecha_venta = :fecha_venta;
BEGIN
  FOR venta IN obtener_ventas_fecha_cursor LOOP
    DBMS_OUTPUT.PUT_LINE('Venta: ' || venta.id_venta);
  END LOOP;
END;


--ObtenerComprasFechaCursor
DECLARE
  CURSOR obtener_compras_fecha_cursor IS
    SELECT * FROM Compras WHERE fecha_compra = :fecha_compra;
BEGIN
  FOR compra IN obtener_compras_fecha_cursor LOOP
    DBMS_OUTPUT.PUT_LINE('Compra: ' || compra.id_compra);
  END LOOP;
END;

--ObtenerProductosMasVendidosCursor
DECLARE
  CURSOR obtener_productos_mas_vendidos_cursor IS
    SELECT p.nombre, SUM(dv.cantidad) AS total_vendido
    FROM Productos p
    JOIN Detalle_Ventas dv ON p.id_producto = dv.id_producto
    GROUP BY p.nombre
    ORDER BY total_vendido DESC;
BEGIN
  FOR producto IN obtener_productos_mas_vendidos_cursor LOOP
    DBMS_OUTPUT.PUT_LINE('Producto más vendido: ' || producto.nombre || ', Total vendido: ' || producto.total_vendido);
  END LOOP;
END;

--ObtenerProductosMasCompradosCursor
DECLARE
  CURSOR obtener_productos_mas_comprados_cursor IS
    SELECT p.nombre, SUM(dc.cantidad) AS total_comprado
    FROM Productos p
    JOIN Detalle_Compras dc ON p.id_producto = dc.id_producto
    GROUP BY p.nombre
    ORDER BY total_comprado DESC;
BEGIN
  FOR producto IN obtener_productos_mas_comprados_cursor LOOP
    DBMS_OUTPUT.PUT_LINE('Producto más comprado: ' || producto.nombre || ', Total comprado: ' || producto.total_comprado);
  END LOOP;
END;


--ObtenerInventarioValorizadoCursor
DECLARE
  CURSOR obtener_inventario_valorizado_cursor IS
    SELECT p.nombre, i.cantidad, p.precio, (i.cantidad * p.precio) AS valor_inventario
    FROM Inventario i
    JOIN Productos p ON i.id_producto = p.id_producto;
BEGIN
  FOR inventario IN obtener_inventario_valorizado_cursor LOOP
    DBMS_OUTPUT.PUT_LINE('Producto: ' || inventario.nombre || ', Cantidad: ' || inventario.cantidad || ', Valor: ' || inventario.valor_inventario);
  END LOOP;
END;

