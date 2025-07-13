CREATE TABLE IF NOT EXISTS customer(
    customer_id int AUTO_INCREMENT PRIMARY KEY NOT NULL ,
    name varchar(100) NOT NULL ,
    email varchar(256) NOT NULL ,
    mobile_number varchar(13) NOT NULL ,
    created_at date NOT NULL ,
    created_by varchar(20) NOT NULL ,
    updated_at datetime DEFAULT NULL,
    updated_by varchar(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS accounts(
    customer_ID int NOT NULL ,
    account_number int AUTO_INCREMENT PRIMARY KEY NOT NULL ,
    account_type varchar(100) NOT NULL ,
    branch_address varchar(200) NOT NULL ,
    created_at date NOT NULL ,
    created_by varchar(20) NOT NULL ,
    updated_at datetime DEFAULT NULL,
    updated_by varchar(20) DEFAULT NULL

);