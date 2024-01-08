def isAnagram(self, s: str, t: str) -> bool:
    if len(s) != len(t):
        return False
    seen = [0] * 26
    for item in s:
        seen[ord(item) - ord('a')] += 1
    for item in t:
        seen[ord(item) - ord('a')] -= 1
    for item in seen:
        if item != 0:
            return False
    return True