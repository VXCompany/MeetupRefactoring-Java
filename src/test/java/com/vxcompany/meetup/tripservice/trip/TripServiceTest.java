package com.vxcompany.meetup.tripservice.trip;

import com.vxcompany.meetup.tripservice.exception.UserNotLoggedInException;
import com.vxcompany.meetup.tripservice.user.User;
import com.vxcompany.meetup.tripservice.user.UserBuilder;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
class TripServiceTest {
    private static final User A_USER = new User();
    private static final User GUEST = null;
    private static final User REGISTERED_USER = new User();
    private static final Trip TO_BAARN = new Trip();

    @InjectMocks
    private TripService tripService;

    @Mock
    private TripDAO tripDAO;

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
                .build();

        when(tripDAO.findTripsBy(befriendedUser))
                .thenReturn(Collections.singletonList(TO_BAARN));

        final List<Trip> trips = tripService.getTripsByUser(befriendedUser, REGISTERED_USER);

        assertEquals(1, trips.size());
    }
}