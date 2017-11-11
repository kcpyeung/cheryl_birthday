(ns cheryl-birthday.core-test
  (:require [clojure.test :refer :all]
            [cheryl-birthday.core :refer :all]
            [clojure.string :as s]))

(def dates ["May 15", "May 16", "May 19", "June 17", "June 18", "July 14", "July 16", "August 14", "August 15", "August 17"])

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
    (is (albert-doesn't-know-and-he-knows-bernard-can't-tell-either "August 15" dates)))
  (testing "Bernard can't tell if there are multiple dates on that day"
    (is (albert-doesn't-know-and-he-knows-bernard-can't-tell-either "August 15" dates)))
  (testing "it cannot be June because otherwise Bernard might have a unique answer in June 18"
    (is (not (albert-doesn't-know-and-he-knows-bernard-can't-tell-either "June 17" dates)))))

(run-tests)
