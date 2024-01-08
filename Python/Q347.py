# Time Complexity : O(n * log(n))
# Space Complexity : O(n)

# sort
def topKFrequent(self, nums: List[int], k: int) -> List[int]:
    freq = {}
    for n in nums:
        if n not in freq:
            freq[n] = 1
        else:
            freq[n] += 1
    # freq.items() -> [(a, 2), (b, 3)]
    # lambda x : x[1], 根据tuple里的第2个排序
    # reverse = True 降序排序
    freq = dict(sorted(freq.items(), key = lambda x : x[1], reverse=True))
    ans = list(freq.keys())[:k]
    return ans



# by heap
def topKFrequent2(self, nums: List[int], k: int) -> List[int]:
    freq = {}
    for n in nums:
        if n not in freq:
            freq[n] = 1
        else:
            freq[n] += 1

    import heapq
    heap = []
    for num, count in freq.items():
        if len(heap) >= k:
            if count > heap[0][0]:
                heapq.heappop(heap)
                heapq.heappush(heap, (count, num))
        else:
            heapq.heappush(heap, (count, num))
    
    return [num for count, num in heap]