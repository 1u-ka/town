(ns urban.components.nav
  (:require [helix.core :refer [$ defnc]]
            [helix.dom :as d]
            [helix.hooks :as h]
            ["react-router-dom" :refer [Link]]
            [urban.cx :refer [void]]))

(defnc ^:export nav [{:keys [nav?]}]
  (let [[cx set-cx] (h/use-context void)]
    (js/console.log (prn cx))
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
              (d/p (str "some tile in focus: " (:tile-in-focus cx))))))))
