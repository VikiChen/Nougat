package com.viki.nougat.Array;

import org.junit.Test;

public class TestArray {

     @Test
     public void  test(){
         Array array =new Array(10);
         for (int i = 0; i < 200; i++) {
             array.addFirst(i);
         }
         System.out.println(array);
         array.add(1,100);
         System.out.println(array);
     }
}
