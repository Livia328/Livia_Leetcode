class Q128:
    def longestConsecutive(self, nums: List[int]) -> int:
        st = set(nums)
        ans = 0
        for num in nums:
            if num - 1 not in st:
                curLen = 0
                while num in st:
                    curLen += 1
                    num += 1
                ans = max(ans, curLen)
        return ans