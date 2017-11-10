(ns cheryl-birthday.core-test
  (:require [clojure.test :refer :all]
            [cheryl-birthday.core :refer :all]))

(deftest test-know?
  (testing "having muliple options is not knowing"
    (is (not (know? ["May 3" "May 6"]))))

  (testing "having no option at all is not knowing"
    (is (not (know? []))))

  (testing "having exactly one option is knowing"
    (is (know? ["May 6"]))))

(deftest test-tell
  (let [dates ["May 15", "May 16", "May 19", "June 17", "June 18", "July 14", "July 16", "August 14", "August 15", "August 17"]]
    (testing "telling the month narrows down possible answers to that month"
      (is (= ["May 15" "May 16" "May 19"] (tell "May" dates))))
    (testing "telling the day narrows down possible answers to that day"
      (is (= ["May 15" "August 15"] (tell "15" dates))))))

(run-tests)
