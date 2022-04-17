(ns urban.core
  (:require [helix.core :refer [defnc $ <>]]
            [helix.hooks :as h]
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
  (let [[nav? set-nav?] (h/use-state false)]
    (<>
     ($ nav {:nav? nav?})
     ($ BrowserRouter
        (d/div
         (d/div {:class "container"}
                (d/div "?")
                (d/a {:id "menu-activator"
                      :on-click #(set-nav? (not nav?))} "|||")
                ($ Routes
                   ($ Route {:path "/" :element ($ xyz)})
                   ($ Route {:path "/pages/:slug" :element ($ page)})
                   ($ Route {:path "/maps/:mid" :element ($ geomap)}))))))))

(defonce root (createRoot (js/document.getElementById "app")))

(defn ^:export ^:dev/after-load init []
  (.render root ($ app)))
