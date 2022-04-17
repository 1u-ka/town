(ns urban.components.tile
  (:require [helix.core :refer [defnc]]
            [helix.dom :as d]
            [helix.hooks :as h]
            [urban.cx :refer [void]]))

(defrecord Option [text])
(defrecord Morph [symbol])
(defrecord Tile [id terrain symbol options morphs])

(defnc ^:export tile [{:keys [t]}]
  (let [[cx set-cx] (h/use-context void)]
    (d/span {:class "tile"
             :on-click #(set-cx (assoc (assoc cx :nav? true) :tile-in-focus (:id t 1)))}
            (:symbol t 1))))
