(ns urban.components.nav
  (:require [helix.core :refer [$ defnc]]
            [helix.dom :as d]
            ["react-router-dom" :refer [Link]]))

(defnc ^:export nav [{:keys [nav?]}]
  (d/nav {:id "menu"
          :style {:display (if nav? "block" "none")}}
         (d/div
          (d/h2 "logotype")
          (d/ul
           (d/li (d/a {:href "/maps/5"} "map nr5"))
           (d/li (d/a {:href "/"} "home"))
           (d/li (d/a {:href "/pages/about"} "about"))))))
