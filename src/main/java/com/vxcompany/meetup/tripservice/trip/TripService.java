package com.vxcompany.meetup.tripservice.trip;

import com.vxcompany.meetup.tripservice.exception.UserNotLoggedInException;
import com.vxcompany.meetup.tripservice.user.User;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    public List<Trip> getTripsByUser(final User user, final User loggedInUser) throws UserNotLoggedInException {
        if (loggedInUser == null) {
            throw new UserNotLoggedInException();
        }

        return user.isFriendsWith(loggedInUser)
                ? tripsBy(user)
                : new ArrayList<>();
    }

    protected List<Trip> tripsBy(final User user) {
        return TripDAO.findTripsByUser(user);
    }
}
