(ns urban.pages)

(defn page [req]
  {:status 200
   :body "Page content: ?"})

(def routes
  ["/pages"
   ["/pages/:pid" {:get {:status 501
                         :body "not implemented"}}]])
