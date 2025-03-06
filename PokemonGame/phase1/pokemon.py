"""
pokemon.py: Containing four different type of Pokemon class that enables user
            to create an instance of Charmander, Bulbasaur, Squirtle or MissingNo
            and allow users to set or get attributes of them
"""

from pokemon_base import PokemonBase, GlitchMon
from typing import Type
import random

__author__ = 'Nazlina Nazarudin'

class Charmander(PokemonBase):
    name = 'Charmander'
    defence = 4
    def __init__(self)->None:
        PokemonBase.__init__(self, 7, 'Fire')
        """
        An initialising method used to initialise the class's variables according to the user input and
        pre-determined values
        :pos: Charmander object is created
        :complexity: Best == Worst O(1)
        """
        self.attack = 6 + self.get_level()
        self.speed = 7 + self.get_level()

    # GETTER METHOD
    def get_attack(self) -> int:
        """
        A method used to get the Charmander object's attack value
        :complexity: Best == Worst O(1)
        :return:  Attack of Charmander object
        """
        return self.attack

    def get_defence(self) -> int:
        """
        An abstract method used to get the Charmander object's defence value
        :complexity: Best == Worst O(1)
        :return:  Defence of Charmander object
        """
        return self.defence

    def get_speed(self) -> int:
        """
        A method used to get the Charmander object's speed value
        :complexity: Best == Worst O(1)
        :return:  Speed of Charmander object
        """
        return self.speed

    def get_name(self) -> str:
        """
        A method used to get the Charmander's name
        :complexity: Best == Worst O(1)
        :return:  Name of Charmander
        """
        return self.name

    def set_level(self, new_level:int)->None:
        """
        A method used to set the Charmander object's level
        :param new_level: new level for Charmander object
        :pre: The new_level must be >0
        :post: The level variable should be changed to new_level and stats should be changed
        :complexity: Best == Worst O(1)
        :raises ValueError: if the new_level is <=0
        """
        PokemonBase.set_level(self,new_level)
        self.attack = 6 + self.get_level()
        self.speed = 7 + self.get_level()

    # OTHER METHOD
    def calculate_damage_dealt(self, enemy:PokemonBase)->int:
        """
        An method used to calculate the damage dealt on the Charmander after being attacked by the opposing pokemon
        :param enemy: Opposing pokemon that is attacking Charmander object
        :pre: enemy should be created by subclass of PokemonBase
        :post: Charmander object's attribute should not be changed
        :complexity: Best == Worst O(1)
        :raises: ValueError if enemy is not a type of subclass of PokemonBase
        :return:  Damage dealt by enemy
        """
        PokemonBase.calculate_damage_dealt(self, enemy)
        if (enemy.get_poke_type()) == 'Fire':
            damage = enemy.get_attack()
        elif (enemy.get_poke_type()) == 'Water':
            damage = enemy.get_attack() * 2
        elif (enemy.get_poke_type()) == 'Grass':
            damage = enemy.get_attack() * 0.5
        else:
            damage = enemy.get_attack()

        if self.get_defence() < damage:
            return damage
        else:
            return damage // 2

    def __str__(self)->str:
        """
        Method showing Charmander object's name and stats
        :complexity: Best = Worst O(1)
        :return:  Pokemon's name, level and hp in str
        """
        return "{}'s {}".format(self.name, PokemonBase.__str__(self))

