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
import java.util.ArrayList;
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
    public List<SteamAppDetails> searchGameInSteamLibrary(String gameName) throws URISyntaxException, IOException, InterruptedException {

        List<Integer> listOfAllGameOcurrences;
        List<String> listJsonOfDesiredGame = new ArrayList<>();
        List<SteamAppDetails> listSteamAppDetails = new ArrayList<>();

        HttpResponse<String> allGames = restApiClient.getAllSteamGames();

        listOfAllGameOcurrences = findAllGameNameOcurrences(allGames.body(), gameName);

        for(int index : listOfAllGameOcurrences) {
            listJsonOfDesiredGame.add(trimDesiredGameJson(allGames.body(), index));
        }

        for(String stringJsonOfDesiredGame : listJsonOfDesiredGame){

            SteamAppDetails steamAppDetails = getSteamAppDetailsFromGameId(
                    gson.fromJson(stringJsonOfDesiredGame, SteamApp.class).getAppid());

            if(Objects.nonNull(steamAppDetails)) {
                listSteamAppDetails.add(steamAppDetails);
            }
        }

        return listSteamAppDetails;
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

        return gameDetailBody.substring(indexOfStartingBracket, indexOfClosingBracket);
    }

    private SteamAppDetails getSteamAppDetailsFromGameId(long steamAppId) throws URISyntaxException, IOException, InterruptedException {

        try {

            HttpResponse<String> gameDetails = restApiClient.getDetailsAboutSteamGame(steamAppId);

            String trimmedGameDetails = trimGameDetailBody(gameDetails.body());

            SteamAppDetails steamAppDetails = gson.fromJson(trimmedGameDetails, SteamAppDetails.class);

            if(validateIfSteamAppIsGameOrDlc(steamAppDetails)) {
                return steamAppDetails;
            }

            return null;

        } catch (RuntimeException e) {
            throw new RuntimeException("Error trying to extract game details: ", e);
        }

    }

    private boolean validateIfSteamAppIsGameOrDlc(SteamAppDetails steamAppDetails) {

        boolean isSuccess = steamAppDetails.isSuccess();
        boolean isGame = steamAppDetails.getData().getType().equals("game");
        boolean isDlc = steamAppDetails.getData().getType().equals("dlc");

        return isSuccess && (isGame || isDlc);
    }

    private List<Integer> findAllGameNameOcurrences(String allGamesFromSteam, String gameName) {

        List<Integer> listOfGamesIndexes = new ArrayList<>();
        int index = allGamesFromSteam.indexOf(gameName);

        while(index >= 0) {
            listOfGamesIndexes.add(index);
            index = allGamesFromSteam.indexOf(gameName, index + 1);
        }

        return listOfGamesIndexes;
    }
}
