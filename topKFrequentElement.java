/*
Priority queue time!

of course, we have to scan through all the element first
while we are scanning, we build a hashmap that record each
element's frequency
Then, for each entry in the hashmap, we add them to the pq!
Make sure the pq size never exceeds k,
finally just return the pq element!
*/

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        if (nums.length == 0) return new int[]{};

        Map<Integer, Integer> freqMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (freqMap.containsKey(nums[i])) {
                freqMap.put(nums[i], freqMap.get(nums[i]) + 1);
            } else {
                freqMap.put(nums[i], 1);
            }
        }

        //PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());
        //Use the following pq initialization to prevent comparisom overflow:
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.getValue(), b.getValue()));

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            pq.offer(entry);
            if (pq.size() > k) pq.poll();
        /* This works, but its better to offer everytime and 
        just poll the pq if its size is > k.
            if (pq.size() < k) {
                pq.offer(entry);
                continue;
            }

            if (entry.getValue() >= pq.peek().getValue()) {
                pq.poll();
                pq.offer(entry);
            }
        */
        }

        int[] res = new int[pq.size()];
        
        int i = 0;
        while (!pq.isEmpty()) {
            res[i] = pq.poll().getKey();
            i++;
        }

        return res;
    }
}