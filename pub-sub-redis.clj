(def pool         (car/make-conn-pool))
(def spec-server1 (car/make-conn-spec))

(defmacro wcar [& body] `(car/with-conn pool spec-server1 ~@body))

(defn handler [request]
  (kit/with-channel request channel
    (kit/on-close channel (fn [status]                 
                            (car/close-listener listener)))
    (kit/on-receive channel (fn [json] 
                              (wcar (car/publish "my-channel" json))))
    (def listener
      (car/with-new-pubsub-listener 
        spec-server1 {"my-channel" (fn f1 [json] 
                                     (kit/send! channel json))}
        (car/subscribe "my-channel")))))

(run-server handler {:port 8000})
