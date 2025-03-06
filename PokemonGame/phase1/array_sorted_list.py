"""
    Array-based implementation of SortedList ADT.
    Items to store should be of time ListItem.
"""

from referential_array import ArrayR
from sorted_list import *


__author__ = 'Maria Garcia de la Banda and Brendon Taylor. Modified by Alexey Ignatiev and Graeme Gange'
__docformat__ = 'reStructuredText'

class ArraySortedList(SortedList[T]):
    """ SortedList ADT implemented with arrays. """
    MIN_CAPACITY = 1

    def __init__(self, max_capacity: int) -> None:
        """ ArraySortedList object initialiser. """

        # first, calling the basic initialiser
        SortedList.__init__(self)

        # initialising the internal array
        size = max(self.MIN_CAPACITY, max_capacity)
        self.array = ArrayR(size)

    def reset(self):
        """ Reset the list. """
        SortedList.__init__(self)

    def __getitem__(self, index: int) -> T:
        """ Magic method. Return the element at a given position. """
        return self.array[index]

    def __setitem__(self, index: int, item: ListItem) -> None:
        """ Magic method. Insert the item at a given position,
            if possible (!). Shift the following elements to the right.
        """
        if self.is_empty() or \
                (index == 0 and item.key <= self[index].key) or \
                (index == len(self) and self[index - 1].key <= item.key) or \
                (index > 0 and self[index - 1].key <= item.key <= self[index].key):

            if self.is_full():
                self._resize()

            self._shuffle_right(index)
            self.array[index] = item
        else:
            # the list isn't empty and the item's position is wrong wrt. its neighbourghs
            raise IndexError('Element should be inserted in sorted order')

    def __contains__(self, item: ListItem):
        """ Checks if value is in the list. """
        for i in range(len(self)):
            if self.array[i] == item:
                return True
        return False

    def _shuffle_right(self, index: int) -> None:
        """ Shuffle items to the right up to a given position. """
        for i in range(len(self), index, -1):
            self.array[i] = self.array[i - 1]

    def _shuffle_left(self, index: int) -> None:
        """ Shuffle items starting at a given position to the left. """
        for i in range(index, len(self)):
            self.array[i] = self.array[i + 1]

    def _resize(self) -> None:
        """ Resize the list. """
        # doubling the size of our list
        new_array = ArrayR(2 * len(self.array))

        # copying the contents
        for i in range(self.length):
            new_array[i] = self.array[i]

        # referring to the new array
        self.array = new_array

    def delete_at_index(self, index: int) -> ListItem:
        """ Delete item at a given position. """
        if index >= len(self):
            raise IndexError('No such index in the list')
        item = self.array[index]
        self.length -= 1
        self._shuffle_left(index)
        return item

    def index(self, item: ListItem) -> int:
        """ Find the position of a given item in the list. """
        pos = self._index_to_add(item)
        if pos < len(self) and self[pos] == item:
            return pos
        raise ValueError('item not in list')

    def is_full(self):
        """ Check if the list is full. """
        return len(self) >= len(self.array)

    def add(self, item: ListItem) -> None:
        """ Add new element to the list. """
        if self.is_full():
            self._resize()

        # find where to place it
        position = self._index_to_add(item)

        self[position] = item
        self.length += 1

    def _index_to_add(self, item: ListItem) -> int:
        """ Find the position where the new item should be placed. """
        low = 0
        high = len(self) - 1

        while low <= high:
            mid = (low + high) // 2
            if self[mid].key < item.key:
                low = mid + 1
            elif self[mid].key > item.key:
                high = mid - 1
            else:
                return mid

        return low

    def __str__(self):
        """
        Magic method to show array sorted list as a string
        :return: Items in array sorted list as string
        :complexity: :complexity: Best == Worst O(n), n is size of array sorted list
        """
        result = ''
        for i in range(len(self)):
            result += str(self.array[i].value) +', '
        return result[0:-2]


    def rearrange(self) -> None:
        """
        Method to rearrange the array sorted list according to the sequence
        Charmander, Bulbasaur, Squirtle, MissingNo if their key are the same
        Always put MissingNo after the pokemons that are not battled
        :pre: ListItem in arraysortedlist should be either Charmander, Bulbasaur, Squirtle or MissingNo
        :pos: Array is sorted in this order Charmander > Bulbasaur > Squirtle > MissingNo if they have the same key  and
                Pokemon(not_yet_battled) > MissingNo (Even if they do not have the same key)
        :complexity: Best O(n) when array is already in this Charmander > Bulbasaur > Squirtle > MissingNo sequence for
                        every similar key
                        worst O(n^2)  when the array is in reveresed sequence which is
                        MissingNo > Squirtle > Bulbasaur > Charmander
                    n = size of array
        """
        msnoitem = None
        for i in range(len(self)):
            if self[i].value.get_name() == "MissingNo":  # Checking if there is MissingNo in ArraySortedList
                msnoitem = self.delete_at_index(i)  # Get MissingNo listitem

        if msnoitem is not None:  # If there is MissingNo in ArraySortedList
            x = self._index_to_add(msnoitem)
            not_yet_battled = None
            for i in range(len(self)):
                if self[i].value.get_name() != "MissingNo" and not self[i].value.battled:  # Find the index of the last pokemon that is not battled yet
                    not_yet_battled = i
            if not_yet_battled is None:
                pass
            elif x > not_yet_battled:
                self._shuffle_right(x)
                self.array[x] = msnoitem
                self.length += 1
            elif x <= not_yet_battled:
                self._shuffle_right(not_yet_battled+1)
                self.array[not_yet_battled+1] = msnoitem
                self.length += 1
        check = False
        while not check:  # Implementing the sequence Charmander > Bulbasaur > Squirtle > MissingNo if they have the same key
            check = True
            for j in range(len(self) - 1):
                if self[j].key == self[j + 1].key:
                    if (self[j].value.get_name() == "MissingNo" and (self[j+1].value.get_name() == "Squirtle" or self[j + 1].value.get_name() == "Bulbasaur" or self[j + 1].value.get_name() == "Charmander")) or (self[j].value.get_name() == "Squirtle" and (self[j + 1].value.get_name() == "Bulbasaur" or self[j + 1].value.get_name() == "Charmander")) or (self[j].value.get_name() == "Bulbasaur" and self[j + 1].value.get_name() == "Charmander"):
                        self[j].value, self[j + 1].value = self[j + 1].value, self[j].value
                        check = False

