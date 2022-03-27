(ns urban.maps
  (:require [urban.db :as db]))

(def map->struct {:title s/Str
                  :contents s/Str})

(defn map->list [req]
  {:status 200
   :body [1 2 3]})

(defn map->show [req]
  {:status 200
   :body {:width 10
          :height 10
          :preface "This map represents a ... suburb. Or an inner city block, etc."}})

(defn map->store [req]
  {:status 501
   :body "missing body (not implemented)"})

(defn tile->list [req]
  ;; @todo   NULL the tiles until map dimensions are met
  {:status 200
   :body [{:id 1
           :terrain "center center"
           :symbol "Museum"
           :options [{:text "add sponsor/investment round"}
                     {:text "add submap"}
                     {:text "add details"}]
           :morphs [{:symbol "Grass"}
                    {:symbol "Park"}
                    {:symbol "Cafe"}]}
          {}
          {}]})

(defn tile->show [req]
  {:status 501
   :body "not implemented"})

(def routes
  ["/maps" {:get map->list
            :post {:parameters {:body map->struct}
                   :handler db/map->create}}
   ["/maps/:mid" {:parameters {:path {:mid s/Int}}
                 :get db/map->show}]
   "/tiles" {}
   ["/tiles/:tid" {:parameters {:path {:tid s/Int}}
                   :get db/tile->show}]])
