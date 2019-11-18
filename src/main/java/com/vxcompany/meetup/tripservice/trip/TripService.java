package com.vxcompany.meetup.tripservice.trip;

import com.vxcompany.meetup.tripservice.exception.UserNotLoggedInException;
import com.vxcompany.meetup.tripservice.user.User;

import java.util.ArrayList;
import java.util.List;

//@Service
public class TripService {

    private final TripDAO tripDAO;

    public TripService(final TripDAO tripDAO) {
        this.tripDAO = tripDAO;
    }

    public List<Trip> getTripsByUser(final User user, final User loggedInUser) throws UserNotLoggedInException {
        validateUser(loggedInUser);

        return user.isFriendsWith(loggedInUser)
                ? tripsBy(user)
                : new ArrayList<>();
    }

    private void validateUser(final User loggedInUser) {
        if (loggedInUser == null) {
            throw new UserNotLoggedInException();
        }
    }

    private List<Trip> tripsBy(final User user) {
        return tripDAO.findTripsBy(user);
    }
}
