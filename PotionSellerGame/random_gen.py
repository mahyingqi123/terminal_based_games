"""
random_gen.py:  lcg function, to return a random number generator base on seed given
                RandomGen class, a class that takes an input seed, create a random number generator,
                then has a function that returns a randomly generated number based on 5 numbers given
                by the generator and an algorithm.
"""
__author__ = 'Ravindi Ratnayake'

from typing import Generator


def lcg(modulus: int, a: int, c: int, seed: int) -> Generator[int, None, None]:
    """Linear congruential generator."""
    while True:
        seed = (a * seed + c) % modulus
        yield seed


class RandomGen:

    def __init__(self, seed: int = 0) -> None:
        """
        Initialise the instance variable randnum
        Args:
            seed: to randomized to generator
        :complexity O(1)
        :post condition: random number generator is created
        """
        self.randnum = lcg(pow(2, 32), 134775813, 1, seed)  # create a random generator

    def randint(self, k: int) -> int:
        """
        Generate a random number less than k
        Method: get 5 values from lcg generator
        Drop 16 least significant bits
        Generate a new number, which is 16 bits long and has a 1 in each bit if at least 3 of
        the 5 generated numbers have a 1 in this bit.
        Then modulo k, plus 1, return

        Args:
            k: to generate a random number less than this value
        Precondition: k should be positive
        :post condition: random number created is created using specific algorithm and can be recreated
        Returns: Random number less than k
        Complexity: O(1)
        """
        a = [next(self.randnum)//(2 ** 16) for _ in range(5)]
        # get 5 random number, and delete 16 least significant bits
        res = 0  # result we get
        for i in range(16):  # Checking every bit if they are 1
            val = 0  # value to store total number of 1
            for j in range(len(a)):  # for every number, check if they are 1
                val += a[j] % 2  # if current bit is 1, add 1 to value
                a[j] = a[j] // 2  # remove the leftmost bit
            if val > 2:  # if there are more than 3 1 in that bit, add a 1 in that bit for result
                res += 2 ** i
        return res % k + 1  # result modulo k, then plus 1


if __name__ == "__main__":
    Random_gen = lcg(pow(2, 32), 134775813, 1, 0)
