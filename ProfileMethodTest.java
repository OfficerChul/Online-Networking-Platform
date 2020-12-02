import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileMethodTest {
    Profile profileEx = new Profile("",new Account("","")
            ,"","","", new String[]{""});

    @Test
    void setName() {
        profileEx.setName("name");
        String expected = "name";
        String actual = profileEx.getName();
        assertEquals(expected,actual);
    }

    @Test
    void setAboutMe() {
        profileEx.setAboutMe("about me");
        String expected = "about me";
        String actual = profileEx.getAboutMe();
        assertEquals(expected,actual);
    }

    @Test
    void setEmail() {
        profileEx.setEmail("email");
        String expected = "email";
        String actual = profileEx.getEmail();
        assertEquals(expected, actual);
    }

    @Test
    void setFriendUserNames() {
        profileEx.setFriendUserNames(new String[] {"friendOne", "friendTwo", "friendThree"});
        String expected = new String[] {"friendOne", "friendTwo", "friendThree"}.toString();
        String actual = profileEx.getFriendUserNames().toString();
        assertEquals(expected, actual);
    }

    @Test
    void setLikesAndInterests() {
        profileEx.setLikesAndInterests("like and interest");
        String expected = "like and interest";
        String actual = profileEx.getLikesAndInterests();
        assertEquals(expected, actual);
    }

    @Test
    void setReceivedFriendRequests() {
        FriendRequest[] friendRequestEx = new FriendRequest[1];
        friendRequestEx[0] = new FriendRequest("sent", "receive");
        profileEx.setReceivedFriendRequests(friendRequestEx);
        String expected = "receive";
        String actual = profileEx.getReceivedFriendRequests()[0].getUsernameWhoReceive();
        assertEquals(expected, actual);
    }

    @Test
    void setSentFriendRequests() {
        FriendRequest[] friendRequestEx = new FriendRequest[1];
        friendRequestEx[0] = new FriendRequest("sent", "receive");
        profileEx.setReceivedFriendRequests(friendRequestEx);
        String expected = "sent";
        String actual = profileEx.getReceivedFriendRequests()[0].getUsernameWhoSent();
        assertEquals(expected, actual);
    }

    @Test
    void addToFriendUsernames() {
        profileEx.addToFriendUsernames("usernameAdd");
        String expected = "usernameAdd";
        String actual = profileEx.getFriendUserNames()[profileEx.getFriendUserNames().length - 1];
        assertEquals(expected, actual);
    }
