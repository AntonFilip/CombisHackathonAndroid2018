package com.immutables.trycodecatch.trycodecatchtest.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.immutables.trycodecatch.trycodecatchtest.Answer;
import com.immutables.trycodecatch.trycodecatchtest.ApplicationContext;
import com.immutables.trycodecatch.trycodecatchtest.Models.BackendModels.AnswerResponse;
import com.immutables.trycodecatch.trycodecatchtest.R;

import java.io.IOException;

import retrofit2.Response;

public class QuestionActivity extends AppCompatActivity
{

    TextView questionTV;
    FloatingActionButton fab;
    EditText ed;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        questionTV = (TextView) findViewById(R.id.question_full_tv);
        final Intent intent = getIntent();
        questionTV.setText(intent.getStringExtra("Question"));
        fab = (FloatingActionButton) findViewById(R.id.fab);
        ed = (EditText) findViewById(R.id.entered_answer);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new AsyncTask<Void, Void, Void>()
                {
                    @Override
                    protected Void doInBackground(Void... voids)
                    {
                        retrofit2.Call<AnswerResponse> call = ApplicationContext.backendService.postAnswer(ApplicationContext.token.accessToken, new Answer(ed.getText().toString(), intent.getStringExtra("QuestionID"), ApplicationContext.loggedInUser.id));
                        try
                        {
                            Response response = call.execute();
                            if (response.isSuccessful())
                            {
                                response.body();
                            }
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                        return null;
                    }
                }.execute();
                finish();
            }
        });
    }
}
