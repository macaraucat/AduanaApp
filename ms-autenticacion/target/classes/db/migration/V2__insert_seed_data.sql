INSERT INTO rol (codigo, nombre) VALUES
('ROL_ADUANA','Funcionario Aduana'),
('ROL_SAG','Funcionario SAG'),
('ROL_PDI','Funcionario PDI');

INSERT INTO usuario (rut, nombres, apellido_paterno, apellido_materno, password, organismo, activo) VALUES
('11.111.111-1','Ana','Torres','Rojas','aduana123','ADUANA',true),
('22.222.222-2','Sergio','Muñoz','Lara','sag123','SAG',true),
('33.333.333-3','Paula','Díaz','Soto','pdi123','PDI',true);

INSERT INTO usuario_rol (id_usuario, id_rol) VALUES 
(1,1),
(2,2),
(3,3);