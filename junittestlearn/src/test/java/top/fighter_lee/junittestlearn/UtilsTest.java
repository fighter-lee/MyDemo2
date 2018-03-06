package top.fighter_lee.junittestlearn;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author fighter_lee
 * @date 2018/1/25
 */
public class UtilsTest {

    @Mock
    AndroidBean bean;

    @Mock
    List list;

    @Before
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test1() throws Exception {
        Assert.assertEquals(Utils.test1(),"is test1");
    }

    @Test
    public void test2() throws Exception {
        Assert.assertEquals(Utils.test2("1","2"),"21");
    }

    @Test(expected = ArithmeticException.class)
    public void testException() {
        int b = 1/0;
        System.out.println(b);
    }

    @Test
    public void test3() throws Exception {
        Bean bean = new Bean();
//        List list = mock(List.class);
//        list.add("1111");
        when(list.get(0)).thenReturn("1111");
        System.out.println(list.get(0).toString());
        bean.setAge("22");
        bean.setName("hahahah");
        Assert.assertEquals("hahahah22",Utils.test3(bean));
    }

    @Test
    public void test4() {
        when(bean.getName()).thenReturn("aaa");
        System.out.println(Utils.test4(bean));
        Assert.assertEquals(Utils.test4(bean),"aaa");
    }

    /**
     * 行为验证
     */
    @Test
    public void test5() {
        list.add("5555");
        list.clear();
        verify(list).add("5555");
        verify(list).clear();
    }

    /**
     * stubbing
     */
    @Test
    public void test6() {
        when(list.get(0)).thenReturn("first");
        when(list.get(1)).thenThrow(new RuntimeException());
        System.out.println(list.get(0));
//        when(list.get(0)).thenReturn("second");
        System.out.println(list.get(0));
//        System.out.println(list.get(1));
        verify(list).get(0);
    }

    /**
     * 参数匹配器
     */
    @Test
    public void test7() {
        when(list.get(anyInt())).thenReturn("hahahah");
        System.out.println(list.get(999));
    }

    /**
     * 多次调用
     */
    @Test
    public void test8() {
        list.add("one");
        list.add("two");
        list.add("three");
        verify(list,never()).add("four");
        verify(list,atMost(1)).add("one");
        verify(list,atLeast(2)).add("one");
    }

    /**
     * 异常处理
     */
    @Test
    public void test9() {
        doThrow(new RuntimeException()).when(list).get(0);
        System.out.println(list.get(0));
    }

}