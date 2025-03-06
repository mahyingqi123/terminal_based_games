"""
poketeam.py: Containing a PokeTeam class that enables user to create an instance of PokeTeam
                with different combination of Pokemons and battle mode
"""
from pokemon import Charmander, Bulbasaur, Squirtle, MissingNo
from sorted_list import ListItem
from queue_adt import CircularQueue
from stack_adt import ArrayStack
from array_sorted_list import ArraySortedList

__author__ = 'Mah Ying Qi and Nazlina Nazarudin'


class PokeTeam:
    limit = 6
    def __init__(self, name: str) -> None:
        """
        :param name: Name for the team
        :post: a poke_team is created
        :complexity: Best == Worst O(1)
        """
        self.name = name  # team name
        self.team = None  # variable to hold ADT for team
        self.battle_mode = 0  # battle mode of team
        self.criterion = None  # criterion chose for battle mode 2
        self.missingNo = None  # variable to hold missingNo

    def choose_team(self, battle_mode: int, criterion: str = None) -> None:
        """
        Method for user to enter the sequence and number of pokemon they want and taking input to assign the team
        :param battle_mode: Battle mode chosen by user
        :param criterion: Criterion chosen by if battle mode is 2
        :pre1: The battle mode must be either 1, 2 or 0
        :pre2: The criterion must be either None,'hp','lvl','attack','speed'or'defence'
        :post: Team should be populated with pokemons based on user input
        :complexity: Best O(n) when user get the input right at the first time
         worst O(n + m) when user only get the input right at mth tries, and battle mode is 2 and contains MissingNo
         n = size of team
         :raises ValueError: if the battled mode is not 1, 2 or 0
                            or
                            if criterion is incorrect
        """
        if battle_mode not in {0,1,2}:
            raise ValueError("Battle mode should be either 0, 1 or 2")
        if criterion is not None and str.lower(criterion) not in {'hp','lvl','attack','speed','defence'}:
            raise ValueError("Criterion not available")
        print("Howdy Trainer! Choose your team as C B S\nwhere C is the number of Charmanders\n      B is the number of "
              "Bulbasaurs\n      S is the number of Squirtles")
        x = [i for i in input().strip().split(' ')]
        while True:
            try:
                if len(x) > 4:  # Testing if there are more than 4 different pokemons, raise ValueError if there are
                    print("Too many pokemons")
                    raise ValueError
                if len(x) < 3:  # Testing if there are less than 3 different pokemons, raise ValueError if there are
                    print("Too few pokemons")
                    raise ValueError
                for i in x:    # Testing if there is any non integer, raise ValueError if there is
                    if not i.isdigit():
                        print("Input should be integers")
                        raise ValueError
                for i in range(len(x)):
                    x[i] = int(x[i])
                if len(x) == 4:  # Testing if there are more than one MissingNo, raise ValueError if there are
                    if x[-1] >1:
                        print("Too many MissingNo")
                        raise ValueError
                if sum(x) >6:  # Testing if there are more than 6 pokemons, raise ValueError if there are
                    print("Too many pokemon count")
                    raise ValueError
                if sum(x)<0:  # Testing if there are negative number of pokemons, raise ValueError if there are
                    print("Too many pokemon count")
                    raise ValueError
            except ValueError:  # If ValueError is raised, ask user input again
                x = [i for i in input().strip().split(' ')]
            else:
                m = None
                if len(x) == 3:  # If there are 3 input integer, only three variables needed
                    c, b, s = x
                elif len(x) == 4:  # If there are 4 input integers, m is needed
                    c, b, s, m = x
                break
        if m == 1:  # If MissingNo exist, create a MissingNo instance
            self.missingNo = MissingNo()
        self.criterion = criterion  # Save criterion
        self.battle_mode = battle_mode  # Save battle mode
        self.assign_team(c, b, s)  # Call assign_team function

    def assign_team(self, charm: int, bulb: int, squir: int) -> None:
        """
        Method to assign/populate the team according to user input
        :param charm: Number of Charmander in team
        :param bulb: Number of Bulbasaur in team
        :param squir: Number of Squirtle in team
        :pre: charm, squir, bulb must be >=0
        :post: Team is populated according to charm, squir, and bulb and battle mode and missingNo
        :complexity: Best O(n) when battle mode is 1 or 0 Worst O(nlogn) when battle mode is 2, n = size of team )
        :raises: ValueError if charm, bulb or squir is negative
        """
        if charm < 0:
            raise ValueError("Number of Charmander should not be negative")
        if bulb < 0:
            raise ValueError("Number of Bulbasaur should not be negative")
        if squir < 0:
            raise ValueError("Number of Squirtle should not be negative")
        if self.battle_mode == 0:  # If battle mode is 0, use Stack ADT, because it follows LIFO
            self.team = ArrayStack(6)
            if self.missingNo is not None:
                self.team.push(self.missingNo)  # Push the last pokemon in first, then second last, etc
            for i in range(squir):
                self.team.push(Squirtle())
            for i in range(bulb):
                self.team.push(Bulbasaur())
            for i in range(charm):
                self.team.push(Charmander())
        elif self.battle_mode == 1:  # If battle mode is 1, use CicularQueue ADT, because the battled pokemon appends to the end
            self.team = CircularQueue(6)
            for i in range(charm):
                self.team.append(Charmander())  # Queue up the pokemon as normal sequence
            for i in range(bulb):
                self.team.append(Bulbasaur())
            for i in range(squir):
                self.team.append(Squirtle())
            if self.missingNo is not None:
                self.team.append(self.missingNo)
        elif self.battle_mode == 2:  # If battle mode is 2, use Array sorted list ADT, because Pokemons need to be sorted according to criterion
            def get_key(pokemon):  # Nested function to get key of pokemon according to the criterion
                if str.lower(self.criterion) == 'lvl':
                    return pokemon.get_level()
                elif str.lower(self.criterion) == 'hp':
                    return pokemon.get_hp()
                elif str.lower(self.criterion) == 'attack':
                    return pokemon.get_attack()
                elif str.lower(self.criterion) == 'defence':
                    return pokemon.get_defence()
                elif str.lower(self.criterion) == 'speed':
                    return pokemon.get_speed()
            self.team = ArraySortedList(6)
            for i in range(charm):
                x = Charmander()
                self.team.add(ListItem(x, -1*get_key(x)))  # Create ListItem for pokemon and add into ArraySortedList, key is multiplied by -1 to creating descending order
            for i in range(bulb):
                x = Bulbasaur()
                self.team.add(ListItem(x, -1*get_key(x)))
            for i in range(squir):
                x = Squirtle()
                self.team.add(ListItem(x, -1*get_key(x)))
            if self.missingNo is not None:
                self.team.add(ListItem(self.missingNo, -1*get_key(self.missingNo)))
            self.team.rearrange()  # Rearrange to let MissingNo to be at the end of the team because none of the pokemon is battled

    def __str__(self) -> str:
        """
        Method to return list of pokemons in team
        :complexity: Best == Worst O(n), n = number of pokemons in team
        :return: Pokemons in team as string
        """
        return str(self.team)
