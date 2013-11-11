package controllers;


import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.login;

import static play.data.Form.form;

public class Auth extends Controller{

    public static class Login{
        public String username;
        public String password;

        public String validate() {
            if (User.authenticate(username, password) == null) {
                return "Invalid user or password";
            }
            return null;
        }
    }

    public static Result login(){
        return ok(views.html.login.render(form(Login.class)));
    }

    public static Result authenticate(){
        Form<Login> loginForm = form(Login.class).bindFromRequest();

        if (loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));
        } else {
            String username = loginForm.get().username;
            User user = User.byUsername(username);

            session().clear();
            session("username", user.username);
            return redirect(routes.Application.index());
        }
    }

    @Security.Authenticated(Secured.class)
    public static Result logout(){
        session().clear();

        flash("success", "You've been logged out");
        return redirect(routes.Auth.login());
    }
}
