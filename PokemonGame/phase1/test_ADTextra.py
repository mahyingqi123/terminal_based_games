"""
test_ADTextra.py: Testing extra methods added to stack_adt, queue_adt and array_sorted_list file
"""
import unittest
from poke_team import PokeTeam
from tester_base import TesterBase, captured_output

__author__ = "Mah Ying Qi"

class TestADTextra(TesterBase):

    def test_arraystack_str(self):
        from stack_adt import ArrayStack
        x = ArrayStack(5)  # Creating sample for testing
        x.push(5)
        x.push(4)
        x.push(3)
        x.push(2)
        x.push(1)
        result = None
        try:  # Try calling ArrayStack str function test if it will fail
            result = str(x)
        except Exception as e:
            self.verificationErrors.append(f"Array stack str method failed: {str(e)}")

        try:  # Check if string returned is correct
            assert result == "1, 2, 3, 4, 5"
        except AssertionError:
            self.verificationErrors.append(f"Array stack str method is incorrect: {result}")

    def test_circularqueue_str(self):
        from queue_adt import CircularQueue
        x = CircularQueue(5)  # Creating sample for testing
        x.append(1)
        x.append(1)
        x.append(2)
        x.serve()  # To make self.front != 0
        x.append(3)
        x.append(4)
        x.append(5)
        result = None
        try:  # Try calling CicularQueue str function test if it will fail
            result = str(x)
        except Exception as e:
            self.verificationErrors.append(f"CicularQueue str method failed: {str(e)}")

        try:  # Check if string returned is correct
            assert result == "1, 2, 3, 4, 5"
        except AssertionError:
            self.verificationErrors.append(f"CicularQueue str method is incorrect: {result}")

    def test_arraysortedlist_str(self):
        from array_sorted_list import ArraySortedList
        from sorted_list import ListItem
        x = ArraySortedList(4)  # Creating sample for testing
        x.add(ListItem("Alex",4))
        x.add(ListItem("Chad",2))
        x.add(ListItem("Ben",3))
        x.add(ListItem("Dex",1))
        result = None
        try:  # Try calling ArraySortedList str function test if it will fail
            result = str(x)
        except Exception as e:
            self.verificationErrors.append(f"ArraySortedList str method failed: {str(e)}")

        try:  # Check if string returned is correct
            assert result == "Dex, Chad, Ben, Alex"
        except AssertionError:
            self.verificationErrors.append(f"ArraySortedList str method is incorrect: {result}")

    def test_arraysortedlist_rearrange(self):
        from array_sorted_list import ArraySortedList
        from pokemon import Charmander, Bulbasaur, Squirtle, MissingNo
        from sorted_list import ListItem
        x = ArraySortedList(4)  # Creating sample for testing
        x.add(ListItem(Charmander(), 4))
        x.add(ListItem(Bulbasaur(), 4))
        x.add(ListItem(Squirtle(), 4))
        x.add(ListItem(MissingNo(), 4))
        try:  # Try calling ArraySortedList rearrange function, test if it will fail
            x.rearrange()
        except Exception as e:
            self.verificationErrors.append(f"ArraySortedList rearrange method failed: {str(e)}")

        try:  # Check if rearrange method is correct
            assert str(x) == "Charmander's HP = 7 and level = 1, Bulbasaur's HP = 9 and level = 1, Squirtle's HP = 8 and level = 1, MissingNo's HP = 8 and level = 1"
        except AssertionError:
            self.verificationErrors.append(f"ArraySortedList rearrange method is incorrect: {str(x)}")

if __name__ == '__main__':
    suite = unittest.TestLoader().loadTestsFromTestCase(TestADTextra)
    unittest.TextTestRunner(verbosity=0).run(suite)






