package com.vxcompany.meetup.tripservice.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static com.vxcompany.meetup.tripservice.user.UserBuilder.aUser;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("A User object")
class UserTest {
    private static final User REMCO = new User();
    private static final User MARIEKE = new User();

    @Test
    void should_indicate_that_users_are_not_friends() {
        final User user = aUser()
                .withFriends(REMCO)
                .build();

        assertFalse(user.isFriendsWith(MARIEKE));
    }

    @Test
    void should_indicate_that_users_are_friends() {
        final User user = aUser()
                .withFriends(MARIEKE, REMCO)
                .build();

        assertTrue(user.isFriendsWith(MARIEKE));
    }
}