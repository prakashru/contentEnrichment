package service;

import org.geonames.ToponymSearchResult;

/**
 * Created by Silvia on 31. 8. 2014.
 * @author Silvia
 */
public interface GeonamesService {
    /**
     * It prints basic informations, latitude, longitude and XML about wanted object. It is also returning this results.
     * For calling this method you also need to write your user name for geonames services. (You can register here http://www.geonames.org/login)
     * @param name
     * @param user
     * @return ToponymSearchResult
     */
    public ToponymSearchResult findInfo(String name, String user);

    /**
     * This method prints coordinates (bounding box) of wanted country. You also need to give your user name to this method.
     * @param countryCode
     * @param user
     */
    public void findBoundingBox(String countryCode, String user);
}
