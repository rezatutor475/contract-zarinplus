/**
 * Created by ImanX. https://github.com/ImanX And Enhanced by Reza Torabi https://github.com/rezatutor475
 */
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

    // ZarinPlus API Endpoints
    public static final String API_WALLET_LIST = "https://api.zarinplus.com/wallet/list";
    public static final String API_WALLET_PROCESS = "https://api.zarinplus.com/payment/process";
    public static final String API_WALLET_BALANCE = "https://api.zarinplus.com/wallet/balance";
    public static final String API_TRANSACTION_HISTORY = "https://api.zarinplus.com/transaction/history";
    public static final String API_CREATE_WALLET = "https://api.zarinplus.com/wallet/create";
    public static final String API_DELETE_WALLET = "https://api.zarinplus.com/wallet/delete";
    public static final String API_UPDATE_WALLET = "https://api.zarinplus.com/wallet/update";
    public static final String API_VERIFY_PAYMENT = "https://api.zarinplus.com/payment/verify";
    public static final String API_WALLET_DETAILS = "https://api.zarinplus.com/wallet/details";

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
    @Override
    public Method getWalletList() {
        return MethodBuilder.perform(API_WALLET_LIST, "POST", null, getJson());
    }

    @Nullable
    @Override
    public Method transfer(int index, String authority) {
        body.put("wallet_id", String.valueOf(index));
        body.put("authority", authority);
        return MethodBuilder.perform(API_WALLET_PROCESS, "POST", null, getJson());
    }

    @Nullable
    public Method getWalletBalance(int walletId) {
        body.put("wallet_id", String.valueOf(walletId));
        return MethodBuilder.perform(API_WALLET_BALANCE, "POST", null, getJson());
    }

    @Nullable
    public Method getTransactionHistory(int walletId, int limit, int offset) {
        body.put("wallet_id", String.valueOf(walletId));
        body.put("limit", String.valueOf(limit));
        body.put("offset", String.valueOf(offset));
        return MethodBuilder.perform(API_TRANSACTION_HISTORY, "POST", null, getJson());
    }

    @Nullable
    public Method createWallet(String name, String currency) {
        body.put("name", name);
        body.put("currency", currency);
        return MethodBuilder.perform(API_CREATE_WALLET, "POST", null, getJson());
    }

    @Nullable
    public Method deleteWallet(int walletId) {
        body.put("wallet_id", String.valueOf(walletId));
        return MethodBuilder.perform(API_DELETE_WALLET, "POST", null, getJson());
    }

    @Nullable
    public Method updateWallet(int walletId, String newName) {
        body.put("wallet_id", String.valueOf(walletId));
        body.put("new_name", newName);
        return MethodBuilder.perform(API_UPDATE_WALLET, "POST", null, getJson());
    }

    @Nullable
    public Method verifyPayment(String authority) {
        body.put("authority", authority);
        return MethodBuilder.perform(API_VERIFY_PAYMENT, "POST", null, getJson());
    }

    @Nullable
    public Method getWalletDetails(int walletId) {
        body.put("wallet_id", String.valueOf(walletId));
        return MethodBuilder.perform(API_WALLET_DETAILS, "POST", null, getJson());
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
        JSONObject json = new JSONObject();
        for (String key : body.keySet()) {
            try {
                json.put(key, body.get(key));
            } catch (JSONException ignored) {}
        }
        return json.toString();
    }
}
