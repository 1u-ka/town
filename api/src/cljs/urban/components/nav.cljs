(ns urban.components.nav
  (:require [helix.core :refer [defnc]]
            [helix.dom :as d]))

(defn ^:export nav [props]
  (js/console.log "AAAAAAAAAAAAAAAAAAAA")
  (d/nav
   (d/div
    (d/h2 "logotype")
    (d/a {:href "/"} "home")
    (d/a {:href "/about"} "about"))))
