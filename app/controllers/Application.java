package controllers;

import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

    static Form<User> userForm = Form.form(User.class);

    public static Result index() {
        return redirect(routes.Application.users());
    }

    public static Result users() {
        return ok(
                views.html.index.render(User.all(), userForm)
        );
    }

    public static Result newUser() {
        Form<User> filledForm = userForm.bindFromRequest();
        if (filledForm.hasErrors()) {
            return badRequest(
                    views.html.index.render(User.all(), filledForm)
            );
        } else {
            User.create(filledForm.get());
            return redirect(routes.Application.users());
        }
    }

    public static Result deleteUser(String id) {
        User.delete(id);
        return redirect(routes.Application.users());
    }

}
