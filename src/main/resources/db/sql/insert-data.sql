INSERT INTO tipoUsuario (nombre) VALUES ('Admin');
INSERT INTO tipoUsuario (nombre) VALUES ('Usuario');
INSERT INTO usuario (usuario, password, tipo, aprobado) 
	VALUES ('admin','admin',1,'S');
INSERT INTO usuario (usuario, password,tipo, aprobado) 
	VALUES ('pepe', '1234',2,'S');
INSERT INTO estado (nombre) VALUES ('Pendiente');
INSERT INTO estado (nombre) VALUES ('Finalizado');
INSERT INTO tipoTarea (nombre) VALUES ('Privada');
INSERT INTO tipoTarea (nombre) VALUES ('Publica');
INSERT INTO tarea (titulo, descripcion, estado, creado_por, tipoTarea) 
	VALUES ('tarea', 'test',1,2,1);
INSERT INTO tarea (titulo, descripcion, estado, creado_por, tipoTarea) 
	VALUES ('tarea1','test',2,2,2);

--COMMIT;