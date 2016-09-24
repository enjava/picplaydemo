package com.en.picplaydemo;

import com.en.picplaydemo.bussiness.JSONUtils;
import com.en.picplaydemo.entity.Advertise;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
  public   void test(){
        List<Advertise>  list= JSONUtils.jsonList();

        for (Advertise advertise:list) {
            System.out.println(advertise.toString());
        }
    }
}