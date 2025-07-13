package com.kasihinapp.utils;

public class ApiConfig {
    public static final String BASE_URL = "http://192.168.0.115/pmobile/";

    // Endpoint pakai gateway `index.php?endpoint=...`
    public static final String LOGIN = BASE_URL + "?endpoint=login";
    public static final String REGISTER = BASE_URL + "?endpoint=register";

    public static final String GET_ALL_CAMPAIGNS = BASE_URL + "?endpoint=get_all_campaigns";
    public static final String CREATE_CAMPAIGN = BASE_URL + "?endpoint=create_campaign";

    public static final String DONATE = BASE_URL + "?endpoint=donate";
    public static final String GET_DONATIONS = BASE_URL + "?endpoint=donation_history";

    public static final String GET_ALL_QUESTS = BASE_URL + "?endpoint=quest_list";
    public static final String SUBMIT_QUEST = BASE_URL + "?endpoint=quest_submit";

    public static final String GET_USER_PROFILE = BASE_URL + "?endpoint=user_profile";
    public static final String UPDATE_USER_PROFILE = BASE_URL + "?endpoint=user_update";

    public static final String GET_POIN_HISTORY = BASE_URL + "?endpoint=poin_history";
}


