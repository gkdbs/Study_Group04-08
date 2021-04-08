package com.example.study_group;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class art extends AppCompatActivity {

    ArrayList<Item> items= new ArrayList<>();
    RecyclerView recyclerView;
    MyAdapater adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art);

        //대량의 데이터 추가 [실무에서는 DB or Network 서버에서 데이터를 읽어옴]
        items.add( new Item("미술학원","미술학원", R.drawable.art01, R.drawable.img02)  );
        items.add( new Item("미술학원","미술학원", R.drawable.art02, R.drawable.img03)  );
        items.add( new Item("미술학원","미술학원", R.drawable.art03, R.drawable.img04)  );
        items.add( new Item("미술학원","미술학원", R.drawable.art04, R.drawable.img05)  );
        items.add( new Item("미술학원","미술학원", R.drawable.art05, R.drawable.img01)  );
        items.add( new Item("미술학원","미술학원", R.drawable.art06, R.drawable.img06)  );

        recyclerView= findViewById(R.id.recycler);
        adapter= new MyAdapater(this, items);
        recyclerView.setAdapter(adapter);

        //리사이클러뷰는 리스트뷰와 다르게 아이템클릭 리스너가 없음.
        //아답터에서 itemView 에 직접 클릭리스너 설정해 주어야 함.

    }

    public void clickAdd(View view) {
        //새로운 아이템 추가[리사이클러뷰에 하는 것이 아니라 ArrayList 에 추가]
//        items.add( new Item("NEW","MESSAGE", R.drawable.ch_sandi, R.drawable.img10) );
//        //아답터에게 새로운 데이터가 추가되었다고 공지!!!! - 자동화면갱신
//        //adapter.notifyDataSetChanged();//1개 추가되었지만 전체를 다시 갱신함(비효율적)
//        //새로운 아이템 1개가 추가되었다고 공지!!!
//        adapter.notifyItemInserted(items.size()-1);
//        //리사이클러뷰의 스크롤위치를 조정
//        recyclerView.scrollToPosition(items.size()-1);

        //보통 새로 추가된 아이템은 첫번째(index:0)로 추가되는 경우가 더 많음[최신순]
        items.add( 0, new Item("NEW","MESSAGE", R.drawable.art06, R.drawable.img06)  );
        adapter.notifyItemInserted(0);
        recyclerView.scrollToPosition(0);
    }

    public void clickDel(View view) {
        if(items.size()==0) return;

        //리사이클러뷰의 아이템뷰를 삭제하는 것이 아니라. ArrayList 의 요소제거
        items.remove(0);
        adapter.notifyItemRemoved(0);
    }

    public void clickLinear(View view) {
        //리사이클러뷰의 배치관리자(LayoutManager) 변경하기
        LinearLayoutManager layoutManager= new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void clickGrid(View view) {
        GridLayoutManager layoutManager= new GridLayoutManager(this, 2);//2칸짜리 격자배치
        recyclerView.setLayoutManager(layoutManager);
    }
}