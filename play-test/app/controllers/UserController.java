package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import database.Database;
import models.User;
import play.libs.F;
import play.libs.Json;
import play.mvc.*;

import java.util.List;

public class UserController extends Mark43Controller
{
    public static F.Promise<Result> getUsersAsync()
    {
        F.Promise<List<User>> promise = makePromise(new F.Function0<List<User>>() {
            @Override
            public List<User> apply() {
                return Database.getUsers();
            }
        });

        return promise.map(new F.Function<List<User>, Result>() {
            @Override
            public Result apply(List<User> users) {
                return ok(Json.toJson(users));
            }
        });
    }

    public static F.Promise<Result> getUserAsync(final Long id)
    {
        F.Promise<User> promise = makePromise(new F.Function0<User>() {
            @Override
            public User apply() {
                return Database.getUser(id);
            }
        });

        return promise.map(new F.Function<User, Result>() {
            @Override
            public Result apply(User user) {
                return user == null ? notFound(success(false)) : ok(Json.toJson(user));
            }
        });
    }

    public static F.Promise<Result> createUserAsync()
    {
        final User newUser = Json.fromJson(request().body().asJson(), User.class);

        F.Promise<User> promise = makePromise(new F.Function0<User>() {
            @Override
            public User apply() {
                return Database.addUser(newUser);
            }
        });

        return promise.map(new F.Function<User, Result>() {
            @Override
            public Result apply(User user) {
                return created(Json.toJson(user));
            }
        });
    }

    public static F.Promise<Result> updateUserAsync(final Long id)
    {
        final User user = Json.fromJson(request().body().asJson(), User.class);

        F.Promise<User> promise = makePromise(new F.Function0<User>() {
            @Override
            public User apply() {
                return Database.updateUser(id, user);
            }
        });

        return promise.map(new F.Function<User, Result>() {
            @Override
            public Result apply(User user) {
                return ok(Json.toJson(user));
            }
        });
    }

    public static F.Promise<Result> deleteUserAsync(final Long id)
    {
        F.Promise<Boolean> promise = makePromise(new F.Function0<Boolean>() {
            @Override
            public Boolean apply() {
                return Database.deleteUser(id);
            }
        });

        return promise.map(new F.Function<Boolean, Result>() {
            @Override
            public Result apply(Boolean deleted) {
                return ok(success(true));
            }
        });
    }

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
        return Json.newObject().put("success", success);
    }
}