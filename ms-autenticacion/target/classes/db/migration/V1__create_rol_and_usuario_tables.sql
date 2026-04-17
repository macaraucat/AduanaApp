CREATE TABLE rol (
    id BIGSERIAL PRIMARY KEY,
    codigo VARCHAR(20) UNIQUE NOT NULL,
    nombre VARCHAR(30) NOT NULL
);

CREATE TABLE usuario (
    id BIGSERIAL PRIMARY KEY,
    rut VARCHAR(12) UNIQUE NOT NULL,
    nombres VARCHAR(20),
    apellido_paterno VARCHAR(20),
    apellido_materno VARCHAR(20),
    password VARCHAR(20) NOT NULL,
    organismo VARCHAR(10) NOT NULL,
    activo BOOLEAN DEFAULT true
);

CREATE TABLE usuario_rol (
    id_usuario BIGINT NOT NULL,
    id_rol BIGINT NOT NULL,
    PRIMARY KEY (id_usuario, id_rol),
    CONSTRAINT fk_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE,
    CONSTRAINT fk_rol FOREIGN KEY (id_rol) REFERENCES rol(id) ON DELETE CASCADE
);