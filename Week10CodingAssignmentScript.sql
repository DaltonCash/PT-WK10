use week10codingassignment;
create table albums(
	albumId int(11) not null auto_increment,
    name varchar(20) not null,
    artist varchar(50) not null,
    recordDate date,
    releaseDate date,
    
    primary key (albumId)
);



