create table if not exists halls(
             id varchar(100) primary key,
             row integer,
             place integer,
             price integer,
             status varchar(100),
             account_phone varchar(100) references accounts(phone))
);