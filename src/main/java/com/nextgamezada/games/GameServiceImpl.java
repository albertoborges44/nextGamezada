package com.nextgamezada.games;

import com.google.gson.Gson;
import com.nextgamezada.steamApp.SteamApp;
import com.nextgamezada.steamApp.SteamAppDetails;
import com.nextgamezada.utils.RestApiClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Objects;

@Service
public class GameServiceImpl implements GameService{

    private final GameDAO dao;

    Gson gson = new Gson();

    private final RestApiClient restApiClient;

    public GameServiceImpl(GameDAO dao, RestApiClient restApiClient) {
        this.dao = dao;
        this.restApiClient = restApiClient;
    }

    @Override
    public Game findByName(String name) {
        return dao.findByName(name);
    }

    @Override
    public List<Game> findByAll() {
        return dao.findAll();
    }

    @Override
    public void createGame(String name, BigDecimal price, String genre) {
        dao.createGame(name, price, genre);
    }

    public Long editGame(Game game) {
        return dao.editGame(game);
    }

    @Override
    public Long deletePool(List<Long> ids) {
        return dao.deletePool(ids);
    }

    @Override
    public SteamAppDetails searchGameInSteamLibrary(String gameName) throws URISyntaxException, IOException, InterruptedException {

        HttpResponse<String> allGames = restApiClient.getAllSteamGames();

        int indexOfDesiredGame = allGames.body().indexOf(gameName);
        String stringJsonOfDesiredGame = trimDesiredGameJson(allGames.body(), indexOfDesiredGame);

        SteamApp steamApp = gson.fromJson(stringJsonOfDesiredGame, SteamApp.class);

        SteamAppDetails steamAppDetails = getSteamAppDetailsFromGameId(steamApp.getAppid());

//        dao.createGame(
//                steamAppDetails.getData().getName(),
//                sanitizeCurrencyPriceFromSteamApp(steamAppDetails.getData().getPrice_overview().getFinal_formatted()),
//                steamAppDetails.getData().getGenres().get(0).getDescription());

        dao.createGameFromSteamSearch(
                steamAppDetails.getData().getName(),
                sanitizeCurrencyPriceFromSteamApp(steamAppDetails.getData().getPrice_overview().getFinal_formatted()),
                Objects.nonNull(steamAppDetails.getData().getGenres()) ?
                        steamAppDetails.getData().getGenres().get(0).getDescription() : "",
                Objects.nonNull(steamAppDetails.getData().getCategories()) ?
                        steamAppDetails.getData().getCategories().stream().anyMatch(category -> category.getId() == 1) : Boolean.FALSE,
                steamAppDetails.getData().getPrice_overview().getDiscount_percent() != 0);

        return steamAppDetails;
    }

    private BigDecimal sanitizeCurrencyPriceFromSteamApp(String stringPrice) {
        return new BigDecimal(stringPrice.replace("R$", "").trim().replace(",", "."));
    }

    private String trimDesiredGameJson(String allGameFromSteamJson, int indexOfDesiredGame) {

        int indexOfStartingBracket = allGameFromSteamJson.lastIndexOf("{", indexOfDesiredGame);
        int indexOfClosingBracket = allGameFromSteamJson.indexOf("}", indexOfDesiredGame);

        return allGameFromSteamJson.substring(indexOfStartingBracket, indexOfClosingBracket + 1);

    }

    private String trimGameDetailBody(String gameDetailBody) {

        int indexOfFirstAtribute = gameDetailBody.indexOf("success");

        int indexOfStartingBracket = gameDetailBody.lastIndexOf("{", indexOfFirstAtribute);
        int indexOfClosingBracket = gameDetailBody.lastIndexOf('}');

        String trimmedString = gameDetailBody.substring(indexOfStartingBracket, indexOfClosingBracket);

        return trimmedString;
    }

    private SteamAppDetails getSteamAppDetailsFromGameId(long steamAppId) throws URISyntaxException, IOException, InterruptedException {

        try {

            HttpResponse<String> gameDetails = restApiClient.getDetailsAboutSteamGame(steamAppId);

            String trimmedGameDetails = trimGameDetailBody(gameDetails.body());

            SteamAppDetails steamAppDetails = gson.fromJson(trimmedGameDetails, SteamAppDetails.class);

            return steamAppDetails;

        } catch (RuntimeException e) {
            throw new RuntimeException("deu ruim", e);
        }

    }
}
