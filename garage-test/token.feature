@ignore
Feature: API Authentication

    Scenario: Authentication test	
				Given url GARAGE_API_URL   
    		And path "/token"
        And request
        """
        {
        	"email": '#(email)',
    			"password": '#(password)'
        }
        """
    		When method POST
    		Then status 200
    		And print response
    		* def token = response.token
    		
