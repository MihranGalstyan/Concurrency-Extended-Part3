import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Mihran Galstyan
 * All rights reserved
 */
public class BlockingQueue {

    private Queue<Runnable> queue = new LinkedList<>();
     public void add(Runnable task){
        queue.add(task);
     }

     public Runnable take(){
         return queue.poll();
     }
}
