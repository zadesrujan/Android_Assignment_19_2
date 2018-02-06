package com.example.user.android_assignment_19_2;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 02-02-2018.
 */

public class MyAdapter extends ArrayAdapter<Model> {
    //public is a method and fields can be accessed by the members of any class.
    //class is a collections of objects.
    //created MyAdapter and extends with ArrayAdapter which is Model class.

    Context context ;
    int resource;
    Model model;
    List<Model>modelList = new ArrayList<>();
    LayoutInflater inflater;
    //Intializing the varaiables.

    public MyAdapter(@NonNull Context context, int resource, @NonNull List<Model> objects) {
        //craeted Myadapter as public and it will get the context resource and list of the objects which are present in model.
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.modelList = objects;
        //giving as context and resources and model list as object to access.
    inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Instantiates a layout XML file into its corresponding View objects. It is never used directly.
    }

    @NonNull
    @Override
    //we use override to tells the compiler that the following method overrides a method of its superclass.
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //in Myadapter we have override the method called getview ,getcount and getItemid
        View  view = LayoutInflater.from(context).inflate(R.layout.list_model, null,false);
        //View view = convertView;
        //in this view layoutinflator we inflate listmodel
        TextView textView = view.findViewById(R.id.textView);
        TextView textView2 = view.findViewById(R.id.textView2);
        TextView textView3 = view.findViewById(R.id.textView3);
        //initialising the textviews from the .xml file
        model = new Model();
        //creating object where we get the position the model obj
        model =  modelList.get(position);
       //here we will get the position of the model list.
        textView.setText(model.getName());
        textView2.setText(model.getVotes());
        textView3.setText(model.getId());
        //here it sets the values of the model obj

        return view;
    }
    @Override
    //we use override to tells the compiler that the following method overrides a method of its superclass.
    public int getCount() {
        return modelList.size();
    }
    //this method will count the size of the model list.

    @Override
    //we use override to tells the compiler that the following method overrides a method of its superclass.
    public long getItemId(int position) {
        return position;
    }
    //it will get the id and it will set the position of the id.
}