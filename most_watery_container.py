class Solution(object):
    def maxArea(self, height):
        """
        :type height: List[int]
        :rtype: int

        use two pointers, each step we calculate the area and keep track of the max area, until two pointer meet
        """
        
        max_area = -1
        l = 0
        r = len(height) - 1

        while l != r:
            area = 0
            if height[l] < height[r]:
                area = (r - l) * height[l]
                l += 1
            elif height[l] > height[r]:
                area = (r - l) * height[r]
                r -= 1
            else: #tie breaker
                area = (r - l) * height[l]
                l += 1 
            max_area = max(max_area, area)
        
        return max_area