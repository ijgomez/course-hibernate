

insert into User(usuario_id, name, password) values (1, 'John', 'Doe');
insert into User(usuario_id, name, password) values (2, 'Bruce', 'Wayne');

insert into Rol(rol_id, name) values (1, 'Admin');
insert into Rol(rol_id, name) values (2, 'Millioner');

insert into usuarios_roles (usuario_id, rol_id) values (1, 1);
insert into usuarios_roles (usuario_id, rol_id) values (2, 2);