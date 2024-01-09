from typing import List

class Q238:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        n = len(nums)
        prefix = [1] * n
        for i in range(1, n):
            prefix[i] = prefix[i - 1] * nums[i - 1]
        suffix = [1] * n
        for i in range(n - 2, -1, -1):
            suffix[i] = suffix[i + 1] * nums[i + 1]
        ans = [0] * n
        for i in range(n):
            ans[i] = prefix[i] * suffix[i]
        return ans

if __name__ == "__main__":
    q238 = Q238()
    test1 = [1,2,3,4]
    print(q238.productExceptSelf(test1))
