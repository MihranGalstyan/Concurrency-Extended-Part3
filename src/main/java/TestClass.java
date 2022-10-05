/**
 * Created by Mihran Galstyan
 * All rights reserved
 */
public class TestClass {
    private static final Object MONITOR = new Object();
    private static final String A = "A";
    private static final String B = "B";
    private static final String C = "C";
    private static String nextLetter = "A";

    public static void main(final String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (MONITOR) {
                    for (int i = 0; i < 5; i++) {
                        try {
                            while (!nextLetter.equals(A)) {
                                MONITOR.wait();
                            }
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.print(A);
                        nextLetter = "B";
                        MONITOR.notifyAll();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (MONITOR) {
                    for (int i = 0; i < 5; i++) {
                        try {
                            while (!nextLetter.equals(B)) {
                                MONITOR.wait();
                            }
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.print(B);
                        nextLetter = "C";
                        MONITOR.notifyAll();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (MONITOR) {
                    for (int i = 0; i < 5; i++) {
                        try {
                            while (!nextLetter.equals(C)) {
                                MONITOR.wait();
                            }
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.print(C);
                        nextLetter = "A";
                        MONITOR.notifyAll();
                    }
                }
            }
        }).start();
    }
}
