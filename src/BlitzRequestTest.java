import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.lukechenshui.blitz_request.Config;
import com.lukechenshui.blitz_request.Main;
import com.lukechenshui.blitz_request.Status;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * Created by luke on 11/23/16.
 */
public class BlitzRequestTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testGetRequest() {
        Config.setUrl("https://mappa-server.herokuapp.com");
        Config.setMethod("GET");
        Main.sendRequest();
        assertEquals(0, Status.getNumErrors());
    }

    @Test
    public void testParameterParsing() {
        Config config = new Config();
        String[] args = new String[]{"--url", "https://mappa-server.herokuapp.com", "--method", "GET", "--num", "10",
                "-uq", "{\"name\":\"Luke\",\"age\":\"19\"}"};
        new JCommander(config, args);
        assertEquals("https://mappa-server.herokuapp.com", Config.getUrl());
        assertEquals("GET", Config.getMethod());
        assertEquals(10, Config.getNumRequests());
    }

    @Test
    public void testParameterValidation() {
        exception.expect(ParameterException.class);
        Config config = new Config();
        String[] args = new String[]{"--url", "not_a_proper_url", "--method", "GE", "--num", "-1"};
        new JCommander(config, args);
    }
}
