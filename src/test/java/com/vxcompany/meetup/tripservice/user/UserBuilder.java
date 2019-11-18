package com.vxcompany.meetup.tripservice.user;

import com.vxcompany.meetup.tripservice.trip.Trip;

import static java.util.Arrays.asList;

public class UserBuilder {
    private User[] friends = new User[]{};
    private Trip[] trips = new Trip[]{};

    public static UserBuilder aUser() {
        return new UserBuilder();
    }

    public UserBuilder withFriends(final User... friends) {
        this.friends = friends;
        return this;
    }

    public UserBuilder withTrips(final Trip... trips) {
        this.trips = trips;
        return this;
    }

    public User build() {
        final User user = new User();
        asList(friends).forEach(user::addFriend);
        asList(trips).forEach(user::addTrip);
        return user;
    }
}
