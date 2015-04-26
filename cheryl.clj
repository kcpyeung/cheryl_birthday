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

(defn statement4 [date]
  (let [at-first (tell (day date))]
    (and
      (not (know at-first))
      (know (filter statement3 at-first))
    )
  )
)

