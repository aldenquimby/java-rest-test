import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;
import play.libs.Json;
import play.mvc.HandlerRef;
import play.mvc.Result;
import play.test.FakeRequest;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

public class UserIntegrationTests
{
    @Test
    public void testGetUsers()
    {
        HandlerRef action = controllers.routes.ref.UserController.getUsers();

        JsonNode res = getValidJson(action, fakeRequest(), OK);

        assertThat(res.isArray());
        assertThat(res.get(0).get("firstName").textValue()).isEqualTo("alden");
    }

    @Test
    public void testGetUser()
    {
        HandlerRef action = controllers.routes.ref.UserController.getUser(1);

        JsonNode res = getValidJson(action, fakeRequest(), OK);

        assertThat(res.get("id").asLong()).isEqualTo(1);
    }

    @Test
    public void testCreateUser()
    {
        HandlerRef action = controllers.routes.ref.UserController.createUser();
        JsonNode body = Json.newObject().put("firstName", "alden").put("lastName", "quimby");

        JsonNode res1 = getValidJson(action, body, CREATED);
        JsonNode res2 = getValidJson(action, body, CREATED);

        assertThat(res1.get("firstName").textValue()).isEqualTo("alden");
        assertThat(res2.get("lastName").textValue()).isEqualTo("quimby");
        assertThat(res1.get("id").asLong()).isNotEqualTo(res2.get("id").asLong());
    }

    @Test
    public void testUpdateUser()
    {
        HandlerRef action = controllers.routes.ref.UserController.updateUser(1);
        JsonNode body = Json.newObject().put("firstName", "alden").put("lastName", "dude");

        JsonNode res = getValidJson(action, body, OK);

        assertThat(res.get("id").asLong()).isEqualTo(1);
        assertThat(res.get("lastName").textValue()).isEqualTo("dude");
    }

    @Test
    public void testDeleteUser()
    {
        HandlerRef action = controllers.routes.ref.UserController.getUser(1);

        getValidJson(action, fakeRequest(), OK);
    }

    private JsonNode getValidJson(HandlerRef action, JsonNode body, int status)
    {
        return getValidJson(action, fakeRequest().withJsonBody(body), status);
    }

    private JsonNode getValidJson(HandlerRef action, FakeRequest fr, int status)
    {
        Result result = callAction(action, fr);
        assertThat(contentType(result)).isEqualTo("application/json");
        assertThat(status(result)).isEqualTo(status);
        return Json.parse(contentAsString(result));
    }
}
