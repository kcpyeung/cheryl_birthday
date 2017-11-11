(ns cheryl-birthday.core
  (:require [clojure.string :as s]))

(defn know? [dates]
  (= 1 (count dates)))

(defn tell [part dates]
  (filter #(s/includes? % part) dates))

(defn month [date]
  (first (s/split date #" ")))

(defn day [date]
  (second (s/split date #" ")))

(defn albert-doesn't-know-and-he-knows-bernard-can't-tell-either [date all-dates]
  (let [what-albert-narrows-down-to (tell (month date) all-dates)
        all-days-in-albert's-list (distinct (map day what-albert-narrows-down-to))
        what-albert-thinks-bernard-can-narrow-down-to (map #(tell % all-dates) all-days-in-albert's-list)
        bernard-can't-identify-a-unique-date (not-any? know? what-albert-thinks-bernard-can-narrow-down-to)]
    (and (not (know? what-albert-narrows-down-to)) bernard-can't-identify-a-unique-date)))
