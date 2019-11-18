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
        boolean isFriend = false;
        if (loggedUser != null) {
            for (final User friend : user.getFriends()) {
                if (friend.equals(loggedUser)) {
                    isFriend = true;
                    break;
                }
            }
            if (isFriend) {
                tripList = TripDAO.findTripsByUser(user);
            }
            return tripList;
        } else {
            throw new UserNotLoggedInException();
        }
    }

    protected User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }

}
