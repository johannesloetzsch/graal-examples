(ns clj-js.core
  (import [org.graalvm.polyglot Context])
  (:gen-class))

(defn -main
  [& args]
  (let [context (-> (Context/newBuilder (into-array ["js"]))
                    (.allowAllAccess true)
                    (.build))
        parse (.eval context "js" "JSON.parse")
        stringify (.eval context "js" "JSON.stringify")
        f (.eval context "js" "function (s) {return s + ' :)'}")
        result (->> (.execute parse (into-array ["{\"a\": 42}"]))
                    (#(.execute stringify (into-array Object [%])))
                    (#(.execute f (into-array Object [%]))))]
       (println result)))
