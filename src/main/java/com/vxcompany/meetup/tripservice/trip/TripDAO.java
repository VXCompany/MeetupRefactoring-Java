package com.vxcompany.meetup.tripservice.trip;

import com.vxcompany.meetup.tripservice.exception.CollaboratorCallException;
import com.vxcompany.meetup.tripservice.user.User;

import java.util.List;

public class TripDAO {

    public static List<Trip> findTripsByUser(final User user) {
        throw new CollaboratorCallException(
                "TripDAO should not be invoked on an unit test.");
    }

    public List<Trip> findTripsBy(final User user) {
        return TripDAO.findTripsByUser(user);
    }
}