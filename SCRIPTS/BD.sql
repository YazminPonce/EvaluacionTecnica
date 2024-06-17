-- Creacion de la base de datos --
create DATABASE inventarioCas;
USE inventarioCas;

create table roles (
idRol int primary key,
nombre     varchar(100)
);

create table usuarios (
idUsuario int primary key,
nombre     varchar(100),
correo     varchar(50),
contrasena  varchar(25),
idRol        int(2),
estatus   		int(1),
 FOREIGN KEY (idRol) REFERENCES roles(idRol)
);

create table productos(
idProducto  int primary key AUTO_INCREMENT,
nombre		  varchar(100),
cantidad      int,
estatus   		int(1)
);

create table movimientos(
idMovimiento   int primary key AUTO_INCREMENT,
idUsuario		int,
tipoMovimiento  varchar(30),
fechaHora		datetime,
 FOREIGN KEY (idUsuario) REFERENCES usuarios(idUsuario)

);

-- Registros de rol --
Insert into roles (idRol,nombre) values (1,"Administrador");
Insert into roles (idRol,nombre) values (2,"Almacenista");

-- Registro Uusario --
Insert into usuarios (idUsuario,nombre,correo,contrasena,idRol,estatus) values (1,"yazmin","yazmin@gmail.com","56234",1,1);
Insert into usuarios (idUsuario,nombre,correo,contrasena,idRol,estatus) values (2,"javier","javier@gmail.com","86924",2,1);


