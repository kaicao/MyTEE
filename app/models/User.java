package models;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.ObjectId;
import play.modules.mongodb.jackson.MongoDB;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kai
 * Date: 3/30/13
 * Time: 3:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class User {

    @Id
    @ObjectId
    public String id;

    public String firstName;
    public String lastName;

    private static JacksonDBCollection<User, String> coll = MongoDB.getCollection("users", User.class, String.class);

    public static List<User> all() {
        return User.coll.find().toArray();
    }

    public static void create(User user) {
        User.coll.save(user);
    }

    public static void delete(String id) {
        User user = User.coll.findOneById(id);
        if (user != null)
            User.coll.remove(user);
    }
}
