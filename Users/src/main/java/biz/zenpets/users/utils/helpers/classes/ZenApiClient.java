package biz.zenpets.users.utils.helpers.classes;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ZenApiClient {

    /** THE BASE URL **/
    private static final String BASE_URL = "http://192.168.11.2/zenpets/public/";
//    private static final String BASE_URL = "https://zenpetsapi.appspot.com/";

    /** THE RETROFIT INSTANCE **/
    private static Retrofit retrofit = null;

    /** INSTANTIATE THE RETROFIT INSTANCE **/
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}