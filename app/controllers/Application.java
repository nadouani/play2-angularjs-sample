package controllers;

import models.Customer;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import java.util.ArrayList;
import java.util.List;

public class Application extends Controller {

    public static Result index() {
        return redirect("/app/index.html");
    }

    @Security.Authenticated(Secured.class)
    public static Result customers() {
        return ok(Json.toJson(Customer.find.all()));
    }

    @Security.Authenticated(Secured.class)
    public static Result customer(Long id) {
        return ok(Json.toJson(Customer.find.byId(id)));
    }

}
