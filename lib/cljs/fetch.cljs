(ns fetch
  ((:require [lambdaisland.fetch])))

(defmacro
  ^{}
  from
  [path expr]
  `(-> (fetch/get ~path :accept :edn)
       (.then (fn [r] (~expr (read-string (:body r)))))))
