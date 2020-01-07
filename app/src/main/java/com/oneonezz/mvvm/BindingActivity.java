package com.oneonezz.mvvm;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.oneonezz.utils.ToastUtil;

public class BindingActivity extends AppCompatActivity {

//    private DataBinding binding;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding = DataBindingUtil.setContentView(this, R.layout.data_binding);
        user = new User("first","last");
//        binding.setUser(user);
    }

//    public void doClick(View view) {
//        binding.tvBindingTest.setText("点击了");  //单向绑定
//    }

    public class Presenter {
        public void onClickExample(View view) {
            ToastUtil.show("点击了");
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            user.setLastName(s.toString());
        }

        public void onClickListenerBinding(User student) {
            Toast.makeText(BindingActivity.this, student.getFirstName(),Toast.LENGTH_SHORT).show();
        }
    }

}
