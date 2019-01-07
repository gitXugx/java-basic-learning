# 基础知识点JVM实现
> 一些基础的知识点,jvm是怎么实现的

## this的实现
> this代表实例对象。只有实例方法才有

java编译器会在编译实例方法的时候会把this作为第一个参数传给方法，如果是类方法则没有this参数

## 父类构造函数
编译子类构造函数默认在第一行代码调用父类的构造函数，如果父类没有无参构造函数则需要手动去调用，并且在子类构造函数的第一行，确保子类初始化时，父类先初始化。


## 编译期不可知, 运行期可知 
> 当然对应的还有编译期可知, 运行期不可变。这两种都时对方法的编译时的描述, 对于静态方法和私有方法, 他们在编译的时候就已经确认调用时是哪个方法, 对于重载和重写的方法他们属于运行时确定。也就是拥有多态的会有运行时确定

**编译期不可知, 运行期可知** 


**编译期可知, 运行期不可变**
- 静态方法
- final方法
- 私有方法
- 构造方法


### 重载的实现
> 方法名相同, 参数不同(参数个数, 参数类型), 或者返回值不同

- 静态重载实现
在编译期间, 根据静态类型来决定而不是实际类型来确定调用的方法, 重载的静态方法也能在编译时就确定调用的方法(或者选择最佳的)。这个其实叫 `静态分派`   





### 重写的实现
> 子类覆盖父类的方法, 来实现多态

```java
public  class OverRewriteTest {
    static abstract class Person{
        abstract void say();
    }
    static class Man extends Person{
        void say() {
            System.out.println("say Man");
        }
    }

    static class Woman extends Person{
        void say() {
            System.out.println("say Woman");
        }
    }

    public  static void main(String[] args){
        Person woman = new Woman();
        Person man = new Man();
        woman.say();
        man.say();
    }
}
```
为什么要写成静态的内部类, 其实这个和静态方法一样, 静态方法是属于类, 而实例方法必须创建实例才能调用实例方法, 所以说为了不创建外部类实例而使用静态内部类。

执行结果:
```text
say Woman
say Man
``` 
下面是上面代码编译的字节码:
```text
public class basic.learning.jvm.basicimp.example1.OverRewriteTest {
  public static void main(java.lang.String[]);
    Code:
       0: new           #2                  // class basic/learning/jvm/basicimp/example1/OverRewriteTest$Woman
       3: dup
       4: invokespecial #3                  // Method basic/learning/jvm/basicimp/example1/OverRewriteTest$Woman."<init>":()V
       7: astore_1
       8: new           #4                  // class basic/learning/jvm/basicimp/example1/OverRewriteTest$Man
      11: dup
      12: invokespecial #5                  // Method basic/learning/jvm/basicimp/example1/OverRewriteTest$Man."<init>":()V
      15: astore_2
      16: aload_1
      17: invokevirtual #6                  // Method basic/learning/jvm/basicimp/example1/OverRewriteTest$Person.say:()V
      20: aload_2
      21: invokevirtual #6                  // Method basic/learning/jvm/basicimp/example1/OverRewriteTest$Person.say:()V
      24: return
}
```
1. 1 - 15是创建对象, 并把变量方法局部变量表的1, 2两个槽
2. 16 - 17是加载第一变量到操作数栈, 通过 `invokevirtual` 命令来调用方法
3. 20 - 21是加载第二变量到操作数栈, 通过 `invokevirtual` 命令来调用方法

现在看来最主要的就是 `invokevirtual` 命令了, 它主要做了什么:

































































