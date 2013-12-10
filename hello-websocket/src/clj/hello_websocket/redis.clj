(ns hello-websocket.redis
  (:use org.httpkit.server)
  (:require [taoensso.carmine :as car :refer (wcar)]))

(def pool         (car/make-conn-pool))
(def spec-server1 (car/make-conn-spec))

(defmacro wcar* [& body] `(car/with-conn pool spec-server1 ~@body))

(defn handler [request]
  (with-channel request channel
    (def listener
      (car/with-new-pubsub-listener 
        spec-server1 {"my-channel" (fn f1 [json] 
                                     (send! channel (str json)))}
        (car/subscribe "my-channel")))
    (on-close channel (fn [status]                 
                            (car/close-listener listener)))
    (on-receive channel (fn [json] 
                              (wcar* (car/publish "my-channel" json))))))

