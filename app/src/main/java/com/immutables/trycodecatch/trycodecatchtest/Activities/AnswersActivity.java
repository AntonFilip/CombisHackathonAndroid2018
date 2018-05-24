package com.immutables.trycodecatch.trycodecatchtest.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.immutables.trycodecatch.trycodecatchtest.ApplicationContext;
import com.immutables.trycodecatch.trycodecatchtest.Models.BackendModels.AnswerResponse;
import com.immutables.trycodecatch.trycodecatchtest.Models.BackendModels.AnswerResponseData;
import com.immutables.trycodecatch.trycodecatchtest.Models.BackendModels.DonationResponse;
import com.immutables.trycodecatch.trycodecatchtest.Models.BackendModels.DonationResponseData;
import com.immutables.trycodecatch.trycodecatchtest.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Response;

public class AnswersActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers);
        final ListView lv = (ListView) findViewById(R.id.questions_lv);
        final Call<AnswerResponse> answerResponseCall = ApplicationContext.backendService.getUserAnswers(ApplicationContext.token.accessToken);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.listview_textview, R.id.question_textview);
        final ArrayList<AnswerResponseData> answerResponseDataArray = new ArrayList<>();
        new AsyncTask<Void, Void, ArrayList<String>>(){
            @Override
            protected ArrayList<String> doInBackground(Void... voids)
            {
                try
                {
                    Response response = answerResponseCall.execute();
                    if(response.isSuccessful()){
                        AnswerResponse answerResponse = (AnswerResponse) response.body();
                        if(answerResponse.success)
                        {
                            ArrayList<String> lista = new ArrayList<String>();

                            for (AnswerResponseData data : answerResponse.data)
                            {
                                answerResponseDataArray.add(data);
                                lista.add(data.question.description);
                            }
                            return lista;
                        }
                    }
                    else
                    {
                        Snackbar.make(lv,"Unsuccessful response from server.", Snackbar.LENGTH_LONG).show();

                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                    Snackbar.make(lv,"Error while loading data from server.", Snackbar.LENGTH_LONG).show();
                }
                return null;
            }

            @Override
            protected void onPostExecute(ArrayList<String> list)
            {
                arrayAdapter.addAll(list);
            }
        }.execute();
        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                AnswerResponseData data = answerResponseDataArray.get(position);
                Intent answersActivityIntent = new Intent(AnswersActivity.this, QuestionActivity.class);

                startActivity(answersActivityIntent);
            }
        });

    }
}
