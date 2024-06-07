package com.myy.testngpo;

import io.opentelemetry.api.internal.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;->"Git Remotes"
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class HomePage {
    /**
     * 成员变量driver ,让所有的测试方法都使用同一个浏览器
     * private WebDriver driver;
     * public static WebDriver driver;方便其他类使用
     */
    public static WebDriver driver;

    public String url = "https://pc.qingwuit.com/login";

    /**
     * 测试前的准备工作，开启网页：网页驱动，商城地址
     */
    @BeforeClass
    public void openBrower() {
        driver = new ChromeDriver();                                //谷歌浏览器的驱动
        driver.get(url);                //商城地址
        //添加隐式等待  element not interactable 元素无法被操纵
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    /**
     * 登录的测试用例
     */
    @Test
    public void shopLog() {
        driver.findElement(By.xpath("//*[@placeholder='手机']")).sendKeys("18888888888");
        driver.findElement(By.xpath("//*[@placeholder='密码']")).sendKeys("123456");
        driver.findElement(By.xpath("//*[@class=\"login_btn\"]")).click();
        //断言，判断是否与预期结果一致
        String uername = null;
        try {
            for (int i = 0; i < 10; i++) {
                uername = driver.findElement(By.xpath("//*[@id='app']/div/div/div[1]/div/div[2]/ul/li[3]/a")).getText();
                if (!StringUtils.isNullOrEmpty(uername)) {
                    break;
                }
                Thread.sleep(500);
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(uername);
        Assert.assertEquals(uername, "商家234");
    }

    /**
     * 这个方法，随便写写，应该放在UserPage
     * 在登录完成之后，对模块测试,断言
     */
    @Test
    public void shopSearch() {
        driver.findElement(By.xpath("//a[text()='个人中心']")).click();
        driver.findElement(By.xpath("//div[text()='待支付']")).click();
        //断言，判断是否与预期结果一致
        String orderStringName = driver.findElement(By.xpath("//div[@class=\"user_right\"]/div[@class=\"user_order\"]/div[@class=\"user_main\"]/div[@class=\"block_title\"]/em")).getText();
        System.out.println(orderStringName);
        Assert.assertTrue(orderStringName.contains("我的订单"));
    }

}
