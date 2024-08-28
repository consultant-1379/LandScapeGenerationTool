use notificationmanager;

create table users(
      username varchar(50) not null primary key,
      password varchar(50) not null,
      enabled boolean not null);

  create table authorities (
      username varchar(50) not null,
      authority varchar(50) not null,
      constraint fk_authorities_users foreign key(username) references users(username));
      create unique index ix_auth_username on authorities (username,authority);
      
      

insert into users (username, password, enabled) values ("eeighiy","123456",1);
insert into authorities (authority, username) values ("ROLE_USER","eeighiy");

insert into users (username, password, enabled) values ("admin","123456",1);
insert into authorities (authority, username) values ("ROLE_ADMIN","admin");
