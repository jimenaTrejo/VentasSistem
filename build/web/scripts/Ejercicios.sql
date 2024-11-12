/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Jimena
 * Created: 11 nov 2024
 */

create database ejemploPrueba;
use ejemploPrueba;

--- 1. CONOCIMIENTOS SQL
--- 1.1) Describe el funcionamiento general de la sentencia JOIN.
		--- Nos sirve para obtener combinaciones de columnas a partir de varias tablas con ayuda de las conexiones de las llaves foraneas y primarias 
--- 1.2) ¿Cuáles son los tipos de JOIN y cuál es el funcionamiento de los mismos?
		--- INNER JOIN: Devuelve solo las filas que tienen un match en ambas tablas.
		--- LEFT JOIN: Devuelve todas las filas de la tabla de la izquierda y las filas que coinciden en la tabla de la derecha. Si no hay un match,
        --- los valores de la tabla de la derecha serán NULL.
		--- RIGHT JOIN: Es lo opuesto al LEFT JOIN. Devuelve todas las filas de la tabla de la derecha y las filas que coinciden en la tabla de la izquierda.
		--- FULL OUTER JOIN: Devuelve todas las filas de ambas tablas, tanto las que tienen un match como las que no.
--- 1.3) ¿Cuál el funcionamiento general de los TRIGGER y qué propósito tienen?
		--- Los triggers son como procedimientos almacenados que se ejecuta en automatico cuando ocurre un evento especifico
        --- estos nos permiten hacer inserciones, actualizaciones o eliminaciones. 
        --- el principal proposito es por ejemplo realizar alguna actualizacion en tablas relacionadas o por ejemplo registrar los cambios realizados en una tabla
---- 1.4) ¿Qué es y para qué sirve un STORED PROCEDURE
		--- Son procedimientos precompilados que podemos almacenar en nuestra base de datos para poder reutilizarlos en caso de necesitarlos,
        --- Ademas son seguros ya que se pueden establecer permisos para los usuarios para ejecutar determinados permisos. 
        
        
CREATE TABLE producto (
    idProducto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio double
);

INSERT INTO producto (nombre, precio) VALUES ('Laptop', 200);
INSERT INTO producto (nombre, precio) VALUES ('PC',  200);
INSERT INTO producto (nombre, precio) VALUES ('MOUSE', 200 );
INSERT INTO producto (nombre, precio) VALUES ('TECLADO', 200 );
INSERT INTO producto (nombre, precio) VALUES ('MONITOR', 200 );
INSERT INTO producto (nombre, precio) VALUES ('MICROFONO', 200 );
INSERT INTO producto (nombre, precio) VALUES ('AUDIFONOS', 200 );
select * from producto;
select * from venta;


CREATE TABLE venta
	(
    idVenta INT AUTO_INCREMENT PRIMARY KEY,
    idProducto int NOT NULL,
    Cantidad INT DEFAULT 0,
    FOREIGN KEY (idProducto) REFERENCES producto(idProducto)
); 


DELIMITER //
CREATE PROCEDURE registrar_venta(
    IN p_idProducto INT,
    IN p_cantidad INT
)
BEGIN
    INSERT INTO venta (idProducto, Cantidad)
    VALUES (p_idProducto, p_cantidad);
END //
DELIMITER ;




call ejemploprueba.registrar_venta(5, 8);
call ejemploprueba.registrar_venta(1, 15);
call ejemploprueba.registrar_venta(6, 13);
call ejemploprueba.registrar_venta(6, 4);
call ejemploprueba.registrar_venta(2, 3);
call ejemploprueba.registrar_venta(5, 1);
call ejemploprueba.registrar_venta(4, 5);
call ejemploprueba.registrar_venta(2, 5);
call ejemploprueba.registrar_venta(6, 2);
call ejemploprueba.registrar_venta(1, 8);
select * from venta;


--- 1.5: Todos los productos con al menos una venta

SELECT p.nombre
FROM producto p
INNER JOIN venta v ON p.idProducto = v.idProducto
GROUP BY p.nombre;

--- 1.6: Productos con ventas y cantidad total vendida

SELECT p.nombre, SUM(v.idCantidad) AS total_vendido
FROM producto p
INNER JOIN venta v ON p.idProducto = v.idProducto
GROUP BY p.nombre;




--- Traer todos los productos (independientemente de si tienen ventas o no) y la suma total ($) vendida por producto
SELECT p.nombre, SUM(v.idCantidad * p.precio) AS total_vendido
FROM producto p
LEFT JOIN venta v ON p.idProducto = v.idProducto
GROUP BY p.nombre;



