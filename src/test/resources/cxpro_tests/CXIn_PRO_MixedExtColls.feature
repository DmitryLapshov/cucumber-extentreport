Feature: CXIn_PRO_MixedExtColls
  Functional tests for CXIn_PRO_MixedExtColls

  Scenario: Default behavior
    Given I can load "CXIn_PRO_MixedExtColls.xml"
    Then I can log in as Loan Officer
    Then I can see my applicants