create table engine
(
    engine_id bigint primary key generated by default as identity ,
    manufacturer varchar(255) not null ,
    volume float not null,
    fuel_type varchar(50) not null
);
create table aircraft (
    aircraft_id bigint primary key generated by default as identity ,
    airframe varchar(255) not null ,
    pilot varchar(255) not null ,
    engine_id bigint references engine(engine_id) not null
);