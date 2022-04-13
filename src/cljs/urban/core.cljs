(ns urban.core
  (:require [ajax.core :refer [GET]]
            [helix.core :refer [defnc $ <>]]
            [helix.hooks :as hooks]
            [helix.dom :as d]
            ["react-dom" :as dom]
            ["react-dom/client" :refer [createRoot]]
            ["react-router-dom" :refer [BrowserRouter Routes Route Switch]]
            [urban.components.geomap :refer [geomap]]
            [urban.components.nav :refer [nav]]
            [urban.components.page :refer [page]]))

(defnc xyz [props]
  (js/console.log "!@#$")
  (<>
   (d/div "abcascasdasdqwd!!!!!@!#!@#!@#@!Q#")))

(defnc app [props]
  (hooks/use-effect
   :once (GET "/assets/map.json"
              {:handler #(.log js/console %)}))
  (<>
   ($ BrowserRouter
      (d/div
       (d/div "!@#!@#!@#!@#")
       (d/div {:class "container"}
              (d/div "hello world")))
      ($ Routes
         ($ Route {:path "/" :component xyz})
         ($ Route {:path "/pages/{pid}"} page)
         ($ Route {:path "/maps/{mid}"} geomap)))))

(defn ^:export ^:dev/after-load init []
  (. (createRoot (js/document.getElementById "app"))
     render
     ($ app)))
