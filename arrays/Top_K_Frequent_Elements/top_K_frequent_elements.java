class Solution {
    public int[] topKFrequent(int[] nums, int k) {
         //how many times a particular num is occuring
         //traverse array--> create a map, num and frequency
         //iterate over array and retrieve the max frequency
         //Bucket-Sort (optimised solution)

         //Map
         Map<Integer, Integer> freqMap = new HashMap<>(); 
         for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1); //Get the current count of num (or 0 if it doesn’t exist), add 1, and store it back in the map.
         }

         //Bucket Array
         List<Integer>[] buckets = new List[nums.length + 1]; //a list of numbers with the same frequency
         for (int key : freqMap.keySet()){
            int freq = freqMap.get(key);
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(key);
         }

         //Collect top k-frequent elements
         int[] result = new int[k];
         int index = 0;

         for (int i = buckets.length - 1; i >= 0 && index < k; i--){ //Start from the highest frequency bucket, go downwards, and keep picking numbers until we have k of them.
            if (buckets[i] != null) {
                for (int num: buckets[i]) {
                    result[index++] = num;
                    if (index == k) break;
                }
            }
         }
         return result;
    }
}

