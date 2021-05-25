package book;

import java.util.List;

import com.book.model.Book;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BookService {	
	@Headers("Accept: application/json ; charset=utf-8")
	
	@POST("/book")
	Call<Void> addBook(@Body Book book);
	
	@PUT("/book")
	Call<Void> editBook(@Body Book book);
	
	@DELETE("/book/{id}")
	Call<Void> removeBook(@Path("id") int id);
	
	@GET("/book")
	Call<List<Book>> getBook();
	
	@GET("/book/{id}")
	Call<Book> getBookById(@Path("id") int id);
	
	
}
