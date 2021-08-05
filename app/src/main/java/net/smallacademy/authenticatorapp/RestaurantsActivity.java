package net.smallacademy.authenticatorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RestaurantsActivity extends AppCompatActivity {

    RecyclerView rv_rests;
    FloatingActionButton fab_add;
    RestsAdapter adapter;
    DatabaseReference db;
    ArrayList<Restaurant> restaurants;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        db= FirebaseDatabase.getInstance().getReference("restaurants");
        db.addValueEventListener(valueEventListener);
        restaurants=new ArrayList<>();
        bindView();
        rv_rests.setLayoutManager(new LinearLayoutManager(this));
        adapter=new RestsAdapter();
        rv_rests.setAdapter(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(rv_rests);
    }

    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            Toast.makeText(RestaurantsActivity.this, "on Move", Toast.LENGTH_SHORT).show();
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

//            if(swipeDir==ItemTouchHelper.LEFT){
                Toast.makeText(RestaurantsActivity.this, "item removido"+swipeDir, Toast.LENGTH_SHORT).show();
                int position = viewHolder.getAdapterPosition();
                db.child(restaurants.get(position).getId()).removeValue();
//            }


        }
    };

    ValueEventListener valueEventListener =new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            restaurants=new ArrayList<>();
            Log.e("Total Restaurants",snapshot.getChildrenCount()+"");
            for (DataSnapshot dttSnapshot2 : snapshot.getChildren()) {
                Restaurant item = (Restaurant) JsonHelpers.convertToModelClass(dttSnapshot2.getValue(), Restaurant.class);
                restaurants.add(item);
                adapter.setList(restaurants);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    private void bindView() {
        fab_add=findViewById(R.id.fab_add);
        rv_rests=findViewById(R.id.rv_rests);
        fab_add.setOnClickListener(v->startActivity(new Intent(this,AddRestaurant.class)));
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(valueEventListener!=null&&db!=null)
            db.removeEventListener(valueEventListener);
    }
}