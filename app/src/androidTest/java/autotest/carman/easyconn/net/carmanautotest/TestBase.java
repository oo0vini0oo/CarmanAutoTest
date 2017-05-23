package autotest.carman.easyconn.net.carmanautotest;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import android.util.Log;

import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Before;

/**
 * Created by wwl on 2017/5/23.
 */

public class TestBase {


    public static final String TAG="ExampleInstrumentedTest";
    public static final String BASIC_SAMPLE_PACKAGE
            = "net.easyconn.carman";
    protected Context mContext;
    protected static final int LAUNCH_TIMEOUT = 5000;
    protected static final String STRING_TO_BE_TYPED = "UiAutomator";
    protected UiDevice mDevice;

    public void initDevice()
    {
        // Initialize UiDevice instance
        while (mDevice == null) {
            mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void startMainActivityFromHomeScreen() {
        initDevice();
        // Start from the home screen
        mDevice.pressHome();

        // Wait for launcher
        final String launcherPackage = mDevice.getLauncherPackageName();
        Assert.assertThat(launcherPackage, IsNull.notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
                LAUNCH_TIMEOUT);

        // Launch the app
        mContext = InstrumentationRegistry.getContext();
        final Intent intent = mContext.getPackageManager()
                .getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE);
        // Clear out any previous instances
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mContext.startActivity(intent);

        // Wait for the app to appear
        BySelector bySelector = By.pkg(BASIC_SAMPLE_PACKAGE);
        if (bySelector != null) {
            try {
                mDevice.wait(Until.hasObject(bySelector.depth(0)), LAUNCH_TIMEOUT);
            } catch (Throwable ee) {
                ee.printStackTrace();
            }
        } else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public void clickByResourceId(String resId)
    {
        try{
            UiObject uiObject = mDevice.findObject(new UiSelector().resourceId(resId));
            //点击音乐
            uiObject.waitForExists(6000);//等待按钮显示出来
            uiObject.click();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 判断文本是否存在
     * */
    public boolean findByText(String Text){
        UiObject text = mDevice.findObject(new UiSelector().text(Text));
        if(text.waitForExists(1000) && text.exists()) {
            return true;
        }
        return false;
    }



    public UiObject clickByText(String text) {
        UiObject uiObject = null;
        try {
            uiObject = mDevice.findObject(new UiSelector().text(text));
            //点击音乐
            uiObject.waitForExists(6000);//等待按钮显示出来
            uiObject.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uiObject;
    }
    public UiObject clickByTextIfExists(String text) {
        UiObject uiObject = null;
        try {
            uiObject = mDevice.findObject(new UiSelector().text(text));
            if (uiObject.waitForExists(3000))
                uiObject.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uiObject;
    }

    public  void clickByResIdIfExists(String resId) throws  Exception
    {
        UiObject img_know = mDevice.findObject(new UiSelector().resourceId(resId));
        if(img_know.exists()){
            img_know.click();
        }

    }



    //自动判断是否存在权限框，if存在点击确认框点击，else return false
    protected boolean permission_allow()
    {
        UiObject permission_allow_button = mDevice.findObject(new UiSelector().resourceId("com.android.packageinstaller:id/permission_allow_button"));
        if(permission_allow_button.waitForExists(1000) && permission_allow_button.exists()) {
            try {
                Log.d(TAG, "permission_allow_button.....");
                return permission_allow_button.click();
            } catch (UiObjectNotFoundException e) {
                e.printStackTrace();
            }

        }
        return false;
    }



    /** 启动应用后，判断权限框是否弹出，弹出调用permission_allow方法。
     *
     * 选择不再提示框
     * 点击确认按钮
     */
    public void goHomePage() throws  Exception
    {
        Log.d(TAG, "waitForIdle.....");
        mDevice.waitForIdle(3000);

        while (permission_allow())
        {
            Thread.sleep(1000);
        }


        UiObject tv_skip = mDevice.findObject(new UiSelector().resourceId("net.easyconn.carman:id/tv_skip"));
        mDevice.waitForIdle(3000);
        //判断是否有跳过按钮
        if(tv_skip.waitForExists(1000) && tv_skip.exists())
        {
            Log.d(TAG, "tv_skip button click .....");
            tv_skip.click();
        }

        UiObject cb_statement_status = mDevice.findObject(new UiSelector().resourceId("net.easyconn.carman:id/cb_statement_status"));
        mDevice.waitForIdle(3000);
        if(cb_statement_status.waitForExists(1000) && cb_statement_status.exists())
        {
            Log.d(TAG, "cb_statement_status button click .....");
            cb_statement_status.click();
        }
        UiObject statement_ok = mDevice.findObject(new UiSelector().resourceId("net.easyconn.carman:id/statement_ok"));
        mDevice.waitForIdle(3000);
        if(statement_ok.waitForExists(1000) && statement_ok.exists())
        {
            Log.d(TAG, "statement_ok button click .....");
            statement_ok.click();
        }
    }
    /**
     * 如果没有登录，就用15971130771账号登录
     * @return
     */
    public void login() throws Exception
    {
        UiObject id_home_main_user =mDevice.findObject(new UiSelector().resourceId("net.easyconn.carman:id/id_home_main_user"));
        id_home_main_user.click();
        UiObject tv_nick_name = mDevice.findObject(new UiSelector().resourceId("net.easyconn.carman:id/tv_nick_name"));
        if("登录/注册".equals(tv_nick_name.getText()))
        {
            tv_nick_name.click();
            UiObject et_phone = mDevice.findObject(new UiSelector().resourceId("net.easyconn.carman:id/et_phone"));
            et_phone.setText("15971130771");
            UiObject btn_get = mDevice.findObject(new UiSelector().resourceId("net.easyconn.carman:id/btn_get"));
            btn_get.click();

            Thread.sleep(1000);


            UiObject et_verification_code = mDevice.findObject(new UiSelector().resourceId("net.easyconn.carman:id/et_verification_code"));
            et_verification_code.setText("567149");
            UiObject btn_login = mDevice.findObject(new UiSelector().resourceId("net.easyconn.carman:id/btn_login"));
            btn_login.click();

        }
        mDevice.pressBack();
        mDevice.waitForIdle(2000);
    }
}
