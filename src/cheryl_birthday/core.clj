(ns cheryl-birthday.core
  (:require [clojure.string :as s]))

(def dates ["May 15", "May 16", "May 19", "June 17", "June 18", "July 14", "July 16", "August 14", "August 15", "August 17"])

(defn know? [dates]
  (= 1 (count dates)))

(defn tell [part dates]
  (filter #(s/includes? % part) dates))

(defn month [date]
  (first (s/split date #" ")))

(defn day [date]
  (second (s/split date #" ")))

(defn albert-doesn't-know-and-he-knows-bernard-can't-tell-either [date]
  (let [what-albert-narrows-down-to (tell (month date) dates)
        all-days-in-albert's-list (into #{} (map day what-albert-narrows-down-to))
        what-albert-thinks-bernard-can-narrow-down-to (map #(tell % dates) all-days-in-albert's-list)
        bernard-can't-identify-a-unique-date (not-any? know? what-albert-thinks-bernard-can-narrow-down-to)]
    (and (not (know? what-albert-narrows-down-to)) bernard-can't-identify-a-unique-date)))

(defn bernard-couldn't-tell-at-first-but-now-he-knows [date]
  (let [what-bernard-narrows-down-to-at-first (tell (day date) dates)
        what-albert-narrowed-down-to (filter albert-doesn't-know-and-he-knows-bernard-can't-tell-either dates)
        days-in-albert's-list (into #{} (map day what-albert-narrowed-down-to))
        dates-with-the-days (map #(tell % what-albert-narrowed-down-to) days-in-albert's-list)
        what-bernard-finally-narrows-down-to (flatten (filter #(= 1 (count %)) dates-with-the-days))
        bernard-can-tell-now (some #{date} what-bernard-finally-narrows-down-to)]
    (and (not (know? what-bernard-narrows-down-to-at-first)) bernard-can-tell-now)))

(defn albert-knows-finally [date]
  (let [what-albert-narrows-down-to (tell (month date) dates)
        what-bernard-narrows-down-to (filter bernard-couldn't-tell-at-first-but-now-he-knows what-albert-narrows-down-to)]
    (and (not (know? what-albert-narrows-down-to)) (know? what-bernard-narrows-down-to))))

(defn cheryl's-birthday []
  (->> dates
       (filter albert-doesn't-know-and-he-knows-bernard-can't-tell-either)
       (filter bernard-couldn't-tell-at-first-but-now-he-knows)
       (filter albert-knows-finally)
       first))
