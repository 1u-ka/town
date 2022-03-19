-- :name create-pages-table
-- :command :execute
-- :result :raw
-- :doc creates pages table
create table if not exists pages(
  id serial primary key,
  title varchar(255) not null
  contents text not null
  created_at timestamp default NOW()
  updated_at timestamp default NOW());

-- :name get-pages ?: :*
select *
from pages;

-- :name get-page-by-id :? :*
select *
from pages
where
  id = :id;

-- :name create-page :? :*
insert into pages(title, contents)
values(:title, :contents);
