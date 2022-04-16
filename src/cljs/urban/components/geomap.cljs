(ns urban.components.geomap
  (:require ["react-router-dom" :refer [useParams]]
            [cljs.core.async :refer [go]]
            [cljs.core.async.interop :refer [<p!]]
            [cljs.reader :refer [read-string]]
            [clojure.walk :refer [keywordize-keys]]
            [helix.core :refer [<> defnc]]
            [helix.dom :as d]
            [helix.hooks :as hooks]
            [lambdaisland.fetch :as fetch]
            [shadow.object :as object]))

(defrecord Option [text])
(defrecord Morph [symbol])
(defrecord Tile [id terrain symbol options morphs])
(defrecord Geomap [width height preface])

(defn genr8
  ([f w h x y tiles]
   (cond (< x w)
         (genr8 f w h (inc x) y (conj tiles (get (get f x) y)))
         (< y h)
         (genr8 f w h 1 (inc y) (conj tiles (get (get f x) y)))
         (= w h x y)
         (conj tiles (get (get f x) y))
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
             (set-tileset))))
    (<>
     (js/console.log tileset)
     (d/h1 (:preface geomap))
     (d/p "geomap compo")
     (d/div
      (map (fn [tile] (d/p {:key tile} (get-in tile [:coords :x])))
           (genr8 tileset (:width geomap) (:height geomap)))))))
