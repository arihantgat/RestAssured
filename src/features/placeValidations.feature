Feature: Validating Place API's
@AddPlace @Regression
Scenario Outline: Verify if place is being Successfully added using AddPlaceAPI
				Given Add Place Payload "<name>" "<language>" "<address>"
				When User calls "AddPlaceAPI" with "Post" http request
				Then the API call got success with status code 200
				And "status" in response body is "OK"
				And "scope" in response body is "APP"
				And verify place_Id created is mapped to "<name>" using "GetPlaceAPI"
				
Examples: 
				|name   |language|address|		
				|Arihant|English |Hupari |
			 #|Riyansh|Hindi   |Hupari |			
			 
@DeletePlace @Regression		 
Scenario: Verify if delete place api is working fine
			 Given DeletePlacePayload
			 When User calls "DeletePlaceAPI" with "Post" http request
			 Then the API call got success with status code 200
			 And "status" in response body is "OK"