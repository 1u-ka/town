(ns urban.core
  (:require [helix.core :refer [defnc $ <> provider]]
            [helix.hooks :as h]
            [helix.dom :as d]
            ["react-dom" :as dom]
            ["react-dom/client" :refer [createRoot]]
            ["react-router-dom" :refer [BrowserRouter Routes Route Switch]]
            [urban.components.geomap :refer [geomap]]
            [urban.components.nav :refer [nav]]
            [urban.components.page :refer [page]]
            [urban.cx :refer [void]]))

(defnc default [props]
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
                   ($ Route {:path "/" :element ($ default)})
                   ($ Route {:path "/pages/:slug" :element ($ page)})
                   ($ Route {:path "/maps/:mid" :element ($ geomap)}))))))))

(defnc voider
  "A React context provider as a global state manager\n
   @return React.Provider\n
   "
  []
  (let [[global set-global] (h/use-state {})]
    (provider
     {:context void
      :value [global set-global]}
     ($ app))))

(defonce root (createRoot (js/document.getElementById "app")))
(defn ^:export ^:dev/after-load init []
  (.render root ($ voider)))
