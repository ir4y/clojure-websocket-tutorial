(ns hello-websocket.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.util.response :as resp]
            [org.httpkit.server :as kit]
            [hello-websocket.echo :as echo]
            [hello-websocket.pubsubatom :as pubsubatom]
            [hello-websocket.redis :as redis]))

(defroutes app-routes
  (GET "/" [] (resp/resource-response "index.html" {:root "public"}))
  (GET "/echo"  [] echo/handler)
  (GET "/atom"  [] pubsubatom/handler)
  (GET "/redis" [] redis/handler)
  (route/resources "/")
  (route/not-found "Not Found"))


(defn -main [& args]
  (kit/run-server (handler/site #'app-routes) {:port 8080}))
