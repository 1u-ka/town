(ns urban.core
  (:require [clojure.pprint :as pp]
            [clojure.string :as str]
            [clojure.data.json :as json]
            [reitit.ring :as ring]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.cors :refer [wrap-cors]]
            [urban.db :as db]
            [urban.maps :as maps]
            [urban.pages :as pages]))

(def app
  (ring/ring-handler
   (ring/router
    [["/" {:status 200
           :body "OK"}]
     maps/routes
     pages/routes]
    {:data {:middleware [[wrap-cors
                          :access-control-allow-origin [#"localhost:4203"]
                          :access-control-allow-methods [:get :post :put :delete]]]}})
   (ring/create-default-handler)))

(defonce server (atom nil))

(defn server->boot [app & meta]
  (println "Server booted.")
  (db/init-maps)
  (db/init-tiles)
  (db/init-pages)
  (->> (or meta {:port 4000})
       (jetty/run-jetty #'app)
       (reset! server)))

(defn server->halt []
  (when-not (nil? @server)
    (println "Server halted.")
    (@server :timeout 100)
    (reset! server nil)))

(defn server-reboot [app]
  (server->halt)
  (server->boot app))

(defn -main [& args]
  (server->boot app))
