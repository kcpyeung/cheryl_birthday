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

(defn bernard-couldn't-tell-at-first-but-now-he-knows [date all-dates]
  (let [what-bernard-narrows-down-to-at-first (tell (day date) all-dates)
        what-bernard-narrows-down-to-after-hearing-albert (filter #(albert-doesn't-know-and-he-knows-bernard-can't-tell-either % all-dates) what-bernard-narrows-down-to-at-first)]
    (and (not (know? what-bernard-narrows-down-to-at-first)) (know? what-bernard-narrows-down-to-after-hearing-albert))))

(defn then-i-also-know-when-cheryl's-birthday-is [date all-dates]
  (let [what-albert-further-narrows-down-to (tell (month date) all-dates)]
    (know? what-albert-further-narrows-down-to)))
