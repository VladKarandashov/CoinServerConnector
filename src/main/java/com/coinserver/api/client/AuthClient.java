package com.coinserver.api.client;

import com.coinserver.api.common.request.LoginRequest;
import com.coinserver.api.common.response.LoginResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.util.Optional;

public class AuthClient {

    private final String serverUrl;

    protected AuthClient(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    private static final String AUTH_PREFIX = "/api/v1/auth";

    public Optional<LoginResponse> login(LoginRequest loginRequestBody) {
        Request request = new Request.Builder()
                .url(serverUrl + AUTH_PREFIX + "/login")
                .post(RequestBody.create(CoinServerClient.writeToJsonString(loginRequestBody), MediaType.get("application/json")))
                .build();

        return CoinServerClient.processRequest(request, new TypeReference<>() {
        });
    }

    public Optional<LoginResponse> registration(LoginRequest loginRequestBody) {
        Request request = new Request.Builder()
                .url(serverUrl + AUTH_PREFIX + "/registration")
                .post(RequestBody.create(CoinServerClient.writeToJsonString(loginRequestBody), MediaType.get("application/json")))
                .build();

        return CoinServerClient.processRequest(request, new TypeReference<>() {
        });
    }
}
