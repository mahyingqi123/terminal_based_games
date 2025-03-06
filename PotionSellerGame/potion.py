"""
potion.py: Contains a potion class that stores potion object's attribute, a function to create empty potion
            and two hash functions, a good one and a bad one.
"""
__author__ = 'Nazlina Nazarudin'


class Potion:
    """
    A potion class that contains all we need for a potion, including methods and attributes
    """
    def __init__(self, potion_type: str, name: str, buy_price: float, quantity: float) -> None:
        """
        To initialise the potion class with its instance variable
        Args:
            potion_type: type of potion
            name: name of potion
            buy_price: buying price of potion
            quantity: quantity of potion
        Complexity: O(1)
        """

        self.potion_type = potion_type  # store type of potion
        self.name = name  # store potion name
        self.buy_price = buy_price  # store potion buying price
        self.quantity = quantity  # store potion quantity

    @classmethod
    def create_empty(cls, potion_type: str, name: str, buy_price: float) -> 'Potion':
        """
        To create an empty potion with its instance variable, but quantity = 0
        Args:
            potion_type: type of potion
            name: name of potion
            buy_price: buying price of potion
        Complexity: O(1)
        :post condition: An empty potion object is created
        Returns: A newly created potion object

        """
        return cls(potion_type, name, buy_price, 0)

    @classmethod
    def good_hash(cls, potion_name: str, tablesize: int) -> int:
        """
        A hash function that hashes input based on their characters and the position of the characters
        Both of num1 and num2 are prime numbers so that it helps us reduce
        the chances of getting into the problem of zero divisors
        when applying the modulo operation a = a * b % (TABLESIZE - 1)

        Args:
            potion_name: the name of the potion
            tablesize: the size of the hash table
        Precondition: tablesize should be positive
        Complexity: O(len(potion_name))
        :post condition: potion name is hashed according to characters and position of the characters
        Returns: the hashed key derived from input name

        """
        value = 0
        num1 = 36523  # Two random prime numbers
        num2 = 14779
        for char in potion_name:  #
            value = (ord(char) + num1 * value) % tablesize  # hash potion name according to character and position
            num2 = num1 * num2 % (tablesize - 1)  # make a new random number
        return value


    @classmethod
    def bad_hash(cls, potion_name: str, tablesize: int) -> int:
        """
        A hash function that hashes input based on their characters without considering the positions.
        Args:
            potion_name: the name of the potion
            tablesize: the size of the hash table
        Precondition: tablesize should be positive
        Complexity: O(len(potion_name))
        :post condition: potion name is hashed according to characters
        Returns: the hashed key derived from input name

        """
        value = 0
        for char in potion_name:  # hash potion name according to character only
            value += ord(char)
        return value % tablesize
