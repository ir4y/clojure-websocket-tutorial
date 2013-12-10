(ns hello-websocket.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [org.httpkit.server :as kit]))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (route/resources "/")
  (route/not-found "Not Found"))

;(def app
  ;(handler/site app-routes))

(defn -main [& args]
  (kit/run-server (handler/site #'app-routes) {:port 8080}))
