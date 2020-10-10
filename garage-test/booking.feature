Feature: Be able to booking

		Background:
			  * def token_response = call read('token.feature') {email:'admin@garage.com', password:'123456'}
				* def token =  token_response.token
				
    Scenario: Create Booking
        Given url GARAGE_API_URL
        And path "/bookings"
        And header Authorization = 'token ' + token
        And request 
        """
        {
           "date": "2020-01-02",
    			 "comments": "comment1",
    			 "vehicle_number_plate" : "AAA-111",
    			 "vehicle_type": "car",
    			 "base_service": {
        	 "id": 1
    			 }
        }
        """
        And header Content-Type = 'application/json; charset=utf-8'
        When method post
        Then status 201
        And print response
        And def location = responseHeaders['Location'][0]
        And def booking_id = location.substring(location.lastIndexOf('/')+1)
        And print booking_id
        
        Given path '/bookings/' + booking_id
        And header Authorization = 'token ' + token
        When method get
        Then status 200
        And print response
        And match response.id == parseInt(booking_id)
        And match response ==
        """
        {
        	 "date": "2020-01-02",
    			 "comments": "comment1",
    			 "base_service": {
    			 		"price": "#number",
    			 		"name": "Annual Service",
    			 		"id": 1,
    			 		"category": "base"
    			 	},
    			 "vat_number": "#null",
    			 "vehicle_model": "#null",
    			 "mechanic": "#null",
    			 "vehicle_type": "car",
    			 "reference": "#string",
    			 "vehicle_number_plate": "AAA-111",
    			 "vehicle_brand": "#null",
    			 "total": "#number",
    			 "extra_services": "#[0]",
    			 "parts": "#[0]",
    			 "id": "#number",
    			 "vehicle_engine": "#null",
    			 "status": "booked",
    			 "customer": {
		            "id": 1,
		            "full_name": "#string",
		            "mobile": "#string",
		            "email": "#string",
		            "role": "#string"
        		}
    		}
        """
       
		Scenario: Get Booking
        Given url GARAGE_API_URL
        And path "/bookings/1"
        And header Authorization = 'token ' + token
        When method get
        Then status 200
        And print response
        And match response contains
        """
        {
        	 "date": "#string",
    			 "comments": "#string",
    			 "base_service": {
    			 		"price": "#number",
    			 		"name": "#string",
    			 		"id": "#number",
    			 		"category": "#string"
    			 	},
    			 "vat_number": "#present",
    			 "vehicle_model": "#present",
    			 "mechanic": "#present",
    			 "vehicle_type": "#string",
    			 "reference": "#string",
    			 "vehicle_number_plate": "#string",
    			 "vehicle_brand": "#present",
    			 "total": "#number",
    			 "extra_services": "#[]",
    			 "parts": "#[]",
    			 "id": "#number",
    			 "vehicle_engine": "#present",
    			 "status": "#string",
    			 "customer": {
		            "id": 1,
		            "full_name": "#string",
		            "mobile": "#string",
		            "email": "#string",
		            "role": "#string"
        		}
    		}
        """
        
    Scenario: List Bookings
        Given url GARAGE_API_URL
        And path "/bookings"
        And header Authorization = 'token ' + token
        When method get
        Then status 200
        And print response
        And match response == '#[]'
        And match response contains
        """
        {
        	 "date": "#string",
    			 "comments": "#string",
    			 "base_service": {
    			 		"price": "#number",
    			 		"name": "#string",
    			 		"id": "#number",
    			 		"category": "#string"
    			 	},
    			 "vat_number": "#present",
    			 "vehicle_model": "#present",
    			 "mechanic": "#present",
    			 "vehicle_type": "#string",
    			 "reference": "#string",
    			 "vehicle_number_plate": "#string",
    			 "vehicle_brand": "#present",
    			 "total": "#number",
    			 "extra_services": "#[]",
    			 "parts": "#[]",
    			 "id": "#number",
    			 "vehicle_engine": "#present",
    			 "status": "#string",
    			 "customer": {
		            "id": 1,
		            "full_name": "#string",
		            "mobile": "#string",
		            "email": "#string",
		            "role": "#string"
        		}
    		}
        """
    
        Scenario: List Bookings when status=booked
        Given url GARAGE_API_URL
        And path "/bookings"
        And header Authorization = 'token ' + token
        And param filter[status] = "booked"
        And header Content-Type = 'application/json; charset=utf-8'
        When method get
        Then status 200
        And print response
        And match response == '#[]'
        And match response contains
         """
        {
        	 "date": "#string",
    			 "comments": "#string",
    			 "base_service": {
    			 		"price": "#number",
    			 		"name": "#string",
    			 		"id": "#number",
    			 		"category": "#string"
    			 	},
    			 "vat_number": "#present",
    			 "vehicle_model": "#present",
    			 "mechanic": "#present",
    			 "vehicle_type": "#string",
    			 "reference": "#string",
    			 "vehicle_number_plate": "#string",
    			 "vehicle_brand": "#present",
    			 "total": "#number",
    			 "extra_services": "#[]",
    			 "parts": "#[]",
    			 "id": "#number",
    			 "vehicle_engine": "#present",
    			 "status": "booked",
    			 "customer": {
		            "id": 1,
		            "full_name": "#string",
		            "mobile": "#string",
		            "email": "#string",
		            "role": "#string"
        		}
    		}
        """
        
    Scenario: List Bookings when status=fixed
        Given url GARAGE_API_URL
        And path "/bookings"
        And header Authorization = 'token ' + token
        And param filter[status] = "fixed"
        And header Content-Type = 'application/json; charset=utf-8'
        When method get
        Then status 200
        And print response
        And match response == '#[]'
        And match response contains
         """
        {
        	 "date": "#string",
    			 "comments": "#string",
    			 "base_service": {
    			 		"price": "#number",
    			 		"name": "#string",
    			 		"id": "#number",
    			 		"category": "#string"
    			 	},
    			 "vat_number": "#present",
    			 "vehicle_model": "#present",
    			 "mechanic": "#present",
    			 "vehicle_type": "#string",
    			 "reference": "#string",
    			 "vehicle_number_plate": "#string",
    			 "vehicle_brand": "#present",
    			 "total": "#number",
    			 "extra_services": "#[]",
    			 "parts": "#[]",
    			 "id": "#number",
    			 "vehicle_engine": "#present",
    			 "status": "fixed",
    			 "customer": {
		            "id": "#number",
		            "full_name": "#string",
		            "mobile": "#string",
		            "email": "#string",
		            "role": "#string"
        		}
    		}
        """
        
    Scenario: List Bookings when status=collected
        Given url GARAGE_API_URL
        And path "/bookings"
        And header Authorization = 'token ' + token
        And param filter[status] = "collected"
        And header Content-Type = 'application/json; charset=utf-8'
        When method get
        Then status 200
        And print response
        And match response == '#[]'
        And match response contains
         """
        {
        	 "date": "#string",
    			 "comments": "#string",
    			 "base_service": {
    			 		"price": "#number",
    			 		"name": "#string",
    			 		"id": "#number",
    			 		"category": "#string"
    			 	},
    			 "vat_number": "#present",
    			 "vehicle_model": "#present",
    			 "mechanic": "#present",
    			 "vehicle_type": "#string",
    			 "reference": "#string",
    			 "vehicle_number_plate": "#string",
    			 "vehicle_brand": "#present",
    			 "total": "#number",
    			 "extra_services": "#[]",
    			 "parts": "#[]",
    			 "id": "#number",
    			 "vehicle_engine": "#present",
    			 "status": "collected",
    			 "customer": {
		            "id": 1,
		            "full_name": "#string",
		            "mobile": "#string",
		            "email": "#string",
		            "role": "#string"
        		}
    		}
        """
        
    Scenario: List Bookings when status=in_service
        Given url GARAGE_API_URL
        And path "/bookings"
        And header Authorization = 'token ' + token
        And param filter[status] = "in_service"
        And header Content-Type = 'application/json; charset=utf-8'
        When method get
        Then status 200
        And print response
        And match response == '#[]'
        And match response contains
         """
        {
        	 "date": "#string",
    			 "comments": "#string",
    			 "base_service": {
    			 		"price": "#number",
    			 		"name": "#string",
    			 		"id": "#number",
    			 		"category": "#string"
    			 	},
    			 "vat_number": "#present",
    			 "vehicle_model": "#present",
    			 "mechanic": "#present",
    			 "vehicle_type": "#string",
    			 "reference": "#string",
    			 "vehicle_number_plate": "#string",
    			 "vehicle_brand": "#present",
    			 "total": "#number",
    			 "extra_services": "#[]",
    			 "parts": "#[]",
    			 "id": "#number",
    			 "vehicle_engine": "#present",
    			 "status": "in_service",
    			 "customer": {
		            "id": 1,
		            "full_name": "#string",
		            "mobile": "#string",
		            "email": "#string",
		            "role": "#string"
        		}
    		}
        """
        
    Scenario: List Bookings when limit=1
        Given url GARAGE_API_URL
        And path "/bookings"
        And header Authorization = 'token ' + token
        And param limit = 1
        And header Content-Type = 'application/json; charset=utf-8'
        When method get
        Then status 200
        And print response
        And match response == '#[1]'
        And match response contains
        """
        {
        	 "date": "#string",
    			 "comments": "#string",
    			 "base_service": {
    			 		"price": "#number",
    			 		"name": "#string",
    			 		"id": "#number",
    			 		"category": "#string"
    			 	},
    			 "vat_number": "#present",
    			 "vehicle_model": "#present",
    			 "mechanic": "#present",
    			 "vehicle_type": "#string",
    			 "reference": "#string",
    			 "vehicle_number_plate": "#string",
    			 "vehicle_brand": "#present",
    			 "total": "#number",
    			 "extra_services": "#[]",
    			 "parts": "#[]",
    			 "id": "#number",
    			 "vehicle_engine": "#present",
    			 "status": "#string",
    			 "customer": {
		            "id": 1,
		            "full_name": "#string",
		            "mobile": "#string",
		            "email": "#string",
		            "role": "#string"
        		}
    		}
        """
        
    Scenario: List Bookings when limit=2
        Given url GARAGE_API_URL
        And path "/bookings"
        And header Authorization = 'token ' + token
        And param limit = 2
        And header Content-Type = 'application/json; charset=utf-8'
        When method get
        Then status 200
        And print response
        And match response == '#[2]'
        And match response contains
        """
        {
        	 "date": "#string",
    			 "comments": "#string",
    			 "base_service": {
    			 		"price": "#number",
    			 		"name": "#string",
    			 		"id": "#number",
    			 		"category": "#string"
    			 	},
    			 "vat_number": "#present",
    			 "vehicle_model": "#present",
    			 "mechanic": "#present",
    			 "vehicle_type": "#string",
    			 "reference": "#string",
    			 "vehicle_number_plate": "#string",
    			 "vehicle_brand": "#present",
    			 "total": "#number",
    			 "extra_services": "#[]",
    			 "parts": "#[]",
    			 "id": "#number",
    			 "vehicle_engine": "#present",
    			 "status": "#string",
    			 "customer": {
		            "id": 1,
		            "full_name": "#string",
		            "mobile": "#string",
		            "email": "#string",
		            "role": "#string"
        		}
    		}
        """
    
    Scenario: Update Booking
        Given url GARAGE_API_URL
        And path "/bookings"
        And header Authorization = 'token ' + token
        And request 
        """
        {
           "date": "2020-01-02",
    			 "comments": "comment1",
    			 "vehicle_number_plate" : "AAA-111",
    			 "vehicle_type": "car",
    			 "base_service": {
        	 "id": 1
    			 }
        }
        """
        And header Content-Type = 'application/json; charset=utf-8'
        When method post
        Then status 201
        And print response
        And def location = responseHeaders['Location'][0]
        And def booking_id = location.substring(location.lastIndexOf('/')+1)
        And print booking_id
        
        Given path '/bookings/' + booking_id
        And header Authorization = 'token ' + token
        And request 
        """
				{
				    "reference": "1d669e10-9b78-441e-871e-50586d7e2e2b",
				    "vehicle_number_plate": "AAA-111",
				    "vehicle_model": null,
				    "vehicle_brand": null,
				    "vehicle_engine": null,
				    "vehicle_type": "car",
				    "status": "booked",
				    "base_service": {
				        "id": 1,
				        "name": "annual_service",
				        "category": "base",
				    },
				    "extra_services": [],
				    "parts": [],
				    "mechanic": null,
				    "customer": {
		            "id": 1,
		            "full_name": "#string",
		            "mobile": "#string",
		            "email": "#string",
		            "role": "#string"
        		},
				    "date": "2020-01-02",
				    "comments": "new comment",
				    "vat_number": null
				}
        """

        When method put
        Then status 201
        
        Given path '/bookings/' + booking_id
        And header Authorization = 'token ' + token
        When method get
        Then status 200
        And print response
        And match response.id == parseInt(booking_id)
        And match response ==
        """
        {
        	 "date": "2020-01-02",
    			 "comments": "new comment",
    			 "base_service": {
    			 		"price": "#number",
    			 		"name": "Annual Service",
    			 		"id": 1,
    			 		"category": "base"
    			 	},
    			 "vat_number": "#null",
    			 "vehicle_model": "#null",
    			 "mechanic": "#null",
    			 "vehicle_type": "car",
    			 "reference": "#string",
    			 "vehicle_number_plate": "AAA-111",
    			 "vehicle_brand": "#null",
    			 "total": "#number",
    			 "extra_services": "#[0]",
    			 "parts": "#[0]",
    			 "id": "#number",
    			 "vehicle_engine": "#null",
    			 "status": "booked",
    			 "customer": "#present"
    		}
        """
        
    Scenario: Delete Booking
    		Given url GARAGE_API_URL
        And path "/bookings"
        And header Authorization = 'token ' + token
        And request 
        """
        {
           "date": "2020-01-02",
    			 "comments": "comment1",
    			 "vehicle_number_plate" : "AAA-111",
    			 "vehicle_type": "car",
    			 "base_service": {
        	 "id": 1
    			 }
        }
        """
        And header Content-Type = 'application/json; charset=utf-8'
        When method post
        Then status 201
        And print response
        And def location = responseHeaders['Location'][0]
        And def booking_id = location.substring(location.lastIndexOf('/')+1)
        And print booking_id
        
        Given path '/bookings/' + booking_id
        And header Authorization = 'token ' + token
        When method delete
        Then status 202
        
        Given path '/bookings/' + booking_id
        And header Authorization = 'token ' + token
        When method get
        Then status 404
