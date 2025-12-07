class Solution(object):
    def maxProfit(self, prices):
        """
        :type prices: List[int]
        :rtype: int
        """


        """
        By tracking the current max profit and min price, we are able to get the max profit with one pass
        """
        max_profit = 0
        min_price = 10**4 + 1 #Constraints provided by the question

        for price in prices:
            if price < min_price:
                min_price = price

            max_profit = max(max_profit, price - min_price)

        return max_profit
    
# class Solution(object):
#     def maxProfit(self, prices):
#         """
#         :type prices: List[int]
#         :rtype: int

#         - Brute force can solve this problem
#         - By dynamically change the left and right index, we can achieve average O(n) time
#             but worst case is still O(n2) time
#         - We first start from the first element, then we check for each element after, with one condition:
#           If we found a elemnet that is smaller than the element we are currrently checking, we record
#           the current highest profit and immediately change our left pointer to that element.
#         """
#         left_pointer = 0
#         right_pointer = 0
#         profit = 0

#         if len(prices) == 1: return 0

#         while left_pointer < len(prices):
#             right_pointer = left_pointer + 1
#             while right_pointer < len(prices):
#                 if prices[right_pointer] > prices[left_pointer] and profit < prices[right_pointer] - prices[left_pointer]:
#                     profit = prices[right_pointer] - prices[left_pointer]
#                     right_pointer += 1
#                 elif prices[right_pointer] < prices[left_pointer]:
#                     left_pointer = right_pointer
#                     break
#                 else:
#                     right_pointer += 1

#             if left_pointer != right_pointer:
#                 left_pointer += 1

#         return profit