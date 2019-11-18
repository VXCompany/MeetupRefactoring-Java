package com.vxcompany.meetup.tripservice.trip;

import com.vxcompany.meetup.tripservice.exception.CollaboratorCallException;
import com.vxcompany.meetup.tripservice.user.User;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TripDAOTest {
    private static final User A_USER = new User();
    private final TripDAO tripDAO = new TripDAO();

    @Test
    void should_throw_an_exception_when_finding_trips_for_user() {
        assertThrows(CollaboratorCallException.class,
                () -> tripDAO.findTripsBy(A_USER));
    }
}