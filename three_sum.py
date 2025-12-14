class Solution(object):
    def threeSum(self, nums):
        res = []
        nums.sort()

        for i, n in enumerate(nums):
            if i > 0 and n == nums[i - 1]: continue

            l = i + 1
            r = len(nums) - 1

            while l < r:
                the_sum = n + nums[l] + nums[r]

                if the_sum > 0:
                    r -= 1
                elif the_sum < 0:
                    l += 1
                else:
                    res.append([n, nums[l], nums[r]])
                    l += 1
                    while nums[l] == nums[l - 1] and l < r:
                        l += 1

        return res


#Very brave and correct solution, but time limit exceeded
# class Solution(object):
#     def twoSum(self, nums, target, skip):
        
#         store_value = {}
#         all_pairs = []

#         for i, n in enumerate(nums):
#             if i == skip: continue
#             if target - n in store_value:
#                 x = n
#                 y = target - n
#                 all_pairs.append([x, y])
#             else:
#                 store_value[n] = i
#         return all_pairs

#     def threeSum(self, nums):
#         """
#         :type nums: List[int]
#         :rtype: List[List[int]]

#         Based on hint, we can use two-sum solution to solve it for all the elements
#         For two sum, the run time is O(n), which means that the runtime for this question will be O(n^2)
#         """

#         res = []
#         seen = []
#         nums.sort()

#         for i, n in enumerate(nums):
#             if i > 0 and nums[i - 1] == n: continue
#             sub_arrays = self.twoSum(nums, -n, i)
#             if len(sub_arrays) != 0:
#                 for array in sub_arrays:
#                     a = max(n, array[0], array[1])        
#                     c = min(n, array[0], array[1])
#                     b = n + array[0] + array[1] - a - c
#                     if [c, b, a] in res:
#                         continue
#                     res.append([c, b, a])

#         return res



        