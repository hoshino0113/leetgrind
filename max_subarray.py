class Solution(object):
    def maxSubArray(self, nums):
        """
        :type nums: List[int]
        :rtype: int

        core idea:
        We can achieve it in O(n) time:
        - We start from index 0, and initialize current sum = max sum = 0
        - If we encounter an element that >= 0:
            - If current sum < 0, then current sum = element and max(max sum, current sum)
            - If current sum >= 0, then current sum += element, and max(max sum, current sum)

        - If we encounter an elemetn that < 0:
            - If current sum + element > 0, then update current sum
            - If current sum + element < 0, then we update the current sum to be 0, and find the max sum of the element or the max_sum

        - Repeat until we reach the end of array
        """
        current_sum = 0
        max_sum = nums[0]

        for num in nums:
            if num >= 0:
                if current_sum >= 0:
                    current_sum += num
                else:
                    current_sum = num

                max_sum = max(current_sum, max_sum)   

            else:
                if current_sum + num >= 0:
                    current_sum += num 
                else:
                    current_sum = 0
                    max_sum = max(num, max_sum)
                
        return max_sum
