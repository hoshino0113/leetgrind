class Solution(object):
    def maxProduct(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        
        We keep the current max product, if the new element:
        - > 0, we multiply it with the max product, update the max product
        - <= 0, we keep going
        - Repeat until we reach the end of the array
        """
        
        current_max = 1
        current_min = 1
        max_product = max(nums)

        for num in nums:
            tmp_max = current_max * num
            tmp_min = current_min * num
            if num != 0:
                current_max = max(num, tmp_max, tmp_min)
                current_min = min(num, tmp_max, tmp_min)
                max_product = max(current_max, max_product)
            else:
                current_max = 1
                current_min = 1
        
        return max_product
