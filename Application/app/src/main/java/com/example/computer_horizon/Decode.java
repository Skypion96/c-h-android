package com.example.computer_horizon;

import android.util.Base64;
import android.util.Log;

import com.example.computer_horizon.models.DecodedToken;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

public class Decode {

    private static String[] split;

    public static String decoded(String JWTEncoded) throws Exception {
        try {
            split = JWTEncoded.split("\\.");
            Log.d("JWT_DECODED", "Header: " + getJson(split[0]));
            Log.d("JWT_DECODED", "Body: " + getJson(split[1]));
            Log.d("JWT_DECODED", "Signature: " + getJson(split[2]));
        } catch (UnsupportedEncodingException e) {
            //Error
        }
        return getJson(split[1]);
    }

    private static String getJson(String strEncoded) throws UnsupportedEncodingException {
        byte[] decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE);
        return new String(decodedBytes, "UTF-8");
    }

    public static String getMail(String token) throws Exception {
        String token_decoded = decoded(token);

        Gson gson = new Gson();
        DecodedToken name = gson.fromJson(token_decoded, DecodedToken.class);

        return name.getMail();
    }
}
