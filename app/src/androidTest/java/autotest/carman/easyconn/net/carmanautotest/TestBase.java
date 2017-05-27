package autotest.carman.easyconn.net.carmanautotest;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import android.util.Log;

import org.hamcrest.core.IsNull;
import org.junit.Assert;

import java.util.List;


/**
 * Created by wwl on 2017/5/23.
 * 测试方法的公共类，所有测试类继承此类
 * */

public class TestBase {


    public static final String TAG="ExampleInstrumentedTest";
    public static final String BASIC_SAMPLE_PACKAGE
            = "net.easyconn.carman";
    protected Context mContext;
    protected static final int LAUNCH_TIMEOUT = 5000;
    protected static final int LONG_WAIT = 2000;
    protected static final int SHORT_WAIT= 1500;
    private int steps = 20;
    protected static final String STRING_TO_BE_TYPED = "UiAutomator";
    protected UiDevice mDevice;

    /**
     * 初始化获取设备
     * */
     void initDevice()
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

    /**
     * 启动应用
     * */
     void startMainActivityFromHomeScreen() {
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


    /**
     * 不用单独调用此方法
     * 自动判断是否存在权限框，if存在点击确认框点击，else return false
     */
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



    /**
     * 启动应用后，判断权限框是否弹出，弹出调用permission_allow方法。
     * 选择不再提示框
     * 点击确认按钮
     */
     void goHomePage() throws  Exception
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

        UiObject tv_cancel = mDevice.findObject(new UiSelector().resourceId("net.easyconn.carman:id/tv_cancel"));
        mDevice.waitForIdle(3000);
        if(tv_cancel.waitForExists(1000) && tv_cancel.exists())
        {
            Log.d(TAG, "tv_cancel button click .....");
            tv_cancel.click();
        }
    }

    /**
     * 登录方法
     * 如果没有登录，就用15971130771账号登录，然后返回主页
     */
     void login() throws Exception
    {
        UiObject id_home_main_user =mDevice.findObject(new UiSelector().resourceId("net.easyconn.carman:id/id_home_main_user"));
        Log.d(TAG,"net.easyconn.carman:id/id_home_main_user");
        id_home_main_user.click();
        UiObject tv_nick_name = mDevice.findObject(new UiSelector().resourceId("net.easyconn.carman:id/tv_nick_name"));
        if("登录/注册".equals(tv_nick_name.getText()))
        {
            tv_nick_name.click();
            UiObject et_phone = mDevice.findObject(new UiSelector().resourceId("net.easyconn.carman:id/et_phone"));
            et_phone.setText("15971130771");
            UiObject btn_get = mDevice.findObject(new UiSelector().resourceId("net.easyconn.carman:id/btn_get"));
            btn_get.click();

            Thread.sleep(1500);


            UiObject et_verification_code = mDevice.findObject(new UiSelector().resourceId("net.easyconn.carman:id/et_verification_code"));
            et_verification_code.setText("884569");
            UiObject btn_login = mDevice.findObject(new UiSelector().resourceId("net.easyconn.carman:id/btn_login"));
            btn_login.click();

        }
        Thread.sleep(1500);
    }

    /**
     * 根据ResourceId点击
     * */
     void clickByResourceId(String resId) throws Exception
    {
        UiObject uiObject = mDevice.findObject(new UiSelector().resourceId(resId));
        uiObject.waitForExists(6000);//等待按钮显示出来
        Log.d(TAG,"clickByResourceId()->resId:"+resId);
        uiObject.click();
        Thread.sleep(1500);
    }

    /**
     * 根据整个控件id找第N个子控件，然后点击
     * 用于地图搜索列表展示界面
     * */
    void clickByResourceIdAndIndex(String resId,int n) throws Exception
       {
        UiObject2 uiObject = mDevice.findObject(By.res(resId));
        List<UiObject2> children = uiObject.getChildren();
        if (children != null && children.size() > 0 && children.size() > n) {
            Log.d(TAG,"clickByResourceIdAndIndex:"+resId+n);
            children.get(n).click();
            Thread.sleep(1500);
        }

    }

    /**
     * 根据整个控件id找第N个子控件的子控件id进行点击
     * 用于详细poi结果展示界面的收藏功能
     * */
    void clickByResourceIdAndIndex(String resId,int n, String childResId) throws Exception
    {
        UiObject2 uiObject = mDevice.findObject(By.res(resId));
        List<UiObject2> children = uiObject.getChildren();
        if (children != null && children.size() > 0 && children.size() > n) {
            Log.d(TAG,"clickByResourceIdAndIndex:"+resId+n);
            children.get(n).findObject(By.res(childResId)).click();
            Thread.sleep(1500);
        }
    }

    /**
     * 根据整个控件id找第N个子控件的第N个子控件的进行点击
     * 用于第三方应用列表长按后的删除功能
     * */
    void clickByResourceIdAndIndex(String resId,int n, int m) throws Exception
    {
        UiObject2 uiObject = mDevice.findObject(By.res(resId));
        List<UiObject2> children = uiObject.getChildren();
        if (children != null && children.size() > 0 && children.size() > n) {
            Log.d(TAG,"clickByResourceIdAndIndex:"+resId+n);
            children.get(n).getChildren().get(m).click();
            Thread.sleep(1500);
        }
    }
    /**
     * 根据整个控件id找第N个子控件的子控件id进行点击
     * 断言点击适应横屏后的开关状态是否符合预期
     * 用于第三方应用长按后适应横屏功能
     * */
    void assertCheckButtonByResourceId(String resId,int n) throws Exception
    {
        UiObject2 uiObject = mDevice.findObject(By.res(resId));
        List<UiObject2> children = uiObject.getChildren();
        if (children != null && children.size() > 0 && children.size() > n) {
            if (children.get(n).getChildren().get(3).getChildren().get(0).isChecked())
            {
                clickByResourceIdAndIndex(resId,n,3);
                Thread.sleep(2000);
                Assert.assertFalse("适应横屏关闭",children.get(n).getChildren().get(3).getChildren().get(0).isChecked());
                Log.d(TAG,"适应横屏关闭:"+resId +"---------关闭");
            }
            else{
                clickByResourceIdAndIndex(resId,n,3);
                Thread.sleep(2000);
                Assert.assertTrue("适应横屏打开",children.get(n).getChildren().get(3).getChildren().get(0).isChecked());
                Log.d(TAG,"适应横屏打开:"+resId +"---------打开");
            }
        }
    }



