(ns urban.core
  (:require [ajax.core :refer [GET]]
            [helix.core :refer [defnc $ <>]]
            [helix.hooks :as hooks]
            [helix.dom :as d]
            ["react-dom" :as dom]
            [urban.components.nav]))

(defn app []
  (hooks/use-effect
   :once (GET "http://google.com"
              {:handler (fn [res] (.log js/console res))}))
  (<>
   ($ urban.components.nav/nav)
   (d/div {:class "container"}
          (d/div "hello world"))))

(defn ^:export ^:dev/after-load init []
  (dom/render ($ app) (js/document.getElementById "app")))
