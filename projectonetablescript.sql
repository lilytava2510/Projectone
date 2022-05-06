create table reimburse(
      reimburse_id int primary key generated always as identity,
      amount int,
      submitted_date date,
      resolve_date date,
      description varchar(200),
      reimburse_author int references users(user_id) on delete cascade,
      resolver int references users(user_id)on delete cascade,
      reimburse_status int references reimbursement_status(status_id) on delete cascade,
      reimburse_type int  references reimbursement_type(type_id) on delete cascade
);

create table users (
user_id int primary key generated always as identity,
username varchar(100),
password varchar(150),
first_name varchar(150),
last_name varchar(100),
email varchar (100) unique,
role_ int references user_roles(role_id) not null
);

create table user_roles (
role_id int primary key generated always as identity,
role_des varchar (100)
);

create table reimbursement_status(
status_id int  primary key generated always as identity,
status varchar(100)
);

create table reimbursement_type(
type_id int  primary key generated always as identity,
type_ varchar(100)
);

drop table users;
drop table reimburse;








