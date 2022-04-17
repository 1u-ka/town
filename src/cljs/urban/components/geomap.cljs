(ns urban.components.geomap
  (:require ["react-router-dom" :refer [useParams]]
            [cljs.core.async :refer [go]]
            [cljs.core.async.interop :refer [<p!]]
            [cljs.reader :refer [read-string]]
            [clojure.walk :refer [keywordize-keys]]
            [helix.core :refer [<> defnc]]
            [helix.dom :as d]
            [helix.hooks :as hooks]
            [lambdaisland.fetch :as fetch]))

(defrecord Option [text])
(defrecord Morph [symbol])
(defrecord Tile [id terrain symbol options morphs])
(defrecord Geomap [width height preface])

(defn map->coord [m x y]
  (get (get m (dec y)) (dec x)))

(defn genr8
  ([f w h x y tiles]
   (js/console.log x y)
   (cond (< x w)
         (genr8 f w h (inc x) y (conj tiles (map->coord f x y)))
         (< y h)
         (genr8 f w h 1 (inc y) (conj tiles (map->coord f x y)))
         (= w h x y)
         (conj tiles (map->coord f x y))
         :else tiles))
  ([fetched w h]
   (genr8 fetched w h 1 1 [])))

(defnc ^:export geomap []
  (let [{mid :mid} (-> (useParams) (js->clj) (keywordize-keys))
        [geomap set-geomap] (hooks/use-state {:preface "Loading geomap.."})
        [tileset set-tileset] (hooks/use-state {})]
    (hooks/use-effect
     :once
     (go (-> "/assets/geomap.edn"
             (fetch/get :accept :edn)
             (<p!)
             (:body)
             (read-string)
             (set-geomap))))
    (hooks/use-effect
     :once
     (go (-> "/assets/tiles.edn"
             (fetch/get :accept :edn)
             (<p!)
             (:body)
             (read-string)
             (genr8 (:width geomap 10) (:height geomap 10))
             (set-tileset))))
    (<>
     (d/h1 (:preface geomap))
     (d/p "geomap compo")
     (js/console.log (prn tileset))
     (d/div {:class "section"
             :style {:width "320px"}}
      (map
       (fn [tile]
         (d/a {:key (-> js/Math .random)
               :class "tile"}
              (:id tile 1)))
       tileset)))))
