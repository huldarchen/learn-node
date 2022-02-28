// /**
//  * @author huldarchen
//  * @version 1.0
//  * @date 2021/12/30 09:09
//  */
// public class ThreadDemo1 {
//   public static final InheritableThreadLocal inheritableThreadLocal =
//       new InheritableThreadLocal<>();
//
//   public static final ThreadLocal threadLocal = new ThreadLocal<>();
//
//   public static void main(String[] args) throws Exception {
//     inheritableThreadLocal.set("inheritableThreadLocal hello");
//
//     threadLocal.set("threadLocal world");
//
//     new Thread(
//             () -> {
//               System.out.println(String.format("子线程可继承值：%s", inheritableThreadLocal.get()));
//
//               System.out.println(String.format("子线程值：%s", threadLocal.get()));
//
//               new Thread(
//                       () -> {
//                         System.out.println(
//                             String.format("孙线程可继承值：%s", inheritableThreadLocal.get()));
//
//                         System.out.println(String.format("孙线程值：%s", threadLocal.get()));
//                       })
//                   .start();
//             })
//         .start();
//
//     Thread.sleep(3000);
//
//     System.out.println("--------------修改可继承值threadLocal和threadLocal的值--------------------");
//
//     inheritableThreadLocal.set("inheritableThreadLocal hello2");
//
//     threadLocal.set("threadLocal world2");
//
//     new Thread(
//             () -> {
//               System.out.println(String.format("子线程可继承值：%s", inheritableThreadLocal.get()));
//
//               System.out.println(String.format("子线程值：%s", threadLocal.get()));
//
//               new Thread(
//                       () -> {
//                         System.out.println(
//                             String.format("孙线程可继承值：%s", inheritableThreadLocal.get()));
//
//                         System.out.println(String.format("孙线程值：%s", threadLocal.get()));
//                       })
//                   .start();
//             })
//         .start();
//   }
// }
