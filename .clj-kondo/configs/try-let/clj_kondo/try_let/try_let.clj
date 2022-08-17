(ns clj-kondo.try-let.try-let)

(defn expression-type [body]
  (if (and (seq? body)
           (#{'catch 'finally} (first body)))
    :catches
    :body))

(defmacro try-let
  [bindings & body]
  (let [{:keys [body catches]} (group-by expression-type body)]
    `(try (let [~@bindings] ~@body)
          ~@catches)))
