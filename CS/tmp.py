import sympy as sp

denominator = 2047
# Use sympy symbols and functions for precise symbolic computation
series_sum = sum(sp.Rational(k * (2**(k-1)), denominator) for k in range(1, 12))

# Simplify the series sum
simplified_sum = sp.simplify(series_sum)

print(simplified_sum)
