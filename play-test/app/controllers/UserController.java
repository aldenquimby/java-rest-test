package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import database.Database;
import models.User;
import play.libs.F;
import play.libs.Json;
import play.mvc.*;

import java.util.List;

public class UserController extends Controller
{
    public static Result getUsers()
    {
        List<User> users = Database.getUsers();
        return ok(Json.toJson(users));
    }

    public static Result getUser(Long id)
    {
        User user = Database.getUser(id);
        if (user == null)
        {
            return notFound(success(false));
        }
        return ok(Json.toJson(user));
    }

    public static Result createUser()
    {
        User newUser = Json.fromJson(request().body().asJson(), User.class);
        User inserted = Database.addUser(newUser);
        return created(Json.toJson(inserted));
    }

    public static Result updateUser(Long id)
    {
        User user = Json.fromJson(request().body().asJson(), User.class);
        User updated = Database.updateUser(id, user);
        return ok(Json.toJson(updated));
    }

    public static Result deleteUser(Long id)
    {
        Database.deleteUser(id);
        return ok(success(true));
    }

    private static ObjectNode success(boolean success)
    {
        ObjectNode node = Json.newObject();
        node.put("success", success);
        return node;
    }
}