package com.edu.squash.ui.seller;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.widget.AppCompatSpinner;

import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;
import com.edu.squash.MainActivity;
import com.edu.squash.R;
import com.edu.squash.app.MyApp;
import com.edu.squash.bean.ParkListBean;
import com.edu.squash.bean.SysCategory;
import com.edu.squash.bean.UploadBean;
import com.edu.squash.ui.login.RegisterUserActivity;
import com.edu.squash.utils.ImageLoadUtils;
import com.edu.squash.utils.MyPermissionsCallback;
import com.edu.squash.utils.RxPermissionsUtils;
import com.google.gson.reflect.TypeToken;
import com.lynn.base.base.BaseActivity;
import com.lynn.base.beans.UserBean;
import com.lynn.base.img.ImgPickerActivity;
import com.lynn.base.net.HttpResponse;
import com.lynn.base.net.OkHttpTool;
import com.lynn.base.net.RuseltBean;
import com.lynn.base.uitls.JsonUtil;
import com.lynn.base.uitls.ToastUtil;
import com.lynn.base.view.AppTitleLayout;
import com.socks.library.KLog;
import com.tbruyelle.rxpermissions2.RxPermissions;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * Created by ABC
 * <p>
 * note
 */
public class AddGoodsAty extends BaseActivity {


    @BindView(R.id.titleLayout)
    AppTitleLayout titleLayout;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.et_desc)
    EditText etDesc;
    @BindView(R.id.iv_pic_img)
    ImageView ivPicImg;

//    @BindView(R.id.spin_classtype)
//    AppCompatSpinner spinClass;


    @BindView(R.id.spinner)
    Spinner spinner;

//    private List<ShopTypeBean> mShopType = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.aty_dish_add;
    }

//    private ShopBean mShopBean;
//    private FoodMenuBean mFoodMenuBean;
//    private boolean isAddMenu = true;

    @Override
    protected void onInitView(Bundle bundle) {

//        mShopBean = (ShopBean) getIntent().getSerializableExtra("shopdetail");
//        mFoodMenuBean = (FoodMenuBean) getIntent().getSerializableExtra("Menudetail");
//        if (mFoodMenuBean != null)
//            isAddMenu = false;

        titleLayout.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishOverAnim();
            }
        });
        ivPicImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                toOpenPic(requestCode);
                toOpenPic();
            }
        });
        titleLayout.setRightTitleText("SAVE");
        titleLayout.setOnRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toSaveData();
            }
        });
        getCategory();

