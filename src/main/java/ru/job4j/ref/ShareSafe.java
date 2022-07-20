package ru.job4j.ref;

public class ShareSafe {
    public static void main(String[] args) throws InterruptedException {
        UserCache cache = new UserCache();
        User firstUser = User.of("First name");
        User secondUSer = User.of("Second Name");
        cache.add(firstUser);
        cache.add(secondUSer);
        Thread first = new Thread(
                () -> {
                    firstUser.setName("Changed First name");
                    secondUSer.setName("Changed Second name");
                }
        );
        first.start();
        first.join();
        System.out.println("find by ID: \n" + cache.findById(1).getName()
                + "\n" + cache.findById(2).getName());
        System.out.println("find ALL : " + cache.findAll());
    }
}
