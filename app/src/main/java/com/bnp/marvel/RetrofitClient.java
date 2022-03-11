package com.bnp.marvel;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;
    private static final String BASE_URL = "http://gateway.marvel.com/v1/public/characters/";
    private static final String public_key = "ee1534f792382107c97327fcf2525679";
    private static final String private_key = "5e09bef6839eb5961c15459c58ab8643854986a9";


    public static Retrofit getRetrofitInstance() {

        long timestamp = new Date().getTime();
        String md5 = timestamp+private_key+public_key;
        String url = BASE_URL+"?ts="+timestamp+"&apikey="+public_key+"&hash="+getMd5(md5);

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static String getMd5(String input)
    {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