    /**
     * 根据ResourceId长按
     * */
     void longClickByText(String text) throws Exception
    {
        UiObject uiObject = mDevice.findObject(new UiSelector().text(text));
        uiObject.waitForExists(6000);//等待按钮显示出来
        Log.d(TAG,"longClickByName()->text:"+text);
        uiObject.longClick();
        Thread.sleep(1500);
    }

    /**
     * 通过id查找并返回开关状态
     * */
    boolean findByResourceIdAndIsChecked(String resId) throws Exception{
        UiObject object=mDevice.findObject(new UiSelector().resourceId(resId));
        return object.exists() && object.isChecked();
    }

    /**
     * 通过ID判断控件是否存在，点击控件，并判断控件状态是否改变
     * */
    void assertCheckButton(String resId) throws Exception {
        UiObject object=mDevice.findObject(new UiSelector().resourceId(resId));
        if (object.exists()) {
            if (object.isChecked()) {
                clickByResourceId(resId);
                Thread.sleep(2000);
                Assert.assertFalse(resId+"关闭", object.isChecked());
                Log.d(TAG,"assertCheckButton:"+resId +"---------关闭");
            } else {
                clickByResourceId(resId);
                Thread.sleep(2000);
                Assert.assertTrue(resId+"打开", object.isChecked());
                Log.d(TAG,"assertCheckButton:"+resId +"---------打开");
            }
        }
    }

    /**
     * 根据text字段来点击
     * */

    UiObject clickByText(String text) throws Exception{
        UiObject uiObject = null;
        uiObject = mDevice.findObject(new UiSelector().text(text));
        uiObject.waitForExists(6000);//等待按钮显示出来
        Log.d(TAG,"clickByText()->text:"+text);
        uiObject.click();
        Thread.sleep(1500);
        return uiObject;

    }

    /**
     * 根据ID确认控件，输入文本
     * */
     void setTextByResourceId(String resId,String text) throws Exception
    {
        UiObject uiObject = mDevice.findObject(new UiSelector().resourceId(resId));
        uiObject.waitForExists(6000);//等待按钮显示出来
        Log.d(TAG,"setTextByResourceId()->resId:"+resId + text);
        uiObject.setText(text);
        Thread.sleep(1500);

    }

    /**
     * 根据text字段判断是否存在，存在则点击
     * */
     UiObject clickByTextIfExists(String text) throws Exception{
        UiObject uiObject = null;
        uiObject = mDevice.findObject(new UiSelector().text(text));
        if (uiObject.waitForExists(3000)) {
            Log.d(TAG,"clickByTextExists()-->text:"+text);
            uiObject.click();
            Thread.sleep(1500);
        }else {
            Log.d(TAG,"clickByTextNOExists()-->text:"+text);
        }
        return uiObject;
    }

    /**
     * 根据resourceId字段判断是否存在，存在则点击
     * */
      void clickByResIdIfExists(String resId) throws  Exception
    {
        UiObject img_know = mDevice.findObject(new UiSelector().resourceId(resId));
        if(img_know.exists()){
            Log.d(TAG,"clickByResIdExists()->resId:"+resId);
            img_know.click();
        }else {
            Log.d(TAG,"clickByResIdNoExists()->text:"+resId);
        }
        Thread.sleep(1500);
    }


    /**
     * 上滑
     */
     void swipeToUp() throws InterruptedException {
        int width= mDevice.getDisplayWidth();
        int height=mDevice.getDisplayHeight();
        Log.d(TAG,"swipeToUp()------------------>");
        mDevice.swipe(width/2,height*3/4,width/2,height/4,steps);
        Thread.sleep(1000);
    }

    /**
     * 下滑
     */
     void swipeToDown() throws InterruptedException {
        int width= mDevice.getDisplayWidth();
        int height=mDevice.getDisplayHeight();
        Log.d(TAG,"swipeToDown()------------------>");
        mDevice.swipe(width/2,height/4,width/2,height*3/4,steps);
        Thread.sleep(1000);
    }

    /**
     * 左滑
     */
     void swipeToLeft() throws InterruptedException {
        int width= mDevice.getDisplayWidth();
        int height=mDevice.getDisplayHeight();
        Log.d(TAG,"swipeToLeft()------------------>");
        mDevice.swipe(width*3/4,height/2,width/4,height/2,steps);
        Thread.sleep(1000);
    }
    /**
     * 右滑
     */
     void swipeToRight() throws InterruptedException {
        int width= mDevice.getDisplayWidth();
        int height=mDevice.getDisplayHeight();
        Log.d(TAG,"swipeToRight()------------------>");
        mDevice.swipe(width/4,height/2,width*3/4,height/2,steps);
        Thread.sleep(1000);
    }


}
