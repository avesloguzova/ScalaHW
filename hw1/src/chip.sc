def solve(n: Int, k: Int): Int = {
  require(n >= 0)
  require(k >= 1)
  if (n <= 1 || k == 1) 1
  else
    1.to(Math.min(n, k)).map(j => solve(n - j, k)).sum
}

solve(2, 2)
solve(3, 2)
solve(3, 3)
solve(4, 2)
solve(4, 3)
solve(4, 4)

