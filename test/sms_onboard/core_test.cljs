(ns sms-onboard.core-test
  (:require [sms-onboard.helpers :refer [get-env format]]
            [cljs.test :refer-macros [deftest is testing use-fixtures]]
            [cljs.nodejs :as nodejs]
            [cljs-lambda.local :refer [invoke channel]]))

(use-fixtures
  :each
  {:before (fn [] (println "starting...")
             (aset (.-env nodejs/process) "ORG_NAME" "COPA"))
   :after  (fn [] (println "tear-down...")
             (aset (.-env nodejs/process) "ORG_NAME" nil))})

(deftest test-get-env
  (testing "get retrieval of node's process.env.var"
    (is (= (get-env "ORG_NAME") "COPA"))))

(deftest formatting-strings
  (testing "test formatting strings using format fn")
    (is (= (format "Welcome to %s") "COPA") "Welcome to COPA"))