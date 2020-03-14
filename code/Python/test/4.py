def f():
    n, a, b = 0, 0, 1
    while n < 100:
        a, b = b, a+b
        print(b)
