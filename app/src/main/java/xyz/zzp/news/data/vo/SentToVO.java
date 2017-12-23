package xyz.zzp.news.data.vo;

/**
 * Created by Lenovo on 12/17/2017.
 */

public class SentToVO {

    private String sentToID;
    private String sentDate;

    private ActedUserVO actedUser;
    private ActedUserVO receivedUser;

    public String getSentToID() {
        return sentToID;
    }

    public String getSentDate() {
        return sentDate;
    }

    public ActedUserVO getActedUser() {
        return actedUser;
    }

    public ActedUserVO getReceivedUser() {
        return receivedUser;
    }
}
