import com.Application;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.service.Apiservice;

import javax.annotation.Resource;

/**
 * @author ZCZ
 * @create 2019-04-11-16:58
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class Test {
    @Resource
    private Apiservice apiService;

    @org.junit.Test
    public void test1() {
        System.out.println(apiService.sss());
    }

   @org.junit.Test
    public void test2() {
        System.out.println(apiService.sss());
    }

}
