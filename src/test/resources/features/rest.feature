@rest
Feature: REST API automation for Careers project

  @rest1
  Scenario: REST API Position CRUD
      # POST /login to get token
    Given I login via REST as "recruiter"
      # POST /positions
    When I create via REST "automation" position
      # GET /positions
    Then I verify via REST new "automation" position is in the list
      # PUT or PATCH??? /positions/{id}
    When I update via REST "automation" position
      # GET /positions/{id}
    Then I verify via REST new "automation" position is updated
      # DELETE /positions/{id}
    When I delete via REST new position
      # GET /positions
    Then I verity via REST new position is deleted

  @rest2
  Scenario: REST API Candidates CRUD
    Given I login via REST as "recruiter"
    # POST
    When I create via REST "sdet" candidate
      # PUT /candidate
      # Then I verify via REST new "sdet" candidate is in the list
    When I add via REST "txt" resume to a new candidate
    Then I verify via REST that "txt" resume has been added
      # PATCH /candidates{id}
    When I update via REST "sdet" candidate
      # GET /candidates{id}
    Then I verify via REST new "sdet" candidate is updated
      # DELETE /candidates{id}
    When I delete via REST new candidate
      # GET / candidates
    Then I verify via REST new candidate is deleted