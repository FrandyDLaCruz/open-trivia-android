package com.course.frandydlacruz.opentrivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.course.frandydlacruz.opentrivia.entities.Category;
import com.course.frandydlacruz.opentrivia.entities.Trivia;
import com.course.frandydlacruz.opentrivia.interfaces.ListItemClickListener;
import com.course.frandydlacruz.opentrivia.interfaces.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://opentdb.com/";
    private RecyclerView rvTriviaCategory;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvTriviaCategory = findViewById(R.id.rv_trivia_category);
        rvTriviaCategory.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        rvTriviaCategory.setLayoutManager(layoutManager);

        showCategories();
    }

    private void showCategories(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestClient service = retrofit.create(RestClient.class);

        service.getCategories().enqueue(new Callback<Category>() {

            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                switch (response.code()) {
                    case 200:
                        adapter = new MyAdapter(response.body(), new ListItemClickListener() {
                            @Override
                            public void onListItemClick(int clickedItemIndex) {
                                showQuestion(clickedItemIndex);
                            }
                        });
                        rvTriviaCategory.setAdapter(adapter);
                        break;

                    default:
                        Toast.makeText(MainActivity.this, "There was a problem with the response", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Bad request", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showQuestion(int category) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestClient service = retrofit.create(RestClient.class);

        service.getTrivia(1, "boolean", category, "url3986").enqueue(new Callback<Trivia>() {

            @Override
            public void onResponse(Call<Trivia> call, Response<Trivia> response) {
                switch (response.code()) {
                    case 200:
                        if (response.body().getResponseCode() == 0) {
                            Intent intent = new Intent(MainActivity.this, Question.class);
                            intent.putExtra("RESULT", response.body().getResults().get(0));
                            startActivity(intent);
                        }
                        break;

                    default:
                        Toast.makeText(MainActivity.this, "There was a problem with the response", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Trivia> call, Throwable t) {

            }
        });
    }
}
