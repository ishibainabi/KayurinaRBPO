package vetclinic.dto;

public class TokenPairResponse {
    private String accessToken;
    private String refreshToken;

    public TokenPairResponse(String access, String refresh) {
        this.accessToken = access;
        this.refreshToken = refresh;
    }

    public String getAccessToken() { return accessToken; }
    public void setAccessToken(String accessToken) { this.accessToken = accessToken; }

    public String getRefreshToken() { return refreshToken; }
    public void setRefreshToken(String refreshToken) { this.refreshToken = refreshToken; }
}