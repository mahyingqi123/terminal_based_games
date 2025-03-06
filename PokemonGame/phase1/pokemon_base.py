"""
pokemon_base.py:    Containing a one base class that enables other class to inherit them and
                    create a class for new Pokemon. Another base class that inherits the previous
                    base class but with additional features
"""

from __future__ import annotations
from abc import ABC, abstractmethod
import random

__author__ = 'Nazlina Nazarudin'


class PokemonBase(ABC):
    def __init__(self, hp:int, poke_type:str)->None:
        """
        An initialising method used to initialise the class's variables according to the user input and
        pre-determined values
        :pos: PokemonBase subclass object is created
        :complexity: Best == Worst O(1)
        """
        if hp < 0:
            raise ValueError("HP should not be negative")
        self.level = 1  # intial variable should always be 1
        self.hp = hp  # set hp according to input
        self.poke_type = poke_type  # set poke_type according to input
        self.battled = False  # all pokemons must not be battled at first
        self.fainted = (self.hp == 0)  # if hp is 0, pokemon has fainted

    # SETTER METHOD
    def set_hp(self, new_hp:int) -> None:
        """
        A setter method used to set the pokemon's hp according to user input
        :param new_hp: new hp for pokemon
        :pre: The new_hp must be >=0
        :post: The hp variable should be changed to new_hp or 0
        :complexity: Best == Worst O(1)
        :raises ValueError: if the new_hp is negative
        """
        if new_hp < 0:
            raise ValueError("HP should be more than or equal to 0")
        else:
            if new_hp == 0:  # If pokemon has 0 HP, then it has fainted
                self.fainted = True
            self.hp = new_hp

    def has_fainted(self) -> bool:
        """
        A method indicating if a pokemon has fainted or not
        :complexity: Best == Worst O(1)
        :return:  True or False the pokemon has fainted
        """
        return self.fainted

    def set_level(self, new_level:int)->None:
        """
        A setter method used to set the pokemon's level
        :param new_level: new level for pokemon
        :pre: The new_level must be >0
        :post: The level variable should be changed to new_level
        :complexity: Best == Worst O(1)
        :raises ValueError: if the new_level is <=0
        """
        if new_level < 1:
            raise ValueError("Invalid input! Level must be at least 1")
        self.level = new_level

    # GETTER METHOD
    def get_hp(self) -> int:
        """
        A getter method used to get the pokemon's hp
        :complexity: Best == Worst O(1)
        :return:  Hp of pokemon
        """
        return self.hp

    def get_level(self) -> int:
        """
        A getter method used to get the pokemon's level
        :complexity: Best == Worst O(1)
        :return:  Level of pokemon
        """
        return self.level

    def get_poke_type(self) -> str:
        """
        A getter method used to get the pokemon's type according to the value initialised by the user
        :complexity: Best == Worst O(1)
        :return:  Type of pokemon
        """
        return self.poke_type

    # OTHER METHODS
    @abstractmethod
    def get_name(self) -> str:
        """
        An abstract method used to get the pokemon's name
        :complexity: Best == Worst O(1)
        :return:  Name of pokemon
        """
        pass

    @abstractmethod
    def get_speed(self) -> int:
        """
        An abstract method used to get the pokemon's speed value
        :complexity: Best == Worst O(1)
        :return:  Speed of pokemon
        """
        pass

    @abstractmethod
    def get_defence(self) -> int:
        """
        An abstract method used to get the pokemon's defence value
        :complexity: Best == Worst O(1)
        :return:  Defence of pokemon
        """
        pass

    @abstractmethod
    def get_attack(self) -> int:
        """
        An abstract method used to getting the pokemon's attack value
        :complexity: Best == Worst O(1)
        :return:  Attack of pokemon
        """
        pass

    @abstractmethod
    def calculate_damage_dealt(self, enemy:PokemonBase):
        """
        An abstract method used to calculate the damage dealt on the selected pokemon after being attacked by the opposing pokemon
        :param enemy: Opposing pokemon that is attacking self pokemon
        :pre: enemy should be created by subclass of PokemonBase
        :post: Pokemon's attribute should not be changed
        :complexity: Best == Worst O(1)
        :raises: ValueError if enemy is not a type of subclass of PokemonBase
        :return:  Damage dealt by selected pokemon
        """
        if not issubclass(type(enemy),PokemonBase):
            raise ValueError('Invalid Pokemon')

    def __str__(self) -> str:
        """
        Method showing pokemon's stats
        :return: HP and Level of pokemon as string
        """
        return "HP = {} and level = {}".format(self.get_hp(), self.get_level())

class GlitchMon(PokemonBase, ABC):
    def __init__(self, hp: int, poke_type: str) -> None:
        """
        Initialising GlitchMon stats
        :pos: GlitchMon subclass object is created
        :complexity: Best == Worst O(1)
        """
        PokemonBase.__init__(self, hp, poke_type)

    def up_hp(self, up:int) -> None:
        """
        Increase GlitchMon HP by 1
        :param up: Value of increment for GlitchMon HP
        :pre: Value of up should be > 0
        :post: GlitchMon's HP should be increased by up
        :complexity: Best == Worst O(1)
        :raises: ValueError if up is <= 0
        """
        if up <= 0:
            raise ValueError("HP cannot be increased by negative of zero number")
        self.hp += up

    def superpower(self) -> None:
        """
        GlitchMon's superpower, has equal chance on executing either one:
        increase level by 1
        increase hp by 1
        increase level and hp by 1
        :post: GlitchMon's either increase level by 1 or
        increase hp by 1 or
        increase level and hp by 1
        :complexity: Best == Worst O(1)
        """
        random.seed()
        x = random.randint(1, 3)  # Randomised between 1, 2 and 3 to create a 1/3 chance for each effect
        if x == 1:  # Gain 1 level
            self.set_level(self.get_level()+1)
        elif x == 2:  # Gain 1 HP
            self.up_hp(1)
        elif x == 3:  # Gain 1 level and 1 HP
            self.set_level(self.get_level()+1)
            self.up_hp(1)
