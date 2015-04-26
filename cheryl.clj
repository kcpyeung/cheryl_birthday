;; Anything you type in here will be executed
;; immediately with the results shown on the
;; right.
(use 'clojure.data)
(require '[clojure.string :as str])

(def possible-dates '("May 15", "May 16", "May 19",
                          "June 17", "June 18",
                          "July 14", "July 16",
                          "August 14", "August 15", "August 17"
                    )
)

(defn tell [part]
  (filter #(.contains % part) possible-dates)
)

(defn know [dates]
  (= 1 (count dates))
)

(defn month [date]
  (first (str/split date #" "))
)

(defn day [date]
  (second (str/split date #" "))
)

(defn statement3 [date]
  (let [candidates (tell (month date))]
    (and
      (not (know candidates))
      (not-any? true? (map know (map tell (map day candidates))))
    )
  )
)

(def months-with-uniq-days
  (map month (flatten (filter #(= (count %) 1) (map tell (map day possible-dates)))))
)

(flatten (map tell months-with-uniq-days))

(def months-without-uniq-days
   (distinct (map #(month %) (clojure.set/difference (set possible-dates) (set (flatten (map tell months-with-uniq-days))))))
)

(defn statement4 [date]
  (let [candidates (tell (day date))]
    (let [allowed-candidates (set (filter #(contains? (set months-without-uniq-days) (month %)) candidates))]
      (clojure.set/difference (set possible-dates) allowed-candidates)
    )
  )
)


(tell (day "May 15"))

(statement4 "July 14")


