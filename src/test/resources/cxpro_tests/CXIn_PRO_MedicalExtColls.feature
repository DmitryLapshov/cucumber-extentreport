Feature: CXIn_PRO_MedicalExtColls
  Functional tests for CXIn_PRO_MedicalExtColls

  Scenario: Default behavior
    Given I can load "CXIn_PRO_MedicalExtColls.xml"
    Then I can log in as Loan Officer
    Then I can see my applicants
