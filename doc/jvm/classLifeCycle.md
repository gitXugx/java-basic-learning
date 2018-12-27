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

## 1.类型的装载、连接和初始化
> java通过类型装载、连接和初始化一个java类型，使该类型可以被正在运行的程序所使用。

我们可以编译我们的代码会生成对应的class文件，这时我们删除
![][doge]



















