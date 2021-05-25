package book.test;

import java.io.IOException;
import java.util.List;

import com.book.model.Book;

import book.BookService;
import book.helper.RetrofitClientCreator;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Test {

	public static void main(String[] args) throws IOException {
		Retrofit retrofit = RetrofitClientCreator.getClient();
		BookService authService = retrofit.create(BookService.class);
		Call<List<Book>> call = authService.getBook();
		Response<List<Book>> response = call.execute();
		response.body().forEach(x -> {
			System.out.println(x);
		});
	}

}
