package com.doanminhthong.onlinesalesappandroidstudio.Activiti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.doanminhthong.onlinesalesappandroidstudio.R;
import com.doanminhthong.onlinesalesappandroidstudio.adapter.LoadSanPham;
import com.doanminhthong.onlinesalesappandroidstudio.connect.ConnectionSQLserver;
import com.doanminhthong.onlinesalesappandroidstudio.model.LoaiSP;
import com.google.android.material.navigation.NavigationView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerView_ManHinhChinh;
    NavigationView navigationView;
    ListView listView_ManHinhChinh;
    DrawerLayout drawerLayout;
    LoadSanPham loadSanPham;
    List<LoaiSP> mangloaisp;
    Connection connect;
    String ConnectionResult = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        linkViews();
        actionBar();
        acctionViewFlipper();
       // GetTextFromSQL();
    }

    private void acctionViewFlipper() {
        List<String> mangQuangCao = new ArrayList<>();
        mangQuangCao.add("http://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-Le-hoi-phu-kien-800-300.png");
        mangQuangCao.add("http://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-HC-Tra-Gop-800-300.png");
        mangQuangCao.add("http://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-big-ky-nguyen-800-300.jpg");
        for (int i = 0; i < mangQuangCao.size(); i++ ) {
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(mangQuangCao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }

        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setOutAnimation(slide_out);
    }

    private void actionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    public void GetTextFromSQL (View v)  {
        TextView txt = (TextView) findViewById(R.id.txt123);

        try {
            ConnectionSQLserver connectionSQLserver = new ConnectionSQLserver();
            connect = connectionSQLserver.connectionClass();
            if (connect != null) {
                String query = "SELECT tensanpham FROM SANPHAM";
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(query);

                while (rs.next()) {
                    txt.setText(rs.getString(1));

                }
            } else {
                ConnectionResult = "Check Connection";
            }

        }
        catch (Exception ex){}
    }

    private void linkViews() {
        toolbar = findViewById(R.id.toolBarmanhinhchinh);
        viewFlipper = findViewById(R.id.viewFlipper);
        recyclerView_ManHinhChinh = findViewById(R.id.recycleview);
        navigationView = findViewById(R.id.navigationview);
        listView_ManHinhChinh = findViewById(R.id.lv_Manhinh);
        drawerLayout = findViewById(R.id.drawerLayout);
        // khoi tao list
        mangloaisp = new ArrayList<>();
        // khoi tao Adapter
        loadSanPham = new LoadSanPham(getApplicationContext(), mangloaisp);
        listView_ManHinhChinh.setAdapter(loadSanPham);
    }


}