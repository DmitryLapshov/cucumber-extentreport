Feature: CXIn_PRO_ExtCollsWDisp_RR
  Functional tests for CXIn_PRO_ExtCollsWDisp_RR

  Scenario: Default behavior
    Given I can load "CXIn_PRO_ExtCollsWDisp_RR.xml"
    Then I can log in as Loan Officer
    Then I can see my applicants

  Scenario: Creation of a new application
    Given I can load "CXIn_PRO_ExtCollsWDisp_RR.xml"
    Then I can log in as Loan Officer
    Then I can see my applicants
    Then I can create a new application