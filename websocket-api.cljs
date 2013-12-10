(def ws (js-obj))

(set! ws (js/WebSocket. "ws://example.com/update"))

(set! (.-onopen ws) (fn [] (js/alert "Connection opened...")))

(set! (.-onclose ws) (fn [] (js/alert "Connection closed...")))
 
(set! (.-onmessage ws) (fn [message] (js/alert (.-data message))))

(.send ws "Hello world")
