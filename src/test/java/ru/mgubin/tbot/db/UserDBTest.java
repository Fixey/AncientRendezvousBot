package ru.mgubin.tbot.db;

import org.junit.jupiter.api.Test;
import ru.mgubin.tbot.entity.PersToPers;
import ru.mgubin.tbot.entity.User;

import java.time.LocalDate;

class UserDBTest {
    @Test
    void getUsersByGender() {
        UserDB userDB = new UserDB();
        userDB.getUsersByGender(1);
        System.out.println(userDB.toString());

    }

    @Test
    void makeLikeTest() {
        UserDB userDB = new UserDB();
        userDB.makeLikeToUser(new PersToPers(6, 8));
    }

    @Test
    void createUser() {
        UserDB userDB = new UserDB();
        LocalDate localDate = LocalDate.of(1990, 10, 10);
        userDB.createUser(new User(10, "Gena", localDate, "MEN", "MEN", "kdfmbs dgdfd"));
    }
    @Test
    void removeLikeToUserTest() {
        UserDB userDB = new UserDB();
        userDB.removeLikeToUser(new PersToPers(5,6));
    }

    @Test
    void isCrushLikeUserTest() {
        UserDB userDB = new UserDB();
        userDB.isCrushLikeUser(3,1).equals(true);
    }
}