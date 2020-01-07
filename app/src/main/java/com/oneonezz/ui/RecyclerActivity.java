package com.oneonezz.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.oneonezz.R;
import com.oneonezz.utils.L;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mRecyclerView.setAdapter(new MyAdapter());
        GallerySnapHelper snapHelper = new GallerySnapHelper();
        snapHelper.attachToRecyclerView(mRecyclerView);
    }


    class MyAdapter extends RecyclerView.Adapter {

        private List<String> dataList;

        public MyAdapter() {
            dataList = new ArrayList<>();
            for (int i = 0; i < 15; i++) {
                dataList.add("TT_" + i);
            }
        }


        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_string, parent, false);
            // 实例化viewholder
            MyViewHolder viewHolder = new MyViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            if (position % 5 == 0) {
                ((MyViewHolder) holder).setVisibility(false);
            } else {
                ((MyViewHolder) holder).setVisibility(true);
            }

            ((MyViewHolder) holder).setData(position, dataList.get(position));
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            private final TextView textView;

            public void setVisibility(boolean isVisible) {
                RecyclerView.LayoutParams param = (RecyclerView.LayoutParams) itemView.getLayoutParams();
                if (isVisible) {
                    param.height = LinearLayout.LayoutParams.MATCH_PARENT;
                    param.width = LinearLayout.LayoutParams.MATCH_PARENT;
                } else {
                    param.height = 0;
                    param.width = 0;
                }
                itemView.setLayoutParams(param);
            }

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.textView2);
            }

            public void setData(int position, String data) {
                textView.setText(data);
                if (position % 2 == 0) {
                    itemView.setBackgroundResource(R.drawable.ic_launcher_background);
                } else {
                    itemView.setBackgroundResource(R.drawable.ic_album);
                }
            }
        }
    }

    class GallerySnapHelper extends SnapHelper {

        /**
         * 这个用来计算滑动到最终位置还需要滑动的距离，在一开始attachToRecyclerView或者targetView layout的时候会调用
         */
        @Nullable
        @Override
        public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View targetView) {
            L.i("oo- calculateDistanceToFinalSnap -> : ");


            int[] out = new int[2];
            out[1] = 1;
            return out;
        }

        /**
         * 用来找到上面的targetView，就是需要对其的view，在calculateDistanceToFinalSnap 调用之前会调用该方法。
         */
        @Nullable
        @Override
        public View findSnapView(RecyclerView.LayoutManager layoutManager) {
            L.i("oo- findSnapView -> : ");
            if (layoutManager instanceof LinearLayoutManager) {
                View view = layoutManager.findViewByPosition(2);
                return view;
            }
            return null;
        }

        /**
         * 用来找到最终的目标位置，在fling操作刚触发的时候会根据速度计算一个最终目标位置，然后开始fling操作
         */
        @Override
        public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
            L.i("oo- findTargetSnapPosition -> : velocityX = " + velocityX + "  velocityY:  " + velocityY);
            return 5;
        }
    }

}
