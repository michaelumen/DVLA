#Author: michael umenyiora
#Feature: Verify vehicle registration.
#
Feature: Verify vehicle registration

  Scenario: Verify vehicle registration
    Given User is on the dvla vehicle search page
    When User starts the vehicle enquiry process
    Then Vehicle details displayed should match details on file in "/resource"
    