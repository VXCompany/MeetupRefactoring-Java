package com.vxcompany.meetup.tripservice.trip;

import com.vxcompany.meetup.tripservice.exception.UserNotLoggedInException;
import com.vxcompany.meetup.tripservice.user.User;
import com.vxcompany.meetup.tripservice.user.UserBuilder;
import org.junit.jupiter.api.BeforeEach;
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
    private TripService tripService;

    @BeforeEach
    void setUp() {
        tripService = new TestableTripService();
    }

    @Test
    void should_throw_exception_when_user_is_not_logged_in() {
        assertThrows(UserNotLoggedInException.class,
                () -> tripService.getTripsByUser(A_USER, GUEST));
    }

    @Test
    void should_return_no_trips_from_strangers() {
        final List<Trip> trips = tripService.getTripsByUser(A_USER, REGISTERED_USER);

        assertTrue(trips.isEmpty());
    }

    @Test
    void should_return_trips_from_friends() {
        final User befriendedUser = UserBuilder.aUser()
                .withFriends(REGISTERED_USER, A_USER)
                .withTrips(TO_BAARN)
                .build();

        final List<Trip> trips = tripService.getTripsByUser(befriendedUser, REGISTERED_USER);

        assertEquals(1, trips.size());
    }

    private static class TestableTripService extends TripService {
        @Override
        protected List<Trip> tripsBy(final User user) {
            return user.trips();
        }
    }
}