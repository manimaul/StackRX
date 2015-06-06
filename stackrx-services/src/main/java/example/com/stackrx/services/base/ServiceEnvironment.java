package example.com.stackrx.services.base;


public class ServiceEnvironment {

    private static ServiceParameters secureParameters;

    public ServiceEnvironment () {
        secureParameters = new ServiceParameters("https://api.stackexchange.com", "PRODUCTION");
        secureParameters.putQueryParameter("format", "json");
        secureParameters.putHeader("Accept", "application/json");
        secureParameters.putHeader("Content-Type", "application/json");
    }

    public ServiceParameters getSecureParameters() {
        return secureParameters;
    }

}
