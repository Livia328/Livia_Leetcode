def binary_search(arr, id):
    low = 0
    high = len(arr) - 1
    
    while low <= high:
        mid = (low + high) // 2  # Use integer division
        
        if arr[mid].id == id:
            return mid
        elif arr[mid].id < id:
            low = mid + 1
        else:
            high = mid - 1
    
    return -1
