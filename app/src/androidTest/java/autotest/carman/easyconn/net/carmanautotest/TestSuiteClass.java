package autotest.carman.easyconn.net.carmanautotest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by wwl on 2017/5/24.
 * 用来批量执行测试类
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestMusic.class,
        TestPersonalCenter.class,
        ExampleInstrumentedTest.class
})
public class TestSuiteClass {

}
