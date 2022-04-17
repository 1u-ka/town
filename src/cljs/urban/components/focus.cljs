(ns urban.components.focus
  (:require [cljs.core.async :refer [go]]
            [cljs.core.async.interop :refer [<p!]]
            [cljs.reader :refer [read-string]]
            [helix.core :refer [<> defnc]]
            [helix.dom :as d]
            [helix.hooks :as h]
            [lambdaisland.fetch :as fetch]))

(defnc ^:export focus
  [{:keys [tid]}]
  (let [[tile set-tile] (h/use-state {})]
    (h/use-effect :once
                  (go (-> (str "/assets/tiles/" tid ".edn")
                          (fetch/get :accept :edn)
                          (<p!)
                          (:body)
                          (read-string)
                          (set-tile))))
    (<>
     (d/div {:class "contextual-menu"}
      (d/p (str "Tile in focus: " (:symbol tile)))
      (d/ul {:class "form-group row"}
            (map (fn [e]
                   (d/li
                    (d/input {:id (:symbol e)
                              :name (:symbol e)
                              :class "form-check-input"
                              :key (:symbol e)
                              :type "radio"
                              :value (:symbol e)}))
                   (d/label {:for (:symbol e)} (:symbol e)))
                 (:morphs tile)))
      (d/p "Options:")
      (d/div {:class "form-group row"}
             (map (fn [e]
                    (d/a {:href "#"} (:text e))) (:options tile)))))))
