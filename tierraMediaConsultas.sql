SELECT * FROM Usuarios as u
WHERE u.Habilitado = 1;

INSERT INTO Usuarios (Nombre, Password, Atraccion_preferida, Presupuesto, Tiempo, Admin, Habilitado)
VALUES
("pepe", "pepe123", "Aventura", 50, 10.0, 0, 0);
SELECT p.nombre, group_concat(Nombre_atraccion) as Atracciones FROM Promociones p LEFT JOIN Promocion_Atraccion ap ON p.Nombre = ap.Nombre_promocion WHERE p.Habilitado = 1 GROUP BY p.Nombre;


SELECT * FROM USUARIOS WHERE id = 2;