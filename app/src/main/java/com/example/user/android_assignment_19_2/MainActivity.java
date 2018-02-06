package com.example.user.android_assignment_19_2;
//Package objects contain version information about the implementation and specification of a Java package.
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //public is a method and fields can be accessed by the members of any class.
    //class is a collections of objects.
    //created MainActivity and extends with AppCompatActivity which is Parent class.

    ListView listView;
    List<Model> modelList  = new ArrayList<>();
    Model model;
    MyAdapter adapter;
    //Intializing the variables.

    @Override
    //we use override to tells the compiler that the following method overrides a method of its superclass.
    protected void onCreate(Bundle savedInstanceState) {
        //protected can be accessed by within the package and class and subclasses
        //The Void class is an uninstantiable placeholder class to hold a reference to the Class object
        //representing the Java keyword void.
        //The savedInstanceState is a reference to a Bundle object that is passed into the onCreate method of every Android Activity
        // Activities have the ability, under special circumstances, to restore themselves to a previous state using the data stored in this bundle.
        super.onCreate(savedInstanceState);
        //Android class works in same.You are extending the Activity class which have onCreate(Bundle bundle) method in which meaningful code is written
        //So to execute that code in our defined activity. You have to use super.onCreate(bundle)
        setContentView(R.layout.activity_main);
        //R means Resource
        //layout means design
        //main is the xml you have created under res->layout->main.xml
        //Whenever you want to change your current Look of an Activity or when you move from one Activity to another .
        //he other Activity must have a design to show . So we call this method in onCreate and this is the second statement to set
        //the design

        listView=(ListView)findViewById(R.id.listview);
        //giving id as listview
        new MovieTask().execute("http://api.themoviedb.org/3/tv/top_rated?api_key=8496be0b2149805afa458ab8ec27560c");
        //inserting the url of the movie task to execute it
    }
    private class MovieTask extends AsyncTask<String,Void,List<Model>> {
        //creating a private class called movie task which extends asyntask with the params of String void and result of the list<model>.
        //Asyntask has three properties which is first do in background with the string params
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        //we use override to tells the compiler that the following method overrides a method of its superclass.
        protected List<Model> doInBackground(String... strings) {
            //here in this doinbackground method contains the code which needs to be executed in background.
            try {
                URL url = new URL(strings[0]);
                //craeting the object as url.
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                //Obtain a new HttpURLConnection by calling URL.openConnection() and casting the result to HttpURLConnection.
                InputStream stream = conn.getInputStream();
                //created object as stream and whatever the input stream will get it will store in stream.
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                //using BufferedReader to read the API's response into a StringBuilder.
                StringBuffer buffer = new StringBuffer();
                //When we have the complete response, we convert it to a JSONObject object.
                //AsyncTask can be called only once. Executing it again will throw an exception
                //Using BufferReader we read the obj in the inputstream
                String inputstring=null;
                //here it reads the inputstring if it is not equal to zero it append the inputstring
                while ((inputstring =reader.readLine()) != null) {
                    buffer.append(inputstring);
                    //Appends the string representation of the inputstring argument to this sequence.
                }
                JSONObject jsonObject =new JSONObject(buffer.toString());
                //creating  json object  to get the strings
                JSONArray jsonArray =jsonObject.getJSONArray("results");
                //inside the json object we have square brackets which is known as jsonarray and  the string values of getjsonarray
                modelList=new ArrayList<Model>();
                //setting an array list to model list

                for (int i =0;i<jsonArray.length();i++){
                    //giving for loop to continue the process
                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                    //created an new object and storing the value of i in it
                    String name =jsonObject1.getString("original_name");
                    //created an object as name and getting the name from the string of the jsonobject.
                    String votes =jsonObject1.getString("vote_count");
                    //created an object as votes and getting the count from the string of the jsonobject.
                    String id =jsonObject1.getString("id");
                    //created an object as id and getting the id from the string of the jsonobject.
                    model=new Model();
                    //giving model as new model
                    model.setName(name);
                    //setting name to the model
                    model.setVotes("Vote: "+votes);
                    //setting votes to the model
                    model.setId("Id: "+id);
                    //setting id to the model
                    modelList.add(model);
                    //adding these to the model list

                }
                return modelList;
                //while it will recall one id and returns to the 2nd like wise it continues untill the size of the list

            } catch (Exception e) {
            }
            return null;

        }
        @Override
        //we use override to tells the compiler that the following method overrides a method of its superclass.
        protected void onPostExecute(List<Model> models) {
            //in this onPostExecute method  call the  listmodel from the myadapter and view the list
            adapter= new MyAdapter(MainActivity.this,R.layout.list_model,models);
            //creating the adapter from main activity and getting the layout of the models.
            listView.setAdapter(adapter);
            //setting the list to the adapter.
        }
    }
}
