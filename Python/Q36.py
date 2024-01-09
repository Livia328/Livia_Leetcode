from typing import List

class Q36:
    def isValidSudoku(self, board: List[List[str]]) -> bool:
        res = set()
        for i in range(9):
            for j in range(9):
                item = board[i][j]
                if item != '.':
                    if (i, item) in res or (item, j) in res or (i // 3, j // 3, item) in res:
                        return False
                    res.add((i, item))
                    res.add((item, j))
                    res.add(((i // 3, j // 3, item)))
        return True