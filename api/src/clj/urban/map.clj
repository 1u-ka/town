
(defn map [req]
  {:status 200
   :body {:width 10
          :height 10
          :preface "This map represents a ... suburb. Or an inner city block, etc."}})

(defn tiles [req]
  ;; @todo   NULL the tiles until map dimensions are met
  {:status 200
   :body {{:id 1
           :terrain "center center"
           :symbol "Museum"
           :options [{:text "add sponsor/investment round"}
                     {:text "add submap"}
                     {:text "add details"}]
           :morphs [{:symbol "Grass"}
                    {:symbol "Park"}
                    {:symbol "Cafe"}]}
          {}
          {}}})
