use register_login_users;

select * from contact_of_user;
select * from user_details;

alter table contact add column additional_email varchar(255);
alter table contact add column additional_user_phone_number varchar(255);
alter table user rename column passw to password;
INSERT into user (password) value ( m.getValue() );
ALTER TABLE contact AUTO_INCREMENT=1;
truncate table contact;
ALTER TABLE contact ALTER contactID SET DEFAULT 1;
insert into user_details values(); #(default);
SET GLOBAL sql_mode='';
alter table contact rename column liveIn to city;
rename table userdetails to user_details;
rename table contact to contact_of_user;
INSERT into contact_of_user (`city`) value ('Baku');
INSERT into contact_of_user (city) value ('baku');
insert into contact_of_user values(1, '12', 'a@mail.ru', 'Baku', 1,'m.ulvi@ultra.az', '7878');