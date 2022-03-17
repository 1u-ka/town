(ns urban.core
  (:require [clojure.pprint :as pp]
            [clojure.string :as str]
            [clojure.data.json :as json]  
            [reitit.ring :as ring]
            [ring.adapter.jetty :as jetty])
  #_(:gen-class))

(defn getparameter
  [req pname]
  (get (:params req) pname))

(defn simple-body-page [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "Hello world"})

(defn request-example
  [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (->>
             (pp/pprint req)
             (str "Request Object: " req))})

(defn hello-name
  [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (->
             (pp/pprint req)
             (str "Hello " (:name (:params req))))})

(def people-collection (atom []))

(defn addperson
  [firstname surname]
  (swap! people-collection conj {:firstname (str/capitalize firstname)
                                  :surname (str/capitalize surname)}))

(addperson "Lorem" "Ipsum")
(addperson "Name" "Surname")

(defn people-handler
  [req]
  {:status  200
   :headers {"Content-Type" "text/json"}
   :body    (str (json/write-str @people-collection))})

(defn addperson-handler [req]
  {:status  200
   :headers {"Content-Type" "text/json"}
   :body    (-> (let [p (partial getparameter req)]
                  (str (json/write-str (addperson (p :firstname) (p :surname))))))})

(def router
  (ring/ring-handler
   (ring/router
    [["/" simple-body-page]
     ["/ping" {:get {:summary "ping test"
                     :handler (fn [req]
                                {:status  200
                                 :headers {"Content-Type" "text/html"}
                                 :body    "pong"})}}]])
   (ring/create-default-handler)))

(defn -main [& args]
  (println "Server booted")
  (jetty/run-jetty #'router {:port 4000}))
