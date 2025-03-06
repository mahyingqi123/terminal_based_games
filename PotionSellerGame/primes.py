"""
primes.py: Containing a largest_prime that takes one input k and find the largest prime number before it
"""
__author__ = 'Ow Yong Chee Hao'


def largest_prime(k: int) -> int:
    """
    Given an integer k it returns the largest prime number that is smaller than K
    Algorithm works by generating a list of True elements, then the first 2 element is change to False.
    As they are 0 and 1 which are not prime.
    Following that it iterates all prime elements,p and change all multiples of p to False.
    Last loops through the list from last element with number(k-1) and returns the number which is the largest prime<k
    when encounter a True.
    Args:
        k: As a parameter to find the largest prime before it, always greater than 2
    :Precondition: k must be greater than 2 and less than 100000
    Complexity: Best = O(k), when k = 3, it does not go into the inner loop
                Worst = O(k**2), when k != 3, it goes int the inner loop
    :post condition: largest prime number before k is found
    :invariant: non prime-number scanned is always marked as false
    Returns: The largest prime number before k
    """
    assert 2 < k <= 100000, "k must be between 1 to 100000"

    prime_list = [True] * k  # k number of Trues
    prime_list[0], prime_list[1] = False, False  # The first(0) and second(1) are not prime number

    for i in range(len(prime_list)):  # loop through the prime_list, # O(k)
        if prime_list[i]:  # If it is a prime, find all the numbers that has it as a factor and mark it as false
            for n in range(i * i, k, i):  # O(k**2)
                prime_list[n] = False

    for i in range(k-1, -1, -1):  # Find the last prime and return it # O(k)
        if prime_list[i]:  # If it is a prime, return
            return i

