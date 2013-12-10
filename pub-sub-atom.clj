(def my-channel (atom nil))

(defn handler [request]
  (kit/with-channel request channel
    (kit/on-close channel (fn [status]        
                            (remove-watch my-channel channel)))
    (kit/on-receive channel (fn [json]
                              (reset! my-channel json)))
    (add-watch my-channel channel
      (fn [_ _ _ json]
        (kit/send! channel json)))))

(run-server handler {:port 8000})
