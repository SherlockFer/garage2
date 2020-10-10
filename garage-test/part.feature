Feature: Be able to manage CarParts
     
    Background:
			  * def token_response = call read('token.feature') {email:'admin@garage.com', password:'123456'}
				* def token =  token_response.token
				 
    Scenario: List CarParts
        Given url GARAGE_API_URL
        And path "/vehicles/parts"
        And header Authorization = 'token ' + token
        When method get
        Then status 200
        And print response
        And match response == '#[]'
        And match response contains
        """
        {
        	 "id": "#number",
        	 "name": "#string",
        	 "price": "#number"
    		}
        """
        
    Scenario: Get CarPart
        Given url GARAGE_API_URL
        And path "/vehicles/parts/1"
        And header Authorization = 'token ' + token
        When method get
        Then status 200
        And print response
        And match response contains
        """
        {
        	 "id": "#number",
        	 "name": "#string",
        	 "price": "#number"
    		}
        """