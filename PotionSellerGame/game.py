"""
game.py:    Consists a Game class with 5 methods.
            __init__ method initialises the instance variables of the class
            set_total_potion_data creates all the empty potions and save them in a table (Hash Table)
            add_potions_to_inventory add quantity to the potions and put them in inventory (AVL Tree)
            choose_potions_for_vendors assign potions for vendors to sell using an algorithm
            solve_game finds and return the maximum profit for each attempt using an algorithm

"""
from __future__ import annotations
# ^ In case you aren't on Python 3.10
__author__ = 'Mah Ying Qi'

from random_gen import RandomGen
from avl import AVLTree
from potion import Potion
from hash_table import LinearProbePotionTable
from array_list import ArrayList


class Game:
    """
    A class that supports the game playing
    ADT used:
    A hash table is used here as there are different kind of potion name,
    so a hash table is a good way to hash the potion name to their object,
    then it is easy to get the object using their name

    An avl tree is used here to sort the potions according to their buy price,
    by doing so, we can get sort the potions according to their buy price,
    then we will be able to search for the kth most expensive potion from it.

    An array list is used in solve game to store a sequence of tuples containing (profit:cost ratio, potion name),
    the reason for using array list is so that we can sort it afterwards according to profit:cost ratio.
    We did not use array sorted list as we do not need it to be immediately sorted after inserting,
    and that costs a lot more time

    Another array list is used in solve game to store a sequence of increasing (buying price, profit),
    details will be discussed under solve_game. Reason we use array list is to have instant access for each element
    """
    def __init__(self, seed=0) -> None:
        """
        Initialise the instance variables of Game class
        Args:
            seed: A seed for random generator
        Precondition: seed should be a number

        Complexity: O(N)
        """
        self.rand = RandomGen(seed=seed)  # A random generator
        self.inventory = None  # An AVL tree storing available potions for vendors
        self.potion_table = None  # A hash table hashing potion name to their object

    def set_total_potion_data(self, potion_data: list[list[str, str, float]]) -> None:
        """
        Create an empty potion object for each potion, then save them in a hash table
        Args:
            potion_data:  A list of potions shown using a list in the format [type, name, data]
        Precondition: input list should contain list with the format [string, string, float],
                        should have at least 1 potion
        Complexity: O(N), N = len(potion_data), as the for-loop loops for N time
                    and every operation inside it is constant time

        Post condition: A hash table hashing potion name to potion object is created
        :invariant: potions is added into hash table

        """
        self.potion_table = LinearProbePotionTable(len(potion_data))  # Creating the hash table to record every potions
        for i in potion_data:  # O(N)
            temp_potion = Potion.create_empty(i[0], i[1], i[2])  # Creating empty potion object
            self.potion_table.insert(i[1], temp_potion)  # hashing name to object

    def add_potions_to_inventory(self, potion_name_amount_pairs: list[tuple[str, float]]) -> None:
        """
        Update the quantity of the potions according to input,
        Then insert non-empty potions into an AVL tree, use buy price as key.

        Args:
            potion_name_amount_pairs: list of potion with tuples containing name and quantity
        :Precondition: Potions given in input should have been given into the set_total_potion_data() method,
                        the potion potion_table should be created.
                        input should be in list[tuple[name, quantity]] format
        :Complexity:    O(C*log(C))
                        C = len(potion_name_amount_pair),
                        It contains only one loop that loops through the input,
                        then in each iteration it inserts one item in the AVL tree which take O(log(C)) time
        :post condition: Non-empty potions are added into AVL tree according to buy price
        :invariant: potions inserted into AVL tree are all non-empty

        """
        self.inventory = AVLTree()  # create an AVL tree for inventory
        for i, j in potion_name_amount_pairs:  # O(C)
            potion = self.potion_table[i]  # getting potion object
            potion.quantity = j  # Updating potion quantity
            self.inventory[potion.buy_price] = potion  # Adding potion available potion to AVL tree according to buy price O(C*log(C))

    def choose_potions_for_vendors(self, num_vendors: int) -> list:
        """
        Generate random number rand for each vendor, then search for randth expensive potion in AVL tree
        using the kth_largest function and assign it to the vendor. We are able to do this because the AVL tree
        is formed by using the buying price as the key.
        Temporarily delete the potion assigned to vendors from the tree, then add them back after all vendors are
        assigned with a potion.

        Args:
            num_vendors: total number of vendors
        Precondition: num_vendors should be less than the amount of potion provided in add_potions_to_potion_table()
                        both set_total_potion_data() and add_potions_to_potion_table() must be called before calling
                        this function
        Complexity: O(C * log(N))
                    N = len(inventory)
                    C = num_vendors

                    for the first for-loop, the time complexity is O(C * log(N))
                    It loops for num_vendors time,O(C)
                    inside the loop, we have the kth_largest function which is
                    O(log(N) time, the del function which is also O(log(N) time,

                    for the second for-loop, the complexity is O(C*log(N)), it loops through the potion removed O(C)
                    in first loop then add them back to the AVL tree O(log(N)) in each iteration

                    Hence, O(C*log(N) + C*log(N)) => O(C * log(N))

        :post condition: All vendors are assigned with a potion
        :invariant: total number of potions available to vendors decreases by 1

        Returns: A list showing each potion that each vendor will be selling

        """
        vendor_list = []  # A list of what potions each vendor took, could've used ArrayList, but type hint says list
        potion_list = []  # A list of potions removed temporarily from the inventory

        for i in range(num_vendors):  # Assigning potions to each vendor O(C)
            rand = self.rand.randint(len(self.inventory))
            # generating pseudo-random number that is less than number of potions
            potion = self.inventory.kth_largest(rand).item  # getting the randth expensive potion  O(C*log(N))
            vendor_list.append((potion.name, potion.quantity))  # adding the potion to the vendor list (name, quantity)
            potion_list.append(potion)  # append to a deleted list to record
            del self.inventory[potion.buy_price]
            # delete the potion from the list O(C*log(N)), so no vendors get the same potion

        for i in potion_list:  # loops through removed potion list, which will have the length of num_vendors so O(C)
            self.inventory[i.buy_price] = i  # Adding back removed potions to potion tree, O(C*log(N))

        return vendor_list

    def solve_game(self, potion_valuations: list[tuple[str, float]], starting_money: list[int]) -> list[float]:
        """
        Description of the method:
        take a sequence of potion valuations and a sequence of starting money,
        return the maximum profit we can get by buying and selling potions at the end of the day

        Description of our algorithm:
        First, go through the potion_valuations and calculate the profit for each potion,
        then put the potions in list, with  profit:cost ratio as key and the potion object as item.

        Then, sort the list according to the profit:cost ratio in decreasing order.
        It is sorted according to profit:cost so that we get the most profitable potion first,

        Then, construct a list with tuples containing (total buying price, total selling price),
        the first tuple contains the price to buy the whole bottle of the potion and the profit for selling it
        second tuple contains the price to buy whole bottle of the potion and the first bottle,
        and also the profit of selling both of them.
        nth tuple contains the price to buy the entire bottle of nth potion and all the bottles before,
        and also the profit of selling all of them.

        Then, for each attempt, we use the budget for each day to search for the position we can buy,
        in the list we created in the step before.

        If the total buying price at that position is equal to our budget, return the total profit at that position
        If the position we get is exceed our number of position, return the profit of the last profit as that is the
        maximum profit we can get, then add the money we have left after buying all potions
        If the position we get is 0, calculate the profit we can get using amount of money we have,
        Any other condition, calculate the profit by adding the profit of the potions before, deduct the cost
        from our budget and add the profit we can have with the current potion with our budget.

        Args:
            potion_valuations: A list showing selling price of each potion in the format (name, price)
            starting_money: A list of starting money for each attempt

        Precondition:   both set_total_potion_data() and add_potions_to_potion_table() must be called before calling
                        this function

        Complexity: O(N*log(N) + M*log(N))
                    N = len(potion_valuations)
                    M = len(starting_money)
                    First loop O(N), Loop through potion_valuations

                    Sorting the list with merge sortO(N*log(N))

                    Second loop O(N), loop for len(potion_valuations) times, operations inside have constant time complexity

                    Third loop O(M*log(N)), Loop through starting money and perform binary search in list with
                    length potion_valuations in each iteration

                    Hence, O(N + N*log(N) + M*log(N)) => O(N*log(N) + M*log(N))

        :post condition: Highest possible profit for each attempt is found
        :invariant: inventory is not modified
        Returns: A list of profit for each attempt

        """
        if len(potion_valuations) == 0:  # No vendor is selling potion
            return starting_money
        potions_available = ArrayList(len(potion_valuations))
        hash_profit = LinearProbePotionTable(len(potion_valuations))  # A hash table hashing potion name to profit
        profit_list = []  # Final list of profit for each attempt, same, could've used ArrayList but typehint says list
        profit_potion = ArrayList(len(potion_valuations))  # A sequence of increasing buying price and profit

        for i, j in potion_valuations:  # looping through potion_valuations, O(N)
            potion = self.potion_table[i]  # get the potion object
            hash_profit[i] = j  # Hashing Potion name to selling price
            profit = j - potion.buy_price  # Profit for this potion
            potions_available.append((profit / potion.buy_price, potion))
            # adding tuple containing (profit:cost ratio and potion object into the list)

        potions_available.merge_sort()  # O(N*log(N))

        total_buy_price = 0  # Variable to store total cost
        total_profit = 0  # Variable to store total profit

        for i in range(len(potion_valuations)):  # O(N)
            x = potions_available[i][1]  # Get the potion object
            total_profit += hash_profit[x.name] * x.quantity  # Calculate profit up to this potion
            total_buy_price += x.buy_price * x.quantity  # Calculate cost up to this potion
            profit_potion.append((total_buy_price, total_profit))  # Append the result into the list

        for i in starting_money:  # looping through starting_money, O(M)
            j = profit_potion.position(i)  # O(M*log(N)) Find the position of our budget

            # O(1) for operations below
            if j == 0:  # If budget cannot buy any whole bottle of potion
                profit = i/profit_potion[j][0] * profit_potion[j][1]
            elif j == len(profit_potion):  # If budget is enough to buy every potion
                profit = profit_potion[len(profit_potion)-1][1] + (i-profit_potion[len(profit_potion)-1][0])
            else:  # If budget is enough to buy more than one potion, but not all
                if i == profit_potion[j][0]:  # If budget can buy bottle/s of potion
                    profit = profit_potion[j][0]
                else:  # If budget can buy bottle + fraction of potion
                    i -= profit_potion[j-1][0]  # money left
                    this_potion_profit = profit_potion[j][1] - profit_potion[j-1][1]  # profit for next potion
                    this_potion_buy_price = profit_potion[j][0] - profit_potion[j-1][0]  # buying price for next potion
                    profit = profit_potion[j-1][1] + (i/this_potion_buy_price * this_potion_profit)
                    # profit from previous potions + profit from current potion

            profit_list.append(profit)  # Append profit of that day to result

        return profit_list
