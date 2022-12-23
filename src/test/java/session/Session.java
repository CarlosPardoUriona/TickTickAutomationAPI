package session;

import utils.GetProperties;

public class Session {
    public String tokenName;
    public String tokenValue;

    public void Session(){
    this.tokenName = GetProperties.getInstance().getTokenName();
    this.tokenValue = GetProperties.getInstance().getTokenValue();

    }
}
