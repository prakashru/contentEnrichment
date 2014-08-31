import org.junit.Test;
import service.DbPediaService;
import service.GeonamesService;
import service.impl.DbPediaServiceImpl;
import service.impl.GeonamesServiceImpl;

/**
 * Created by Silvia on 31. 8. 2014.
 * @author Silvia
 */

public class TestingClass {

    @Test
    public void testGeonamesInfo() {
        GeonamesService geonames = new GeonamesServiceImpl();
        try {
            geonames.findInfo("spissky", "silv");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGeonamesBoundingBox() {
        GeonamesService geonames = new GeonamesServiceImpl();
        geonames.findBoundingBox("hu","silv");
    }

    @Test
    public void testDbPedia() {
        DbPediaService dbPedia = new DbPediaServiceImpl();
        dbPedia.getMetadata("Spiš Castle");
    }
}
