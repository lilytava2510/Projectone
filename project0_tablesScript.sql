create table if not exists users (
user_id int primary key generated always as identity,
firstname varchar(120),
lastname varchar(130),
username varchar(130),
email varchar(140),
password varchar(120),
title varchar(120)
);

create table if not exists account (
account_id int primary key generated always as identity,
created boolean, 
balance int, 
account_fk int references users(user_id)not null
);
create table if not exists transactions_junction_table (
withdraw int references users(user_id),
deposit int references users(user_id),
transactions_fk int references account(account_id) not null,
primary key (withdraw,deposit)
);
select * from users;

