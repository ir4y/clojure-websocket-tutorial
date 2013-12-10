(defproject hello-websocket "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.6"]
                 [com.taoensso/carmine "2.4.0"]
                 [http-kit "2.1.13"]]
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]]}}
  :main hello-websocket.handler)
