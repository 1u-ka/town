create table if not exists pages(
  id serial primary key,
  title varchar(255) not null,
  contents text not null,
  created_at timestamp default NOW(),
  updated_at timestamp default NOW());