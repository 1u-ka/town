(ns urban.components.page
  (:require [goog.string :as str]
            [helix.core :refer [defnc]]
            [helix.dom :as d]))

(defn ^:export page [props]
  (.log js/console 123)
  (d/div
   (d/h1 (str/format "page #%d" 123))))
