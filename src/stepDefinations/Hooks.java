package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		StepDefinations sd = new StepDefinations();
		if(sd.placeId == null) {
		sd.add_place_payload("Gat", "Spanish", "Europe");
		sd.user_calls_with_http_request("AddPlaceAPI", "POST");
		sd.verify_place_id_created_is_mapped_to_using("Gat", "GetPlaceAPI");
	  }
	}
}
