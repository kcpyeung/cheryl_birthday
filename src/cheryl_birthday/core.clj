(ns cheryl-birthday.core
  (:require [clojure.string :as s]))

(defn know? [dates]
  (= 1 (count dates)))

(defn tell [part dates]
  (filter #(s/includes? % part) dates))
