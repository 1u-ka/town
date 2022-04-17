(ns urban.components.nav
  (:require [helix.core :refer [$ defnc]]
            [helix.dom :as d]
            [helix.hooks :as h]
            ["react-router-dom" :refer [Link]]
            [urban.components.focus :refer [focus]]
            [urban.cx :refer [void]]))

(defnc ^:export nav [{:keys [nav?]}]
  (let [[cx set-cx] (h/use-context void)]
    (d/nav {:id "menu"
            :style {:display (if nav? "block" "none")}}
           (d/div
            (d/h2 "logotype")
            (d/ul
             (d/li (d/a {:href "/maps/5"} "map nr5"))
             (d/li (d/a {:href "/"} "home"))
             (d/li (d/a {:href "/pages/about"} "about")))
            (when-not (= nil (:tile-in-focus cx))
              (d/hr)
              (d/div ($ focus {:tid (:tile-in-focus cx)})))))))
