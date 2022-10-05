/**
 * Created by Mihran Galstyan
 * All rights reserved
 */
public class Main {
    public static void main(final String[] args) {
        BlockingQueue blockingQueue = new BlockingQueue();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    Runnable task = blockingQueue.take();
                    if (task != null){
                        new Thread(task).start();
                    }
                }
            }
        }).start();

        for (int i = 0; i < 10; i++) {
            final int index =  i;
            blockingQueue.add(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(index);
                }
            });
        }

    }
}