class Bulbasaur(PokemonBase):
    name = 'Bulbasaur'
    attack = 5
    defence = 5
    def __init__(self) -> None:
        PokemonBase.__init__(self, 9, 'Grass')
        """
        An initialising method used to initialise the class's variables according to the user input and
        pre-determined values
        :pos: Bulbasaur object is created
        :complexity: Best == Worst O(1)
        """
        self.speed = 7 + self.get_level() // 2


    # GETTER METHOD
    def get_attack(self) -> int:
        """
        A method used to get the Bulbasaur object's attack value
        :complexity: Best == Worst O(1)
        :return:  Attack of Bulbasaur object
        """
        return self.attack

    def get_defence(self) -> int:
        """
        An  method used to get the Bulbasaur object's defence value
        :complexity: Best == Worst O(1)
        :return:  Defence of Bulbasaur object
        """
        return self.defence

    def get_speed(self) -> int:
        """
        An method used to get the Bulbasaur's speed value
        :complexity: Best == Worst O(1)
        :return:  Speed of Bulbasaur object
        """
        return self.speed

    def get_name(self) -> str:
        """
        An method used to get the Bulbasaur object's name
        :complexity: Best == Worst O(1)
        :return:  Name of Bulbasaur
        """
        return self.name

    def set_level(self, new_level:int) -> None:
        """
        A method used to set the Bulbasaur object's level and stats
        :param new_level: new level for Bulbasaur object
        :pre: The new_level must be >0
        :post: The level variable should be changed to new_level and stats should be changed
        :complexity: Best == Worst O(1)
        :raises ValueError: if the new_level is <=0
        """
        PokemonBase.set_level(self, new_level)
        self.speed = 7 + self.get_level()//2

    # OTHER METHOD
    def calculate_damage_dealt(self, enemy:PokemonBase) -> int:
        """
        An method used to calculate the damage dealt on the Bulbasaur Object after being attacked by the opposing pokemon
        :param enemy: Opposing pokemon that is attacking Bulbasaur object
        :pre: enemy should be created by subclass of PokemonBase
        :post: Bulbasaur object's attribute should not be changed
        :complexity: Best == Worst O(1)
        :raises: ValueError if enemy is not a type of subclass of PokemonBase
        :return:  Damage dealt by enemy
        """
        PokemonBase.calculate_damage_dealt(self, enemy)
        if enemy.get_poke_type() == 'Grass':
            damage = enemy.get_attack()
        elif enemy.get_poke_type() == 'Fire':
            damage = enemy.get_attack() * 2
        elif enemy.get_poke_type() == 'Water':
            damage = enemy.get_attack() * 0.5
        else:
            damage = enemy.get_attack()

        if (self.get_defence() + 5) < damage:
            return damage
        else:
            return damage // 2

    def __str__(self) -> str:
        """
        Method showing Bulbasaur object's name and stats
        :complexity: Best = Worst O(1)
        :return:  Pokemon's name, level and hp in str
        """
        return "{}'s {}".format(self.name, PokemonBase.__str__(self))

class Squirtle(PokemonBase):
    name = 'Squirtle'
    def __init__(self) -> None:
        PokemonBase.__init__(self, 8, 'Water')
        """
        An initialising method used to initialise the class's variables according to the user input and
        pre-determined values
        :pos: Squirlte object is created
        :complexity: Best == Worst O(1)
        """
        self.attack = 4 + self.get_level() // 2
        self.defence = 6 + self.get_level()
        self.speed = 7

    # GETTER METHOD
    def get_attack(self) -> int:
        """
        A method used to get the Squirtle object's attack value
        :complexity: Best == Worst O(1)
        :return:  Attack of Squirtle Object
        """
        return self.attack

    def get_defence(self) -> int:
        """
        An method used to get the Squirtle Object's defence value
        :complexity: Best == Worst O(1)
        :return:  Defence of Squirtle Object's
        """
        return self.defence

    def get_speed(self) -> int:
        """
        An method used to get the Squirtle object's speed value
        :complexity: Best == Worst O(1)
        :return:  Speed of Squirtle Object
        """
        return self.speed

    def get_name(self) -> str:
        """
        An method used to get the Squirtle's name
        :complexity: Best == Worst O(1)
        :return:  Name of Squirtle
        """
        return self.name

    def set_level(self, new_level: int) -> None:
        """
        A method used to set the Squirtle object's level and stats
        :param new_level: new level for Squirtle object
        :pre: The new_level must be >0
        :post: The level variable should be changed to new_level and stats should be changed
        :complexity: Best == Worst O(1)
        :raises ValueError: if the new_level is <=0
        """
        PokemonBase.set_level(self, new_level)
        self.attack = 4 + self.get_level()//2
        self.defence = 6 + self.get_level()

    # OTHER METHOD
    def calculate_damage_dealt(self, enemy:PokemonBase) -> int:
        """
        An method used to calculate the damage dealt on the Squirtle Object after being attacked by the opposing pokemon
        :param enemy: Opposing pokemon that is attacking Squirtle object
        :pre: enemy should be created by subclass of PokemonBase
        :post: Squirtle object's attribute should not be changed
        :complexity: Best == Worst O(1)
        :raises: ValueError if enemy is not a type of subclass of PokemonBase
        :return:  Damage dealt by enemy
        """
        PokemonBase.calculate_damage_dealt(self,enemy)
        if (enemy.get_poke_type()) == 'Water':
            damage = enemy.get_attack()
        elif (enemy.get_poke_type()) == 'Grass':
            damage = enemy.get_attack() * 2
        elif (enemy.get_poke_type()) == 'Fire':
            damage = enemy.get_attack() * 0.5
        else:
            damage = enemy.get_attack()

        if (self.get_defence() * 2) < damage:
            return damage
        else:
            return damage // 2

    def __str__(self) -> str:
        """
        Method showing Squirtle object's name and stats
        :complexity: Best = Worst O(1)
        :return:  Pokemon's name, level and hp in str
        """
        return "{}'s {}".format(self.name, PokemonBase.__str__(self))

