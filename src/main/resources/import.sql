/* Populate tabla regiones */
insert into regiones (nombre) values ('Bulgaria');
insert into regiones (nombre) values ('Portugal');
insert into regiones (nombre) values ('Indonesia');
insert into regiones (nombre) values ('Russia');
insert into regiones (nombre) values ('North Korea');

/* Populate tabla clientes */
insert into clientes ( region_id, nombre, apellido, email, create_at) values (1,'Miguelita', 'Duhig', 'mduhig0@usda.gov',  TO_DATE('2021-10-16', 'YYYY-MM-DD') );
insert into clientes ( region_id, nombre, apellido, email, create_at) values (2,'Melicent', 'MacChaell', 'mmacchaell1@symantec.com',  TO_DATE('2021-11-16', 'YYYY-MM-DD') );
insert into clientes ( region_id, nombre, apellido, email, create_at) values (3,'Helsa', 'Manthorpe', 'hmanthorpe2@un.org', TO_DATE('2020-02-15', 'YYYY-MM-DD') );
insert into clientes ( region_id, nombre, apellido, email, create_at) values (1,'Guillermo', 'Bitterton', 'gbitterton3@facebook.com', TO_DATE('2021-01-29', 'YYYY-MM-DD') );
insert into clientes ( region_id, nombre, apellido, email, create_at) values (1,'Rahel', 'Eloy', 'reloy4@altervista.org', TO_DATE('2019-10-27', 'YYYY-MM-DD') );
insert into clientes ( region_id, nombre, apellido, email, create_at) values (2,'Mindy', 'Broomfield', 'mbroomfield5@wikimedia.org', TO_DATE('2019-12-31', 'YYYY-MM-DD') );
insert into clientes ( region_id, nombre, apellido, email, create_at) values (2,'Reina', 'Paintain', 'rpaintain7@desdev.cn', TO_DATE('2021-01-25', 'YYYY-MM-DD') );
insert into clientes ( region_id, nombre, apellido, email, create_at) values (3,'Gustav', 'Rewan', 'grewan8@slate.com', TO_DATE('2021-05-13', 'YYYY-MM-DD') );
insert into clientes ( region_id, nombre, apellido, email, create_at) values (2,'Vicente', 'Marti', 'vmarti@tresbits.es', TO_DATE('1992-07-17', 'YYYY-MM-DD') );
insert into clientes ( region_id, nombre, apellido, email, create_at) values (3,'Alberto', 'Raga', 'alberto@tresbits.es', TO_DATE('1992-05-13', 'YYYY-MM-DD') );
insert into clientes ( region_id, nombre, apellido, email, create_at) values (2,'Reina', 'Paintain', 'vmline@gmail.com', TO_DATE('2021-01-25', 'YYYY-MM-DD') );
insert into clientes ( region_id, nombre, apellido, email, create_at) values (3,'Gustav', 'Rewan', 'grewan888@gmail.com', TO_DATE('2021-05-13', 'YYYY-MM-DD') );
insert into clientes ( region_id, nombre, apellido, email, create_at) values (2,'Vicente', 'Marti', 'vmarti_soriano@gmail.com', TO_DATE('1992-07-17', 'YYYY-MM-DD') );
insert into clientes ( region_id, nombre, apellido, email, create_at) values (3,'Vicente', 'Soriano', 'vicentemline@gmail.com', TO_DATE('2021-05-13', 'YYYY-MM-DD') );

/* Creamos algunos usuarios con sus roles */

insert into usuarios ( username, password, enabled, email, nombre, apellido ) values ('andres', '$2a$10$3wyHnv/t/k5bDWGKmtAhSOXVoOR633z9RQ0RxSoW7ypooDIipNeY.', true, 'vmarti@tresbits.es', 'Vicente', 'Marti Soriano');
insert into usuarios ( username, password, enabled, email, nombre, apellido ) values ('admin', '$2a$10$3wyHnv/t/k5bDWGKmtAhSOXVoOR633z9RQ0RxSoW7ypooDIipNeY.', true, 'vicentemline@gmail.com', 'vicente', 'prueba2');

insert into roles ( nombre ) values ('ROLE_USER');
insert into roles ( nombre ) values ('ROLE_ADMIN');

insert into usuarios_roles ( usuario_id, role_id ) values ( 1, 1 );
insert into usuarios_roles ( usuario_id, role_id ) values ( 2, 2 );
insert into usuarios_roles ( usuario_id, role_id ) values ( 2, 1 );