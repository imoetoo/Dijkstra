import java.util.ArrayList;

class PriorityQueue
{

    // Inner class to store an element and its priority
    private class Pair {
        String val;
        int priority;

        public Pair(String val, int priority) {
            this.val = val;
            this.priority = priority;
        }
    }
    // Declare an ArrayList to store our elements
    ArrayList<Pair> queue; //look above, for what the dataset pair stores

    // Initialize our priority queue
    public PriorityQueue() {
        this.queue = new ArrayList<Pair>();
    }
    public boolean isEmpty() {
        return this.queue.isEmpty();
    }
    // Insert an element into the queue with a given priority
    public void insert(String val, int priority)
    {

        // Create a new pair object to store our element and its priority
        Pair pair = new Pair(val, priority);

        // Add the pair to the queue
        this.queue.add(pair);
    }

    // Remove the element with the highest priority from the queue
    public String remove() {
        int minIdx = 0;
        // Iterate through the queue to find the element with the highest priority
        for (int i = 1; i < this.queue.size(); i++) {
            if (this.queue.get(i).priority < this.queue.get(minIdx).priority) {
                minIdx = i;
            }
        }
        String val = this.queue.get(minIdx).val;

        // Shift all elements after the removed element to the left
        while (minIdx < this.queue.size() - 1) {
            this.queue.set(minIdx, this.queue.get(minIdx + 1));
            minIdx++;
        }
        this.queue.remove(this.queue.size() - 1);
        return val;
    }


    public static void main(String[] args) {
        PriorityQueue pq = new PriorityQueue();
        pq.insert("Task 2", 2);
        pq.insert("Task 3", 3);
        pq.insert("Task 4", 4);
        pq.insert("Task 1", 1);
        System.out.println(pq.remove()); // Output: Task 4
        System.out.println(pq.remove()); // Output: Task 3
        System.out.println(pq.remove()); // Output: Task 2
        System.out.println(pq.remove()); // Output: Task 1
    }
}
 