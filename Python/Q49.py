def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
    record = {}
    for s in strs:
        # python中，s = app, sorted是['a', 'p', 'p']
        # 再用'' join一下就是排序好的string了
        key = ''.join(sorted(s))
        if key not in record:
            record[key] = []
        record[key].append(s)
    return list(record.values())