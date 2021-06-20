-- create schema register_login_users;
use register_login_users1;

CREATE TABLE contact_of_user (
  contactID                    INT PRIMARY KEY AUTO_INCREMENT,
  user_phone_number            VARCHAR(255),
  email                        VARCHAR(255),
  city                         VARCHAR(255),
  userId                       INT,
  additional_email             VARCHAR(255),
  additional_user_phone_number VARCHAR(255)
);

CREATE TABLE user_details (
  userID           INT primary key auto_increment,
  name             VARCHAR(255),
  surname          VARCHAR(255),
  username         VARCHAR(255),
  password         varchar(512),
  registrationDate VARCHAR(255)
);
drop table user_details;
drop table contact_of_user;

truncate table contact_of_user;
truncate table user_details;
