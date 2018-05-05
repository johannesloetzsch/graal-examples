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
        json (.execute parse (into-array ["{\"a\": 23, \"b\": 42}"]))
        result (->> json
                    #_(#(.execute stringify (into-array Object [%])))
                    #_(#(.execute f (into-array Object [%]))))]
       (println result)
       (println (.getMember json "a"))
       (println (into {} (.as json java.util.Map)))))
