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

(run-tests)
