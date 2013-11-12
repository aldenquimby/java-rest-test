import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.libs.F.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

public class UserIntegrationTests
{
    /*
    final static int PORT = 3333;
    final static String API_BASE = "/api/v1/users";
    final static String APPLICATION_JSON = "application/json";

    @Test
    public void fullBlownTestWithServer()
    {
        runServerHitEndpoint("", new Callback<TestBrowser>() {
            @Override
            public void invoke(TestBrowser browser) throws Throwable {
                assertThat(browser.pageSource()).contains("alden");
            }
        });
    }

    @Test
    public void simpleTestWithReverseRouting()
    {
        Result result = callAction(controllers.routes.UserController.getUsers());
        assertThat(status(result)).isEqualTo(OK);
        assertThat(contentType(result)).isEqualTo(APPLICATION_JSON);
        assertThat(contentAsString(result)).contains("alden");
    }

    private void runServerHitEndpoint(final String endpoint, final Callback<TestBrowser> test)
    {
        running(testServer(PORT, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
            @Override
            public void invoke(TestBrowser browser) throws Throwable {
                browser.goTo(API_BASE + endpoint);
                test.invoke(browser);
            }
        });
    }
    */

}
