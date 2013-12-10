(defn handler [request]
 (with-channel request channel
  (on-close channel (fn [status] (println "channel closed: " status)))
  (on-receive channel (fn [data]
                       (send! channel data)))))

(run-server handler {:port 8000})