//        setDataUi();
//


    }

    private void toSaveData() {


        String name = etName.getText().toString().trim();

        String price = etPrice.getText().toString().trim();

        String desc = etDesc.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(price) || TextUtils.isEmpty(desc)|| TextUtils.isEmpty(imgUrl)) {
            ToastUtil.showShortCenter(mContext, "no data");
            return;
        }

        String url = OkHttpTool.Base_URL + "app/addGoods?my=1";//也可以在这拼接参数
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("goodsName", name);
        parameters.put("goodsDesc", desc);
        parameters.put("goodsTypeCategory", selectedItem);
        parameters.put("goodsPrice", price);
        parameters.put("goodsSellerId", MyApp.getApp().getUser().getUserId());
        parameters.put("goodsImg",imgUrl);
        OkHttpTool.httpGet(url, parameters, new OkHttpTool.ResponseCallback() {
            @Override
            public void onResponse(final boolean isSuccess, final int responseCode, final String response, Exception exception) {

                KLog.e("测试", "----" + isSuccess + "  " + response + "    ");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (isSuccess && !TextUtils.isEmpty(response)) {
                            RuseltBean message = JsonUtil.parseJson(response, RuseltBean.class);
                            if (message != null) {
                                ToastUtil.showShortCenter(mContext, message.getMsg());
                                if (message.getCode() == 200) {
                                    finish();
                                }
                            }

                        }
                    }
                });
            }
        });

    }

    private String selectedItem;

    private void initClassType(String[] items) {

//        String[] items = {"Item 1", "Item 2", "Item 3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 记录当前选择的项
                selectedItem = (String) parent.getItemAtPosition(position);
                KLog.d("Spinner", "Selected item: " + selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // 什么都不做
            }
        });

    }


    private void getCategory() {
        String url = OkHttpTool.Base_URL + "app/getCategory?my=1";//也可以在这拼接参数
        Map<String, Object> parameters = new HashMap<>();


        OkHttpTool.httpGet(url, parameters, new OkHttpTool.ResponseCallback() {
            @Override
            public void onResponse(final boolean isSuccess, final int responseCode, final String response, Exception exception) {

                KLog.e("测试", "----" + isSuccess + "  " + response + "    ");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (isSuccess && !TextUtils.isEmpty(response)) {
                            HttpResponse<List<SysCategory>> message = JsonUtil.parseJson(response, new TypeToken<HttpResponse<List<SysCategory>>>() {
                            }.getType());
                            if (message != null) {
                                if (message.getCode() == 200) {
                                    if (message.getData() != null && message.getData().size() > 0) {
                                        String[] items = new String[message.getData().size()];
                                        for (int i = 0; i < message.getData().size(); i++) {
                                            items[i] = message.getData().get(i).getCategoryName();
                                        }
                                        initClassType(items);
                                    }
                                }
                            }

                        }
                    }
                });
            }
        });
    }


    private void toOpenPic() {

        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .flatMap(new Function<Boolean, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Boolean aBoolean) throws Exception {
                        if (aBoolean)
                            return Observable.just(true);
                        return Observable.error(new Throwable("shop need Permissions！"));
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {
                        Intent intent = new Intent(mContext, ImgPickerActivity.class);
                        intent.putExtra(ImgPickerActivity.EXTRA_MAX_COUNT, 1);
                        startActivityForResult(intent, requestCode);
                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private int requestCode = 2020;
    private String headImg;

    @Override
    protected void onActivityResult(int requestCode2, int mresultCode, Intent data) {
        super.onActivityResult(requestCode2, mresultCode, data);
        if (mresultCode == RESULT_OK && requestCode == requestCode2) {
            if (data != null) {
                headImg = data.getStringExtra("photo");
                KLog.e("----头像" + headImg);
                if (!TextUtils.isEmpty(headImg)) {
                    ImageLoadUtils.loadCircleImage(mContext, headImg, ivPicImg);
                    toUpload(headImg);
                }

            }

        }
    }
    private String imgUrl;

    private void toUpload(String imgURL) {



        if (TextUtils.isEmpty(imgURL) ) {
            ToastUtil.showShortCenter(mContext, "incomplete information");
            return;
        }

        String url = OkHttpTool.Base_URL + "app/upload?my=1";//也可以在这拼接参数
        Map<String, Object> parameters = new HashMap<>();

        Map<String, File> files =new HashMap<>();
        files.put("file",new File(imgURL));

        OkHttpTool.httpPostWithFile(url, parameters,files, new OkHttpTool.ResponseCallback() {
            @Override
            public void onResponse(final boolean isSuccess, final int responseCode, final String response, Exception exception) {

                KLog.e("测试上传", "----" + isSuccess + "  " + response + "    ");
                if (isSuccess){
                    UploadBean uploadBean = JsonUtil.parseJson(response,UploadBean.class);
                    if (uploadBean != null){
                        imgUrl = uploadBean.getUrl();
                    }
                }
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        if (isSuccess && !TextUtils.isEmpty(response)) {
//                            RuseltBean message = JsonUtil.parseJson(response, RuseltBean.class);
//                            if (message != null) {
//                                ToastUtil.showShortCenter(mContext, message.getMsg());
//                                if (message.getCode() == 200) {
//                                    finish();
//                                }
//                            }
//
//                        }
//                    }
//                });
            }
        });

    }

}
