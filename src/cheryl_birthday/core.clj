(ns cheryl-birthday.core
  (:require [clojure.string :as str]))

(def possible-dates '("May 15", "May 16", "May 19",
                       "June 17", "June 18",
                       "July 14", "July 16",
                       "August 14", "August 15", "August 17"))

(defn know? [dates]
  (= 1 (count dates)))
