package com.vxcompany.meetup.tripservice.trip;

import com.vxcompany.meetup.tripservice.exception.UserNotLoggedInException;
import com.vxcompany.meetup.tripservice.user.User;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TripServiceTest {
    private static final User A_USER = new User();
    private static final User GUEST = null;

    @Test
    void should_throw_exception_when_user_is_not_logged_in() {
        final TripService tripService = new TestableTripService();

        assertThrows(UserNotLoggedInException.class,
                () -> tripService.getTripsByUser(A_USER));
    }

    private static class TestableTripService extends TripService {
        @Override
        protected User getLoggedUser() {
            return TripServiceTest.GUEST;
        }
    }
}