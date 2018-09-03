CREATE TABLE npc(id int not null,
nombre varchar(25) not null,
mapa varchar(25) not null, titulo varchar(35));
CREATE TABLE origen (id int not null,
objeto varchar(25) not null,
mapa1 varchar(50), mapa2 varchar(50), archivo varchar(50), name_map1 varchar(50) default "", name_map2 varchar(50) default "", mapa3 varchar(50) default "", name_map3 varchar(50) default "", map4 varchar(50) default "", name_map4 varchar(50) default "");
CREATE TABLE food_base(id int not null,
nombre varchar(25) not null,
npc_id int, costo_g int, costo_s int,
archivo varchar(50) not null, lvl_requerido int);
CREATE TABLE ingrediente (id int not null,
nombre varchar(25) not null,
ubicacion_id int not null,
archivo varchar(50) not null, lvl_requerido int);
CREATE TABLE food_finish (id int not null,
nombre varchar(50),
lvl_requerido int,
ing_p int not null, c_ingp int,
food_base_id int not null,
ing2 int, c_ing2 int,
ing3 int, c_ing3 int,
base2 int, c_base2 int,
costo_g int, costo_s int,
name_green varchar(50) default "deliciosos",
name_orange varchar(50) default "exquisito",
name_purple varchar(50) default "sublime",
name_golden varchar(50) default "real",
archivo varchar(25) not null);
