(ns urban.components.page
  (:require [clojure.walk :refer [keywordize-keys]]
            [goog.string :as str]
            [helix.core :refer [defnc]]
            [helix.dom :as d]
            ["react-router-dom" :refer [useParams]]))

(defn ^:export page []
  (let [{slug :slug} (-> (useParams) (js->clj) (keywordize-keys))]
    (d/div
     (d/h1 (str "page " slug)))))
