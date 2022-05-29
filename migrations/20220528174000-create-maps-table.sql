create table if not exists maps(
  id          serial primary key,
  width       int default 0,
  height      int default 0,
  description varchar(255),
  created_at  timestamp default NOW(),
  updated_at  timestamp default NOW());