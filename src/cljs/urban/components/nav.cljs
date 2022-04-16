(ns urban.components.nav
  (:require [helix.core :refer [$ defnc]]
            [helix.dom :as d]
            ["react-router-dom" :refer [Link]]
            ))

(defn ^:export nav [props]
  (d/nav
   (d/div
    (d/h2 "logotype")
    (d/ul
     (d/li (d/a {:href "/maps/5"} "map nr5"))
     (d/li (d/a {:href "/"} "home"))
     (d/li (d/a {:href "/pages/about"} "about"))))))