class MissingNo(GlitchMon):
    name = 'MissingNo'

    def __init__(self) -> None:
        """
        An initialising method used to initialise the class's variables according to the user input and
        pre-determined values
        :post: MissingNo object is created
        :complexity: Best == Worst O(1)
        """
        GlitchMon.__init__(self, 8, 'None')
        self.attack = int(16/3) + self.get_level()
        self.defence = int(16/3) + self.get_level()
        self.speed = 7 + self.get_level()//2

    def get_attack(self) -> int:
        """
        A method used to get the MissingNo's attack value
        :complexity: Best == Worst O(1)
        :return:  Attack of MissingNo
        """
        return self.attack

    def get_defence(self) -> int:
        """
        An method used to get the MissingNo Object's defence value
        :complexity: Best == Worst O(1)
        :return:  Defence of MissingNo Object's
        """
        return self.defence

    def get_speed(self) -> int:
        """
        An method used to get the MissingNo object's speed value
        :complexity: Best == Worst O(1)
        :return:  Speed of MissingNo Object
        """
        return self.speed

    def get_name(self) -> str:
        """
        An method used to get the MissingNo's name
        :complexity: Best == Worst O(1)
        :return:  Name of MissingNo
        """
        return self.name

    def calculate_damage_dealt(self, enemy:PokemonBase) -> int:
        """
        An method used to calculate the damage dealt on the MissingNo Object after being attacked by the opposing pokemon
        :param enemy: Opposing pokemon that is attacking MissingNo object
        :pre: enemy should be created by subclass of PokemonBase
        :post: MissingNo object's attribute should not be changed
        :complexity: Best == Worst O(1)
        :raises: ValueError if enemy is not a type of subclass of PokemonBase
        :return:  Damage dealt by enemy
        """
        PokemonBase.calculate_damage_dealt(self,enemy)
        random.seed()
        x = random.randint(1, 4)
        if x == 1:
            self.superpower()
            return 0
        else:
            damage = enemy.get_attack()
            random.seed(2)
            y = random.randint(1,3)
            if y == 1:
                if self.get_defence() < damage:
                    return damage
                else:
                    return damage // 2
            elif y == 2:
                if self.get_defence() + 5 < damage:
                    return damage
                else:
                    return damage // 2
            elif y == 3:
                if self.get_defence() * 2 < damage:
                    return damage
                else:
                    return damage // 2

    def set_level(self, new_level:int) -> None:
        """
        A method used to set the MissingNo object's level and stats
        :param new_level: new level for MissingNo object
        :pre: The new_level must be >0
        :post: The level variable should be changed to new_level and stats should be changed
        :complexity: Best == Worst O(1)
        :raises ValueError: if the new_level is <=0
        """
        PokemonBase.set_level(self, new_level)
        self.attack += 1
        self.defence += 1
        self.speed += 1
        self.hp += 1

    def __str__(self) -> str:
        """
        Method showing MissingNo object's name and stats
        :complexity: Best = Worst O(1)
        :return:  Pokemon's name, level and hp in str
        """
        return "{}'s {}".format(self.name, PokemonBase.__str__(self))