public class Solution {
    public boolean hasDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) { //This loop selects each element in the array one by one.
            for (int j = i + 1; j < nums.length; j++) { //compares the current element nums[i] with every element that comes after it.
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
