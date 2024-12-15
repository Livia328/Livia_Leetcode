import random
from collections import Counter

def simulate_draw(deck, num_draws):
    """从卡牌组中随机抽取指定数量的卡牌"""
    return random.choices(deck, k=num_draws)

def count_matches(draws):
    """统计抽取结果中的每种卡牌数量"""
    return Counter(draws)

def calculate_collisions(count):
    """根据卡牌的数量统计碰数"""
    collisions =0
    for quantity in count.values():
        if quantity >1:
            collisions += quantity // 2 # 每多一张与第-张重复都算一碰
    return collisions

def main():
    # 定义卡牌种类，每种 6 张，创建卡牌组
    num_types = 9
    num_per_type=7
    deck = [f"Card{i + 1}" for i in range(num_types)for _ in range(num_per_type)]
    #保存所有抽取的结果和碰数
    total_runs = 1000000 # 模拟次数
    collision_count_no_wish = Counter() # 统计无许愿情况下的碰数频率
    first = 1
    for _ in range(total_runs):
        print("次数:", first)
        #创建一个字典跟踪每种卡牌的剩余数量
        remaining_counts = {f"card{i + 1}": num_per_type for i in range(num_types)}
        draws = simulate_draw(deck,num_types) #抽取 9 张卡牌
        count = count_matches(draws) # 统计抽职的卡牌
        collisions = calculate_collisions(count) # 计算碰数
        # 处理许愿逻辑
        wish_for = random.choice(deck) # 随机选择一种卡牌进行许愿
        additional_draws = count.get(wish_for,0) # 获取希望的卡牌在抽取中的数里
        collisions_no_wish = collisions
        collisions_last = collisions
        print("draws:")
        print(draws)
        print("collisions:")
        print(collisions)
        print("wish_for:")
        print(wish_for)
        print("additional_draws:")
        print(additional_draws)
        #更新剩余卡牌的数里
        for card in draws:
            remaining_counts[card] -= 1
        
        # 许愿版
        while collisions_with_wish > 0:
            #计算剩余可抽卡牌
            remaining_deck_with_wish = [card for card, count in remaining_counts.items() for_ in range(count)]
            print("remaining_deck_with_wish:")
            print(remaining_deck_with_wish)
            draws_with_wish = simulate_draw(remaining_deck_with_wish,collisions_with_wish) # 抽职 碰数 张卡牌
            draws += draws_with_wish
            count = count_matches(draws) # 统计抽取的卡牌
            collisions_new = calculate_collisions(count)
            collisions_with_wish = collisions_new - collisions_last # 计算碰数
            collisions_last = collisions_new
            print("draws:")
            print(draws)
            print("count:")
            print(count)