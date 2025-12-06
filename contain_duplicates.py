class Solution(object):
    def containsDuplicate(self, nums):
        """
        :type nums: List[int]
        :rtype: bool

        brute force can solve the issue, but in O(n**2) time
        - we can sort the array then find duplicate in O(nlongn) time

        however, we use hashmap to solve this issue
        """
        visited_nums = {}
        for num in nums:
            if num in visited_nums: return True
            visited_nums[num] = 1

        return False

