package com.vxcompany.meetup.tripservice.trip;

import com.vxcompany.meetup.tripservice.exception.UserNotLoggedInException;
import com.vxcompany.meetup.tripservice.user.User;
import com.vxcompany.meetup.tripservice.user.UserBuilder;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TripServiceTest {
    private static final User A_USER = new User();
    private static final User GUEST = null;
    private static final User REGISTERED_USER = new User();
    private static final Trip TO_BAARN = new Trip();

    @Test
    void should_throw_exception_when_user_is_not_logged_in() {
        final TripService tripService = new TestableTripService(GUEST);

        assertThrows(UserNotLoggedInException.class,
                () -> tripService.getTripsByUser(A_USER));
    }

    @Test
    void should_return_no_trips_from_strangers() {
        final TripService tripService = new TestableTripService(REGISTERED_USER);

        final List<Trip> trips = tripService.getTripsByUser(A_USER);

        assertTrue(trips.isEmpty());
    }

    @Test
    void should_return_trips_from_friends() {
        final TripService tripService = new TestableTripService(REGISTERED_USER);
        final User befriendedUser = UserBuilder.aUser()
                .withFriends(REGISTERED_USER, A_USER)
                .withTrips(TO_BAARN)
                .build();

        final List<Trip> trips = tripService.getTripsByUser(befriendedUser);

        assertEquals(1, trips.size());
    }

    private static class TestableTripService extends TripService {

        private final User loggedInUser;

        private TestableTripService(final User loggedInUser) {
            this.loggedInUser = loggedInUser;
        }

        @Override
        protected User getLoggedUser() {
            return loggedInUser;
        }

        @Override
        protected List<Trip> tripsBy(final User user) {
            return user.trips();
        }
    }
}