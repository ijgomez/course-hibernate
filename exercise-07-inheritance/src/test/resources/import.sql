insert into Rol(rol_id, name) values (1, 'Admin');
insert into Rol(rol_id, name) values (2, 'Millioner');

insert into User(id, name, password, rol_id, TIPO_USUARIO) values (1, 'John', 'Doe', 1, 'org.course.hibernate.beans.User');
insert into User(id, name, password, rol_id, TIPO_USUARIO) values (2, 'Bruce', 'Wayne', 2, 'org.course.hibernate.beans.User');