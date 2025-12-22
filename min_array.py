class Solution(object):
    def findMin(self, nums):
        """
        :type nums: List[int]
        :rtype: int

        min() does not work as it takes O(n) time

        We use something similar to binary search:
        - If L pointer's value < R pointer's value, meaning that nums[L] will be the smallest, return directly
        - If not:
        - We need to decide where exactly our middle pointer is: 
            - if its on the LHS: We only loop in the RHS, since it means all LHS values to that middle pointer will be smaller
                -> the mid value is greater than the L value, update the l pointer
            - if its on the RHS: We only loop in the LHS, since all RHS values will be greater than the middle_value
                -> the mid value is smaller than the L value, update the R pointer
        """
        # given that 1 <= n <= 5000, where n is len(nums)

        min_num = nums[0]
        left_pointer = 0
        right_pointer = len(nums) - 1

        while left_pointer <= right_pointer:

            if nums[left_pointer] < nums[right_pointer]:
                min_num = min(min_num, nums[left_pointer])
                break
            
            middle_pointer = int(floor((left_pointer + right_pointer) / 2))
            min_num = min(nums[middle_pointer], min_num)

            if nums[middle_pointer] >= nums[left_pointer]: #meaning that everything on the LHS of the middle pointer will be smaller than middle,
                #but, there might be something smaller on the RHS. therefore, only search RHS
                left_pointer = middle_pointer + 1
            else: #meaning that everything on the RHS will be bigger than the middle, therefore only seartch LHS
                right_pointer = middle_pointer - 1
        
        return min_num