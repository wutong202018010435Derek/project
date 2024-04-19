package com.edu.squash.ui.me;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.edu.squash.R;
import com.edu.squash.app.MyApp;
import com.edu.squash.bean.SysAddress;
import com.lynn.base.base.BaseActivity;
import com.lynn.base.net.OkHttpTool;
import com.lynn.base.net.RuseltBean;
import com.lynn.base.uitls.JsonUtil;
import com.lynn.base.uitls.ToastUtil;
import com.lynn.base.view.AppTitleLayout;
import com.socks.library.KLog;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by ABC
 * on 2024/3/2
 * note
 */
public class AddAdressAty extends BaseActivity {

    @BindView(R.id.titleLayout)
    AppTitleLayout titleLayout;


    @BindView(R.id.et_province)
    EditText et_province;

    @BindView(R.id.et_city)
    EditText et_city;

    @BindView(R.id.et_area)
    EditText et_area;

    @BindView(R.id.et_street)
    EditText et_street;

    @BindView(R.id.et_address)
    EditText et_address;


    @BindView(R.id.et_consignee)
    EditText et_consignee;

    @BindView(R.id.et_phone)
    EditText et_phone;




    @Override
    protected int getLayoutId() {
        return R.layout.aty_add_address;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        titleLayout.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishOverAnim();
            }
        });

        titleLayout.setTitleText("NewAddress");
        titleLayout.setRightTitleText("toSave");
        titleLayout.setVisibility(View.VISIBLE);
        titleLayout.setOnRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    toSave();
            }
        });
    }

    private void toSave() {

        String province = et_province.getText().toString();
        String city= et_city.getText().toString();
        String area= et_area.getText().toString();
        String street= et_street.getText().toString();
        String address= et_address.getText().toString();
        String consignee= et_consignee.getText().toString();
        String phone= et_phone.getText().toString();

        SysAddress sysAddress = new SysAddress();
        sysAddress.setaProvice(province);
        sysAddress.setaCity(city);
        sysAddress.setaArea(area);
        sysAddress.setaStreet(street);
        sysAddress.setaAddress(address);
        sysAddress.setaConsignee(consignee);
        sysAddress.setaPhone(phone);

        toSaveData(sysAddress);


    }

    private void toSaveData(SysAddress sysAddress) {


//        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(price) || TextUtils.isEmpty(desc)) {
//            ToastUtil.showShortCenter(mContext, "no data");
//            return;
//        }


        String url = OkHttpTool.Base_URL + "app/addaddress?my=1";//也可以在这拼接参数
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("aProvice", sysAddress.getaAddress());
        parameters.put("aCity", sysAddress.getaCity());
        parameters.put("aArea", sysAddress.getaArea());
        parameters.put("aStreet", sysAddress.getaStreet());
        parameters.put("aAddress", sysAddress.getaAddress());
        parameters.put("aConsignee", sysAddress.getaConsignee());
        parameters.put("aPhone", sysAddress.getaPhone());
        parameters.put("aUserId", MyApp.getApp().getUser().getUserId());
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
}
