class Solution(object):
    def twoSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]


        What we have been thinking is that there are two ways to solve this issue:
        - Brute force, for each element, we search the solution
        - Sorted array: Use a sorted array, we may make the search function much faster
        - By hint, we can ultilize hash map (the space) to make it even faster
        """
        
        store_value = {}

        i = 0
        while i < len(nums):
            if target - nums[i] in store_value:
                x = store_value[target - nums[i]]
                y = i
                return [x, y]
            else:
                store_value[nums[i]] = i
                
            i += 1
        
        return "Not Found!"