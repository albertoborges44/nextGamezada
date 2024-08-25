package com.nextgamezada.utils;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class RestApiClient {

    private static final String ALL_GAMES_STEAM_API = "https://api.steampowered.com/ISteamApps/GetAppList/v0002/?format=json";
    private static final String GAME_DETAIL_STEAM_API = "https://store.steampowered.com/api/appdetails?appids=";

    HttpClient client = HttpClient.newHttpClient();

    public HttpResponse<String> getAllSteamGames() throws URISyntaxException, IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(ALL_GAMES_STEAM_API))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> getDetailsAboutSteamGame(long gameId) throws URISyntaxException, IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(GAME_DETAIL_STEAM_API + gameId))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

}
