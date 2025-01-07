package resoruces;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.location_child;

public class TestDataBuild {
	public AddPlace addPlacePayload(String name, String language, String address){
		//body parameters
				AddPlace place = new AddPlace();
				place.setAccuracy(50);
				place.setAddress(address);
				place.setLanguage(language);
				place.setName(name);
				place.setPhone_number("(+91) 866 858 8290");
				place.setWebsite("www.arihantgat.com");
				List<String> myList = new ArrayList<String>();
				myList.add("BSNL Office");
				myList.add("Mangal Karyalay");
				place.setTypes(myList);
				location_child l = new location_child();
				l.setLat(-38.383494);
				l.setLng(33.427362);
				place.setLocation(l);
				return place;
	}
	public String deletePlacePyload(String placeId) {
		return "{\r\n"
				+ "    \"place_id\":\""+placeId+"\"\r\n"
				+ "}\r\n"
				+ "";
	}
}
