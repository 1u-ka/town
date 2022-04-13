-- :name init-maps
-- :command :execute
-- :result :raw
-- :doc creates the maps table
create table if not exists maps(
  id          serial primary key,
  width       int default 0,
  height      int default 0,
  description varchar(255),
  created_at  timestamp default NOW(),
  updated_at  timestamp default NOW());

-- :name get-maps ?: :*
select *
from maps;

-- :name map->show :? :*
select *
from maps
where
  id = :id;

-- :name map->create :? :*
insert into maps(width, height, description)
values (:width, :height, :description);

-- :name init-tiles
-- :co mmand :execute
-- :result :raw
-- :doc creates a tiles table
create table if not exists tiles(
  id      serial primary key,
  coordx  int,
  coordy  int,
  terrain varchar(255),
  symbol  varchar(255),
  map     int,
  page    int);

-- :name create-tile :? :*
insert into tiles(coordx, coordy, terrain, symbol, map, page)
values (:coordx, :coordy, :terrain, :symbol, :map, :page);

-- :name tile->show ?: :*
select *
from tiles
where
  id = :id;
