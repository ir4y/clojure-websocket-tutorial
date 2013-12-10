(ns hello-websocket)

(def ws (js-obj))
  
(def text (.getElementById js/document "text"))
(def output (.getElementById js/document "output"))

(defn send []
  (.send ws (.-value text)))

(defn display [message]
  (let [p (.createElement js/document "p")
        new_text (.createTextNode js/document message)]
    (.appendChild p new_text)
    (if (.-firstChild output)
      (.insertBefore output p (.-firstChild output))
      (.appendChild output p))))

(defn init-websocket [ws-url]
  (set! ws (js/WebSocket. ws-url))
  (set! (.-onopen ws) (fn [] (display "Connection opened...")))
  (set! (.-onclose ws) (fn [] (display "Connection closed...")))
  (set! (.-onmessage ws) (fn [message]
                           (let [user-text (.-data message)]
                             (display user-text)
                             (set! (.-value text) "")))))


