package edu.itstep.a05retrofit2.activity;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import edu.itstep.a05retrofit2.R;
import edu.itstep.a05retrofit2.api.PostApi;
import edu.itstep.a05retrofit2.api.UserApi;
import edu.itstep.a05retrofit2.model.Post;
import edu.itstep.a05retrofit2.model.User;
import edu.itstep.a05retrofit2.service.NetworkService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PostActivity extends AppCompatActivity {
    private PostApi postApi;
    private TextView tvBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        tvBody = findViewById(R.id.tvBody);

        postApi = NetworkService.getInstance().getPostApi();

        int postId = getIntent().getIntExtra(ListPostsActivity.POST_ID, 0);

        if (postId != 0) {
            postApi.getPostById(postId)
                    .enqueue(new Callback<Post>() {
                        @Override
                        public void onResponse(Call<Post> call, Response<Post> response) {
                            Post post = response.body();
                            tvBody.setText(post.toString());
                        }

                        @Override
                        public void onFailure(Call<Post> call, Throwable t) {
                            tvBody.setText(t.getMessage());
                        }
                    });
        }
    }


}