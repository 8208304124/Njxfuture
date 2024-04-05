package com.example.njxfuture.API;

import com.example.njxfuture.API.DataModels.Account;
import com.example.njxfuture.API.DataModels.ArticleData.ArticleDataModel;
import com.example.njxfuture.API.DataModels.NotificationDataModel;
import com.example.njxfuture.API.DataModels.OtpGenerateDataModel;
import com.example.njxfuture.API.DataModels.PackageDataModel.PackageDetailsDataModel;
import com.example.njxfuture.API.DataModels.PushNotificationDataModel;
import com.example.njxfuture.API.DataModels.UpdateUserDataModel;
import com.example.njxfuture.API.DataModels.User;
import com.example.njxfuture.API.DataModels.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class APIRequests {
    static String BASE_URL = "http://njx.revacg.in/";

    interface MyApiService {
        @GET("/checkimei.php")
        Call<UserModel> getData(@Query("imeival") String param1);
    }
    interface ReadNotify {
        @GET("/pushred.php")
        Call<List<String>> getData(@Query("imeival") String param1,@Query("nid") String nid);
    }
    interface PushNotification {
        @GET("/pushnot.php")
        Call<List<PushNotificationDataModel>> getData(@Query("imeival") String param1);
    }
    interface GenerateOtp {
        @GET("/genotp.php")
        Call<OtpGenerateDataModel> getData(@Query("imeival") String param1, @Query("mno") String param2);
    }

    interface Login {
        @GET("/logusr.php")
        Call<UpdateUserDataModel> getData(@Query("imeival") String iemi,
                                          @Query("phno") String mno,
                                          @Query("pwd") String pwd);
    }

    interface OtpVerify {
        @GET("/otpveri.php")
        Call<UpdateUserDataModel> getData(@Query("imeival") String iemi,
                                          @Query("otp") String otp, @Query("mno") String mno);
    }

    interface CreateAccount {
        @GET("/regusr.php")
        Call<Account> getData(@Query("imeival") String iemi,
                              @Query("ufnm") String name,
                              @Query("phno") String mno,
                              @Query("gstin") String gst,
                              @Query("pwd") String pwd,
                              @Query("emlid") String emlid);
    }

    interface Articles {
        @GET("/artlst.php")
        Call<List<ArticleDataModel>> getData(@Query("imeival") String iemi, @Query("lid") int lid);
    }

    interface Notifications {
        @GET("/notlst.php")
        Call<List<NotificationDataModel>> getData(@Query("imeival") String iemi);
    }

    interface PackageDetails {
        @GET("/pckdet.php")
        Call<PackageDetailsDataModel> getData(@Query("imeival") String iemi, @Query("pckid") String pckid, @Query("dt") String dt);
    }

    interface GetUser {
        @GET("/usrdet.php")
        Call<User> getData(@Query("imeival") String iemi);
    }

    interface UpdateUser {
        @GET("/updusr.php")
        Call<UpdateUserDataModel> getData(@Query("imeival") String iemi,
                                          @Query("ufnm") String ufnm,
                                          @Query("phno") String phno,
                                          @Query("gstin") String gstin,
                                          @Query("pwd") String pwd,
                                          @Query("emlid") String emlid
        );
    }

    // Create a Retrofit instance
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    // Create an instance of the API interface
    private static final MyApiService apiService = retrofit.create(MyApiService.class);

    private static final ReadNotify getRead = retrofit.create(ReadNotify.class);
    private static final CreateAccount createAccount = retrofit.create(CreateAccount.class);
    private static final Articles articlesFetch = retrofit.create(Articles.class);
    private static final Notifications notificationFetch = retrofit.create(Notifications.class);
    private static final PackageDetails packageFetch = retrofit.create(PackageDetails.class);
    private static final GetUser fetchUser = retrofit.create(GetUser.class);
    private static final UpdateUser updateUser = retrofit.create(UpdateUser.class);
    private static final Login getLogin = retrofit.create(Login.class);
    private static final OtpVerify otpVerify = retrofit.create(OtpVerify.class);
    private static final GenerateOtp getOtpVerify = retrofit.create(GenerateOtp.class);
    private static final PushNotification getPushNotification = retrofit.create(PushNotification.class);

    // Define a method to make API requests

    public static Call<List<String>> readNotify(String param1, String param2) {
        return getRead.getData(param1,param2);
    }
    public static Call<UserModel> fetchData(String param1) {
        return apiService.getData(param1);
    }
    public static Call<List<PushNotificationDataModel>> getPushNotify(String param1) {
        return getPushNotification.getData(param1);
    }
    public static Call<UpdateUserDataModel> getOtpVerify(String param1, String otp, String mno) {
        return otpVerify.getData(param1, otp, mno);
    }

    public static Call<UpdateUserDataModel> getUserLogin(String param1, String mno, String pwd) {
        return getLogin.getData(param1, mno, pwd);
    }

    public static Call<Account> creatAcc(String param1, String param2, String param3, String param4, String param5, String param6) {
        return createAccount.getData(param1, param2, param3, param4, param5, param6);
    }

    public static Call<List<ArticleDataModel>> fetchArticles(String param1, int param2) {
        return articlesFetch.getData(param1, param2);
    }

    public static Call<List<NotificationDataModel>> fetchNotifications(String param1) {
        return notificationFetch.getData(param1);
    }

    public static Call<PackageDetailsDataModel> fetchPackages(String param1, String param2, String param3) {
        return packageFetch.getData(param1, param2, param3);
    }

    public static Call<User> getUser(String param1) {
        return fetchUser.getData(param1);
    }

    public static Call<UpdateUserDataModel> updateUserDetails(String param1, String param2, String param3, String param4, String param5, String param6) {
        return updateUser.getData(param1, param2, param3, param4, param5, param6);
    }

    public static Call<OtpGenerateDataModel> generateOtp(String param1, String param2) {
        return getOtpVerify.getData(param1, param2);
    }
}
