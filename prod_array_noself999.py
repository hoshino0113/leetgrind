class Solution(object):
    def productExceptSelf(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]

        Brute force can solve the issue
        division, as hinted in the problem, can shorten the runtime significantly (but why not allow -> divided by 0)

        For this question, we initialized two arrays, one for prefix and the other for suffix products
        we simplily multiply the prefix value and suffix product (i - 1 and i + 1)together to get the result:
        
        """
        n = len(nums)
        prefix_product = []
        suffix_product = [1] * n

        temp = 1
        for num in nums:
            temp = num * temp
            prefix_product.append(temp)
        
        temp = 1
        for i in range(n - 1, -1, -1):
            temp = nums[i] * temp
            suffix_product[i] = temp

        output = []

        #since len(nums) >= 2 given by the question
        for i, num in enumerate(nums):
            if i == 0:
                output.append(suffix_product[i + 1])
            elif i == len(nums) - 1:
                output.append(prefix_product[i - 1])
            else:
                output.append(prefix_product[i - 1] * suffix_product[i + 1])
        
        return output

        #The following are good version, but with the insert() operation which slows down:
        # prefix_product = []
        # suffix_product = []

        # temp = 1

        # for num in nums:
        #     temp = num * temp
        #     prefix_product.append(temp)
        
        # temp = 1
        # for num in reversed(nums):
        #     temp = num * temp
        #     suffix_product.insert(0, temp)

        # output = []

        # #since len(nums) >= 2 given by the question
        # for i, num in enumerate(nums):
        #     if i == 0:
        #         output.append(suffix_product[i + 1])
        #     elif i == len(nums) - 1:
        #         output.append(prefix_product[i - 1])
        #     else:
        #         output.append(prefix_product[i - 1] * suffix_product[i + 1])
        
        # return output