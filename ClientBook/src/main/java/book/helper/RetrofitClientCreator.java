package book.helper;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitClientCreator {
	
	public static final String BASE_URL = "http://localhost:9999";
	
	public static Retrofit getClient() {
		return new Retrofit.Builder().baseUrl(BASE_URL)
				.addConverterFactory(JacksonConverterFactory.create())
				.build();
	}
}
