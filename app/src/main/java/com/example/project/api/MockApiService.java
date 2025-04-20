package com.example.project.api;

import com.example.project.model.LoginResponse;
import com.example.project.model.User;

import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import okhttp3.Request;
import okio.Timeout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MockApiService implements ApiService {

    private final Executor executor = Executors.newSingleThreadExecutor();

    private static final HashMap<String, String> users = new HashMap<>();

    @Override
    public Call<LoginResponse> login(User user) {
        return new FakeCall(user, false);
    }

    @Override
    public Call<LoginResponse> register(User user) {
        return new FakeCall(user, true);
    }

    static class FakeCall implements Call<LoginResponse> {
        private final User user;
        private final boolean isRegistration;

        FakeCall(User user, boolean isRegistration) {
            this.user = user;
            this.isRegistration = isRegistration;
        }

        @Override
        public void enqueue(Callback<LoginResponse> callback) {
            Executors.newSingleThreadExecutor().execute(() -> {
                try {
                    Thread.sleep(1000);

                    LoginResponse response = new LoginResponse();

                    if (isRegistration) {
                        if (users.containsKey(user.getUsername())) {
                            response.setSuccess(false);
                            response.setMessage("Username already exists");
                        } else {
                            users.put(user.getUsername(), user.getPassword());
                            response.setSuccess(true);
                            response.setMessage("Registration successful");
                        }
                    } else {
                        String storedPassword = users.get(user.getUsername());
                        if (storedPassword != null && storedPassword.equals(user.getPassword())) {
                            response.setSuccess(true);
                            response.setMessage("Login successful");
                        } else {
                            response.setSuccess(false);
                            response.setMessage("Invalid username or password");
                        }
                    }

                    callback.onResponse(this, Response.success(response));

                } catch (InterruptedException e) {
                    callback.onFailure(this, e);
                }
            });
        }

        @Override public Response<LoginResponse> execute() { return null; }
        @Override public boolean isExecuted() { return false; }
        @Override public void cancel() {}
        @Override public boolean isCanceled() { return false; }
        @Override public Call<LoginResponse> clone() { return this; }
        @Override public Request request() { return new Request.Builder().url("http://localhost").build(); }
        @Override public Timeout timeout() { return new Timeout(); }
    }
}
