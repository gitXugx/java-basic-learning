# 类型的生命周期
> java中一个编译生成的class文件，在class二进制文件读入到jvm创建类型。

**类型是什么？**
我们编码时有时候可以看到
```java

public class Application {

    public static void main(String[] args) {
        //获取该类的类型
        Class<Application> applicationClass = Application.class;
        
    }
}
```
这个Class对象就是在类型装载的时候生成的一个对应类型的对象

## 1 类型的装载、连接和初始化
> java通过类型装载、连接和初始化一个java类型，使该类型可以被正在运行的程序所使用。


```java
public class User {
    private String userName;
    private String passWord;
    private Integer age;
}

public class Test {
    public static void main(String[] args) throws InterruptedException {
//        Thread.sleep(10000L);
        User user = new User();
        user.setAge(1);
        user.setPassWord("1234");
        user.setUserName("xgx");
        System.out.println(user);
    }
}
```

我们可以编译我们的代码会生成对应的class文件，这时我们删除User.class,运行程序结果如下:
```text
Exception in thread "main" java.lang.NoClassDefFoundError: basic/learning/jvm/classlife/example1/User
	at basic.learning.jvm.classlife.example1.Test.main(Test.java:15)
Caused by: java.lang.ClassNotFoundException: basic.learning.jvm.classlife.example1.User
	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:349)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
	... 1 more
```
说明我们在编译的时候要加载User.class文件

**那类加载是什么时候加载呢?**
下面我们打开`Thread.sleep(10000L)`注释掉的代码。
1. 编译代码生成对应的俩个class文件: Test.class  User.class
2. 删除User.class文件，运行
3. 主线程会等待,这个时候并没有抛出上面的异常
4. **等睡眠时间到了后,就会抛出和上面一样的异常**

可以看出class文件的加载是在用到的时候才加载

**具体什么时候类的类型才必须加载初始化呢**
1. 当创建某个类型的实例时(new、反射创建、克隆、反序列化 )
2. 调用某个类的静态方法的时候
3. 调用javaAPI反射方法时
4. 初始化某个类的子类时(初始化子类需要先初始化化父类)
5. 虚拟机启动时被标记为启动类的类
6. 使用某个类或接口的静态字段时

**什么时候接口的类型加载呢?**


**解析到方法区中的数据结构是什么?**



### 1.1 装载

装载验证:
1. 保证class文件是预期格式
2. 除object外每个类都要有父类，并确保他的父类被装载



装载是由3个动作组成
1. 通过该类的全限定名，找到class文件，读取该类型的二进制流
2. 解析该类型的二进制流到方法区的数据结构中
3. 创建一个对应类型的class实例

类型的加载是怎么加载的？

[类加载器]()

### 1.2 连接
连接分为3个步骤
1. 验证
2. 准备
3. 解析

#### 1.2.1 验证

**连接时,正式验证做哪些东西？**
1. 类与类之间的兼容
    1. final不能拥有子类
    2. final方法不能被覆盖
    3. 检查类型和父类型之间方法声明没有不兼容的(两个方法拥有相同的名字，参数相同，返回值却不相同)
2. 类与超类之间的兼容
    1. 检查所有的常量池入口一致(**占时不懂**)
    2. 检查常量池中的特殊字符串(类名，方法名，字段名是否合法(以关键字命名))是否符合规范
    3. 检查字节码完整性(虚拟机没规定必须在连接阶段进行字节码验证，但是在连接的时候一次性验证，可以提高性能，在执行时验证还是比较低效)





### 1.3 初始化

**初始化**
1. 子类初始化，他的父类必须在子类之前初始化
2. 父接口的子类初始化，不需要初始化父接口
3. 父接口的子类装载时，父接口也必须被装载


延迟解析验证：
1. 符号引用的元素必须存在
2. 检查是否有访问该元素的权限













































