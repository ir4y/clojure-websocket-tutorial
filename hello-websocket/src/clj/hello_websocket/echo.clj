(ns hello-websocket.echo
  (:use org.httpkit.server))

(defn handler [request]
 (with-channel request channel
  (on-close channel (fn [status] (println "channel closed: " status)))
  (on-receive channel (fn [data]
                       (send! channel data)))))


