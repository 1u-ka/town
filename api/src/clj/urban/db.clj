(ns urban.db
  (:require [hugsql.core :as hugsql]))

(def cnf
  {:classname "org.postgresql.Driver"
   :subprotocol "postgresql"
   :subname "//localhost:5434/urban"
   :user "council"
   :password "abcd"})

(def config cnf)

(hugsql/def-db-fns "urban/planning.sql")
