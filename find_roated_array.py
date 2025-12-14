class Solution(object):
    def search(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: int

        very similar to find minimun element in the sorted and rotated array

        idea: We only search part of the array, not all.
        We need to iniitalize a middle pointer, so that each time we compare the target number with it, if:
        1. target number == nums[middle] then return
        2. Distinguish if LHS is smaller or bigger than the middle, then:
            - based on the target number, decide which way to go
        3. Repeating, until we either find the number or hit the l > r, which means no target number is found(return -1)
        """
        
        #initialize the l and r pointers
        l = 0
        r = len(nums) - 1

        while l <= r:
            middle = int(floor((l + r)/2))
            if nums[middle] == target:
                return middle
            
            if nums[middle] >= nums[l]: #meaning that everything on the LHS will be smaller, but we might have smaller number of RHS
                if target == nums[r]: return r
                if target == nums[l]: return l

                if target < nums[middle] and target > nums[l]:
                    r = middle - 1
                else:
                    l = middle + 1

            else: #meanign that everything on the RHS will be larger than the middle, but we also have more larger number on LHS
                if target == nums[r]: return r
                if target == nums[l]: return l

                if target > nums[middle] and target < nums[l]:
                    l = middle + 1
                else:
                    r = middle - 1

        return -1
        