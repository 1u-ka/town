(ns urban.core
  (:require [helix.core :refer [defnc $ <>]]
            [helix.hooks :as hooks]
            [helix.dom :as d]
            ["react-dom" :as dom]
            ["react-dom/client" :refer [createRoot]]
            ["react-router-dom" :refer [BrowserRouter Routes Route Switch]]
            [urban.components.geomap :refer [geomap]]
            [urban.components.nav :refer [nav]]
            [urban.components.page :refer [page]]))

(defnc xyz [props]
  (<>
   (d/div "homepage compo")))

(defnc app [props]
  (<>
   ($ nav)
   ($ BrowserRouter
      (d/div
       (d/div {:class "container"}
              (d/div "?")
              ($ Routes
                 ($ Route {:path "/" :element ($ xyz)})
                 ($ Route {:path "/pages/:slug" :element ($ page)})
                 ($ Route {:path "/maps/:mid" :element ($ geomap)})))))))

(defonce root (createRoot (js/document.getElementById "app")))

(defn ^:export ^:dev/after-load init []
  (.render root ($ app)))
