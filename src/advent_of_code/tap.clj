(ns advent-of-code.tap)

(defonce taps (atom {}))

(defn tap-internal [[path value]]
  (swap! taps update-in path (fnil conj []) value))

(defmacro tap [expr]
  (let [str-expr (str expr)]
    `(let [spy-val# ~expr]
       (tap> [[*ns* '~str-expr] spy-val#])
       spy-val#)))

(add-tap tap-internal)

(comment @taps)

(comment
  (reset! taps {})
  (remove-tap tap-internal)
  (add-tap tap-internal))
