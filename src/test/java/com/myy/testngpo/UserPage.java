package com.myy.testngpo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserPage {
    //成员变量
    private WebDriver driver;

    /**
     * 测试前置条件，开启网页：网页驱动，商城地址
     */
    @BeforeClass
    public void openBrower(){
        driver = HomePage.driver;
    }

    @Test
    public void modifierInfo(){
        driver.findElement(By.xpath("//a[@href='/user']")).click();
        driver.findElement(By.xpath("//a[text()='用户资料']")).click();
        driver.findElement(By.xpath("//input[@class=\"el-input__inner\"]")).clear();                        //清空值
        driver.findElement(By.xpath("//input[@class=\"el-input__inner\"]")).sendKeys("商家234");//再赋值
        driver.findElement(By.xpath("//div[@class=\"el-radio-group\"]/label[1]")).click();
        driver.findElement(By.xpath("//span[text()='确定']")).click();//deng

    }



}
