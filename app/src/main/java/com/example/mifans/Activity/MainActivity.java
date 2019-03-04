package com.example.mifans.Activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mifans.Adapter.MyPagerAdapter;
import com.example.mifans.Fragment.PagerFragment;
import com.example.mifans.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private boolean isinsert = false;
    private MyDatabaseHelper databaseHelper = new MyDatabaseHelper(MainActivity.this, "User.db", null, 1);
    SQLiteDatabase database;
    View goSearch;
    TabLayout tabLayout;
    ViewPager viewPager;
    private List<Fragment> fragmentList;
    public static final int TAKE_PHOTO = 1;//拍照更换头像
    public static final int CHOOSE_PICTURE = 2;//从相册选择图片
    private Uri imageUri;
    ImageView head;
    TextView nickname;
    TextView slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goSearch = findViewById(R.id.search);
        goSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
            }
        });

        ImageView sliding = findViewById(R.id.hua);
        sliding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "向右滑动拖出滑动菜单", Toast.LENGTH_SHORT).show();
            }
        });

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        fragmentList = new ArrayList<Fragment>();

        FragmentManager fm = getSupportFragmentManager();
        initFragment();

        final MyPagerAdapter myPagerAdapter = new MyPagerAdapter(fm, fragmentList);
        viewPager.setAdapter(myPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        //滑动菜单部分
        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_collect:
                        Toast.makeText(MainActivity.this, "请充值获取该服务", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "请充值获取该服务", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        isinsert = preferences.getBoolean("isinsert",true);
        if (isinsert){
            userTableInsert();//向User表添加数据,头像，个性签名，昵称，目前只有我一个用户就只添加一个用户了
            editor.putBoolean("isinsert",false);
            editor.apply();
        }
        //navigationView头部点击事件
        View headView = navigationView.inflateHeaderView(R.layout.nav_header);
        slogan = headView.findViewById(R.id.slogan);
        nickname = headView.findViewById(R.id.nake_name);
        //加载已保存的昵称和个性签名

        head = headView.findViewById(R.id.icon_image);
        database = databaseHelper.getWritableDatabase();
        Cursor cursor = database.query("User", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            byte[] imagequery = cursor.getBlob(cursor.getColumnIndex("image"));
            String nicknameText = cursor.getString(cursor.getColumnIndex("nickname"));
            String sloganText = cursor.getString(cursor.getColumnIndex("slogan"));
            Bitmap bitmap = BitmapFactory.decodeByteArray(imagequery, 0, imagequery.length);
            head.setImageBitmap(bitmap);
            nickname.setText(nicknameText);
            slogan.setText(sloganText);
        }

        //更换头像
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setIcon(R.drawable.touxiang);
                dialog.setTitle("设置头像");

                final String[] choose = {"从相册选取", "拍照", "取消"};


                dialog.setItems(choose, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            //申请读写Sd卡权限
                            if (ContextCompat.checkSelfPermission(MainActivity.this,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                            } else {
                                openAlbum();
                            }
                        } else if (which == 1) {
                            File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
                            try {
                                if (outputImage.exists()) {
                                    outputImage.delete();
                                }
                                outputImage.createNewFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (Build.VERSION.SDK_INT >= 24) {
                                imageUri = FileProvider.getUriForFile(MainActivity.this, "com.example.mifans.todaynews", outputImage);
                            } else {
                                imageUri = Uri.fromFile(outputImage);
                            }
                            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                            startActivityForResult(intent, TAKE_PHOTO);
                        } else {

                        }
                    }
                });
                dialog.show();

            }
        });


        //更改昵称
        nickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View view = View.inflate(MainActivity.this, R.layout.dialog_name, null);
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(view);
                final EditText editText = view.findViewById(R.id.edit_name);
                TextView textView = view.findViewById(R.id.dialog_title);
                Button buttonOk = view.findViewById(R.id.ok);
                Button buttonCan = view.findViewById(R.id.cancle);
                textView.setText("请输入用户名");
                final AlertDialog dialog = builder.show();
                buttonOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nickname.setText(editText.getText());
                        database = databaseHelper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put("nickname", editText.getText().toString());
                        database.update("User",values,null,null);//更新User表昵称信息
                        dialog.dismiss();
                    }
                });
                buttonCan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


            }
        });

        //更改个性签名
        slogan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = View.inflate(MainActivity.this, R.layout.dialog_name, null);
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(view);
                final EditText editText = view.findViewById(R.id.edit_name);
                TextView textView = view.findViewById(R.id.dialog_title);
                Button buttonOk = view.findViewById(R.id.ok);
                Button buttonCan = view.findViewById(R.id.cancle);
                textView.setText("请输入个性签名");
                final AlertDialog dialog = builder.show();
                buttonOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        slogan.setText(editText.getText());
                        database = databaseHelper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put("slogan", editText.getText().toString());
                        database.update("User",values,null,null);//更新User表签名信息
                        dialog.dismiss();
                    }
                });
                buttonCan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });


    }

    public void initFragment() {
        //推荐
        PagerFragment fragmentFood = PagerFragment.newInstance("http://m.toutiao.com/list/?tag=news_food&ac=wap&count=50&format=json_raw&as=A17538D54D106FF&cp=585DF0A65F0F1E1");
        //热点
        PagerFragment fragmentHot = PagerFragment.newInstance("http://m.toutiao.com/list/?tag=news_hot&ac=wap&count=50&format=json_raw&as=A17538D54D106FF&cp=585DF0A65F0F1E1");
        //游戏
        PagerFragment fragmentGame = PagerFragment.newInstance("http://m.toutiao.com/list/?tag=news_game&ac=wap&count=50&format=json_raw&as=A17538D54D106FF&cp=585DF0A65F0F1E1");
        //汽车
        PagerFragment fragmentCar = PagerFragment.newInstance("http://m.toutiao.com/list/?tag=news_car&ac=wap&count=50&format=json_raw&as=A17538D54D106FF&cp=585DF0A65F0F1E1");
        //娱乐
        PagerFragment fragmentStory = PagerFragment.newInstance("http://m.toutiao.com/list/?tag=news_story&ac=wap&count=50&format=json_raw&as=A17538D54D106FF&cp=585DF0A65F0F1E1");
        //科技
        PagerFragment fragmentTech = PagerFragment.newInstance("http://m.toutiao.com/list/?tag=news_tech&ac=wap&count=50&format=json_raw&as=A17538D54D106FF&cp=585DF0A65F0F1E1");
        //养生
        PagerFragment fragmentRegimen = PagerFragment.newInstance("http://m.toutiao.com/list/?tag=news_regimen&ac=wap&count=50&format=json_raw&as=A17538D54D106FF&cp=585DF0A65F0F1E1");

        fragmentList.add(fragmentHot);
        fragmentList.add(fragmentGame);
        fragmentList.add(fragmentCar);
        fragmentList.add(fragmentStory);
        fragmentList.add(fragmentTech);
        fragmentList.add(fragmentRegimen);
        fragmentList.add(fragmentFood);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        head.setImageBitmap(bitmap);
                        database = databaseHelper.getWritableDatabase();
                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                        byte[] imagedata1 = out.toByteArray();
                        ContentValues values = new ContentValues();
                        values.put("image", imagedata1);
                        database.update("User", values,null,null);//更新User表头像信息
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
//                        FileUtil.saveImage(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PICTURE:
                if (resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= 19) {
                        handleImageOnKitKat(data);
                    } else {
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PICTURE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(this, "您取消了授权，该功能无法使用", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            imagePath = uri.getPath();
        }
        displayImage(imagePath);

    }


    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }


    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            head.setImageBitmap(bitmap);
            database = databaseHelper.getWritableDatabase();

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            byte[] imagedata1 = out.toByteArray();
            ContentValues values = new ContentValues();
            values.put("image", imagedata1);
            database.update("User",values,null,null);//由于目前只有我一位用户，默认全部更新
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ;
//            FileUtil.saveImage(bitmap);
        } else {
            Toast.makeText(this, "获取文件失败", Toast.LENGTH_SHORT).show();
        }
    }

    private void userTableInsert() {
        database = databaseHelper.getWritableDatabase();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] imagedata1 = out.toByteArray();
        ContentValues values = new ContentValues();
        values.put("image",imagedata1);
        values.put("nickname","请输入用户名");
        values.put("slogan","请输入个性签名");
        database.insert("User",null,values);
    }

}
