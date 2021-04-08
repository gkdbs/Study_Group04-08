package com.example.study_group;


import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter2 extends RecyclerView.Adapter {

    Context context;
    ArrayList<Item2> items;

    public MyAdapter2(Context context2, ArrayList<Item2> items2) {
        this.context = context2;
        this.items = items2;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);

        View itemView2= inflater.inflate(R.layout.recycler_item2, parent, false);
        VH holder2= new VH(itemView2);

        return holder2;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh= (VH)holder;

        //대입할 데이터 요소객체(Item) 얻어오기
        Item2 item= items.get(position);
        vh.tvName2.setText(item.name2);
        vh.tvMsg2.setText(item.msg2);

        //이미지 설정 - gif, network 를 편하게 할 수 있는 이미지 외부 라이브러리 사용 : Glide or Picasso
        //vh.ivIcon.setImageResource(item.icon); //gif 파일은 움직이지 못함
        Glide.with(context).load(item.icon2).into(vh.ivIcon2);
        Glide.with(context).load(item.img2).into(vh.ivImg2);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //아이템뷰 안에 있는 뷰들의 참조변수를 멤버로 가지는 클래스
    class VH extends RecyclerView.ViewHolder{

        CircleImageView ivIcon2;
        TextView tvName2;
        TextView tvMsg2;
        ImageView ivImg2;

        public VH(@NonNull View itemView) {
            super(itemView);

            ivIcon2= itemView.findViewById(R.id.iv_icon2);
            tvName2= itemView.findViewById(R.id.tv_name2);
            tvMsg2= itemView.findViewById(R.id.tv_msg2);
            ivImg2= itemView.findViewById(R.id.iv_img2);

            //아이템뷰를 클릭했을 때..
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //클릭한 항목 위치(index 번호) 알아내기
                    int position= getLayoutPosition();
                    //Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();

                    //그 선택된 항목의 상세내역을 보여주는 새로운 화면(Activity)를 실행
                    //선택된 아이템의 name, img 정보를 전달해주기
                    String name= items.get(position).name2;
                    int imgId= items.get(position).img2;

                    Intent intent= new Intent(context, DetailActivity2.class);
                    intent.putExtra("name", name);
                    intent.putExtra("imgId", imgId);

                    //전환 효과 [ LoLLIPOP 버전 이상 - api21 ]
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                        ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation((Activity)context, new Pair<View, String>(ivImg2,"img"));
                        context.startActivity(intent, options.toBundle() );
                    }else{
                        context.startActivity(intent);
                    }

                }
            });


            //아이템 롱~클릭 리스너 설정
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    int position= getLayoutPosition();
                    items.remove(position);
                    notifyItemRemoved(position);

                    return true;
                }
            });

        }
    }
}




