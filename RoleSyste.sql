drop database  if exists role_system;
create database role_system;
use role_system;
create table user_role(
    id int primary key auto_increment,
    username varchar(100),
    password varchar(100),
    create_date datetime,
    update_date datetime,
    role varchar(40)
)character set utf8;