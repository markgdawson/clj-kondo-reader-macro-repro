(ns core
  (:require [try-let :refer [try-let]]))

;; This gives a warning
(try-let [a 10]
         #(+ a 2)
         (catch Throwable e e))

;; This does not
(try-let [a 10]
         a
         #(+ a 2)
         (catch Throwable e e))

;; Neither does this
(try-let [a 10]
         (fn [] (+ a 2))
         (catch Throwable e e))

;; And the macroexpansion of the first example above doesn't produce errors.
(try (let* [a 10] #(+ a 2)) (catch Throwable e e))
