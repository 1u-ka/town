(ns urban.core
  (:require [ajax.core :refer [GET]]
            [helix.core :refer [defnc $]]
            [helix.dom :as d]
            ["react-dom" :as dom]))

(defn nav []
  (d/nav
   (d/div
    (d/h2 "logotype")
    (d/a {:href "/"} "home")
    (d/a {:href "/about"} "about"))))

(defn app []
  (d/div
   ($ nav)
   (d/div {:class "container"}
          (d/div "hello world"))))

(defn ^:export init []
  (dom/render ($ app) (js/document.getElementById "app")))
