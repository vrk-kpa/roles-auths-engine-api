package fi.vm.kapa.rova.admin.model;

public class ClientDetailDTO {

    private String clientId;
    private String apiSecret;
    private String oauthSecret;

    public ClientDetailDTO() {
        // NOP
    }

    public ClientDetailDTO(String clientId, String apiSecret, String oauthSecret) {
        this.clientId = clientId;
        this.apiSecret = apiSecret;
        this.oauthSecret = oauthSecret;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

    public String getOauthSecret() {
        return oauthSecret;
    }

    public void setOauthSecret(String oauthSecret) {
        this.oauthSecret = oauthSecret;
    }
}
