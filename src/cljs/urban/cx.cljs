(ns urban.cx
  (:require [helix.core]
            [helix.hooks :as h]))

(def initial {:main "lorem"})

(def void (helix.core/create-context initial))
