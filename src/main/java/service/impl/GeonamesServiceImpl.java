package service.impl;

import com.jayway.restassured.response.Response;
import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;
import service.GeonamesService;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.path.xml.XmlPath.from;

/**
 * Created by Silvia on 31. 8. 2014.
 * @author Silvia
 */

public class GeonamesServiceImpl implements GeonamesService {

    @Override
    public ToponymSearchResult findInfo(String name, String user)  {
        WebService.setUserName(user);
        ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
        searchCriteria.setQ(name);

        ToponymSearchResult searchResult = null;
        try {
            searchResult = WebService.search(searchCriteria);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int count=0;
        for (Toponym toponym : searchResult.getToponyms()) {
            count++;
            double latitude = toponym.getLatitude();
            double longitude = toponym.getLongitude();
            System.out.println(count+". Name: "+toponym.getName()+", Country Name: "+toponym.getCountryName()+", Longitude: "
                    +longitude+", Latitude:  "+latitude+", Country Code: "+toponym.getCountryCode());
            Response response = get("http://ws.geonames.org/findNearbyPlaceName?lat="+latitude+"&lng="+longitude+"&username="+user);
            System.out.println("XML for this object: ");
            response.print();

        }

        return searchResult;
    }

    public void findBoundingBox(String countryCode, String user){
        Response skuska = get("http://ws.geonames.org/countryInfo?country="+countryCode+"&username="+user);
        //skuska.print();  // for printing whole XML of this country
        String xml = skuska.asString();
        String countryName = from(xml).get("geonames.country.countryName").toString();
        String west = from(xml).get("geonames.country.west").toString();
        String north = from(xml).get("geonames.country.north").toString();
        String south = from(xml).get("geonames.country.south").toString();
        String east = from(xml).get("geonames.country.east").toString();
        System.out.println("Country Name: "+countryName+"\nWest: "+west+"\nNorth:  "+north+"\nSouth: "+south+"\nEast: "+east);

    }

}
