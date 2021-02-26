package com.afair.common.utils

import com.afair.pojo.entity.Area

/**
 * @author WangBing* @date 2021/1/4 16:58
 */
class test {
    public static void main(String[] args) {
        String a = "hello  roll";
        String b = "'wo shi xiao laji'";
        println(a[-1]);
        println(a[1])
        println(a[1..3])
        println(a[4..1])
        println(a[4..<1])
        def list = [100, 1001];
        def list2 = [13, 14];
        def list3 = [1, 2, 3, 4, 13, 14, 16, 14, 13];
        println list.plus(list2)
        println list3.minus(list2)
        Object obj = 2;
        method(obj);
        char c = 'a'
        assert Character.digit((char) 'a', 16) == 10
//        def rootfiles=new File("test").listRoots();
//        rootfiles.each {
//            file->println file.absolutePath
//        }
    }

    static void method(int args) {
        println "int"
    }

    static void method(Object obj) {
        println "obj"
    }

    static def display() {
        print("display")
    }
}
