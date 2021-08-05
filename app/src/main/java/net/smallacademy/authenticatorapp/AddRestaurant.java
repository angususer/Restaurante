package net.smallacademy.authenticatorapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.FirebaseDatabase;

public class AddRestaurant extends AppCompatActivity {
    private EditText et_name,et_address,et_food,et_time,time_hour,image_url;
    private String restId;
    Button btn_add;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);
        restId= FirebaseDatabase.getInstance().getReference().child("restaurants").push().getKey();
        bindView();
        attachListeners();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void attachListeners() {
        btn_add.setOnClickListener(v-> {
            FirebaseDatabase.getInstance().getReference("restaurants").child(restId).setValue(
              new Restaurant(restId,et_name.getText().toString(),image_url.getText().toString(),
                            et_address.getText().toString(),et_food.getText().toString(),
                            et_time.getText().toString(),time_hour.getText().toString())
                );
            clearForm();
        });
    }

    private void clearForm() {
        image_url.setText("");
        et_name.setText("");
        et_address.setText("");
        et_food.setText("");
        et_time.setText("");
        time_hour.setText("");
        btn_add.setText("");
        Toast.makeText(this,"Restaurante adicionado com sucesso",Toast.LENGTH_LONG).show();
    }

    private void bindView() {
        image_url=findViewById(R.id.image_url);
        et_name=findViewById(R.id.et_name);
        et_address=findViewById(R.id.et_address);
        et_food=findViewById(R.id.et_food);
        et_time=findViewById(R.id.et_time);
        time_hour=findViewById(R.id.time_hour);
        btn_add=findViewById(R.id.btn_add);
    }


}