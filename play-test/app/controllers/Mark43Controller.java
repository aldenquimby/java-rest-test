package controllers;

import play.libs.F;
import play.mvc.Controller;

public class Mark43Controller extends Controller
{
    protected static <T> F.Promise<T> makePromise(F.Function0<T> func)
    {
        return F.Promise.promise(func);
    }
}
