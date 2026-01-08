/*
Maintain two PQ, one is max heap the other one is the min heap
Every element in the max heap is smaller than every element in the min heap, except:
- maxHeap.peek == minHeap.peek. This is allowed
While we are performing the addNum, we need to balance the numbers in both heap, to make sure their
sizes differ by at most 1;
Please take a look at how we performed the balancing
*/
class MedianFinder {
    PriorityQueue<Integer> dataSmall;
    PriorityQueue<Integer> dataLarge;

    public MedianFinder() {
        this.dataSmall = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        this.dataLarge = new PriorityQueue<>((a, b) -> Integer.compare(a, b));
    }
    
    public void addNum(int num) {
        if (this.dataSmall.isEmpty()) {
            dataSmall.offer(num);
            return;
        }
        //Careful! We should make sure we insert the right number to the right heap!!
        if (this.dataLarge.isEmpty()) {
            if (num >= dataSmall.peek()) {
                dataLarge.offer(num);
            } else {
                dataLarge.offer(dataSmall.poll());
                dataSmall.offer(num);
            }
            return;
        }

        if (this.dataSmall.peek() >= num) {
            dataSmall.offer(num);
            //perform balancing
            if (dataSmall.size() > dataLarge.size() + 1) {
                dataLarge.offer(dataSmall.poll());
            }
            return;
        } else {
            dataLarge.offer(num);
            //perform balancing
            if (dataLarge.size() > dataSmall.size() + 1) {
                dataSmall.offer(dataLarge.poll());
            }
        }
    }
    
    public double findMedian() {
        if (dataSmall.size() > dataLarge.size()) return ((double) dataSmall.peek());
        if (dataLarge.size() > dataSmall.size()) return ((double) dataLarge.peek()); 

        int small = dataSmall.peek();
        int large = dataLarge.peek();
        //do not forget to return a double instead of an int
        return (((double) small + large) / 2);
    }
}

/* Works but time limit exceeded, nice try
class MedianFinder {
    PriorityQueue<Integer> data;

    public MedianFinder() {
        this.data = new PriorityQueue<>((a, b) -> a - b);
    }
    
    public void addNum(int num) {
        this.data.offer(num);
    }
    
    public double findMedian() {
        int dataSize = data.size();
        if (dataSize == 1) return data.peek();
        int end = dataSize / 2 + 1;
        int[] temp = new int[dataSize + 1];

        if (dataSize % 2 == 0) {
            int first = 0;
            int second = 0;
            for (int i = 0; i < end; i++) {
                temp[i] = data.poll();
                if (i == end - 2) first = temp[i];
                if (i == end - 1) second = temp[i];
            }
            for (int i = 0; i < end; i++) {
                data.offer(temp[i]);
            }
            return ((double)first + second) / 2;
            
        } else {
            int median = 0;
            for (int i = 0; i < end; i++) {
                temp[i] = data.poll();
                if (i == end - 1) {
                    median = temp[i];
                }
            }
            for (int i = 0; i < end; i++) {
                data.offer(temp[i]);
            }
            return median;
        }
    }
}
*/

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */