-- ROLES
INSERT INTO bd_iam.rol (codigo, nombre) VALUES
('TURISTA','Turista'),
('FUNC_PDI','Funcionario PDI'),
('FUNC_SAG','Funcionario SAG'),
('FUNC_ADUANA','Funcionario Aduana'),
('ADMIN','Administrador');

-- PERMISOS
INSERT INTO bd_iam.permiso (codigo, descripcion) VALUES
('DECLARACION_CREAR','Crear declaración'),
('DECLARACION_LEER','Consultar'),
('DECLARACION_APROBAR','Aprobar'),
('PAGO_REGISTRAR','Registrar pago'),
('PAGO_APROBAR','Aprobar pagos'),
('USUARIO_CREAR','Crear usuarios'),
('USUARIO_LISTAR','Listar'),
('REPORTE_VER','Ver reportes');

-- ASIGNAR PERMISOS
INSERT INTO bd_iam.rol_permiso (id_rol, id_permiso)
SELECT r.id, p.id FROM bd_iam.rol r, bd_iam.permiso p
WHERE r.codigo='TURISTA' AND p.codigo IN ('DECLARACION_CREAR','DECLARACION_LEER');

INSERT INTO bd_iam.rol_permiso (id_rol, id_permiso)
SELECT r.id, p.id FROM bd_iam.rol r, bd_iam.permiso p
WHERE r.codigo='FUNC_PDI' AND p.codigo IN ('DECLARACION_LEER','DECLARACION_APROBAR','REPORTE_VER');

INSERT INTO bd_iam.rol_permiso (id_rol, id_permiso)
SELECT r.id, p.id FROM bd_iam.rol r, bd_iam.permiso p
WHERE r.codigo='FUNC_SAG' AND p.codigo IN ('DECLARACION_LEER','DECLARACION_APROBAR');

INSERT INTO bd_iam.rol_permiso (id_rol, id_permiso)
SELECT r.id, p.id FROM bd_iam.rol r, bd_iam.permiso p
WHERE r.codigo='FUNC_ADUANA' AND p.codigo IN ('DECLARACION_LEER','DECLARACION_APROBAR','PAGO_REGISTRAR','PAGO_APROBAR','REPORTE_VER');

INSERT INTO bd_iam.rol_permiso (id_rol, id_permiso)
SELECT r.id, p.id FROM bd_iam.rol r CROSS JOIN bd_iam.permiso p WHERE r.codigo='ADMIN';

-- USUARIOS
INSERT INTO bd_iam.usuario (rut, nombres, apellido_paterno, email, password, organismo, paso_asignado, activo, fecha_creacion, fecha_actualizacion) VALUES
('11111111-1','Juan','Pérez','juan@mail.cl','1234','TURISTA',NULL,TRUE,NOW()),
('22222222-2','María','González','maria@pdi.cl','1234','PDI','Los Libertadores',TRUE,NOW()),
('33333333-3','Carlos','Muñoz','carlos@sag.cl','1234','SAG','Cardenal Samoré',TRUE,NOW()),
('44444444-4','Ana','Rojas','ana@aduana.cl','1234','ADUANA','Chacalluta',TRUE,NOW()),
('99999999-9','Admin','Sistema','admin@aduana.cl','1234','ADUANA',NULL,TRUE,NOW());

-- ASIGNAR ROLES
INSERT INTO bd_iam.usuario_rol (id_usuario, id_rol)
SELECT u.id, r.id FROM bd_iam.usuario u JOIN bd_iam.rol r ON
 (u.rut='11111111-1' AND r.codigo='TURISTA') OR
 (u.rut='22222222-2' AND r.codigo='FUNC_PDI') OR
 (u.rut='33333333-3' AND r.codigo='FUNC_SAG') OR
 (u.rut='44444444-4' AND r.codigo='FUNC_ADUANA') OR
 (u.rut='99999999-9' AND r.codigo='ADMIN');