(ns cheryl-birthday.core-test
  (:require [clojure.test :refer :all]
            [cheryl-birthday.core :refer :all]
            [clojure.string :as s]))

(deftest test-know?
  (testing "having muliple options is not knowing"
    (is (not (know? ["May 3" "May 6"]))))

  (testing "having no option at all is not knowing"
    (is (not (know? []))))

  (testing "having exactly one option is knowing"
    (is (know? ["May 6"]))))

(deftest test-tell
  (testing "telling the month narrows down possible answers to that month"
    (is (= ["May 15" "May 16" "May 19"] (tell "May" dates))))
  (testing "telling using month"
    (is (= ["May 15" "May 16" "May 19"] (tell (month "May 15") dates))))
  (testing "telling the day narrows down possible answers to that day"
    (is (= ["May 15" "August 15"] (tell "15" dates))))
  (testing "telling using day"
    (is (= ["May 15" "August 15"] (tell (day "May 15") dates)))))

(deftest test-albert-doesn't-know-and-he-knows-bernard-can't-tell-either
  (testing "Albert doesn't know if there are multiple dates in that month"
    (is (albert-doesn't-know-and-he-knows-bernard-can't-tell-either "August 15")))
  (testing "Bernard can't tell if there are multiple dates on that day"
    (is (albert-doesn't-know-and-he-knows-bernard-can't-tell-either "August 15")))
  (testing "it cannot be May because otherwise Bernard might have a unique answer in May 19"
    (is (not (albert-doesn't-know-and-he-knows-bernard-can't-tell-either "May 15"))))
  (testing "it cannot be June because otherwise Bernard might have a unique answer in June 18"
    (is (not (albert-doesn't-know-and-he-knows-bernard-can't-tell-either "June 17"))))
  (testing "Albert made his assertion by removing months with unique day"
    (is (= ["July 14", "July 16", "August 14", "August 15", "August 17"] (filter albert-doesn't-know-and-he-knows-bernard-can't-tell-either dates)))))

(deftest test-bernard-couldn't-tell-at-first-but-now-he-knows
  (testing "Bernard couldn't tell initially because the day part is not unique in the original list"
    (is (not-any? know? (filter bernard-couldn't-tell-at-first-but-now-he-knows dates))))
  (testing "Bernard knows after hearing Albert"
    (let [what-albert-narrows-to ["July 14", "July 16", "August 14", "August 15", "August 17"]]
      (is (= ["July 16" "August 15" "August 17"] (filter bernard-couldn't-tell-at-first-but-now-he-knows what-albert-narrows-to))))))

(deftest test-albert-knows-finally
  (let [what-albert-knew-at-first ["July 14", "July 16", "August 14", "August 15", "August 17"]
        what-bernard-narrows-to ["July 16" "August 15" "August 17"]]
    (testing "Albert couldn't tell at first"
      (is (not (know? (filter albert-knows-finally what-albert-knew-at-first)))))
    (testing "Albert knows the answer too after Bernard found out"
      (is (know? (filter albert-knows-finally what-bernard-narrows-to))))))

(deftest test-cheryl's-birthday
  (is (= "July 16" (cheryl's-birthday))))

(run-tests)
