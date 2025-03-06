"""
battle.py: Containing a Battle class that enables user to create an instance of Battle an allow two teams to battle with each other
"""
from poke_team import PokeTeam
from array_sorted_list import ArraySortedList
from stack_adt import ArrayStack
from queue_adt import CircularQueue
from pokemon_base import PokemonBase
from typing import Callable

__author__ = 'Ravindi Ratnayake, Ow Yong Chee Hao and Mah Ying Qi'

class Battle:

    def __init__(self, trainer_one_name: str, trainer_two_name: str)->None:
        """
        Initialising method to initialise a battle with two teams
        :param trainer_one_name: Name for first team
        :param trainer_two_name: Name for second team
        :post: two teams are created for the battle
        :complexity: Best == Worst O(1)
        """
        self.battle_mode = 0  # battle mode number of battle
        self.team1 = PokeTeam(trainer_one_name)  # variable to hold team1
        self.team2 = PokeTeam(trainer_two_name)  # variable to hold team2

    def set_mode_battle(self) -> str:
        """
        Battle mode where each Pokemon will keep fighting until it faints and return the winner.
        :post: both team1 and team2 are populated then fight until one or both of them are empty,
                if one of the team wins, the team will have remaining pokemons that can be viewed,
                winner's name is returned
        :complexity: Best == Worst O(n + m) teams battle for n rounds and user's input is incorrect for m times
        :return: Name of the trainer who won
        """
        self.battle_mode = 0  # Set battle mode to 0 for this battle
        self.team_choosing()  # Choose team for both trainer
        pop = ArrayStack.pop  # Get the first from team
        push = ArrayStack.push  # Put on top of team
        self.team_battling(pop, push)  # Two teams battling
        return self.winner()  # Return the name of the winner

    def rotating_mode_battle(self) -> str:
        """
        A battle mode that a pokemon battles, then go to the end of the queue, and return the name of winner
        :post: both team1 and team2 are populated then fight until one or both of them are empty,
                if one of the team wins, the team will have remaining pokemons that can be viewed,
                winner's name is returned
        :complexity: Best == Worst O(n + m) teams battle for n rounds and user's input is incorrect for m times
        :return: Name of the trainer who won
        """
        self.battle_mode = 1  # Set battle mode to 1 for this battle
        self.team_choosing()  # Choose team for both trainer
        serve = CircularQueue.serve  # Get the first from team
        append = CircularQueue.append  # Put at back of team
        self.team_battling(serve, append)  # Two teams battling
        return self.winner()  # Return the name of the winner

    def optimised_mode_battle(self, criterion_team1: str, criterion_team2: str) -> str:
        """
        Battle mode that arrange team according to the criterion given.
        Five criterion can be chosen, hp, lvl, attack, defence and speed
        :param criterion_team1: Criterion chosen for team 1
        :param criterion_team2: Criterion chosen for team 2
        :pre: criterion for both team should be 'hp','lvl','attack','speed'or'defence'
        :post: both team1 and team2 are populated then fight until one or both of them are empty,
                if one of the team wins, the team will have remaining pokemons that can be viewed,
                winner's name is returned
        :complexity: Best == Worst O(n*logk + m) teams battle for n rounds with k pokemons and user's input is incorrect for m times
        :raises: ValueError if user enter invalid criterion
        :return: Name of the trainer who won
        """

        if str.lower(criterion_team1) not in {'hp','lvl','attack','speed','defence'}:  # Testing if criterion is valid
            raise ValueError("Criterion for team 1 is invalid")
        if str.lower(criterion_team2) not in {'hp','lvl','attack','speed','defence'}:  # Testing if criterion is valid
            raise ValueError("Criterion for team 2 is invalid")
        self.battle_mode = 2  # Set battle mode to 2 for this battle
        self.team_choosing(criterion_team1,criterion_team2)  # Choose team for both trainer
        getting = ArraySortedList.delete_at_index  # Get from team
        adding = ArraySortedList.add  # Add into team
        self.team_battling(getting, adding)  # Two teams battling
        return self.winner()  # Return the name of the winner

    def winner(self)->str:
        """
        Method indicating which team has won
        :pre: on of the teams should be emptym team should be initialized already
        :post: team should not be modified
        :complexity: Best == Worst O(1)
        :raises: ValueError if none of the team is empty or team is not initialised
        :return: Which team from the battle won
        """
        if self.team1.team is None:  # Testing if team is populated
            raise ValueError("Team 1 is not chosen yet")
        if self.team2.team is None:  # Testing if team is populated
            raise ValueError("Team 2 is not chosen yet")
        if self.team1.team.is_empty() and self.team2.team.is_empty():  # If both team empty, it's a draw
            return "Draw"
        elif self.team1.team.is_empty():  # If one team is empt
            return self.team2.name
        elif self.team2.team.is_empty():
            return self.team1.name
        else:
            raise ValueError("Battle has not ended")

    def team_battling(self, take:Callable, put: Callable)->None:
        """
        Method for two teams to battle according to getting method and adding method given as parameter
        :param take: Method to get from team
        :param put: Method to add into team
        :post: both team1 and team2 fight until one or both of them are empty, the team and pokemons attribute are changed
        :complexity: Best == worst O(n) happens when they battle for n rounds
        """
        def update_key(pokemon, teamx: PokeTeam) -> None:  # Nested function to update pokemon's key according to criterion
            if str.lower(teamx.criterion) == 'lvl':
                pokemon.key = -1 * pokemon.value.get_level()
            elif str.lower(teamx.criterion) == 'hp':
                pokemon.key = -1 * pokemon.value.get_hp()
            elif str.lower(teamx.criterion) == 'attack':
                pokemon.key = -1 * pokemon.value.get_attack()
            elif str.lower(teamx.criterion) == 'defence':
                pokemon.key = -1 * pokemon.value.get_defence()
            elif str.lower(teamx.criterion) == 'speed':
                pokemon.key = -1 * pokemon.value.get_speed()
            teamx.team.rearrange()  # Rearrange the pokemons after updating the key

        while not (self.team1.team.is_empty() or self.team2.team.is_empty()):  # Ends when one or both of the teams are empty
            if self.battle_mode == 2:
                l1 = take(self.team1.team,0)  # Get the first pokemon in team1 as p1
                l2 = take(self.team2.team,0)  # Get the first pokemon in team1 as p2
                p1 = l1.value
                p2 = l2.value
            else:
                p1 = take(self.team1.team)  # Get the first pokemon in team1 as p1
                p2 = take(self.team2.team)  # Get the first pokemon in team1 as p2
                l1 = p1
                l2 = p2
            self.battle_instructions(p1, p2)  # p1 battle with p2
            if self.battle_mode == 2:
                update_key(l1, self.team1)  # update key for p1
                update_key(l2, self.team2)  # update ket for p2
            if not p1.has_fainted():  # Put back the pokemon if it is not fainted
                put(self.team1.team, l1)
            if not p2.has_fainted():
                put(self.team2.team, l2)

    def battle_instructions(self, p1:PokemonBase, p2:PokemonBase)->None:
        """
        Method for two pokemon to combat according to the instructions given in specification.
        :pre: Two pokemons from opposing teams
        :post: Two pokemons' attribute changed
        :complexity: Best == Worst Complexity O(1)
        Worst case happens when they do not have the same speed, then they are not fainted after they attack each other,
        then they both are not fainted after losing 1 hp
        """
        if not issubclass(type(p1),PokemonBase):  # Testing if input is Pokemon
            raise ValueError('Invalid Attacking Pokemon')
        if not issubclass(type(p2),PokemonBase):  # Testing if input is Pokemon
            raise ValueError('Invalid Defending Pokemon')
        p1.battled = True  # set them as they have battled
        p2.battled = True
        if p1.get_speed() == p2.get_speed():  # Both pokemon have same speed
            self.attack(p1, p2)  # p1 attacks p2
            self.attack(p2, p1)  # p2 attacks p1
            if p1.has_fainted() and p2.has_fainted():  # Both faint after first attack
                pass
            elif p1.get_hp() > 0 and p2.has_fainted():  # p2 fainted
                p1.set_level(p1.get_level()+1)                          # level up
            elif p1.has_fainted() and p2.get_hp() > 0:  # p1 fainted
                p2.set_level(p2.get_level()+1)                          # level up
            else:                                       # Both of them are not fainted
                p1.set_hp(p1.get_hp()-1)                            # Both lose 1 hp
                p2.set_hp(p2.get_hp()-1)
                if p1.has_fainted() and p2.has_fainted():  # Both faint after losing 1 hp
                    pass
                elif p1.get_hp() > 0 and p2.has_fainted():  # p2 faints after losing 1 hp
                    p1.set_level(p1.get_level()+1)                          # Level up
                elif p1.has_fainted() and p2.get_hp() > 0:  # p2 faints after losing 1 hp
                    p2.set_level(p2.get_level()+1)                          # Level up
                else:                                       # Both did not faint
                    pass
        else:  # They have different speed
            if p1.get_speed() > p2.get_speed():     # p1 is faster
                attacker = p1                       # p1 attacks
                defender = p2                       # p2 defends
            else:                                   # p2 is faster
                attacker = p2                       # p2 attacks
                defender = p1                       # p1 defends
            self.attack(attacker, defender)  # Attacker attacks defender
            if defender.get_hp() > 0:               # defender did not faint
                self.attack(defender, attacker)  # Defender attacks attacker
                if attacker.has_fainted():          # first attacker fainted
                    defender.set_level(defender.get_level()+1)            # second attacker level up
                else:                               # first attacker did not faint
                    attacker.set_hp(attacker.get_hp()-1)              # both lose 1 hp
                    defender.set_hp(defender.get_hp()-1)
                    if attacker.has_fainted() and defender.has_fainted():  # both fainted
                        pass
                    elif attacker.get_hp() > 0 and defender.has_fainted():  # second attacker fainted
                        attacker.set_level(attacker.get_level()+1)
                    elif attacker.has_fainted() and defender.get_hp() > 0:  # first attacker fainted
                        defender.set_level(defender.get_level()+1)
                    else:       # None fainted
                        pass
            else:                       # defender fainted
                attacker.set_level(attacker.get_level()+1)    # first attacker level up

    def team_choosing(self, c1: str = None, c2: str = None) -> None:
        """
        Method to choose team for team 1 and team 2
        :post: Two teams are initialised and populated according to user input
        :complexity: Best O(n) when user get the input right at the first time
         worst O(n + m) when user only get the input right at mth tries
         n = size of team
        """
        print(self.team1.name + ': ')
        self.team1.choose_team(self.battle_mode, c1)  # Choose team for team1
        print(self.team2.name + ': ')
        self.team2.choose_team(self.battle_mode, c2)  # Choose team for team2

    def attack(self, attacking:PokemonBase, defending:PokemonBase)->None:
        """
        Method to let attacker attack defender and change hp of defender and change its stats
        :pre: both attacking adn defending side shoud be pokemon
        :post: stats of defending side is changed but attacking side remains
        :complexity: Best == Worst O(1)
        :raises: ValueError if one or both attacking and defending side is not a Pokemon
        """
        if not issubclass(type(attacking),PokemonBase):  # Testing if input is Pokemon
            raise ValueError('Invalid Attacking Pokemon')
        if not issubclass(type(defending),PokemonBase):  # Testing if input is Pokemon
            raise ValueError('Invalid Defending Pokemon')
        damage_took = defending.calculate_damage_dealt(attacking)  # Calculate damage made by attacker
        try:
            defending.set_hp(defending.get_hp() - damage_took)  # Change HP
        except ValueError:
            defending.set_hp(0)     # If HP <0, Pokemon fainted, HP = 0
