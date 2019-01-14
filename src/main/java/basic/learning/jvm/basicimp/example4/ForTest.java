package basic.learning.jvm.basicimp.example4;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2019 yowits Corporation. All rights reserved.
 * @create ：2019/1/14 17:28
 */
public class ForTest {
    public  static void main(String[] args){
        Integer[] ints = new Integer[]{1 ,1 ,3,4 ,5};
        int length = ints.length;
        for(int i = 0 ; i < length ;i ++ ){
            Integer anInt = ints[i];
        }
        for(Integer  s: ints){

        }
    }
}
