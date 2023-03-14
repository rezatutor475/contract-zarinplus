package com.zarinpal.provider.sample;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zarinpal.contract.Contract;
import com.zarinpal.contract.Menu;
import com.zarinpal.contract.conectivity.MethodBuilder;
import com.zarinpal.provider.core.network.methods.Method;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

class ZarinPlusContract implements Contract {

    private final String title;
    private final String description;
    private final HashMap<String, String> body = new HashMap<>();

    //ZarinPlus Contract
    public static final String API_WALLET_LIST = "https://api.zarinplus.com/wallet/list";
    public static final String API_WALLET_PROCESS = "https://api.zarinplus.com/payment/process";


    @NonNull
    public static ZarinPlusContract create(String authorization, String title, String description) {
        return new ZarinPlusContract(authorization, title, description);
    }

    private ZarinPlusContract(String authorization, String title, String description) {
        this.title = title;
        this.description = description;
        this.body.put("zp_access_token", authorization);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public Method getWalletList() {
        return MethodBuilder.perform(API_WALLET_LIST, "POST", null, getJson());
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public Method transfer(int index, String authority) {
        body.put("wallet_id", String.valueOf(index));
        body.put("authority", authority);
        return MethodBuilder.perform(API_WALLET_PROCESS, "POST", null, getJson());
    }

    @Override
    public Menu getMenu() throws URISyntaxException {
        return Menu.create(
                title,
                description,
                new URI("https://play-lh.googleusercontent.com/3Mqa-_SnMmBUYt_sbUezbvVdZHJT-eQl0-2E6yLt1A1vsg40bx7ZEQU5gJ-WruLz3-lp=s96-rw")
        );
    }

    private String getJson() {
        try {
            return new JSONObject(body.toString()).toString();
        } catch (JSONException e) {
            return "{}";
        }
    }
}
