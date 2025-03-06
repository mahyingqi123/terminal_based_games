""" Hash Table ADT

Defines a Hash Table using Linear Probing for conflict resolution.
It currently rehashes the primary cluster to handle deletion.
"""
__author__ = 'Brendon Taylor, modified by Jackson Goerner'
__docformat__ = 'reStructuredText'
__modified__ = '21/05/2020'
__since__ = '14/05/2020'

from referential_array import ArrayR
from typing import TypeVar, Generic
from primes import largest_prime
from potion import Potion
T = TypeVar('T')


class LinearProbePotionTable(Generic[T]):
    """
    Linear Probe Potion Table

    This potion table does not support deletion.

    attributes:
        count: number of elements in the hash table
        table: used to represent our internal array
        table_size: current size of the hash table
    """

    def __init__(self, max_potions: int, good_hash: bool = True, tablesize_override: int = -1) -> None:
        """
        author: Mah Ying Qi
        To initialise a hash table
        :param max_potions: maximum number of potions that can be inputted
        :param good_hash: to use good_hash or not
        :param tablesize_override: to use given table size or compute a suitable one
        :precondition: tablesize_override should be either -1 or a positive number
        :post condition: table is created
        Complexity: O(1)
        """
        self.conflict_count = 0
        self.probe_max = 0
        self.probe_total = 0
        self.max_potions = max_potions
        self.good_hash = good_hash
        if tablesize_override != -1:  # Use given table size
            if tablesize_override < max_potions:  # if table size given is smaller than total number of potions
                self.table_size = max_potions
            else:
                self.table_size = tablesize_override
        else:
            self.table_size = largest_prime(int(max_potions*2.5))
            # Table size used is largest prime before 2.5*max_potions so that
            # Load will be less than 1/2
        self.initalise_with_tablesize(self.table_size)

    def hash(self, potion_name: str) -> int:
        """
        author: Mah Ying Qi
        To return the hashed valued based on the input
        :param potion_name: name to be hashed
        :return: hashed value
        :precondition: potion_name should be a string
        Complexity: O(len(potion_name)), time complexity to hash the potion's name is O(len(potion_name))
        :post condition: potion_name is hashed
        """
        if self.good_hash:
            return Potion.good_hash(potion_name, self.table_size)
        else:
            return Potion.bad_hash(potion_name, self.table_size)

    def statistics(self) -> tuple:
        """
        author: Mah Ying Qi
        To get the conflict count, probe total and probe max of hash table
        :post condition: hash table's statistics is returned
        Complexity: O(1)
        :return: tuple(conflict_count, probe_total, probe_max)
        """
        return self.conflict_count, self.probe_total, self.probe_max

    def __len__(self) -> int:
        """
        Returns number of elements in the hash table
        :complexity: O(1)
        """
        return self.count

    def __linear_probe(self, key: str, is_insert: bool) -> int:
        """
        Find the correct position for this key in the hash table using linear probing,
        and also update the statistics of hash table
        :complexity best: O(K) first position is empty
                          where K is the size of the key
        :complexity worst: O(K + N) when we've searched the entire table
                           where N is the table_size
        :raises KeyError: When a position can't be found
        """
        position = self.hash(key)  # get the position using hash
        first = True  # To mark that this is the first collision
        probe_length = 0  # Probe length is 0 at the beginning
        if is_insert and self.is_full():
            raise KeyError(key)

        for _ in range(len(self.table)):  # start traversing
            if self.table[position] is None:  # found empty slot
                if is_insert:
                    if probe_length > self.probe_max:
                        # If probe length of this linear probe is greater than maximum linear probe, replace it
                        self.probe_max = probe_length
                    return position
                else:
                    raise KeyError(key)  # so the key is not in
            elif self.table[position][0] == key:  # found key
                return position
            else:  # there is something but not the key, try next
                position = (position + 1) % len(self.table)
                if is_insert:  # If it is insert, we update the statistics
                    probe_length += 1  # Keep track of probe length for this linear probe
                    self.probe_total += 1  # Increase total probe length by 1
                    if first:  # If it is the first collision
                        self.conflict_count += 1  # Increase the conflict count by 1
                        first = False  # It is not the first anymore
        raise KeyError(key)

    def __contains__(self, key: str) -> bool:
        """
        Checks to see if the given key is in the Hash Table
        :see: #self.__getitem__(self, key: str)
        """
        try:
            _ = self[key]
        except KeyError:
            return False
        else:
            return True

    def __getitem__(self, key: str) -> T:
        """
        Get the item at a certain key
        :see: #self.__linear_probe(key: str, is_insert: bool)
        :raises KeyError: when the item doesn't exist
        """
        position = self.__linear_probe(key, False)
        return self.table[position][1]

    def __setitem__(self, key: str, data: T) -> None:
        """
        Set an (key, data) pair in our hash table
        :see: #self.__linear_probe(key: str, is_insert: bool)
        :see: #self.__contains__(key: str)
        """
        if len(self) == len(self.table) and key not in self:
            raise ValueError("Cannot insert into a full table.")
        position = self.__linear_probe(key, True)

        if self.table[position] is None:
            self.count += 1
        self.table[position] = (key, data)

    def initalise_with_tablesize(self, tablesize: int) -> None:
        """
        Initialise a new array, with table size given by tablesize.
        Complexity: O(n), where n is len(tablesize)
        """
        self.count = 0
        self.table = ArrayR(tablesize)

    def is_empty(self):
        """
        Returns whether the hash table is empty
        :complexity: O(1)
        """
        return self.count == 0

    def is_full(self):
        """
        Returns whether the hash table is full
        :complexity: O(1)
        """
        return self.count == len(self.table)

    def insert(self, key: str, data: T) -> None:
        """
        Utility method to call our setitem method
        :see: #__setitem__(self, key: str, data: T)
        """
        self[key] = data

    def __str__(self) -> str:
        """
        Returns all they key/value pairs in our hash table (no particular order)
        :complexity: O(N) where N is the table size
        """
        result = ""
        for item in self.table:
            if item is not None:
                (key, value) = item
                result += "(" + str(key) + "," + str(value) + ")\n"
        return result
