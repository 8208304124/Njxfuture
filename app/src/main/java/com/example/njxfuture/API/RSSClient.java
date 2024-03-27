package com.example.njxfuture.API;

import com.example.njxfuture.API.DataModels.MoneyControlDataModels.RSSFeed;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.GET;

public class RSSClient {
    private static final String BASE_URL = "https://www.moneycontrol.com/rss/";
    public interface RSSService {
        @GET("MCtopnews.xml")
        Call<RSSFeed> getTopNews();
    }
    public interface RSSService2 {
        @GET("mostpopular.xml")
        Call<RSSFeed> getTopNews();
    }
    public interface RSSService3 {
        @GET("economy.xml")
        Call<RSSFeed> getTopNews();
    }
    private final RSSService rssService;
    private final RSSService2 rssService2;
    private final RSSService3 rssService3;
    public RSSClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        rssService = retrofit.create(RSSService.class);
        rssService2 = retrofit.create(RSSService2.class);
        rssService3 = retrofit.create(RSSService3.class);
    }

    public Call<RSSFeed> getTopNews() {
        return rssService.getTopNews();
    }
    public Call<RSSFeed> getTopNews1() {
        return rssService2.getTopNews();
    }
    public Call<RSSFeed> getTopNews2() {
        return rssService3.getTopNews();
    }
}
