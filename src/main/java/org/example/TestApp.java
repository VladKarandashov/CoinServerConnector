package org.example;

import org.apache.commons.lang3.RandomStringUtils;
import org.example.client.CoinServerClient;
import org.example.common.request.CreateBuyOrderRequest;
import org.example.common.request.CreateSellAllOrderRequest;
import org.example.common.request.CreateSellOrderRequest;
import org.example.common.request.LoginRequest;

import java.math.BigDecimal;

public class TestApp {
    public static void main( String[] args )
    {
        CoinServerClient coinServerClient = new CoinServerClient("localhost", "8080");

        var allPrices = coinServerClient.prices.getAllPrices();
        assert allPrices.isPresent() && allPrices.get().size() > 0;
        var coin = allPrices.get().get(0);

        var pricesByCoin = coinServerClient.prices.getCandlestickBarsBySymbol(coin.getSymbol());
        assert pricesByCoin.isPresent() && pricesByCoin.get().size() > 0;

        var pricesByCoinAndInterval = coinServerClient.prices.getCandlestickBarsBySymbol(coin.getSymbol(), "1m");
        assert pricesByCoinAndInterval.isPresent() && pricesByCoinAndInterval.get().size() > 0;

        var statisticsBySymbol = coinServerClient.prices.getStatisticsBySymbol(coin.getSymbol());
        assert statisticsBySymbol.isPresent();

        var leaderboard = coinServerClient.statistics.getLeaderboard();
        assert leaderboard.isPresent();
        assert leaderboard.get().getUserScoreList().size() > 0;

        var login = RandomStringUtils.randomAlphabetic(32);
        coinServerClient.registration(new LoginRequest(login, "verystrongpassword"));

        var usdBalance = coinServerClient.balance.getUsdBalance();
        assert usdBalance.isPresent();
        assert usdBalance.get().compareTo(BigDecimal.ZERO) != 0;

        var status = coinServerClient.orders.createBuyOrder(new CreateBuyOrderRequest(coin.getSymbol(), usdBalance.get()));
        assert status;

        var assetsBalance = coinServerClient.balance.getAssetsBalance();
        assert assetsBalance.isPresent();
        var assetBalance = coinServerClient.balance.getAssetsBalance(coin.getSymbol());
        assert assetBalance.isPresent();
        var balance = coinServerClient.balance.getBalance();
        assert balance.isPresent();

        coinServerClient.login(new LoginRequest(login, "verystrongpassword"));

        status = coinServerClient.orders.createSellOrder(new CreateSellOrderRequest(coin.getSymbol(), assetBalance.get().getAssetsCount().multiply(BigDecimal.valueOf(0.8))));
        assert status;
        status = coinServerClient.orders.createSellAllOrder(new CreateSellAllOrderRequest(coin.getSymbol()));
        assert status;

        var orders = coinServerClient.orders.getOrdersBySymbol(coin.getSymbol()).orElseThrow();
        assert orders.size() == 3;

        orders = coinServerClient.orders.getAllOrders().orElseThrow();
        assert orders.size() == 3;

    }
}
