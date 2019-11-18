package com.vxcompany.meetup.tripservice.trip;

import com.vxcompany.meetup.tripservice.exception.UserNotLoggedInException;
import com.vxcompany.meetup.tripservice.user.User;
import com.vxcompany.meetup.tripservice.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    public List<Trip> getTripsByUser(final User user) throws UserNotLoggedInException {
        List<Trip> tripList = new ArrayList<>();
        final User loggedUser = getLoggedUser();
        if (loggedUser != null) {
            final boolean isFriend = user.isFriendsWith(loggedUser);
            if (isFriend) {
                tripList = tripsBy(user);
            }
            return tripList;
        } else {
            throw new UserNotLoggedInException();
        }
    }

    protected List<Trip> tripsBy(final User user) {
        return TripDAO.findTripsByUser(user);
    }

    protected User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }

}
