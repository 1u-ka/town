create table if not exists tiles(
  id      serial primary key,
  coordx  int,
  coordy  int,
  terrain varchar(255),
  symbol  varchar(255),
  map     int,
  page    int);